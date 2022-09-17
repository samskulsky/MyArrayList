import java.util.ArrayList;

public class BigOTester {

	public static void main(String[] args) {
		final int BIG_NUM = 100000;
		
		long startTime = System.currentTimeMillis();
		ArrayList<Double> listy = new ArrayList<Double>();
		for (int i = 0; i < BIG_NUM; i++) {
			listy.add(Math.sqrt(i));
		}
		System.out.println(listy.get((int) (Math.random() * BIG_NUM)));
		long endTime = System.currentTimeMillis();
		System.out.println("That took " + ((endTime - startTime)/1000.0) + " seconds");
		
		MyArrayList<Double> myListy = new MyArrayList<Double>();
		for (int i = 0; i < BIG_NUM; i++) {
			myListy.add(Math.sqrt(i));
		}
		System.out.println(myListy.get((int) (Math.random() * BIG_NUM)));
		endTime = System.currentTimeMillis();
		System.out.println("That took " + ((endTime - startTime)/1000.0) + " seconds");
		
	}

}
