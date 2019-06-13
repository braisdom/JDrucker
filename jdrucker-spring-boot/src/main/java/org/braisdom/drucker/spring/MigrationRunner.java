package org.braisdom.drucker.spring;

import org.braisdom.drucker.JDrucker;
import org.braisdom.drucker.database.DatabaseSession;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.ExitCodeGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;

public class MigrationRunner implements CommandLineRunner, ExitCodeGenerator {

    public static final String MIGRATION_INDICATOR = "migrate";

    private final ApplicationContext applicationContext;
    private final String xsqlPath;
    private final DatabaseSession databaseSession;

    public MigrationRunner(ApplicationContext applicationContext, DatabaseSession databaseSession) {
        this(applicationContext, JDrucker.DEFAULT_XSQL_PATH, databaseSession);
    }

    public MigrationRunner(ApplicationContext applicationContext, String xsqlPath, DatabaseSession databaseSession) {
        this.applicationContext = applicationContext;
        this.xsqlPath = xsqlPath;
        this.databaseSession = databaseSession;
    }

    @Override
    public void run(String... args) throws Exception {
        if(args != null && args.length > 0) {
            String rawMigration = args[0];
            if(MIGRATION_INDICATOR.equals(rawMigration)) {
                SpringApplication.exit(applicationContext, this);
            }
        }
    }

    @Override
    public int getExitCode() {
        return 0;
    }

    private void initializeTables() {

    }

    private void migrate() {

    }
}
