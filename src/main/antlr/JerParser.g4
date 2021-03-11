parser grammar JerParser;

options { tokenVocab=JerLexer; }

compilationUint
    : importedType* declaration+ EOF
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
    | processDeclaration
    | abstractDeclaration
    | typeDeclaration
    ;

/** static variable and methods **/
constantDeclaration
    : CONST IDENTIFIER ':' type ('=' variableInitializer)
    ;
processDeclaration
    : PROCESS name=IDENTIFIER (WITH formalParameters)? block
    ;
formalParameters
    : formalParameter (',' formalParameter)*
    ;
formalParameter
    : IDENTIFIER ':' type
    ;

/** abstract & custom type **/
abstractDeclaration
    : ABSTRACT TYPE_NAME '{' propertySignature* functionSignature*'}'
    ;
functionSignature
    : FUNCTION name=IDENTIFIER '(' formalParameters? ')' TO type
    ;
functionDeclaration
    : functionSignature block
    ;
typeDeclaration
    : TYPE TYPE_NAME typeAbstractions? '{' propertySignature* constructorDeclaration* functionDeclaration*'}'
    ;
typeAbstractions
    : IS TYPE_NAME (',' TYPE_NAME)*
    ;
propertySignature
    : IDENTIFIER ':' type
    ;
constructorDeclaration
    : '(' constructorFormalArguments? ')' block
    ;
constructorFormalArguments
    : constructorFormalArgument (',' constructorFormalArgument)*
    ;
constructorFormalArgument
    : IDENTIFIER (':' TYPE_NAME)?
    ;

/** data type & variable **/
type
    : TYPE_NAME
    | arrayType
    ;
arrayType
    : '[' type
    ;


/** statements **/
block
    : '{' statement* '}'
    ;

statement
    : localVariableDeclaration
    | embeddedStatement
    ;
embeddedStatement
    : block
    | assignStatement
    | processStatement
    | selectionStatement
    | loopStatement
    | returnStatement
    ;

localVariableDeclaration
    : IDENTIFIER ':' type ('=' variableInitializer)?
    ;
variableInitializer
    : arrayInitializer
    | expression
    ;
arrayInitializer
    : '{' variableInitializer (',' variableInitializer)* '}'
    ;
assignStatement
    : IDENTIFIER '=' expression
    ;
selectionStatement
    : IF '(' expression ')' embeddedStatement (ELSE embeddedStatement)?
    ;
loopStatement
    : WHILE '(' expression ')' embeddedStatement
    ;
processStatement
    : RUN IDENTIFIER expressionList?
    ;
expressionList
    : expression (',' expression)*
    ;
returnStatement
    : RETURN expression
    ;

/** expressions **/
expression
    : literal
    | THIS
    | expression '.' IDENTIFIER '(' expressionList? ')' // function call
    | expression '[' IDENTIFIER ']'                     // property
    | variableValue
    | objectCreation
    ;
variableValue
    : IDENTIFIER
    ;
objectCreation
    : NEW TYPE_NAME '(' expressionList? ')'
    ;
literal
    : DECIMAL_LITERAL
    | FLOAT_LITERAL
    | CHAR_LITERAL
    | STRING_LITERAL
    | BOOL_LITERAL
    | NULL_LITERAL
    ;