package org.braisdom.drucker;

import net.sf.cglib.proxy.Enhancer;
import org.braisdom.drucker.database.TableBehavior;
import org.braisdom.drucker.database.DatabaseSession;
import org.braisdom.drucker.database.TableBehaviorProxy;
import org.braisdom.drucker.database.TableRow;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class JDrucker {

    public static final String DEFAULT_XSQL_PATH = "xsql";

    static {
        try {
            ClassLoader classLoader = JDrucker.class.getClassLoader();
            cacheFile("/xsql/schema_migrations_table.xsql", new File(classLoader.getResource("xsql/schema_migrations_table.xsql").getPath()));
        } catch (IOException e) {
            // Ignore
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
        cacheXsqlIfNecessary(xsqlFilePath);
    }

    public static void migrate(String xsqlFilePath, int threadCount) throws IOException {
        cacheXsqlIfNecessary(xsqlFilePath);
    }

    public static void cacheXsqlIfNecessary(String xsqlFilePath) throws IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        if (classLoader == null)
            classLoader = JDrucker.class.getClassLoader();
        URL url = classLoader.getResource(xsqlFilePath);
        List<String> cacheNameSegments = new ArrayList<>();

        cacheNameSegments.add("/" + xsqlFilePath);
        traverseXsqlFiles(cacheNameSegments, new File(url.getPath()).listFiles());
    }

    private static void traverseXsqlFiles(List<String> cacheNameSegments, File[] xsqlFiles) throws IOException {
        for (File xsqlFile : xsqlFiles) {
            cacheNameSegments.add(xsqlFile.getName());
            if (xsqlFile.isDirectory()) {
                traverseXsqlFiles(cacheNameSegments, xsqlFile.listFiles());
            } else {
                cacheFile(String.join(File.separator, cacheNameSegments.toArray(new String[]{})), xsqlFile);
            }
        }
    }

    private static void cacheFile(String cacheName, File xsqlFile) throws IOException {
//        XSQLCache.cacheXSQLDeclaration(cacheName, XSQLDefinition.getXSQLDeclaration(new FileInputStream(xsqlFile)));
    }

}
