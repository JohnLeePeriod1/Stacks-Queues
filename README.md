# Stacks and Queues and Lists, Oh My!

This project will give you experience using Stack, and Queues, to implement two simple applications.

## RPN Calculator

The first of these applications is a simple Reverse-Polish Notation calculator.  An RPN calculator is one in which the operators appear *after* their respective operands, rather than in between them.  So, instead of computing the following:

  (2 + 3) * 5

an RPN calculator would compute this equivalent expression:

   2 3 + 5 *

RPN notation is convenient for several reasons.  First, no parentheses are necessary---the computation is always unambiguous.  Second, such a calculator is easy to implement given a stack.  

The calculator is invoked with no arguments, and starts out with an empty stack.  It takes its input from the standard input stream, and writes its output to the standard output stream.  Here are the commands your calculator must respond to:

    <number>  push the number onto the stack.  Example: 3, -2, 4.56,
              0.12, 1., and .79 are all numbers, but abc, -3c, .54r, 
              and k6w are not. Always valid.

    +         pop the top two numbers from the stack, add them
              together, and push the result.  Requires a stack with
              at least two operands.

    -         pop the top two numbers from the stack, subtract the
              first popped number from the second, and push the
              result.  Requires a stack with at least two operands.

    *         pop the top two numbers from the stack, multiply them
              together, and push the result.  Requires a stack with
              at least two operands.

    /         pop the top two numbers from the stack, divide the
              second popped number by the first (i.e. second/first), 
              and push the result.  Requires a stack with at least 
              two operands.

    n         negate: pop the top item, multiply it by -1, and push
              the result on the stack.  Requires a stack with at
              least one operand.

    d         duplicate: pop the top item and push two copies of it.
              Requires a stack with at least one operand.

    r         reverse: pop the top two items, push the first popped
              item, then the second.  Requires a stack with at least
              two operands.

    p         print: print the top item to standard output, followed
              by a newline.  Requires a stack with at least one
              operand.  Leaves the stack unchanged.

    c         clear: pop all items from the stack.  Always valid.

    a         print-all: print all items from the stack in one line,
              from top-most to bottom-most, each separated by a
              single space, followed by a newline.  Always valid.
              Leaves the stack unchanged.

    q         quit: exit the calculator.  Always valid.


Each command is separated by whitespace. You may not assume that user input is always correct.  There are three error messages to report.

   1) If a user enters something other than one of the commands above,
      leave the stack unchanged, advance to the next whitespace
      character, and print the following message:

	      "Bad input"

   2) If a user enters a command that requires more operands than are
      present, leave the stack unchanged, advance to the next
      whitespace character, and print the following message:

	      "Not enough operands"

   3) If a user enters the divide command with a zero on the top of
      the stack, leave the stack unchanged, advance to the next
      whitespace character, and print the following message:

	      "Divide by zero"

Note that the phrase "leave the stack unchanged" is not to be taken literally.  It is okay to pop the top two operands for division; if
the divisor is zero, be sure push them back before reading the next command. Here is a short example:

    2
    3
    4
    +
    *
    p
    14
    +
    Not enough operands
    d
    +
    p
    28
    q

## Call Center Simulation

The second application is a simple discrete-event simulator, modeling the behavior of a single reservation agent at Northwest Airlines. When a customer calls Northwest, they are asked to enter their WorldPerks number.  Calls are then answered in priority order: customers who are Platinum Elite---those having flown 75,000 miles or more in the current or previous calendar year---have their calls answered first, followed by Gold (50,000), Silver (25,000), and finally "regular" customers.

(Now you know why you can never reach a reservation agent on the phone!)

A discrete-event simulator is so called because it considers time as a discrete sequence of points, with zero or more events happening at each point in time.  In our simulator, time starts at "time 0", and progresses in increments of one.  Each increment is referred to as a "tick".

A discrete-event simulator is usually driven by a script of "independent events" plus a set of "causal rules".

In our simulator, the independent events are the set of customers that place calls to the call center.  These events are in a file.  The first line of the file has a single entry---the number of events (N) contained in the next N lines.  Each of those N lines has the following format:

    <timestamp> <name> <status> <duration>

Each field is delimited by a space.  You may assume that the lines are sorted by timestamp-order, from lowest to highest.  Timestamps need not be unique.

    <timestamp> is an integer, zero or greater, that denotes the tick at which this call comes in.

    <name> is a string; the name of the caller.  This string has no spaces.

    <status> is one of the following four strings:
      "none"     -- no special status
      "silver"   -- silver elite
      "gold"     -- gold elite
      "platinum" -- platinum elite

    <duration> is an integer, denoting the number of ticks required to service this call.

You may assume that the input file is semantically and syntactically correct. Your simulator must obtain this input file from the standard input stream System.in.

Your simulator will maintain four queues, one for each status level. The simulation proceeds as follows (these are the causal rules):


* At the beginning of a "tick", announce it.

      Starting tick #<tick>

* Any callers with timestamps equal to that tick number are inserted into their appropriate queues.  When a caller is inserted, you should print a message that looks like this:

      Call from <name> a <status> member

