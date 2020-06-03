
public class Interrupt {
	public static void main(String args[]) {
		Thread thread  = new Thread(new BlockerThread());
		thread.start();
		thread.interrupt();
		System.out.println("Thread execution is over!");
	}
	
	public static class BlockerThread implements Runnable {
		@Override
		public void run() {
			try {
				System.out.println("Thread is on sleep");
				Thread.sleep(5000000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Exiting Blocker Thread");
			}
		}
	}
}
