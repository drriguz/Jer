lexer grammar JerLexer;

USE :     'use';
IF  :     'if';
ELSE:     'else';
WHILE:    'while';
RETURN:   'return';
ABSTRACT: 'abstract';
TYPE:     'type';
STRUCT:   'struct';
CONST:    'const';
IS:       'is';
NEW:      'new';
PROCESS:  'process';
FUNCTION: 'function';
WITH:     'with';
RUN:      'run';
THIS:     'this';

LPAREN: '(';
RPAREN: ')';
LBRACE: '{';
RBRACE: '}';
LBRACK: '[';
RBRACK: ']';
SLASH : '/';
COLON : ':';
COMMA : ',';
DOT   : '.';
EAUQL : '=';

TO    : '->';


NULL_LITERAL:      'null';
BOOL_LITERAL:      'true' | 'false';
DECIMAL_LITERAL:   '0' | [1-9] Digits?;
FLOAT_LITERAL:     Digits '.' Digits? 'f';
CHAR_LITERAL:      '\'' (~['\\\r\n] | EscapeSequence) '\'';
STRING_LITERAL :   '"' (~["\\\r\n] | EscapeSequence)* '"';

WS:                 [ \t\r\n\u000C]+ -> channel(HIDDEN);
COMMENT:            '/*' .*? '*/'    -> channel(HIDDEN);
LINE_COMMENT:       '//' ~[\r\n]*    -> channel(HIDDEN);

IDENTIFIER:         [a-z] LetterOrDigit*;
TYPE_NAME:               [A-Z] LetterOrDigit*;
fragment LetterOrDigit
    : Letter
    | [0-9]
    ;
fragment Letter
    : [a-zA-Z$_] // these are the "java letters" below 0x7F
    | ~[\u0000-\u007F\uD800-\uDBFF] // covers all characters above 0x7F which are not a surrogate
    | [\uD800-\uDBFF] [\uDC00-\uDFFF] // covers UTF-16 surrogate pairs encodings for U+10000 to U+10FFFF
    ;
fragment Digits
    : [0-9] +
    ;
fragment EscapeSequence
    : '\\' [btnfr"'\\]
    | '\\' ([0-3]? [0-7])? [0-7]
    | '\\' 'u'+ HexDigit HexDigit HexDigit HexDigit
    ;
fragment HexDigit
    : [0-9a-fA-F]
    ;