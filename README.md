# Expression Evaluator

The project consists of a mathematical expression evaluator, which can perform any calculations, no matter how complex, taking into account the correct evaluation of the order of operations.


## Project overview

The program consists of two steps:
- it takes the infix notation of a mathematic expression and converts it to its postfix (or reverse polish) notation;
- it evaluates the postfix notation, providing the result of the mathematical expression.

### Operators accepted 
| Operator |   Description  | Associativity | Precedence |
|    :-:   |       :-:      |      :-:      |     :-:    |
|     +    |     addition   |   left-right  |     11     |
|     -    |  substraction  |   left-right  |     11     |
|     *    | multiplication |   left-right  |     12     |
|     /    |    division    |   left-right  |     12     |
|     ^    |    power up    |   right-left  |     13     |  


## About infix, postfix and prefix notations
Infix, postfix and prefix notations represent three different ways of writing equivalent mathematical expressions.

- **Infix**: 3 + 4
- **Prefix**: + 3 4
- **Postfix**: 3 4 +

### Infix Notation
The infix notation is the usual, common way we write mathematical expresions. In infix notation the operators are placed between operands and parentheses surrounding groups of operands and operators are necessary to indicate the intended order in which operations are to be performed. In the absence of parentheses, certain precedence rules determine the order of operations.

### Prefix Notation
Prefix notation, also known as Polish notation (PN) is a mathematical notation in which operators precede their operands. The prefix notation was invented in 1924 and the description of "Polish" refers to the nationality of its inventor (Polish logician Jan Łukasiewicz). 

### Postfix Notation
Postfix Notation, or Reverse Polish notation (RPN) is a mathematical notation in which operators follow their operands. Both prefix and postfix notations eliminate the need for paratheses as long as each operator has a fixed number of operands.


## Converting from Infix to Postfix notation
**Shunting yard algorithm** was invented by Edsger Dijkstra and is one of the most famous algorithm for converting expressions from infix form to postfix form. The algorithm is stack-based. For the conversion there are two String variables (input and output) and a stack that holds operators pending to be added to the output. The program reads each symbol in order and performs an action based on that symbol.

**Shunting yard algorithm pseudocode**  
```
1. as long as there is an entity to read:  
     1.1 read the entity (i.e. operand or operator)
     1.2. if the entity is an operand (i.e. a number), then:
         1.2.1 add it to the postfix form
     1.3 if the entity is an operator (be it O1), then:
         1.3.1 while ((there is an operator at the top of the stack (be it O2)) AND
                       (O2 is not a left paranthesis) AND
                       ((precedence(O1) < precedence(O2)) OR 
                       (precedence(O1) = precedence(O2) AND O2 has left-right associativity)))
             1.3.1.1 extract O2 and add it to the postfix form
         1.3.2 add O1 to the stack
     1.4 if the entity is a left-parenthesis, then:
         1.4.1 add the parenthesis to the stack
     1.5 if the entity is a right-paranthesis, then:
         1.5.1 while the operator at the top of the stack (be it O) is not a left-parenthesis:
             1.5.1.1 extract O and add it to the postfix form
         1.5.2 if the stack is empty (and a left-parenthesis was not found)
             1.5.2.1 return error (i.e. expression had wrong parentheses)
         1.5.3 pop the left-parenthesis from the top of the stack
//no more entities to read at this point
2. as long as there is an operator on the stack (be it O)
  2.1. extract O and add it to the postfix form
  2.2. if O is a left-paranthesis, then:
    2.2.1. return error (i.e. expression had wrong parantheses)
3. display/return the postfix form
```
 
## Evaluation of postfix expression
The algorithm uses a stack to perform the operations in the correct order.

**Postfix expression evaluation algorithm pseudocode**  
```
1. while there is an entity to read
  1.1. read the entity (i. e. operand or operator)
  1.2. if the entity is an operand (i.e. number) then:
    1.2.1 add the entity to the stack
  1.3. if the entity is an operator (be it O) then:
    1.3.1. pull an operand from the stack (be it O1)
    1.3.2. pull an operand from the stack (be it O2)
    1.3.3. if there are no two operands (i.e. O1 and O2):
      1.3.3.1. return error (postfix expression is wrong)
    1.3.4. result = O2 O O1
    1.3.5. add result to the stack
2. pop the result from the stack
3. if stack is not empty
  3.1. return error (postfix expression is wrong)
4. display/return result  
```

## Examples of running the application:

### Example 1
**Infix Expression: 12 + 6 - 37 * 3 * (2 - 4)**  

**Postfix Expression: 12 6 + 37 3 * 2 4 - * -** 

**Expression evaluation: 240**

### Example 2
**Infix Expression: 32 + (2 + 1) * 2 ^ 3 ^ 2 - 81 / (5 - 1 * 20 / 2)**  

**Postfix Expression: 32 2 1 + 2 3 2 ^ ^ * + 81 5 1 20 * 2 / - / -** 

**Expression evaluation: 1584**
