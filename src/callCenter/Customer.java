//*******************************************************
// DO NOT MODIFY THIS FILE!!!
//*******************************************************

package callCenter;

public interface Customer
{
	public String name();	// The name of the customer
	public String status();	// The status of the customer (none, silver, gold, platinum)
	public int timestamp();	// The tick the customers call comes in at
	public int duration();	// The duration the customers call takes
}
