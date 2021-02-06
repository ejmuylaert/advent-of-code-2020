grammar Program;

program: instruction+;

instruction: NOP NUMBER # NoOp
           | JMP NUMBER # Jump
           | ACC NUMBER # Acc
           ;

NOP: 'nop';
JMP: 'jmp';
ACC: 'acc';

NUMBER: [+-][0-9]+;

NL: '\r'?'\n' -> skip;
WS: [\t ]+ -> skip;