package callCenter;

public class RealCustomer implements Customer {

	private String name;
	private String status;
	private int timeStamp;
	private int duration;
	public RealCustomer(String line) {
		String[] vals = line.split(" ");
		this.timeStamp = Integer.parseInt(vals[0]);
		this.name = vals[1];
		this.status = vals[2];
		this.duration = Integer.parseInt(vals[3]);
	}

	@Override
	public String name() {
		return this.name;
	}

	@Override
	public String status() {
		return this.status;
	}

	@Override
	public int timestamp() {
		return this.timeStamp;
	}

	@Override
	public int duration() {
		return this.duration;
	}

}
