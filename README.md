C Compiler 

I have designed a compiler for C language that ironically enough is written in Java. It translates the code from C to x86 Assembly on 32 bits.  Then, the Assembly code can be further used with the assembler and linker, which are separate programs, in order to translate the assembly code into object code and finally into machine code.

Please go to the following github link to pull the project and to test it out:
https://github.com/odumitr/compiler

System requirments:
-Java 8, IDE e.g. Eclipse or terminal, tested on Ubuntu 18.04.2 env, not guarateed to work on other OS, gcc C compiler, Assembly compiler and linker

How to run it:
-after pulling the project from github, import it into your IDE and run Test class;
-I have created invalid and valid C files that have to be compiled to Assembly; the location is in test/lexer_parser/stage_x; for the C files that are invalid, the compiler will raise an Error and the C code will not compile; for the valid C files, the compiler will output the compiled Assembly code into <file_name_ouput.s>, which will be located in the same folder as the file <test/lexer_parser/stage_x/file_name> (stage_x – stage_1: return a number, stage_2 – unary operators, stage_3 – binary operators)
-once you have compiled all the valid C files, you can run the Assembly code and check out its output with the following command: 
./gcc assembly_file_name.s -o output_file_name
./output_file_name
echo $?

Compiler design and architecture:

The compiler reads the C file, lex it, parse it, generates assembly code according to the grammar, output the assembly code to a file.
I have managed to cover so far stage 1 – return an integer, stage 2- unary operators (bitwise complement, negation, logical negation), stage 3- binary operators (addition, subtraction, multiplication, division).
I will continue expanding the compiler with other functionalities in the next weeks (if statements, loops, functions).

Each stage was divided into 3 major subparts:
1. Lexing – building a list of tokens out of the string available in the C file; I have used my own lexer and I have used some regular expressions in order to do the lexing and that part relates to the regular expressions that we covered in class
2. Parsing – building an (AST) abstract syntax tree out of the list of tokens obtained from lexing. The AST has to be built according to the context free grammar that covers the language. Basically, the tokens that are contained in the C file can be viewed as part of a language; the language has to obey certain rules that are inforced through the grammar. So the AST was built according to the rules of the grammar. If at any point of the building process, the “string” a.k.a. C code was not corresponding to the grammar rules, it meant that “string” is not part of the language, therefore an error will be raised. The grammar changed through development, as I had to incorporate more functionalities. The final grammar though looks like this (= means substitution rule):
program = function
function = “int” <id> “(” “)” “{” statement “}”
statement = “return” exp “;”
exp = term {(“+”|”-”) term}
term = factor {(“*”|”/”) factor}
factor = “(” exp “)” | unary_op factor | int


3. Finally, after the AST was built in Parsing phase, we are ready to generate some good old Assembly; this part was pretty straight forward, the only part worth mentioning was that I had to be careful about how I was traversing the AST, since some commands have to happen before others.

The cool and interesting part about the design is that it was made in a very easily extensible, modular way. I intend to keep it that way for the next features also. POO combined with a very decent context free grammar and software practices makes the code so easy to follow and extend.
