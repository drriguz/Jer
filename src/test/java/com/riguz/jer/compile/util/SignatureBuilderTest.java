package com.riguz.jer.compile.util;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;

import static com.riguz.jer.compile.util.SignatureBuilder.getMethodDescriptor;
import static org.junit.Assert.*;

public class SignatureBuilderTest {
    @Test
    public void createSignatureForVoidMethod() {
        assertEquals("()V", getMethodDescriptor(Collections.emptyList(), null));
        assertEquals("([Ljava/lang/String;)V", getMethodDescriptor(Arrays.asList("[Ljava/lang/String;"), null));
    }
}