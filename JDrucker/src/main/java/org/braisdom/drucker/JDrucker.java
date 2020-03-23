package org.braisdom.drucker;

import net.sf.cglib.proxy.Enhancer;
import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.database.TableBehavior;
import org.braisdom.drucker.database.TableBehaviorProxy;
import org.braisdom.drucker.database.TableRow;
import org.braisdom.drucker.xsql.XSQLDefinition;
import org.braisdom.drucker.xsql.XSQLDefinition.XSQLDeclaration;
import org.braisdom.drucker.xsql.XSQLException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JDrucker {

    public static final String DEFAULT_XSQL_PATH = "xsql";

    private static final String SCHEMA_MIGRATIONS_SQL = "xsql/schema_migrations.xsql";
    private static final Map<String, XSQLDeclaration> xsqlDeclarationCache = new HashMap<>();
    private static final int DEFAULT_THREAD_COUNT = 50;

    public static <T extends TableBehavior> T getProxy(DatabaseSession databaseSession,
                                                       Class<T> tableClass,
                                                       Class<? extends TableRow> tableRowClass) {
        Objects.requireNonNull(databaseSession, "databaseSession cannot be null");
        Objects.requireNonNull(tableClass, "tableClass cannot be null");
        Objects.requireNonNull(tableRowClass, "tableRowAccessorClass cannot be null");

        TableBehaviorProxy tableBehaviorProxy = new TableBehaviorProxy(databaseSession, tableClass, tableRowClass);

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(tableClass);
        enhancer.setCallback(tableBehaviorProxy);

        return tableClass.cast(enhancer.create());
    }

    public static void initializeTable(Collection<XSQLDeclaration> xsqlDeclarations,
                                       DatabaseSession databaseSession) {
        initializeTable(xsqlDeclarations, databaseSession, DEFAULT_THREAD_COUNT);
    }

    public static void initializeTable(Collection<XSQLDeclaration> xsqlDeclarations,
                                       DatabaseSession databaseSession,
                                       int threadCount) {
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        Connection connection = null;
        try {
            connection = databaseSession.getRawConnection();
            connection.setAutoCommit(false);
            List<Callable<Object>> callables = new ArrayList<>();
            for (XSQLDeclaration xsqlDeclaration : xsqlDeclarations)
                callables.add(new TableInitializerWorker(xsqlDeclaration, connection));
            executorService.invokeAll(callables);
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            throw new MigrationException(e.getMessage(), e);
        } catch (InterruptedException e) {
            throw new MigrationException(e.getMessage(), e);
        } finally {
            executorService.shutdown();
            close(null, connection);
        }
    }

    public static void migrate(String xsqlFilePath, int threadCount) throws IOException {

    }

    public static void loadXsqlFile(String xsqlFilePath) throws IOException {
        List<String> cacheNameSegments = new ArrayList<>();
        cacheNameSegments.add(xsqlFilePath);

        if(xsqlFilePath.startsWith("classpath:")) {
            xsqlFilePath = xsqlFilePath.split(":")[1];
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            if (classLoader == null)
                classLoader = JDrucker.class.getClassLoader();
            URL url = classLoader.getResource(xsqlFilePath);

            traverseXsqlFiles(cacheNameSegments, new File(url.getPath()).listFiles());
        }else {
            traverseXsqlFiles(cacheNameSegments, new File(xsqlFilePath).listFiles());
        }
    }

    public static Collection<XSQLDeclaration> getXsqlDeclaration() {
        return xsqlDeclarationCache.values();
    }

    public static XSQLDeclaration getXSQLDeclaration(String xsqlFileName) {
        return xsqlDeclarationCache.get(xsqlFileName);
    }

    private static void traverseXsqlFiles(List<String> cacheNameSegments, File[] xsqlFiles) throws IOException {
        for (File xsqlFile : xsqlFiles) {
            if (xsqlFile.isDirectory()) {
                List<String> tempCacheNameSegments = new ArrayList<>();
                tempCacheNameSegments.addAll(cacheNameSegments);
                tempCacheNameSegments.add(xsqlFile.getName());
                traverseXsqlFiles(tempCacheNameSegments, xsqlFile.listFiles());
            } else {
                String path = String.join(File.separator, cacheNameSegments.toArray(new String[]{}));
                cacheFile(String.format("%s/%s", path, xsqlFile.getName()), xsqlFile);
            }
        }
    }

    private static void cacheFile(String cacheName, File xsqlFile) throws IOException {
        if (xsqlDeclarationCache.get(cacheName) != null)
            throw new XSQLException("Duplicated xslq file: " + cacheName);
        xsqlDeclarationCache.put(cacheName, XSQLDefinition.parse(xsqlFile.getName(), new FileInputStream(xsqlFile)));
    }

    private static void close(Statement statement, Connection connection) {
        try {
            if (statement != null)
                statement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException ex) {
            throw new MigrationException(ex.getMessage(), ex);
        }
    }

    private static class TableInitializerWorker implements Callable {

        private final XSQLDeclaration xsqlDeclaration;
        private final Connection connection;

        public TableInitializerWorker(XSQLDeclaration xsqlDeclaration, Connection connection) {
            this.xsqlDeclaration = xsqlDeclaration;
            this.connection = connection;
        }

        @Override
        public Object call() throws Exception {
            Statement statement = null;
            try {
                XSQLDefinition.Initialize initialize = xsqlDeclaration.getInitialize();
                if (initialize != null) {
                    List<String> sqls = xsqlDeclaration.getInitialize().getSqlStatements();
                    statement = connection.createStatement();
                    for (String sql : sqls) {
                        statement.execute(sql);
                    }
                }
                return null;
            } catch (SQLException ex) {
                throw new MigrationException(ex.getMessage(), ex);
            } finally {
                close(statement, null);
            }
        }
    }

}
