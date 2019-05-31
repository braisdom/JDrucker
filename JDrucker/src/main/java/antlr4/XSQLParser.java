// Generated from /Users/braisdom/Workspace/JDrucker/JDrucker/src/main/java/XSQL.g4 by ANTLR 4.7.2
package antlr4;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XSQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, SQL=13, SQL_BEGINNING_KEYWORDS=14, ID=15, 
		WS=16, COMMENT=17, LINE_COMMENT=18, DIALECT=19, INT=20, FLOAT=21;
	public static final int
		RULE_xsqlDecl = 0, RULE_dialectDecl = 1, RULE_initDecl = 2, RULE_migrationsDecl = 3, 
		RULE_sqlDecl = 4, RULE_migrationsVersionDecl = 5, RULE_dialectOption = 6, 
		RULE_sqlBlock = 7, RULE_tableName = 8;
	private static String[] makeRuleNames() {
		return new String[] {
			"xsqlDecl", "dialectDecl", "initDecl", "migrationsDecl", "sqlDecl", "migrationsVersionDecl", 
			"dialectOption", "sqlBlock", "tableName"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'initialize'", "'migrations'", "'{'", "'}'", "'sql'", "'version'", 
			"'mysql'", "'oracle'", "'sqlite'", "'postgresql'", "'mssql'", null, null, 
			null, null, null, null, "'dialect:'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "SQL", "SQL_BEGINNING_KEYWORDS", "ID", "WS", "COMMENT", "LINE_COMMENT", 
			"DIALECT", "INT", "FLOAT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "XSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public XSQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class XsqlDeclContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(XSQLParser.EOF, 0); }
		public DialectDeclContext dialectDecl() {
			return getRuleContext(DialectDeclContext.class,0);
		}
		public InitDeclContext initDecl() {
			return getRuleContext(InitDeclContext.class,0);
		}
		public MigrationsDeclContext migrationsDecl() {
			return getRuleContext(MigrationsDeclContext.class,0);
		}
		public List<SqlDeclContext> sqlDecl() {
			return getRuleContexts(SqlDeclContext.class);
		}
		public SqlDeclContext sqlDecl(int i) {
			return getRuleContext(SqlDeclContext.class,i);
		}
		public XsqlDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_xsqlDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterXsqlDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitXsqlDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitXsqlDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final XsqlDeclContext xsqlDecl() throws RecognitionException {
		XsqlDeclContext _localctx = new XsqlDeclContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_xsqlDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DIALECT) {
				{
				setState(18);
				dialectDecl();
				}
			}

			setState(22);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(21);
				initDecl();
				}
			}

			setState(25);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(24);
				migrationsDecl();
				}
			}

			setState(30);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) {
				{
				{
				setState(27);
				sqlDecl();
				}
				}
				setState(32);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(33);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DialectDeclContext extends ParserRuleContext {
		public TerminalNode DIALECT() { return getToken(XSQLParser.DIALECT, 0); }
		public DialectOptionContext dialectOption() {
			return getRuleContext(DialectOptionContext.class,0);
		}
		public DialectDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dialectDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterDialectDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitDialectDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitDialectDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DialectDeclContext dialectDecl() throws RecognitionException {
		DialectDeclContext _localctx = new DialectDeclContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_dialectDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(35);
			match(DIALECT);
			setState(36);
			dialectOption();
			setState(37);
			match(T__0);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class InitDeclContext extends ParserRuleContext {
		public TableNameContext tableName() {
			return getRuleContext(TableNameContext.class,0);
		}
		public SqlBlockContext sqlBlock() {
			return getRuleContext(SqlBlockContext.class,0);
		}
		public InitDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterInitDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitInitDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitInitDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final InitDeclContext initDecl() throws RecognitionException {
		InitDeclContext _localctx = new InitDeclContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_initDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__1);
			setState(40);
			tableName();
			setState(41);
			sqlBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MigrationsDeclContext extends ParserRuleContext {
		public List<MigrationsVersionDeclContext> migrationsVersionDecl() {
			return getRuleContexts(MigrationsVersionDeclContext.class);
		}
		public MigrationsVersionDeclContext migrationsVersionDecl(int i) {
			return getRuleContext(MigrationsVersionDeclContext.class,i);
		}
		public MigrationsDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_migrationsDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterMigrationsDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitMigrationsDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitMigrationsDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MigrationsDeclContext migrationsDecl() throws RecognitionException {
		MigrationsDeclContext _localctx = new MigrationsDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_migrationsDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43);
			match(T__2);
			setState(44);
			match(T__3);
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__6) {
				{
				{
				setState(45);
				migrationsVersionDecl();
				}
				}
				setState(50);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(51);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SqlDeclContext extends ParserRuleContext {
		public SqlBlockContext sqlBlock() {
			return getRuleContext(SqlBlockContext.class,0);
		}
		public DialectOptionContext dialectOption() {
			return getRuleContext(DialectOptionContext.class,0);
		}
		public List<TerminalNode> ID() { return getTokens(XSQLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(XSQLParser.ID, i);
		}
		public SqlDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterSqlDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitSqlDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitSqlDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlDeclContext sqlDecl() throws RecognitionException {
		SqlDeclContext _localctx = new SqlDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_sqlDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) {
				{
				setState(53);
				dialectOption();
				}
			}

			setState(56);
			match(T__5);
			setState(58); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(57);
				match(ID);
				}
				}
				setState(60); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(62);
			sqlBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MigrationsVersionDeclContext extends ParserRuleContext {
		public TerminalNode FLOAT() { return getToken(XSQLParser.FLOAT, 0); }
		public SqlBlockContext sqlBlock() {
			return getRuleContext(SqlBlockContext.class,0);
		}
		public MigrationsVersionDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_migrationsVersionDecl; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterMigrationsVersionDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitMigrationsVersionDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitMigrationsVersionDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MigrationsVersionDeclContext migrationsVersionDecl() throws RecognitionException {
		MigrationsVersionDeclContext _localctx = new MigrationsVersionDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_migrationsVersionDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(T__6);
			setState(65);
			match(FLOAT);
			setState(66);
			sqlBlock();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DialectOptionContext extends ParserRuleContext {
		public DialectOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_dialectOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterDialectOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitDialectOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitDialectOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DialectOptionContext dialectOption() throws RecognitionException {
		DialectOptionContext _localctx = new DialectOptionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_dialectOption);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << T__11))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class SqlBlockContext extends ParserRuleContext {
		public List<TerminalNode> SQL() { return getTokens(XSQLParser.SQL); }
		public TerminalNode SQL(int i) {
			return getToken(XSQLParser.SQL, i);
		}
		public SqlBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sqlBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterSqlBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitSqlBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitSqlBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SqlBlockContext sqlBlock() throws RecognitionException {
		SqlBlockContext _localctx = new SqlBlockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sqlBlock);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(T__3);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==SQL) {
				{
				{
				setState(71);
				match(SQL);
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			match(T__4);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TableNameContext extends ParserRuleContext {
		public List<TerminalNode> ID() { return getTokens(XSQLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(XSQLParser.ID, i);
		}
		public TableNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_tableName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).enterTableName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof XSQLListener ) ((XSQLListener)listener).exitTableName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof XSQLVisitor ) return ((XSQLVisitor<? extends T>)visitor).visitTableName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TableNameContext tableName() throws RecognitionException {
		TableNameContext _localctx = new TableNameContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_tableName);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(79);
				match(ID);
				}
				}
				setState(82); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27W\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\5\2\26"+
		"\n\2\3\2\5\2\31\n\2\3\2\5\2\34\n\2\3\2\7\2\37\n\2\f\2\16\2\"\13\2\3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\5\3\5\3\5\7\5\61\n\5\f\5\16\5\64"+
		"\13\5\3\5\3\5\3\6\5\69\n\6\3\6\3\6\6\6=\n\6\r\6\16\6>\3\6\3\6\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\t\3\t\7\tK\n\t\f\t\16\tN\13\t\3\t\3\t\3\n\6\nS\n\n"+
		"\r\n\16\nT\3\n\2\2\13\2\4\6\b\n\f\16\20\22\2\3\3\2\n\16\2V\2\25\3\2\2"+
		"\2\4%\3\2\2\2\6)\3\2\2\2\b-\3\2\2\2\n8\3\2\2\2\fB\3\2\2\2\16F\3\2\2\2"+
		"\20H\3\2\2\2\22R\3\2\2\2\24\26\5\4\3\2\25\24\3\2\2\2\25\26\3\2\2\2\26"+
		"\30\3\2\2\2\27\31\5\6\4\2\30\27\3\2\2\2\30\31\3\2\2\2\31\33\3\2\2\2\32"+
		"\34\5\b\5\2\33\32\3\2\2\2\33\34\3\2\2\2\34 \3\2\2\2\35\37\5\n\6\2\36\35"+
		"\3\2\2\2\37\"\3\2\2\2 \36\3\2\2\2 !\3\2\2\2!#\3\2\2\2\" \3\2\2\2#$\7\2"+
		"\2\3$\3\3\2\2\2%&\7\25\2\2&\'\5\16\b\2\'(\7\3\2\2(\5\3\2\2\2)*\7\4\2\2"+
		"*+\5\22\n\2+,\5\20\t\2,\7\3\2\2\2-.\7\5\2\2.\62\7\6\2\2/\61\5\f\7\2\60"+
		"/\3\2\2\2\61\64\3\2\2\2\62\60\3\2\2\2\62\63\3\2\2\2\63\65\3\2\2\2\64\62"+
		"\3\2\2\2\65\66\7\7\2\2\66\t\3\2\2\2\679\5\16\b\28\67\3\2\2\289\3\2\2\2"+
		"9:\3\2\2\2:<\7\b\2\2;=\7\21\2\2<;\3\2\2\2=>\3\2\2\2><\3\2\2\2>?\3\2\2"+
		"\2?@\3\2\2\2@A\5\20\t\2A\13\3\2\2\2BC\7\t\2\2CD\7\27\2\2DE\5\20\t\2E\r"+
		"\3\2\2\2FG\t\2\2\2G\17\3\2\2\2HL\7\6\2\2IK\7\17\2\2JI\3\2\2\2KN\3\2\2"+
		"\2LJ\3\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OP\7\7\2\2P\21\3\2\2\2QS\7\21"+
		"\2\2RQ\3\2\2\2ST\3\2\2\2TR\3\2\2\2TU\3\2\2\2U\23\3\2\2\2\13\25\30\33 "+
		"\628>LT";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}