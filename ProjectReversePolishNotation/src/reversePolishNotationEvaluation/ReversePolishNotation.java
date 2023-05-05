package reversePolishNotationEvaluation;

import java.util.Deque;
import java.util.ArrayDeque;

public class ReversePolishNotation {
	
	private static boolean isOperator(char c) {
		return c=='+' || c=='-' || c=='*' || c=='/' || c=='^';
	}
	
	private static boolean isParanthesis(char c) {
		return c=='(' || c==')';
	}
	
	private static boolean isDigit(char c) {
		return Character.isDigit(c);
	}
	
	private static int getPrecedence(char c) {
		if(c=='+' || c=='-') {
			return 11;
		} else if(c=='*' || c=='/'){
			return 12;
		} else if(c=='^'){
			return 13;
		} else {
			return -1;
		}
	}
	
	private static String getAssociativity(char c) {
		if(c=='+' || c=='-' || c=='*' || c=='/') {
			return "left-right";
		} else if(c=='^') {
			return "right-left";
		} else {
			return "no associativity";
		}
	}
	
	private static Integer mathOperation(int op1, int op2, char op) {
		switch(op) {
		case '+':
			return op1+op2;
		case '-':
			return op1-op2;
		case '*':
			return op1*op2;
		case '/':
			return op1/op2;
		case '^':
			return (int)Math.pow(op1, op2);
		default:
			System.out.print("error. invalid operator.");
			return null;
		}
	}
	
	public static String reversePolishNotation(String expr) {
		if(expr==null || expr.isBlank()) {
			return null;
		}
		Deque<Character> stack=new ArrayDeque<>();
		StringBuilder sb=new StringBuilder();
		StringBuilder tempNb=new StringBuilder();
		for(int i=0; i<expr.length(); i++) {
			char c=expr.charAt(i);
			if(c==' ') {
				continue;
			} else if(isDigit(c)) {
				while(isDigit(c)) {
					tempNb.append(c);
					i++;
					if(i<expr.length()) {
						c=expr.charAt(i);
					} else {
						break;
					}
				}
				i--;
				sb.append(tempNb).append(" ");
				tempNb.delete(0, tempNb.length());
			} else if(isOperator(c)) {
				while((!stack.isEmpty()) 
						&& (isOperator(stack.peek())) 
						&& (getPrecedence(c)<getPrecedence(stack.peek()) 
								|| (getPrecedence(c)==getPrecedence(stack.peek()) 
								&& getAssociativity(stack.peek()).equals("left-right")))) {
					sb.append(stack.pop()).append(" ");
				}
				stack.push(c);	
			} else if(isParanthesis(c)) {
				if(c=='(') {
					stack.push(c);
				} else {
					while(!stack.isEmpty() && stack.peek()!='(') {
						sb.append(stack.pop()).append(" ");
					}
					if(stack.isEmpty()) {
						return "error. expression invalid.";
					} else {
						stack.pop();
					}
				}
			} else {
				return "error. expression invalid.";
			}
		}
		while(!stack.isEmpty()) {
			if(stack.peek()=='(') {
				return "error. expression invalid."; 
			} else {
				sb.append(stack.pop()).append(" ");
			}
		}
		return sb.toString();
	}
	
	public static Integer postFixEvaluation(String expr) {
		if(expr==null || expr.isBlank()) {
			return null;
		}
		int res;
		Deque<Integer> stack=new ArrayDeque<>();
		for(int i=0; i<expr.length(); i++) {
			char c=expr.charAt(i);
			if(c==' ') {
				continue;
			} else if(isDigit(c)) {
				int beginIdx=i;
				while(isDigit(expr.charAt(i))) {
					i++;
				}
				int endIdx=i;
				i--;
				int tempNb=Integer.valueOf(expr.substring(beginIdx, endIdx));
				stack.push(tempNb);
			} else {
				if(stack.size()<2) {
					System.out.println("error. expression invalid.");
					return null;
				}
				int op2=stack.pop();
				int op1=stack.pop();
				int crtRes=mathOperation(op1, op2, c);
				stack.push(crtRes);
			}		
		}
		if(stack.isEmpty()) {
			System.out.println("error. expression invalid.");
			return null;
		} else {
			res=stack.pop();
		}
		if(!stack.isEmpty()) {
			System.out.println("error. expression invalid.");
			return null;
		} else {
			return res;
		}
	}

	public static void main(String[] args) {
		String inFixExpr="32+(2+1)*2^3^2-81/(5-1*20/2)";
		System.out.println("Infix Expression: " + inFixExpr);
		String postFixExpr=reversePolishNotation(inFixExpr);
		System.out.println("Postfix Expression: " + postFixExpr);
		int result=postFixEvaluation(postFixExpr);
		System.out.println("Expression evaluation: " + result);
		
	}
}
