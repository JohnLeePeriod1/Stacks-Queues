package callCenter;

import java.io.FileNotFoundException;
import java.util.*;
import java.io.*;

public class CallCenter
{
	
	public static void main(String[] args) throws FileNotFoundException
	{
		CallerList list = new CallerList();
		Scanner in = new Scanner(System.in);
		System.out.print("Enter input file: ");
		File file = new File(in.nextLine());
		Scanner fileScan = new Scanner(file);
		int eventNum = Integer.parseInt(fileScan.nextLine());
		int eventCount = 0;
		while (fileScan.hasNextLine() && eventCount < eventNum){
			RealCustomer x = new RealCustomer(fileScan.nextLine());
			list.addCustomer(x);
			eventCount++;
		}
		
		while (!list.allCustomersHandled()){
			list.handleTick();
		}
		
	}
	
}
