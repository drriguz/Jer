package com.riguz.jer.compile.def.expression;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LiteralTest {
    @Test
    public void convertToByte() {
        assertEquals(127, (long) new Literal("127").asByte());
        assertEquals(-12, (long) new Literal("-12").asByte());
    }

    @Test
    public void convertToChar() {

        assertEquals('a', (long) new Literal("a").asChar());
        assertEquals('A', (long) new Literal("A").asChar());
        assertEquals('\n', (long) new Literal("\\n").asChar());
        assertEquals('H', (long) new Literal("\\u0048").asChar());
    }

    @Test
    public void convertToShort() {
        assertEquals(12, (long) new Literal("12").asShort());
    }

    @Test
    public void convertToInteger() {
        assertEquals(12, (long) new Literal("12").asInteger());
    }

    @Test
    public void convertToFloat() {
        assertEquals(12.3, new Literal("12.3").asFloat(), 0.1f);
        assertEquals(3.1415926, new Literal("3.1415926").asFloat(), 0.1f);
    }

    @Test
    public void convertToDouble() {
        assertEquals(12.3, new Literal("12.3").asDouble(), 0.1f);
        assertEquals(311723913.1415926, new Literal("311723913.1415926").asDouble(), 0.1f);
    }

    @Test
    public void convertToString() {
        assertEquals("Hello World", new Literal( "\u0048\u0065\u006C\u006C\u006F World").asString());
    }
}