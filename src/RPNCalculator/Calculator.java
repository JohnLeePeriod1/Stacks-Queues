package RPNCalculator;
import java.util.Stack;
import java.util.*;
import java.io.*;


public class Calculator
{	
	
	public static void main(String[] args)
	{
		Stack<Double> input = new Stack<Double>();
		Scanner console = new Scanner(System.in);
	    String in = console.nextLine();
	    while (!in.equals("q")){
	    	boolean isNum = isNumeric(in);
	    	if (isNum == true){
	    		input.push(Double.parseDouble(in));
	    	}
	    	else {
	    		if (in.length() > 1){
	    			System.out.println("Bad input");
	    			return;
	    		}
	    		int charVal = in.charAt(0);
	    		if (charVal > 96 && charVal < 123 ){
	    			if (in.equals("c")){
	    				while (!input.isEmpty()){
	    					input.pop();
	    				}
	    			}
	    			else if (in.equals("d")){
	    				if (input.size() >= 1){
	    					double temp = input.pop();
	    					for (int i = 0; i < 2; i++){
	    					input.push(temp);
	    					}
	    				}
	    				else {
	    					System.out.println("Not enough operands");
	    				}
	    			}
	    			else if (in.equals("n")){
	    				if (input.size() > 0){
	    					input.push(input.pop() * -1);
	    				}
	    				else {
	    					System.out.println("Not enough operands");
	    				}
	    			}
	    			else if (in.equals("r")){
	    				if (input.size() < 2){
	    					System.out.println("Not enough operands");
	    				}
	    				else {
	    					double num1 = input.pop();
	    					double num2 = input.pop();
	    					input.push(num1);
	    					input.push(num2);
	    				}
	    			}
	    			else if (in.equals("a")){
	    				Stack<Double> tempStorage = new Stack<Double>();
	    				while (!input.isEmpty()){
	    					int temp = (int) (input.peek().doubleValue());
	    					if (temp == input.peek())
	    						System.out.print(temp);
	    					else{
	    						System.out.print(input.peek());
	    					}
	    					if (input.size() != 1)
	    						System.out.print(" ");
	    					tempStorage.push(input.pop());
	    				}
	    				while(!tempStorage.isEmpty()){
	    					input.push(tempStorage.pop());
	    				}
	    				System.out.println();
	    			}
	    			//Travis Test
	    			else if (in.equals("p")){
	    				if (input.size() < 1){
	    					System.out.println("Not enough operands");
	    				}
	    				else {
	    					int tempe = (int) (input.peek().doubleValue());
	    					if (tempe == input.peek())
	    						System.out.println(tempe);
	    					else {
	    						System.out.println(input.peek());
	    					}
	    				}
	    			}
	    			else {
	    				System.out.println("Bad input");
	    			}
	    			
	    		}
	    		else {
	    			if (in.equals("+")){
	    				if (input.size() < 2)
	    					System.out.println("Not enough operands");
	    				else {
	    					double numOne = input.pop();
	    					double numTwo = input.pop();
	    					input.push(numOne + numTwo);
	    				}
	    			}
	    			else if (in.equals("-")){
	    				if (input.size() < 2)
	    					System.out.println("Not enough operands");
	    				else {
	    					double firstPop = input.pop();
	    					double secondPop = input.pop();
	    					input.push(secondPop - firstPop);
	    				}
	    			}
	    			else if (in.equals("*")){
	    				if (input.size() < 2)
	    					System.out.println("Not enough operands");
	    				else {
	    					double unoPop = input.pop();
	    					double dosPop = input.pop();
	    					input.push(unoPop * dosPop);
	    				}
	    			}
	    			else if (in.equals("/")){
	    				if (input.size() < 2)
	    					System.out.println("Not enough operands");
	    				else {
	    					double pop1 = input.pop();
	    					double pop2 = input.pop();
	    					if (pop1 != 0)
	    						input.push(pop2/pop1);
	    					else {
	    						System.out.println("Divide by zero");
	    					}
	    				}
	    			}
	    			else {
	    				System.out.println("Bad input");
	    			}
	    		}
	    	}
	    	
	    	in = console.nextLine();
	    }
	}
	
	public static boolean isNumeric(String input){
		boolean isNumeric;
		try {
			double temporary = Double.parseDouble(input);
			isNumeric = true;
		}
		catch (NumberFormatException x){
			isNumeric = false;
		}
		return isNumeric;
	}
}