grammar Customs;

list: group+ EOF;

group: form+ NL*;

form: QUESTION+ NL?;

QUESTION: [a-z];
NL: '\r'?'\n';
WS: [\t ]+ -> skip;
