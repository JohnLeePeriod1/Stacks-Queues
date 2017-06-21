package callCenter;

import java.util.LinkedList;
import java.util.Queue;

public class CallerList implements Queues {

	private Queue<RealCustomer> Pravat = new LinkedList<RealCustomer>();
	private Queue<RealCustomer> platinum = new LinkedList<RealCustomer>();
	private Queue<RealCustomer> gold = new LinkedList<RealCustomer>();
	private Queue<RealCustomer> silver = new LinkedList<RealCustomer>();
	private Queue<RealCustomer> none =  new LinkedList<RealCustomer>();
	private int tick = 0;
	private boolean done;
	private boolean answering;
	private int callStart;
	private RealCustomer Michael;
	
	public CallerList() {
	}
	
	public void addCustomer(RealCustomer x){
		Pravat.add(x);
		if (x.status().equals("platinum"))
			platinum.add(x);
		else if (x.status().equals("gold"))
			gold.add(x);
		else if (x.status().equals("silver"))
			silver.add(x);
		else if (x.status().equals("none"))
			none.add(x);
	}

	@Override
	public void handleTick() {
		System.out.println("Starting tick #" + tick);
		printCalls();
		if (!platinum.isEmpty() || !gold.isEmpty() || !silver.isEmpty() || !none.isEmpty()){
			if (!platinum.isEmpty() && !answering && tick >= platinum.peek().timestamp()){
				callStart = tick;
				Michael = platinum.remove();
				System.out.println("Answering call from " + Michael.name());
				answering = true;
			}
			else if (!gold.isEmpty() && !answering && tick >= gold.peek().timestamp()){
				callStart = tick;
				Michael = gold.remove();
				System.out.println("Answering call from " + Michael.name());
				answering = true;
			}
			
			else if (!silver.isEmpty() && !answering && tick >= silver.peek().timestamp()){
				callStart = tick;
				Michael = silver.remove();
				System.out.println("Answering call from " + Michael.name());
				answering = true;
			}
			
			else if (!none.isEmpty() && !answering && tick >= none.peek().timestamp()){
				callStart = tick;
				Michael = none.remove();
				System.out.println("Answering call from " + Michael.name());
				answering = true;
			}			
		}

		if (answering){
			if (tick - callStart + 1 == Michael.duration())
					answering = false;
		}
		
		else if (platinum.isEmpty() && gold.isEmpty() && silver.isEmpty() && none.isEmpty()) {
			done = true;
		}
		
		
		tick++;
	}

	@Override
	public Boolean allCustomersHandled() {
		return done;
	}
	
	private void printCalls(){
		RealCustomer temp;
		for (int i = 0; i < Pravat.size(); i++){
			temp = Pravat.remove();
			if (tick == temp.timestamp()){
				String status = temp.status();
				if (status.equals("none"))
					status = "regular";
				System.out.println("Call from " + temp.name() + " a " + status + " member");
			}
			Pravat.add(temp);
		}
		
	}

}
