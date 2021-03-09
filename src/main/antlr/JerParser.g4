parser grammar JerParser;

options { tokenVocab=JerLexer; }

@header { package com.riguz.jer.antlr.generated; }

compilationUint
    : importedType* declaration* EOF
    ;

/** imports **/
importedType
    : USE fullPath
    ;
fullPath
    : (IDENTIFIER '/')* TYPE_NAME
    ;

declaration
    : constantDeclaration
    | methodDeclaration
    | abstractDeclaration
    | typeDeclaration
    ;

constantDeclaration
    : CONST variableDeclaration
    ;
/** method **/
methodDeclaration
    : methodSignature methodImplementation?
    ;
methodSignature
    : IDENTIFIER '(' formalParameters? ')' functionReturnType?
    ;
formalParameters
    : formalParameter (',' formalParameter)*
    ;
functionReturnType
    : TO type
    ;
methodImplementation
    : '=' block
    ;
formalParameter
    : IDENTIFIER ':' type
    ;
/** abstract & type **/
abstractDeclaration
    : ABSTRACT TYPE_NAME '{' propertyDeclaration* methodSignature*'}'
    ;
typeDeclaration
    : TYPE TYPE_NAME typeAbstractions? '{' propertyDeclaration* constructorDeclaration* methodDeclaration*'}'
    ;
typeAbstractions
    : IS TYPE_NAME (',' TYPE_NAME)*
    ;
propertyDeclaration
    : IDENTIFIER ':' type
    ;
constructorDeclaration
    : '(' constructorFormalArguments? ')' methodImplementation
    ;
constructorFormalArguments
    : constructorFormalArgument (',' constructorFormalArgument)*
    ;
constructorFormalArgument
    : IDENTIFIER (':' TYPE_NAME)?
    ;
/** type & variable **/
type
    : TYPE_NAME
    | arrayType
    ;
arrayType
    : '[' type
    ;
variableDeclaration
    : IDENTIFIER ':' type ('=' variableInitializer)?
    ;
variableInitializer
    : arrayInitializer
    | expression
    ;
arrayInitializer
    : '{' variableInitializer (',' variableInitializer)* '}'
    ;

block
    : '{' statement* '}'
    ;
statement
    : variableDeclaration
    | embeddedStatement
    ;

embeddedStatement
    : block
    | assignment
    | expressionStatement
    | selectionStatement
    | loopStatement
    | returnStatement
    ;
assignment
    : IDENTIFIER '=' expression
    ;
selectionStatement
    : IF '(' expression ')' statement (ELSE statement)?
    ;
loopStatement
    : WHILE '(' expression ')' statement
    ;
returnStatement
    : RETURN expression
    ;
expressionStatement
    : methodCall
    ;

methodCall
    : instance=IDENTIFIER? '('methodName=IDENTIFIER methodArguments? ')'
    ;
methodArguments
    : expression (',' expression)*
    ;

expression
    : primary
    | expression bop='.'
        ( methodCall
        | IDENTIFIER
        )
    | methodCall
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