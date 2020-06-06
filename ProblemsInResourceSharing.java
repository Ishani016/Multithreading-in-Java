
public class NonAtomicOperationInResourceSharing {
	public static void main(String argsp[]) {
		CurrentStock currentStock = new CurrentStock();
		AddItemsToStockThread addItemsToStockThread = new AddItemsToStockThread(currentStock);
		RemoveItemsFromStockThread removeItemsFromStockThread = new RemoveItemsFromStockThread(currentStock);
		
		addItemsToStockThread.start();
		removeItemsFromStockThread.start();
		
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
		
		public void addItems() {	
				items++;
		}
		
		public void removeItems() {
				items--;
		}
		
		public int getItems() {
			return items;
		}
	}
}
