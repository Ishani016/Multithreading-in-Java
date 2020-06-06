public class SynchronizedBlock {
	public static void main(String argsp[]) throws InterruptedException {
		CurrentStock currentStock = new CurrentStock();
		AddItemsToStockThread addItemsToStockThread = new AddItemsToStockThread(currentStock);
		RemoveItemsFromStockThread removeItemsFromStockThread = new RemoveItemsFromStockThread(currentStock);
		
		addItemsToStockThread.start();
		removeItemsFromStockThread.start();
		
		addItemsToStockThread.join();
		removeItemsFromStockThread.join();
		
		System.out.println("Items remaining in stock: " + currentStock.getItems());
	}
	
	public static class AddItemsToStockThread extends Thread {
		CurrentStock stock;
		
		public AddItemsToStockThread(CurrentStock stock) {
			this.stock = stock;
		}
		
		@Override 
		public void run() {
			for(int i=1;i<=10000;i++)
				stock.addItems();
		}
	}
	
	public static class RemoveItemsFromStockThread extends Thread {
		CurrentStock stock;
		
		public RemoveItemsFromStockThread(CurrentStock stock) {
			this.stock = stock;
		}
		
		@Override 
		public void run() {
			for(int i=1;i<=10000;i++)
				stock.removeItems();
		}
	}
	
	public static class CurrentStock {
		private int items;
		Object lock = new Object();
		
		public void addItems() {	
			synchronized(this.lock) {
				items++;
			}
		}
		
		public void removeItems() {
			synchronized(this.lock) {
				items--;
			}
		}
		
		public int getItems() {
			synchronized(this.lock) {
				return items;
			}
		}
	}
}
