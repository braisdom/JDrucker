//Define a grammar called XSQL
grammar XSQL;

options {
    language = Java;
}

xsqlDecl: dialectDecl initDecl migrationsDecl? sqlDecl* EOF;
dialectDecl: DIALECT dialectOption ';';
initDecl: 'initialize' tableName sqlBlock;
migrationsDecl: 'migrations' '{' migrationsVersionDecl* '}';
sqlDecl: dialectOption? 'sql' ID+ sqlBlock;
migrationsVersionDecl: 'version' FLOAT sqlBlock;
dialectOption: 'mysql' | 'oracle' | 'sqlite' | 'postgresql' | 'mssql';
sqlBlock: '{' SQL* '}';
tableName: ID+;

SQL: SQL_BEGINNING_KEYWORDS .*? ';';
SQL_BEGINNING_KEYWORDS: 'CREATE' | 'DROP' | 'SHOW' | 'USE' | 'DESCRIBE' | 'SELECT' | 'UPDATE' | 'DELETE' | 'ALTER' | 'INSERT'
    | 'CALL' | 'COMMIT' | 'RENAME' | 'TRUNCATE' | 'REPLACE'  | 'SAVEPOINT' | 'ROLLBACK' | 'DEALLOCATE' | 'DECLARE' | 'REPEAT'
    | 'MERGE';
ID: ('A'..'Z' | 'a'..'z' | '_') ( 'A'..'Z' | 'a'..'z' | '_' | '0'..'9')+;
WS : (' ' |'\t' |'\n' |'\r' )+ -> skip;
COMMENT: '/*' .*? '*/' -> skip;
LINE_COMMENT: '#' ~[\r\n]* -> skip;
DIALECT: 'dialect:';
INT: ('0'..'9')+;
FLOAT: INT+ '.' INT+;