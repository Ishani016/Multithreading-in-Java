
public class Main {
	public static void main(String args[]) throws InterruptedException {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				//Code that will run in new thread
				System.out.println("Inside thread: "+ Thread.currentThread().getName());
			}
		});
		thread.setName("Worker Thread");
		thread.setPriority(Thread.NORM_PRIORITY);
		System.out.println("Current thread priority is: "+Thread.currentThread().getPriority());
		System.out.println("Current thread is: "+ Thread.currentThread().getName());
		thread.start();
		System.out.println("Current thread is: "+ Thread.currentThread().getName());
		Thread.sleep(1000);
		
		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				// TODO Auto-generated method stub
				System.out.println("Error detected in "+t.getName()+" and the eror is: " + e.getMessage());
			}
		});
	}
}
