grammar Password;

list: line+ EOF;

line: leftNumber '-' rightNumber target ':' password NL*;

leftNumber: NUM;
rightNumber: NUM;
target: CHAR;
password: target+;

NUM: [1-9][0-9]*;
CHAR: [a-z];
NL: '\r'?'\n';
WS: [\t ]+ -> skip;
