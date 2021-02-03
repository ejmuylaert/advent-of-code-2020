grammar Bags;

rules: bagRule+;

bagRule: bag  CONTAIN NO_BAGS EOL                      # Empty
       | bag  CONTAIN contents (COMMA contents)* EOL   # Containing
       ;

bag: STRING STRING BAG;
contents: NUMBER bag;

// First define the literals, so they won't be parsed as generic STRINGs
NO_BAGS: 'no other bags';
CONTAIN: 'contain';
BAG: 'bag' 's'?;

COMMA: ',';
EOL: '.' NL?;

NUMBER: [1-9][0-9]*;
STRING: [a-z]+;

NL: '\r'?'\n';
WS: [\t ]+ -> skip;