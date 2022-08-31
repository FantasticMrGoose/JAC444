
package reverseThread;

public class ReverseThread extends Thread {
	
	private static int numThread = 0;
	
	public void run() {
		
		numThread++;
		if(numThread <= 50) {
			ReverseThread thread = new ReverseThread();
			thread.start();
			thread.setName(String.valueOf(numThread));
			
			try {
				thread.join();
				System.out.println("Hello from Thread! " + thread.getName());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		else {
			return;
		}
		
	}
	
	public static void main(String[] args) {
		ReverseThread thread = new ReverseThread();
		thread.start();
	}
	
}


