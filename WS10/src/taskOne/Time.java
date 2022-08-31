
package taskOne;

public class Time implements Comparable<Time>, Cloneable{
 
	private long timeSince;
	
	public Time(){};

	public Time(int hour, int minute, int second) {
		timeSince = (hour * 3600) + (minute * 60) + second;
	}
	
	public Time (long timePassed) {
		timeSince = timePassed;
	}
	
	public int getHour() {
		return (int) ((timeSince / 3600) % 24);
	}
	
	public int getMinute() {
		return (int) ((timeSince / 60) % 60);
	}
	
	public int getSecond() {
		return (int) timeSince % 60;
	}
	
	public long getSeconds(){
		return  timeSince;
	} 
	
	public String toString() {
		String hour = " hour ";
		String minute = " minute ";
		String second = " second ";
		if(this.getHour() > 1) {
			hour = " hours ";
		}
		if(this.getHour() > 1) {
			minute = " minutes ";
		}
		if(this.getHour() > 1) {
			second = " seconds ";
		}
		return String.format(this.getHour() + hour + this.getMinute() + minute + this.getSecond() + second);
	}
	
	@Override
	public int compareTo(Time time) {
		return (int) (this.timeSince - time.timeSince);
	}
	
	public Time clone(){
		Time newTime = new Time(timeSince);
		return newTime;
	}
	
}
