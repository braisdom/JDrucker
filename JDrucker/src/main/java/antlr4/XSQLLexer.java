// Generated from /Users/braisdom/Workspace/JDrucker/JDrucker/src/main/java/XSQL.g4 by ANTLR 4.7.2
package antlr4;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class XSQLLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.7.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, SQL=13, SQL_BEGINNING_KEYWORDS=14, ID=15, 
		WS=16, COMMENT=17, LINE_COMMENT=18, DIALECT=19, INT=20, FLOAT=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "SQL", "SQL_BEGINNING_KEYWORDS", "ID", "WS", 
			"COMMENT", "LINE_COMMENT", "DIALECT", "INT", "FLOAT"
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


	public XSQLLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "XSQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u0157\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\3\2\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t"+
		"\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\7\16}\n\16\f\16\16\16\u0080\13\16\3\16\3\16\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u0119\n\17\3\20\3\20\6\20"+
		"\u011d\n\20\r\20\16\20\u011e\3\21\6\21\u0122\n\21\r\21\16\21\u0123\3\21"+
		"\3\21\3\22\3\22\3\22\3\22\7\22\u012c\n\22\f\22\16\22\u012f\13\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\23\3\23\7\23\u0138\n\23\f\23\16\23\u013b\13\23"+
		"\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\6\25\u0149"+
		"\n\25\r\25\16\25\u014a\3\26\6\26\u014e\n\26\r\26\16\26\u014f\3\26\3\26"+
		"\6\26\u0154\n\26\r\26\16\26\u0155\4~\u012d\2\27\3\3\5\4\7\5\t\6\13\7\r"+
		"\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25"+
		")\26+\27\3\2\6\5\2C\\aac|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17"+
		"\17\2\u0175\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2"+
		"\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27"+
		"\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2"+
		"\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\3-\3\2\2\2"+
		"\5/\3\2\2\2\7:\3\2\2\2\tE\3\2\2\2\13G\3\2\2\2\rI\3\2\2\2\17M\3\2\2\2\21"+
		"U\3\2\2\2\23[\3\2\2\2\25b\3\2\2\2\27i\3\2\2\2\31t\3\2\2\2\33z\3\2\2\2"+
		"\35\u0118\3\2\2\2\37\u011a\3\2\2\2!\u0121\3\2\2\2#\u0127\3\2\2\2%\u0135"+
		"\3\2\2\2\'\u013e\3\2\2\2)\u0148\3\2\2\2+\u014d\3\2\2\2-.\7=\2\2.\4\3\2"+
		"\2\2/\60\7k\2\2\60\61\7p\2\2\61\62\7k\2\2\62\63\7v\2\2\63\64\7k\2\2\64"+
		"\65\7c\2\2\65\66\7n\2\2\66\67\7k\2\2\678\7|\2\289\7g\2\29\6\3\2\2\2:;"+
		"\7o\2\2;<\7k\2\2<=\7i\2\2=>\7t\2\2>?\7c\2\2?@\7v\2\2@A\7k\2\2AB\7q\2\2"+
		"BC\7p\2\2CD\7u\2\2D\b\3\2\2\2EF\7}\2\2F\n\3\2\2\2GH\7\177\2\2H\f\3\2\2"+
		"\2IJ\7u\2\2JK\7s\2\2KL\7n\2\2L\16\3\2\2\2MN\7x\2\2NO\7g\2\2OP\7t\2\2P"+
		"Q\7u\2\2QR\7k\2\2RS\7q\2\2ST\7p\2\2T\20\3\2\2\2UV\7o\2\2VW\7{\2\2WX\7"+
		"u\2\2XY\7s\2\2YZ\7n\2\2Z\22\3\2\2\2[\\\7q\2\2\\]\7t\2\2]^\7c\2\2^_\7e"+
		"\2\2_`\7n\2\2`a\7g\2\2a\24\3\2\2\2bc\7u\2\2cd\7s\2\2de\7n\2\2ef\7k\2\2"+
		"fg\7v\2\2gh\7g\2\2h\26\3\2\2\2ij\7r\2\2jk\7q\2\2kl\7u\2\2lm\7v\2\2mn\7"+
		"i\2\2no\7t\2\2op\7g\2\2pq\7u\2\2qr\7s\2\2rs\7n\2\2s\30\3\2\2\2tu\7o\2"+
		"\2uv\7u\2\2vw\7u\2\2wx\7s\2\2xy\7n\2\2y\32\3\2\2\2z~\5\35\17\2{}\13\2"+
		"\2\2|{\3\2\2\2}\u0080\3\2\2\2~\177\3\2\2\2~|\3\2\2\2\177\u0081\3\2\2\2"+
		"\u0080~\3\2\2\2\u0081\u0082\7=\2\2\u0082\34\3\2\2\2\u0083\u0084\7E\2\2"+
		"\u0084\u0085\7T\2\2\u0085\u0086\7G\2\2\u0086\u0087\7C\2\2\u0087\u0088"+
		"\7V\2\2\u0088\u0119\7G\2\2\u0089\u008a\7F\2\2\u008a\u008b\7T\2\2\u008b"+
		"\u008c\7Q\2\2\u008c\u0119\7R\2\2\u008d\u008e\7U\2\2\u008e\u008f\7J\2\2"+
		"\u008f\u0090\7Q\2\2\u0090\u0119\7Y\2\2\u0091\u0092\7W\2\2\u0092\u0093"+
		"\7U\2\2\u0093\u0119\7G\2\2\u0094\u0095\7F\2\2\u0095\u0096\7G\2\2\u0096"+
		"\u0097\7U\2\2\u0097\u0098\7E\2\2\u0098\u0099\7T\2\2\u0099\u009a\7K\2\2"+
		"\u009a\u009b\7D\2\2\u009b\u0119\7G\2\2\u009c\u009d\7U\2\2\u009d\u009e"+
		"\7G\2\2\u009e\u009f\7N\2\2\u009f\u00a0\7G\2\2\u00a0\u00a1\7E\2\2\u00a1"+
		"\u0119\7V\2\2\u00a2\u00a3\7W\2\2\u00a3\u00a4\7R\2\2\u00a4\u00a5\7F\2\2"+
		"\u00a5\u00a6\7C\2\2\u00a6\u00a7\7V\2\2\u00a7\u0119\7G\2\2\u00a8\u00a9"+
		"\7F\2\2\u00a9\u00aa\7G\2\2\u00aa\u00ab\7N\2\2\u00ab\u00ac\7G\2\2\u00ac"+
		"\u00ad\7V\2\2\u00ad\u0119\7G\2\2\u00ae\u00af\7C\2\2\u00af\u00b0\7N\2\2"+
		"\u00b0\u00b1\7V\2\2\u00b1\u00b2\7G\2\2\u00b2\u0119\7T\2\2\u00b3\u00b4"+
		"\7K\2\2\u00b4\u00b5\7P\2\2\u00b5\u00b6\7U\2\2\u00b6\u00b7\7G\2\2\u00b7"+
		"\u00b8\7T\2\2\u00b8\u0119\7V\2\2\u00b9\u00ba\7E\2\2\u00ba\u00bb\7C\2\2"+
		"\u00bb\u00bc\7N\2\2\u00bc\u0119\7N\2\2\u00bd\u00be\7E\2\2\u00be\u00bf"+
		"\7Q\2\2\u00bf\u00c0\7O\2\2\u00c0\u00c1\7O\2\2\u00c1\u00c2\7K\2\2\u00c2"+
		"\u0119\7V\2\2\u00c3\u00c4\7T\2\2\u00c4\u00c5\7G\2\2\u00c5\u00c6\7P\2\2"+
		"\u00c6\u00c7\7C\2\2\u00c7\u00c8\7O\2\2\u00c8\u0119\7G\2\2\u00c9\u00ca"+
		"\7V\2\2\u00ca\u00cb\7T\2\2\u00cb\u00cc\7W\2\2\u00cc\u00cd\7P\2\2\u00cd"+
		"\u00ce\7E\2\2\u00ce\u00cf\7C\2\2\u00cf\u00d0\7V\2\2\u00d0\u0119\7G\2\2"+
		"\u00d1\u00d2\7T\2\2\u00d2\u00d3\7G\2\2\u00d3\u00d4\7R\2\2\u00d4\u00d5"+
		"\7N\2\2\u00d5\u00d6\7C\2\2\u00d6\u00d7\7E\2\2\u00d7\u0119\7G\2\2\u00d8"+
		"\u00d9\7U\2\2\u00d9\u00da\7C\2\2\u00da\u00db\7X\2\2\u00db\u00dc\7G\2\2"+
		"\u00dc\u00dd\7R\2\2\u00dd\u00de\7Q\2\2\u00de\u00df\7K\2\2\u00df\u00e0"+
		"\7P\2\2\u00e0\u0119\7V\2\2\u00e1\u00e2\7T\2\2\u00e2\u00e3\7Q\2\2\u00e3"+
		"\u00e4\7N\2\2\u00e4\u00e5\7N\2\2\u00e5\u00e6\7D\2\2\u00e6\u00e7\7C\2\2"+
		"\u00e7\u00e8\7E\2\2\u00e8\u0119\7M\2\2\u00e9\u00ea\7F\2\2\u00ea\u00eb"+
		"\7G\2\2\u00eb\u00ec\7C\2\2\u00ec\u00ed\7N\2\2\u00ed\u00ee\7N\2\2\u00ee"+
		"\u00ef\7Q\2\2\u00ef\u00f0\7E\2\2\u00f0\u00f1\7C\2\2\u00f1\u00f2\7V\2\2"+
		"\u00f2\u0119\7G\2\2\u00f3\u00f4\7F\2\2\u00f4\u00f5\7G\2\2\u00f5\u00f6"+
		"\7E\2\2\u00f6\u00f7\7N\2\2\u00f7\u00f8\7C\2\2\u00f8\u00f9\7T\2\2\u00f9"+
		"\u0119\7G\2\2\u00fa\u00fb\7T\2\2\u00fb\u00fc\7G\2\2\u00fc\u00fd\7R\2\2"+
		"\u00fd\u00fe\7G\2\2\u00fe\u00ff\7C\2\2\u00ff\u0119\7V\2\2\u0100\u0101"+
		"\7O\2\2\u0101\u0102\7G\2\2\u0102\u0103\7T\2\2\u0103\u0104\7I\2\2\u0104"+
		"\u0119\7G\2\2\u0105\u0106\7C\2\2\u0106\u0107\7P\2\2\u0107\u0108\7C\2\2"+
		"\u0108\u0109\7N\2\2\u0109\u010a\7[\2\2\u010a\u010b\7\\\2\2\u010b\u0119"+
		"\7G\2\2\u010c\u010d\7F\2\2\u010d\u010e\7G\2\2\u010e\u010f\7V\2\2\u010f"+
		"\u0110\7C\2\2\u0110\u0111\7E\2\2\u0111\u0119\7J\2\2\u0112\u0113\7G\2\2"+
		"\u0113\u0114\7Z\2\2\u0114\u0115\7R\2\2\u0115\u0116\7N\2\2\u0116\u0117"+
		"\7C\2\2\u0117\u0119\7P\2\2\u0118\u0083\3\2\2\2\u0118\u0089\3\2\2\2\u0118"+
		"\u008d\3\2\2\2\u0118\u0091\3\2\2\2\u0118\u0094\3\2\2\2\u0118\u009c\3\2"+
		"\2\2\u0118\u00a2\3\2\2\2\u0118\u00a8\3\2\2\2\u0118\u00ae\3\2\2\2\u0118"+
		"\u00b3\3\2\2\2\u0118\u00b9\3\2\2\2\u0118\u00bd\3\2\2\2\u0118\u00c3\3\2"+
		"\2\2\u0118\u00c9\3\2\2\2\u0118\u00d1\3\2\2\2\u0118\u00d8\3\2\2\2\u0118"+
		"\u00e1\3\2\2\2\u0118\u00e9\3\2\2\2\u0118\u00f3\3\2\2\2\u0118\u00fa\3\2"+
		"\2\2\u0118\u0100\3\2\2\2\u0118\u0105\3\2\2\2\u0118\u010c\3\2\2\2\u0118"+
		"\u0112\3\2\2\2\u0119\36\3\2\2\2\u011a\u011c\t\2\2\2\u011b\u011d\t\3\2"+
		"\2\u011c\u011b\3\2\2\2\u011d\u011e\3\2\2\2\u011e\u011c\3\2\2\2\u011e\u011f"+
		"\3\2\2\2\u011f \3\2\2\2\u0120\u0122\t\4\2\2\u0121\u0120\3\2\2\2\u0122"+
		"\u0123\3\2\2\2\u0123\u0121\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2"+
		"\2\2\u0125\u0126\b\21\2\2\u0126\"\3\2\2\2\u0127\u0128\7\61\2\2\u0128\u0129"+
		"\7,\2\2\u0129\u012d\3\2\2\2\u012a\u012c\13\2\2\2\u012b\u012a\3\2\2\2\u012c"+
		"\u012f\3\2\2\2\u012d\u012e\3\2\2\2\u012d\u012b\3\2\2\2\u012e\u0130\3\2"+
		"\2\2\u012f\u012d\3\2\2\2\u0130\u0131\7,\2\2\u0131\u0132\7\61\2\2\u0132"+
		"\u0133\3\2\2\2\u0133\u0134\b\22\2\2\u0134$\3\2\2\2\u0135\u0139\7%\2\2"+
		"\u0136\u0138\n\5\2\2\u0137\u0136\3\2\2\2\u0138\u013b\3\2\2\2\u0139\u0137"+
		"\3\2\2\2\u0139\u013a\3\2\2\2\u013a\u013c\3\2\2\2\u013b\u0139\3\2\2\2\u013c"+
		"\u013d\b\23\2\2\u013d&\3\2\2\2\u013e\u013f\7f\2\2\u013f\u0140\7k\2\2\u0140"+
		"\u0141\7c\2\2\u0141\u0142\7n\2\2\u0142\u0143\7g\2\2\u0143\u0144\7e\2\2"+
		"\u0144\u0145\7v\2\2\u0145\u0146\7<\2\2\u0146(\3\2\2\2\u0147\u0149\4\62"+
		";\2\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b*\3\2\2\2\u014c\u014e\5)\25\2\u014d\u014c\3\2\2\2"+
		"\u014e\u014f\3\2\2\2\u014f\u014d\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151"+
		"\3\2\2\2\u0151\u0153\7\60\2\2\u0152\u0154\5)\25\2\u0153\u0152\3\2\2\2"+
		"\u0154\u0155\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2\2\2\u0156,\3"+
		"\2\2\2\f\2~\u0118\u011e\u0123\u012d\u0139\u014a\u014f\u0155\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}