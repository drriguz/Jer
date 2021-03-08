parser grammar JerParser;

options { tokenVocab=JerLexer; }

@header { package com.riguz.jer.antlr.generated; }

compilationUint
    : typeDeclaration * EOF
    ;

typeDeclaration
    : functionDeclaration
    ;

functionDeclaration
    : IDENTIFIER '(' ')'
    ;