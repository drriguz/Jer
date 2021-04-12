package com.riguz.jer.compile.def.expression;

import com.riguz.jer.compile.def.expression.Literal.Type;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LiteralTest {
    @Test
    public void convertToByte() {
        assertEquals(127, (long) new Literal("127", Type.DECIMAL).asByte());
        assertEquals(-12, (long) new Literal("-12", Type.DECIMAL).asByte());
    }

    @Test
    public void convertToChar() {

        assertEquals('a', (long) new Literal("a", Type.CHAR).asChar());
        assertEquals('A', (long) new Literal("A", Type.CHAR).asChar());
        assertEquals('\n', (long) new Literal("\\n", Type.CHAR).asChar());
        assertEquals('H', (long) new Literal("\\u0048", Type.CHAR).asChar());
    }

    @Test
    public void convertToShort() {
        assertEquals(12, (long) new Literal("12", Type.DECIMAL).asShort());
    }

    @Test
    public void convertToInteger() {
        assertEquals(12, (long) new Literal("12", Type.DECIMAL).asInteger());
    }

    @Test
    public void convertToFloat() {
        assertEquals(12.3, new Literal("12.3", Type.DECIMAL).asFloat(), 0.1f);
        assertEquals(3.1415926, new Literal("3.1415926", Type.DECIMAL).asFloat(), 0.1f);
    }

    @Test
    public void convertToDouble() {
        assertEquals(12.3, new Literal("12.3", Type.DECIMAL).asDouble(), 0.1f);
        assertEquals(311723913.1415926, new Literal("311723913.1415926", Type.DECIMAL).asDouble(), 0.1f);
    }

    @Test
    public void convertToString() {
        assertEquals("Hello World", new Literal("\u0048\u0065\u006C\u006C\u006F World", Type.STRING).asString());
    }
}