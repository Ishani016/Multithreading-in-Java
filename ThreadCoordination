import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadCoordination {
	public static void main(String args[]) throws InterruptedException {
		List<Long> nums = Arrays.asList(1000000000000L, 4534L, 585L, 263L, 857L, 499L, 200L);
		
		List<FactorialThread> threads = new ArrayList<>();
		
		//factorials will be calculated parallelly for all the numbers in the list 
		//a thread will be created to calculate factorial for each number
		for(long num: nums) {
			threads.add(new FactorialThread(num));
		}
		
		for(Thread thread: threads) {
			//daemon thread will allow us to exit even if the current thread is not fully executed
			//all the factorial threads are now daemon threads
			thread.setDaemon(true);
			thread.start();
		}
		
		//main thread and factorial threads are running independently 
		//to force main thread to wait till the factorial thread returns a result, we will use join()
		
		//for every thread, we will add join() method. join() will return only when that thread has terminated
		for(Thread thread: threads) {
			thread.join(2000);
		}
		
		for(int i=0;i<nums.size();i++) {
			FactorialThread factorialThread = threads.get(i);
			if(factorialThread.isFinished()) 
				System.out.println("Factorial of "+nums.get(i)+" is: "+ factorialThread.getResult());
			else
				System.out.println("Factorial of "+nums.get(i)+" is still in progress");
		}
	}
	
	public static class FactorialThread extends Thread {
		private long number;
		private boolean isFinished = false;
		private BigInteger result = BigInteger.ONE;
		
		public FactorialThread(Long number) {
			this.number = number;
		}
		
		@Override
		public void run() {
			for(long i=1;i<=number;i++) {
				result = result.multiply(new BigInteger(Long.toString(i)));
			}
			
			isFinished = true;
		}
		
		public BigInteger getResult() {
			return result;
		}
		
		public boolean isFinished() {
			return isFinished;
		}
	}
	
	
}
