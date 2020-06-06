import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hackers {
	public static final int MAX_VAL = 9999;
	public static void main(String args[]) {
		Random random = new Random();
		Vault vault = new Vault(random.nextInt(MAX_VAL));
		
		List<Thread> threads = new ArrayList<>();
		threads.add(new AscendingThread(vault));
		threads.add(new DescendingThread(vault));
		threads.add(new PoliceThread());
		
		for(Thread thread: threads) {
			thread.start();
		}
	}
	
	private static class Vault {
		private int password;
		
		public Vault(int password) {
			this.password = password;
		}
		
		public boolean isCorrectPassword(int guess) {
			try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
            }
			return guess==this.password;
		}
	}
	
	private static class HackerThread extends Thread {
		Vault vault;
		
		public HackerThread(Vault vault) {
			this.vault = vault;
			this.setName(this.getClass().getSimpleName());
			this.setPriority(Thread.MAX_PRIORITY);
		}
		
		@Override
		public void start() {
			System.out.println("Starting thread: "+this.getName());
			super.start();
		}
	}
	
	private static class AscendingThread extends HackerThread {		
		public AscendingThread(Vault vault) {
			super(vault);
		}

		@Override
		public void run() {
			for(int i=0;i<MAX_VAL;i++) {
				if(vault.isCorrectPassword(i)) {
					System.out.println(this.getName() + " guessed the password " + i);
					System.exit(0);
				}
			}
		}
	}
	
	private static class DescendingThread extends HackerThread {
		public DescendingThread(Vault vault) {
			super(vault);
		}
		
		@Override 
		public void run() {
			for(int i=MAX_VAL;i>=0;i--) {
				if(vault.isCorrectPassword(i)) {
					System.out.println(this.getName()+" guessed the password "+i);
					System.exit(0);
				}
			}
		}
		
	}
	
	private static class PoliceThread extends Thread {
		@Override 
		public void run() {
			for(int i=1;i<=10;i++) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println(i);
			}
			System.out.println("Game over!");
			System.exit(0);
		}
	}
	
}
