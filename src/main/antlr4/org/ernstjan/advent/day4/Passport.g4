grammar Passport;

passports: passport+;

passport: field+ (NL* | EOF);

// Grab optional newline after field, so it won't count as separator between passports
field: 'byr' ':' NUMBER NL?   # BirthYear
     | 'iyr' ':' NUMBER NL?    # IssueYear
     | 'eyr' ':' NUMBER NL?    # ExpirationYear
     | 'hgt' ':' NUMBER UNIT NL?     # Height
     | 'hcl' ':' COLOR_HEX NL? # HairColor
     | 'ecl' ':' COLOR_NAME NL? # EyeColor
     | 'pid' ':' (DIGITS | NUMBER) NL?     # PassportId
     | 'cid' ':' (DIGITS | NUMBER) NL?    # CountryId
     ;

UNIT: 'cm' | 'in';
NUMBER: [1-9][0-9]*;
DIGITS: [0-9]+;
COLOR_HEX: '#'[0-9a-f]+;
COLOR_NAME: [a-z]+;
NL: '\r'?'\n';
WS: [\t ]+ -> skip;
