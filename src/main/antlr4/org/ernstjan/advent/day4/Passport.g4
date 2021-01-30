grammar Passport;

passports: passport+;

passport: field+ (NL* | EOF);

// Grab optional newline after field, so it won't count as separator between passports
field: 'byr' ':' birthYear NL?          # birthYearLabel
     | 'iyr' ':' issueYear NL?          # issueYearLabel
     | 'eyr' ':' expirationYear NL?     # expirationYearLabel
     | 'hgt' ':' height NL?             # heightLabel
     | 'hcl' ':' hairColor NL?          # hairColorLabel
     | 'ecl' ':' eyeColor NL?           # eyeColorLabel
     | 'pid' ':' passportId NL?         # passportIdLabel
     | 'cid' ':' countryId NL?          # countryIdLabel
     ;

// A wrong value can be a combination of numbers (or digits) and text, this value
// will be parsed as 2 tokens, this is the reason for the '+' in 'any+'
birthYear   : NUMBER
            | any+
            ;

issueYear   : NUMBER
            | any+
            ;

expirationYear  : NUMBER
                | any+
                ;

height  : HEIGHT
        | any+
        ;


hairColor   : HEX_COLOR
            | any+
            ;

eyeColor    : COLOR_NAME
            | any+
            ;

passportId  : (DIGITS | NUMBER)
            | any+
            ;

countryId   : (DIGITS | NUMBER)
            | any+
            ;

any: (UNIT | NUMBER | DIGITS | HEX_COLOR | COLOR_NAME | HEIGHT | STRING);

HEIGHT: NUMBER UNIT;
UNIT: 'cm' | 'in';
NUMBER: [1-9][0-9]*;
DIGITS: [0-9]+;
HEX_COLOR: '#'[0-9a-z]+;
COLOR_NAME: 'amb' | 'blu' | 'brn' | 'gry' | 'grn' | 'hzl' | 'oth';
STRING: [a-zA-Z]+;
NL: '\r'?'\n';
WS: [\t ]+ -> skip;