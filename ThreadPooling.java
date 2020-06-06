import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Throughput {
	private final static String file = "/Users/ishaniparashar/Downloads/throughput/resources/war_and_peace.txt";
	private final static int NUMBER_OF_THREADS = 3;
	public static void main(String args[]) throws IOException {	
		String word = "war";
		String text = new String(Files.readAllBytes(Paths.get(file)));
		
		//In thread pooling, the threads sit in the pool and tasks are distributed among them using the queue
		//Fixed Thread Pool Executor is used to implement thread pooling
		//Fixed number of threads are provided in the parameter
		//It comes with built in queue
		Executor executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
		Thread task = new CountThread(word, text);
		executor.execute(task);
		//Note that we don't have to provide Thread.start() as executor takes care of it.
	}
	
	public static class CountThread extends Thread{
		private String word;
		private String text;

		public CountThread(String word, String text) {
			this.word = word;
			this.text = text;
		}
		@Override 
		public void run() {
			System.out.println("Total occurrence of "+word+" in War and Peace is:"+ countWord());
		}
		
		//Counts the occurrence of the word in the novel War and Peace
		public long countWord() {
			System.out.println("Word is: "+word);
			int index = 0;
			long count = 0;
			while(index>=0) {
				index = text.indexOf(word, index);
				if(index>=0) {
					index++;
					count++;
				}
			}
			return count;
		}
	}
}
