package com.riguz.jer.compile.antlr;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.misc.ParseCancellationException;

/** ref:
 *  https://stackoverflow.com/questions/18132078/handling-errors-in-antlr4
 */
public class ThrowingErrorListener extends BaseErrorListener {
    public static final ThrowingErrorListener INSTANCE = new ThrowingErrorListener();

    private ThrowingErrorListener(){

    }

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new ParseCancellationException("line " + line + ":" + charPositionInLine + " " + msg);
    }
}
