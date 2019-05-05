package org.jparsec;

import org.jparsec.pattern.Patterns;

public class SqlBlockScanner extends Parser<String> {
    private final Parser<?> openQuote;
    private final Parser<?> closeQuote;
    private final Parser<?> commented;

    public SqlBlockScanner() {
        this.openQuote = Patterns.string("{").toScanner("{");
        this.closeQuote = Patterns.string("}").toScanner("}");
        this.commented = Patterns.ANY_CHAR.toScanner("SQL");
    }

    @Override
    boolean apply(final ParseContext ctxt) {
        if (!openQuote.apply(ctxt)) return false;
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(openQuote.toString());
        for (int level = 1; level > 0; ) {
            final int step = ctxt.step;
            final int at = ctxt.at;
            if (at >= ctxt.source.length())
                return false;
            stringBuilder.append(ctxt.source.charAt(at));
            if (closeQuote.apply(ctxt)) {
                if (at == ctxt.at) {
                    throw new IllegalStateException("closing SQL scanner not consuming input.");
                }
                level--;
                continue;
            }
            if (openQuote.apply(ctxt)) {
                if (at == ctxt.at) {
                    throw new IllegalStateException("opening SQL scanner not consuming input.");
                }
                level++;
                continue;
            }
            if (!ctxt.stillThere(at, step)) return false;
            if (commented.apply(ctxt)) {
                if (at == ctxt.at) {
                    throw new IllegalStateException("SQL not consuming input.");
                }
                continue;
            }
            return false;
        }
        ctxt.result = stringBuilder.toString();
        return true;
    }

    @Override
    public String toString() {
        return "SQL statement";
    }
}