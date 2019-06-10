package org.braisdom.drucker;

import net.sf.cglib.proxy.Enhancer;
import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.database.TableBehavior;
import org.braisdom.drucker.database.TableBehaviorProxy;
import org.braisdom.drucker.database.TableRow;
import org.braisdom.drucker.xsql.XSQLDefinition;
import org.braisdom.drucker.xsql.XSQLException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class JDrucker {

    public static final String DEFAULT_XSQL_PATH = "xsql";

    private static final String SCHEMA_MIGRATIONS_SQL = "xsql/schema_migrations.xsql";

    private static Map<String, XSQLDefinition.XSQLDeclaration> xsqlDeclarationCache = new HashMap<>();

    static {
        try {
            ClassLoader classLoader = JDrucker.class.getClassLoader();
            cacheFile(SCHEMA_MIGRATIONS_SQL, new File(classLoader.getResource(SCHEMA_MIGRATIONS_SQL).getPath()));
        } catch (IOException e) {
            throw new XSQLException(e.getMessage(), e);
        }
    }

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

    public static void initializeTable() throws IOException {
        initializeTable(DEFAULT_XSQL_PATH, -1);
    }

    public static void initializeTable(String xsqlFilePath, int threadCount) throws IOException {

    }

    public static void migrate(String xsqlFilePath, int threadCount) throws IOException {

    }

    public static void loadXsqlFile(String xsqlFilePath) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null)
            classLoader = JDrucker.class.getClassLoader();
        URL url = classLoader.getResource(xsqlFilePath);
        List<String> cacheNameSegments = new ArrayList<>();

        cacheNameSegments.add(xsqlFilePath);

        traverseXsqlFiles(cacheNameSegments, new File(url.getPath()).listFiles());
    }

    public static XSQLDefinition.XSQLDeclaration getXSQLDeclaration(String xsqlFileName) {
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
        if(xsqlDeclarationCache.get(cacheName) != null)
            throw new XSQLException("Duplicated xslq file: " + cacheName);
        xsqlDeclarationCache.put(cacheName, XSQLDefinition.parse(new FileInputStream(xsqlFile)));
    }

}
