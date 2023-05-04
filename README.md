# Expression Evaluator

**The project consists of a mathematical expression evaluator, which can perform any calculations, no matter how complex, taking into account the correct evaluation of the order of operations.**

**The programme consists of two steps:**
- **it takes the infix notation of a mathematic expression and converts it to its postfix (or reverse polish) notation;**
- **it evaluates the postfix notation, providing the result of the mathematical expression.**

## Infix, Postfix and Prefix notations
Represent three different ways of writing equivalent mathematical expressions.

- **Infix**: 3 + 4
- **Postfix**: 3 4 +
- **Prefix**: + 3 4

### Infix Notation
The infix notation is the usual, common way we write mathematical expresions. In infix notation the operators are placed between operands and parentheses surrounding groups of operands and operators are necessary to indicate the intended order in which operations are to be performed. In the absence of parentheses, certain precedence rules determine the order of operations.

### Prefix Notation
Prefix notation, also known as Polish notation (PN) is a mathematical notation in which operators precede their operands. The prefix notation was invented in 1924 and the description of "Polish" refers to the nationality of its inventor (Polish logician Jan Łukasiewicz). 

### Postfix Notation
Postfix Notation, or Reverse Polish notation (RPN) is a mathematical notation in which operators follow their operands. Both prefix and postfix notations eliminate the need for paratheses as long as each operator has a fixed number of operands.

## Converting from Infix to Postfix notation
### Shunting yard algorithm
Shunting yard algorithm was invented by Edsger Dijkstra and it is one of the most famous algorithm for converting expressions from infix form to postfix form.

Shunting yard algorithm pseudocode

```
1. as long as there is an entity to read:  
     1.1 read the entity (i.e. operand or operator)
     1.2. if the entity is an operand (i.e. a number), then:
         1.2.1 add it to the postfixed form
     1.3 if the entity is an operator (be it O1), then:
         1.3.1 while ((there is an operator at the top of the stack (be it O2)) AND
                       ((precedence(O1) < precedence(O2)) OR (precedence(O1) = precedence(O2) AND O2 has left-associativity)))
             1.3.1.1 extract O2 and add it to the postfixed form
         1.3.2 add O1 to the stack
     1.4 if the entity is a left-parenthesis, then:
         1.4.1 add the parenthesis to the stack
     1.5 if the entity is a right-paranthesis, then:
         1.5.1 as long as the operator at the top of the stack (be it O) is not a left-parenthesis:
             1.5.1.1 extract O and add it to the postfixed form
         1.5.2 if the stack is empty (and a left-parenthesis was not found)
             1.5.2.1 return error (i.e. expression had wrong parentheses)
         1.5.3 pop the left-parenthesis from the top of the stack
no more entities to read at this point
2. as long as there is an operator on the stack (be it O)
  2.1. extract O and add it to the postfixed form
  2.2. if O is a left-paranthesis, then:
    2.2.1. return error (i.e. expression had wrong parantheses)
3. display/return the postfixed form
```




