parser grammar JerParser;

options { tokenVocab=JerLexer; }

@header { package com.riguz.jer.antlr.generated; }

compilationUint
    : useDeclaration* typeDeclaration* EOF
    ;

useDeclaration
    : USE fullPath
    ;

fullPath
    : (IDENTIFIER '/')* TYPE;

typeDeclaration
    : functionDeclaration
    ;

functionDeclaration
    : IDENTIFIER '(' formalParameters? ')' functionReturnType? '='
      methodBody
    ;
functionReturnType
    : TO type
    ;
formalParameter
    : IDENTIFIER ':' type
    ;
type
    : TYPE
    | arrayType
    ;
arrayType
    : '[' type
    ;
formalParameters
    : formalParameter (',' formalParameter) *
    ;

methodBody
    : block
    ;
block
    : '{' blockStatement* '}'
    ;
blockStatement
    : variableDeclaration
    | statement
    ;
variableDeclaration
    : IDENTIFIER ':' type ('=' variableInitializer)?
    ;
variableInitializer
    : arrayInitializer
    | expression
    ;
arrayInitializer
    : '{' (variableInitializer (',' variableInitializer)*)? '}'
    ;
statement
    : block
    | IF parExpression statement (ELSE statement) ?
    | expression
    ;
expression
    : primary
    | methodCall
    ;
methodCall
    : IDENTIFIER '(' expressionList? ')'
    ;
expressionList
    : expression (',' expression)*
    ;
parExpression
    : '(' expression ')'
    ;
primary
    : '(' expression ')'
    | literal
    | IDENTIFIER
    ;
literal
    : DECIMAL_LITERAL
    | FLOAT_LITERAL
    | CHAR_LITERAL
    | STRING_LITERAL
    | BOOL_LITERAL
    | NULL_LITERAL
    ;