* After any new calls are inserted into the call queues, the (single) agent is allowed to act.  

  If the agent is not busy, the agent checks each queue, in priority order from Platinum to None.  If the agent finds a call, the agent answers the call, printing a message such as:

      Answering call from <name>

  This will keep the agent busy for \<duration> ticks.

  If the agent was already busy at the beginning of this tick, the agent continues servicing the current client until the appropriate number of ticks have expired.

  If the agent is not busy, and there are no current calls, the agent does nothing, and the clock advances.  The program terminates only when all listed calls have been placed, answered, and completed.

Here is a sample input file:

    3
    0 Andrew gold 2
    0 Chris none 1
    1 Brian silver 1

And the output produced by running the simulator on it:

    Enter input file: sampleInput.txt
    Starting tick #0
    Call from Andrew a gold member
    Call from Chris a regular member
    Answering call from Andrew
    Starting tick #1
    Call from Brian a silver member
    Starting tick #2
    Answering call from Brian
    Starting tick #3
    Answering call from Chris
    Starting tick #4


## Rubric

You are not allowed to use any other methods on either the Stack or Queue class than what we mentioned in class (even if the class implements it). For Stack the only methods you can use are pop, peek, push, isEmpty and size. For Queue the only methods you can use are remove, peek, add, isEmpty, and size. Points will be taken off if you do not follow this and use other methods. 

| Requirement                                                 | Points                 |
| ----------------------------------------------------------- | ---------------------- |
| RPN Calculator Unit Tests                                   | 20 pts (~0.5 pts each) |
| Call Center Unit Tests                                      | 15 pts (~2 pts each)   |
| Good Style (Commenting, naming, breaking into methods, etc. | 10 pts                 |
| **Total**                                                   | **45 pts**             |


## Extra Credit

For extra credit, you will implement a templated container class---the double-ended, doubly-linked list---and use this structure in your two other applications (RPN Calculator, Call Center).

The double-ended list, or Dlist, is a templated container. It supports the following operational methods:

	isEmpty: 		a predicate that returns true if the 
				list is empty, false otherwise

	insertFront/insertBack: inserts an object at the front/back of 
				the list, respectively.

	removeFront/removeBack:	removes an object from the front/back 
				of a non-empty list, respectively;
				throws an exception if the list is empty.

	peekFront/peekBack:	returns an object from the front/back 
				of a non-empty list without removing 
				the object, respectively; throws an 
				exception if the list is empty.

	size:			returns the number of objects in the list.

The complete DList and Node classes are provided below. You should add these files into a new package in your eclipse project. 

(DList.java)
````
package dList;

/**
 * Contains a double-ended list of Objects
 */
public class DList<T>
{
	/**
	 * Pointer to the first node in the list (NULL if none)
	 */
	private Node<T> first;
	
	/**
	 * Pointer to the last node in the list (NULL if none)
	 */
	private Node<T> last;
	
	/**
	 * Constructor for DList
	 */
	public DList()
	{
		// TODO: Replace with your code
	}

	/**
	 * @return true if the list is empty, false otherwise
	 */
	public boolean isEmpty()
	{
		// TODO: Replace with your code
		return false;
	}
	
	/**
	 * Inserts item at the front of the list
	 */
	public void insertFront(T item)
	{
		// TODO: Replace with your code
	}
	
	/**
	 * Inserts item at the back of the list
	 */
	public void insertBack(T item)
	{
		// TODO: Replace with your code
	}
	
	/**
	 * @return and remove the first object from a non-empty list.
	 * Throws an exception if the list is empty.
	 */
	public T removeFront()
	{
		// TODO: Replace with your code
		return null;
	}
	
	/**
	 * @return and remove the last object from a non-empty list.
	 * Throws an exception if the list is empty.
	 */
	public T removeBack()
	{
		// TODO: Replace with your code
		return null;
	}
	
	/**
	 * @return the first object from a non-empty list.
	 * Throws an exception if the list is empty.
	 */
	public T peekFront()
	{
		// TODO: Replace with your code
		return null;
	}
	
	/**
	 * @return the last object from a non-empty list.
	 * Throws an exception if the list is empty.
	 */
	public T peekBack()
	{
		// TODO: Replace with your code
		return null;
	}
	
	/**
	 * @return the size of the list
	 */
	public int size()
	{
		// TODO: Replace with your code
		return 0;
	}
}
````

(Node.java)
````
//*******************************************************
//DO NOT MODIFY THIS FILE!!!
//*******************************************************

package dList;

/**
 * Contains a single node in the list with links to the
 * next and previous node in the list.
 */
public class Node<T>
{
	/**
	 * A link to the next node in the list.
	 * NULL if none.
	 */
	public Node<T> next;
	
	/**
	 * A link to the previous node in the list.
	 * NULL if none.
	 */
	public Node<T> previous;
	
	/**
	 * The data in this node in the list.
	 */
	public T data;
	
	/**
	 * Constructor to create a new node with data
	 * and no previous/next node links.
	 */
	public Node(T data)
	{
		this(data, null, null);
	}
	
	/**
	 * Constructor to create a new node with data
	 * and links to the previous/next nodes.
	 */
	public Node(T data, Node<T> next, Node<T> previous)
	{
		this.data = data;
		this.next = next;
		this.previous = previous;
	}
}
````

You are to provide your implementation for the DList class in addition to using this data structure in your other two programs (RPN Calculator, and Call Center). All unit tests for the two programs should still pass.


