//*******************************************************
//DO NOT MODIFY THIS FILE!!!
//*******************************************************

package callCenterTests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import callCenter.CallCenter;

public class Tests
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
		System.setOut(System.out);
		System.setErr(System.err);
		System.setIn(System.in);
	}
	
	@Test
	public void testSample()
	{	
		File expected = new File("sampleOutput.txt");
		String inputString = "sampleInput.txt\n";
		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);
		
		try {
			CallCenter.main(null);
		} catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		verifyOutput(expected, "Test Sample");
	}
	
	@Test
	public void testCustomerOrderAnswered()
	{
		File expected = new File("orderAnsweredOutput.txt");
		String inputString = "orderAnsweredInput.txt\n";
		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);
		
		try {
			CallCenter.main(null);
		} catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		verifyOutput(expected, "Test Customer Order Answered");
	}
	
	@Test
	public void testCustomerOrderAnswered_DifferentTick()
	{
		File expected = new File("orderAnsweredDifferentTickOutput.txt");
		String inputString = "orderAnsweredDifferentTickInput.txt\n";
		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);
		
		try {
			CallCenter.main(null);
		} catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		verifyOutput(expected, "Test Customer Order Answered - Different Tick");
	}
	
	@Test
	public void testNewCallInMiddleOfService()
	{
		File expected = new File("newCallInMiddleOfServiceOutput.txt");
		String inputString = "newCallInMiddleOfServiceInput.txt\n";
		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);
		
		try {
			CallCenter.main(null);
		} catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		verifyOutput(expected, "Test New Call In Middle Of Service");
	}
	
	@Test
	public void testInputContainsMoreEvents()
	{
		File expected = new File("moreEventsOutput.txt");
		String inputString = "moreEventsInput.txt\n";
		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);
		
		try {
			CallCenter.main(null);
		} catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		verifyOutput(expected, "Test Input Contains More Events Than Declared");
	}
	
	@Test
	public void testFirstCallLate()
	{
		File expected = new File("firstCallLateOutput.txt");
		String inputString = "firstCallLateInput.txt\n";
		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);
		
		try {
			CallCenter.main(null);
		} catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		verifyOutput(expected, "Test First Call Late");
	}
	
	@Test
	public void testCustomersSameName()
	{
		File expected = new File("customersSameNameOutput.txt");
		String inputString = "customersSameNameInput.txt\n";
		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);
		
		try {
			CallCenter.main(null);
		} catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		verifyOutput(expected, "Test Customers Same Name");
	}
	
	/**
	 * Helper method that gets a string representation
	 * of the expected output file
	 */
	private String getExpectedString(File expectedFile)
	{
		String expected = "";
		try {
			Scanner file = new Scanner(expectedFile);
			
			while(file.hasNextLine())
			{
				expected += file.nextLine() + "\n";
			}			
			
			file.close();
		}
		catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException - Did you delete the expected output file? (" + expectedFile.getName() + ")");
		}
		
		return expected;
	}
	
	/**
	 * Helper method to verify the output matches the expected
	 */
	private void verifyOutput(File expectedFile, String message)
	{		
		String actual = outContent.toString().replaceAll("\r", "");
		String expected = getExpectedString(expectedFile);
		
		assertEquals("Output for '" + message + "' didn't match the expected", expected, actual);
	}
}
