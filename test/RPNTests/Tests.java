//*******************************************************
//DO NOT MODIFY THIS FILE!!!
//*******************************************************
package RPNTests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import static org.junit.Assert.*;

import RPNCalculator.Calculator;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    Tests.CommonOperators.class,
    Tests.MathOperators.class,
    Tests.OtherOperators.class,
})

public class Tests 
{
	public static class CommonOperators
    	{
		private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	    
		/**
		 * Set the Standard output and error streams to
		 * send to our private streams instead of the
		 * console before each test runs
		 */
		@Before
		public void setup()
		{
			System.setOut(new PrintStream(outContent));
			System.setErr(new PrintStream(errContent));
		}
		
	    /**
	     * Reset the Standard output, error, and input
	     * streams back to their default (Console and
	     * System.in) after each test runs
	     */
		@After
		public void cleanUpStreams()
		{
			System.setOut(null);
			System.setErr(null);
			System.setIn(System.in);
			}
		
		@Test
		public void testInputNumbers_ValidPositiveIntegers()
		{
			// Small Integers
			System.setIn(new ByteArrayInputStream("39\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// Medium Integers
			System.setIn(new ByteArrayInputStream("123456\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// Large Integers
			System.setIn(new ByteArrayInputStream("1234567890\np\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("39\n123456\n1234567890\n", outContent);
		}
		
		@Test
		public void testInputNumbers_ValidNegativeIntegers()
		{
			// Small Integers
			System.setIn(new ByteArrayInputStream("-22\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// Medium Integers
			System.setIn(new ByteArrayInputStream("-98765\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// Large Integers
			System.setIn(new ByteArrayInputStream("-1234567890\np\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("-22\n-98765\n-1234567890\n", outContent);
		}
		
		@Test
		public void testInputNumbers_ValidPositiveDoubles()
		{
			// No whole part
			System.setIn(new ByteArrayInputStream(".89\np\nq\n".getBytes()));
			Calculator.main(null);
					
			// Zero whole part
			System.setIn(new ByteArrayInputStream("0.723\np\nq\n".getBytes()));
			Calculator.main(null);
					
			// Non-zero whole part
			System.setIn(new ByteArrayInputStream("5.028\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// No decimal
			System.setIn(new ByteArrayInputStream("29574.\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// Zero decimal
			System.setIn(new ByteArrayInputStream("93.0\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// Non-zero decimal ends in zero
			System.setIn(new ByteArrayInputStream("3.730920\np\nq\n".getBytes()));
			Calculator.main(null);
			
			validateOutput("0.89\n0.723\n5.028\n29574\n93\n3.73092\n", outContent);
		}
		
		@Test
		public void testInputNumbers_ValidNegativeDoubles()
		{
			// No whole part
			System.setIn(new ByteArrayInputStream("-.28\np\nq\n".getBytes()));
			Calculator.main(null);
					
			// Zero whole part
			System.setIn(new ByteArrayInputStream("-0.028\np\nq\n".getBytes()));
			Calculator.main(null);
					
			// Non-zero whole part
			System.setIn(new ByteArrayInputStream("-63.988\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// No decimal
			System.setIn(new ByteArrayInputStream("-7832.\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// Zero decimal
			System.setIn(new ByteArrayInputStream("-56.0\np\nq\n".getBytes()));
			Calculator.main(null);
			
			// Non-zero decimal ends in zero
			System.setIn(new ByteArrayInputStream("-13.24620\np\nq\n".getBytes()));
			Calculator.main(null);
			
			validateOutput("-0.28\n-0.028\n-63.988\n-7832\n-56\n-13.2462\n", outContent);
		}
		
		@Test
		public void testInputNumbers_InvalidInput()
		{
			// Single character (not a command)
			System.setIn(new ByteArrayInputStream("o\nq\n".getBytes()));
			Calculator.main(null);		
			
			// Text only
			System.setIn(new ByteArrayInputStream("hello\nq\n".getBytes()));
			Calculator.main(null);
					
			// Text in middle
			System.setIn(new ByteArrayInputStream("57test91\nq\n".getBytes()));
			Calculator.main(null);
					
			// Text at beginning
			System.setIn(new ByteArrayInputStream("bad72.9\nq\n".getBytes()));
			Calculator.main(null);
			
			// Text at end
			System.setIn(new ByteArrayInputStream("385.42input\nq\n".getBytes()));
			Calculator.main(null);
			
			validateOutput("Bad input\nBad input\nBad input\nBad input\nBad input\n", outContent);
		}
	
		@Test
		public void testPrint_LeavesStackUnchanged()
		{
			System.setIn(new ByteArrayInputStream("10\np\np\np\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("10\n10\n10\n", outContent);
		}
		
		@Test
		public void testPrint_NotEnoughOperands()
		{
			System.setIn(new ByteArrayInputStream("p\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Not enough operands\n", outContent);
		}
		
		@Test
		public void testPrintAll()
		{
			System.setIn(new ByteArrayInputStream("8.25\n.28\n02.63\n278\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("278 2.63 0.28 8.25\n", outContent);
		}
		
		@Test
		public void testPrintAll_LeavesStackUnchanged()
		{
			System.setIn(new ByteArrayInputStream("981\n46.2\n7.0\n7.24\na\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("7.24 7 46.2 981\n7.24 7 46.2 981\n", outContent);
		}
		
		@Test
		public void testPrintAll_NoOperands()
		{
			System.setIn(new ByteArrayInputStream("a\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("\n", outContent);
		}
    	}
	
	public static class MathOperators
	{
		private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	    
		/**
		 * Set the Standard output and error streams to
		 * send to our private streams instead of the
		 * console before each test runs
		 */
		@Before
		public void setup()
		{
			System.setOut(new PrintStream(outContent));
			System.setErr(new PrintStream(errContent));
		}
		
	    /**
	     * Reset the Standard output, error, and input
	     * streams back to their default (Console and
	     * System.in) after each test runs
	     */
		@After
		public void cleanUpStreams()
		{
			System.setOut(null);
			System.setErr(null);
			System.setIn(System.in);
			}
		
		@Test
		public void testAdd_TwoPositive()
		{
			System.setIn(new ByteArrayInputStream("78\n6.15\na\n+\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("6.15 78\n84.15\n", outContent);
		}
		
		@Test
		public void testAdd_TwoNegative()
		{
			System.setIn(new ByteArrayInputStream("-.64\n-5.32\na\n+\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("-5.32 -0.64\n-5.96\n", outContent);
		}
		
		@Test
		public void testAdd_PositiveAndNegative()
		{
			System.setIn(new ByteArrayInputStream("55\n-82\na\n+\na\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream("-0.53\n2.46\na\n+\na\nq\n".getBytes()));
			Calculator.main(null);
			
			validateOutput("-82 55\n-27\n2.46 -0.53\n1.93\n", outContent);
		}
		
		@Test
		public void testAdd_NotEnoughOperands()
		{
			System.setIn(new ByteArrayInputStream("+\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream("4\n+\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Not enough operands\nNot enough operands\n", outContent);
		}
		
		@Test
		public void testSubtract_TwoPositive()
		{
			System.setIn(new ByteArrayInputStream("78\n6.15\na\n-\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("6.15 78\n71.85\n", outContent);
		}
		
		@Test
		public void testSubtract_TwoNegative()
		{
			System.setIn(new ByteArrayInputStream("-.64\n-5.3\na\n-\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("-5.3 -0.64\n4.66\n", outContent);
		}
		
		@Test
		public void testSubtract_PositiveAndNegative()
		{
			System.setIn(new ByteArrayInputStream("55\n-82\na\n-\na\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream("-0.53\n2.46\na\n-\na\nq\n".getBytes()));
			Calculator.main(null);
			
			validateOutput("-82 55\n137\n2.46 -0.53\n-2.99\n", outContent);
		}
		
		@Test
		public void testSubtract_NotEnoughOperands()
		{
			System.setIn(new ByteArrayInputStream("-\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream(".6\n-\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Not enough operands\nNot enough operands\n", outContent);
		}
		
		@Test
		public void testMultiply_TwoPositive()
		{
			System.setIn(new ByteArrayInputStream("78\n6\na\n*\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("6 78\n468\n", outContent);
		}
		
		@Test
		public void testMultiply_TwoNegative()
		{
			System.setIn(new ByteArrayInputStream("-83\n-5\na\n*\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("-5 -83\n415\n", outContent);
		}
		
		@Test
		public void testMultiply_PositiveAndNegative()
		{
			System.setIn(new ByteArrayInputStream("55\n-82\na\n*\na\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream("-0.53\n2.46\na\n*\na\nq\n".getBytes()));
			Calculator.main(null);
			
			validateOutput("-82 55\n-4510\n2.46 -0.53\n-1.3038\n", outContent);
		}
		
		@Test
		public void testMultiply_NotEnoughOperands()
		{
			System.setIn(new ByteArrayInputStream("*\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream("4\n*\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Not enough operands\nNot enough operands\n", outContent);
		}

		@Test
		public void testDivide_TwoPositive()
		{
			System.setIn(new ByteArrayInputStream("78\n6\na\n/\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("6 78\n13\n", outContent);
		}
		
		@Test
		public void testDivide_TwoNegative()
		{
			System.setIn(new ByteArrayInputStream("-3\n-6\na\n/\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("-6 -3\n0.5\n", outContent);
		}
		
		@Test
		public void testDivide_PositiveAndNegative()
		{
			System.setIn(new ByteArrayInputStream("55\n-5\na\n/\na\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream("-0.5\n2.5\na\n/\na\nq\n".getBytes()));
			Calculator.main(null);
			
			validateOutput("-5 55\n-11\n2.5 -0.5\n-0.2\n", outContent);
		}
		
		@Test
		public void testDivide_NotEnoughOperands()
		{
			System.setIn(new ByteArrayInputStream("/\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream("4\n/\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Not enough operands\nNot enough operands\n", outContent);
		}
		
		@Test
		public void testDivide_DivideByZero()
		{
			System.setIn(new ByteArrayInputStream("4\n0\n/\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Divide by zero\n", outContent);
		}
	}
	
	public static class OtherOperators
	{
		private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
		private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
	    
		/**
		 * Set the Standard output and error streams to
		 * send to our private streams instead of the
		 * console before each test runs
		 */
		@Before
		public void setup()
		{
			System.setOut(new PrintStream(outContent));
			System.setErr(new PrintStream(errContent));
		}
		
	    /**
	     * Reset the Standard output, error, and input
	     * streams back to their default (Console and
	     * System.in) after each test runs
	     */
		@After
		public void cleanUpStreams()
		{
			System.setOut(null);
			System.setErr(null);
			System.setIn(System.in);
		}

		@Test
		public void testNegate_OneItemPositive()
		{
			System.setIn(new ByteArrayInputStream("93.67\na\nn\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("93.67\n-93.67\n", outContent);
		}

		@Test
		public void testNegate_OneItemNegative()
		{
			System.setIn(new ByteArrayInputStream("-0.53\na\nn\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("-0.53\n0.53\n", outContent);
		}
		
		@Test
		public void testNegate_MoreThanOneItem()
		{
			System.setIn(new ByteArrayInputStream("95\n-073\na\nn\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("-73 95\n73 95\n", outContent);
		}
		
		@Test
		public void testNegate_NotEnoughOperands()
		{
			System.setIn(new ByteArrayInputStream("n\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Not enough operands\n", outContent);
		}
		
		@Test
		public void testDuplicate_OneItem()
		{
			System.setIn(new ByteArrayInputStream("724\na\nd\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("724\n724 724\n", outContent);
		}
		
		@Test
		public void testDuplicate_MoreThanOneItem()
		{
			System.setIn(new ByteArrayInputStream("-.42\n-56.26\na\nd\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("-56.26 -0.42\n-56.26 -56.26 -0.42\n", outContent);
		}
		
		@Test
		public void testDuplicate_NotEnoughOperands()
		{
			System.setIn(new ByteArrayInputStream("d\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Not enough operands\n", outContent);
		}
		
		@Test
		public void testReverse_TwoItems()
		{
			System.setIn(new ByteArrayInputStream("2\n6.250\na\nr\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("6.25 2\n2 6.25\n", outContent);
		}
		
		@Test
		public void testReverse_MoreThanTwoItems()
		{
			System.setIn(new ByteArrayInputStream("7.3\n901\n8.52\n536.\na\nr\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("536 8.52 901 7.3\n8.52 536 901 7.3\n", outContent);
		}
		
		@Test
		public void testReverse_NotEnoughOperands()
		{
			System.setIn(new ByteArrayInputStream("r\nq\n".getBytes()));
			Calculator.main(null);
			
			System.setIn(new ByteArrayInputStream("2\nr\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("Not enough operands\nNot enough operands\n", outContent);
		}
		
		@Test
		public void testClear_OneItem()
		{
			System.setIn(new ByteArrayInputStream("462\na\nc\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("462\n\n", outContent);
		}
		
		@Test
		public void testClear_MultipleItems()
		{
			System.setIn(new ByteArrayInputStream("-63\n90.30\n-.753\n0.348\na\nc\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("0.348 -0.753 90.3 -63\n\n", outContent);
		}
		
		@Test
		public void testClear_NoOperands()
		{
			System.setIn(new ByteArrayInputStream("c\na\nq\n".getBytes()));
			Calculator.main(null);
			validateOutput("\n", outContent);
		}
	}
	
	/**
	 * Helper method to validate what was sent to the
	 * Standard output is what was expected
	 */
	private static void validateOutput(String expected, ByteArrayOutputStream outContent)
	{
		assertEquals(expected, outContent.toString().replaceAll("\r", ""));
	}
}
