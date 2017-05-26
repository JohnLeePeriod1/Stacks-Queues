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
    
	@Before
    public void setup() {
            System.setOut(new PrintStream(outContent));
            System.setErr(new PrintStream(errContent));
    }
	
	@Test
	public void testSample() {
		
		File expected = new File("sampleOutput.txt");
		String inputString = "sampleInput.txt\n";
		ByteArrayInputStream input = new ByteArrayInputStream(inputString.getBytes());
		System.setIn(input);
		
		try {
			CallCenter.main(null);
		} catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		verifyOutput(expected);
	}
	
	@After
    public void cleanUpStreams() {
        System.setOut(null);
        System.setErr(null);
        System.setIn(System.in);
    }
	
	private String getExpectedString(File expectedFile)
	{
		String expected = "";
		try {
			Scanner file = new Scanner(expectedFile);
			
			while(file.hasNextLine())
			{
				expected += file.nextLine();
				expected += "\r\n";
			}			
			
			file.close();
		}
		catch (FileNotFoundException e) {
			fail("Caught a FileNotFoundException");
		}
		
		return expected;
	}
	
	private void verifyOutput(File expectedFile)
	{		
		String actual = outContent.toString();
		String expected = getExpectedString(expectedFile);
			
		if(!actual.equals(expected))
		{
			fail("Output doesn't match the expected!");
		}
	}
}
