
package taskOne;

import java.util.Scanner;

public class Test {

	public static void main(String args[]) {
		Scanner input = new Scanner(System.in);
		
		System.out.print("Enter time1 (hour minute second):");
		Time time1 = new Time(input.nextInt(), input.nextInt(), input.nextInt());
	       System.out.println("\n" + time1);
	       System.out.println("\nElapsed seconds in time1: " + time1.getSeconds());
	       
	       System.out.print("\nEnter time2 (elapsed time in seconds): ");
	       long newTime = input.nextLong();
	       Time time2 = new Time(newTime);
	       System.out.println("\n" + time2);
	       System.out.println("\nElapsed seconds in time2: " + time2.getSeconds());

	       System.out.println("\ntime1.compareTo(time2)? " + time1.compareTo(time2));
	       
	       Time Time3 = time1.clone();
	       System.out.println("\ntime3 is created as a clone of time1");
	       System.out.println("\ntime1.compareTo(time3)? " + time1.compareTo(Time3));
	       
	       input.close();
	}
}
