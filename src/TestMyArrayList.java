import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class TestMyArrayList {

	ArrayList<String> list = new ArrayList<String>(5);
	MyArrayList<String> mylist = new MyArrayList<String>(5);

	void fillListsWithData() {
		mylist.add("A");
		mylist.add("B");
		mylist.add("C");
		list.add("A");
		list.add("B");
		list.add("C");
	}

	@Test
	@DisplayName("[5] Test if your add and get methods work correctly")
	public void testAdd() {
		fillListsWithData();

		for (int i = 0; i < list.size(); i++) {
			assertNotNull(mylist.get(i));
			
			assertEquals(list.get(i), mylist.get(i));
		}
	}


	@Test
	@DisplayName("[5] Test if your size method works correctly")
	public void testSize() {
		fillListsWithData();

		assertEquals(list.size(), mylist.size());
	}

	@Test
	@DisplayName("[5] Test if your toString method works correctly")
	public void testToString() {
		fillListsWithData();

		assertEquals(list.toString(), mylist.toString());
	}


	@Test
	@DisplayName("[5] Test if your isEmpty() works correctly")
	public void testIsEmpty() {
		assertTrue(mylist.isEmpty());

		mylist.add("X");

		assertFalse(mylist.isEmpty());
	}

	@Test
	@DisplayName("[5] Test if your set() works correctly")
	public void testSet() {
		fillListsWithData();
		assertEquals(list.set(1, "Z"), mylist.set(1, "Z"));
		for (int i = 0; i < list.size(); i++) {
			assertNotNull(mylist.get(i));
			assertEquals(list.get(i), mylist.get(i));
		}
	}


	@Test
	@DisplayName("[5] Test if your get() throws the correct exception")
	public void testException() {
		fillListsWithData();

		assertThrows(IndexOutOfBoundsException.class, () -> mylist.get(4));
	}

	@Test
	@DisplayName("[5] Test if your add() works correctly after expanding")
	public void testAddAfterExpansion() {
		fillListsWithData();

		mylist.add("D");
		mylist.add("E");
		mylist.add("F");
		mylist.add("G");

		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");

		for (int i = 0; i < list.size(); i++) {
			assertNotNull(mylist.get(i));
			assertEquals(list.get(i), mylist.get(i));
		}
	}

	@Test
	@DisplayName("[5] Test if your add() works for null")
	public void testAddNull() {
		list = new ArrayList<String>(10);
		mylist = new MyArrayList<String>(10);

		mylist.add("B");
		mylist.add("C");
		mylist.add("D");
		mylist.add(null);
		mylist.add("F");
		mylist.add("G");
		
		list.add("B");
		list.add("C");
		list.add("D");
		list.add(null);
		list.add("F");
		list.add("G");
		
		assertEquals(list.size(), mylist.size());

		for (int i = 0; i < list.size(); i++) {
			assertEquals(list.get(i), mylist.get(i));
		}
	}

	@Test
	@DisplayName("[5] Test if your add at index method works correctly")
	public void testAddAtIndex() {
		fillListsWithData();

		mylist.add(1, "D");
		list.add(1, "D");


		for (int i = 0; i < list.size(); i++) {
			assertNotNull(mylist.get(i));
			assertEquals(list.get(i), mylist.get(i));
		}
	}

	@Test
	@DisplayName("[5] Test if your remove at index method works correctly.")
	public void testRemoveIndices() {
		list = new ArrayList<String>(10);
		mylist = new MyArrayList<String>(10);

		mylist.add("B");
		mylist.add("C");
		mylist.add("D");
		mylist.add("E");
		mylist.add("F");
		mylist.add("G");
		mylist.remove(3);

		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");
		list.remove(3);

		assertEquals(list.size(), mylist.size());

		for (int i = 0; i < list.size(); i++) {
			assertNotNull(mylist.get(i));
			assertEquals(list.get(i), mylist.get(i));
		}
	}

	@Test
	@DisplayName("[5] Test if your remove object method works correctly.")
	public void testRemoveStrings() {
		list = new ArrayList<String>(10);
		mylist = new MyArrayList<String>(10);

		mylist.add("A");
		mylist.add("B");
		mylist.add("C");
		mylist.add("D");
		mylist.add("E");
		mylist.add("F");
		mylist.add("G");
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		list.add("G");

		assertEquals(mylist.remove("A"), list.remove("A"));

		assertEquals(mylist.remove("F"), list.remove("F"));

		for (int i = 0; i < list.size(); i++) {
			assertNotNull(mylist.get(i));
			assertEquals(list.get(i), mylist.get(i));
		}
	}
	@Test
	@DisplayName("[5] Test if your remove object method works correctly for non-Strings.")
	public void testRemoveIntegers() {
		ArrayList<Integer> intList = new ArrayList<Integer>(10);
		MyArrayList<Integer> myIntList = new MyArrayList<Integer>(10);

		myIntList.add(1);
		myIntList.add(2);
		myIntList.add(10);
		myIntList.add(4);
		myIntList.add(5);
		intList.add(1);
		intList.add(2);
		intList.add(10);
		intList.add(4);
		intList.add(5);

		assertEquals(myIntList.remove(Integer.valueOf(10)), intList.remove(Integer.valueOf(10)));

		assertEquals(intList.size(), myIntList.size());

		for (int i = 0; i < intList.size(); i++) {
			assertNotNull(myIntList.get(i));
			assertEquals(intList.get(i), myIntList.get(i));
		}
	}
	
	 @Test
	    @DisplayName("[5] Test moveToBack")
	    public void testMoveToBack() {
	    	mylist = new MyArrayList<String>();
	    	mylist.add("A");
	    	mylist.add("B");
	    	mylist.add("A");
	    	mylist.add("C");
	    	mylist.add("D");
	    	mylist.add("A");
	    	mylist.add("A");
	    	mylist.add("E");
	    	mylist.add("F");
	    	mylist.add("G");
	    	mylist.add("A");
	    	mylist.moveToBack("A");
	    	
	    	list = new ArrayList<String>();
	    	list.add("B");
	    	list.add("C");
	    	list.add("D");
	    	list.add("E");
	    	list.add("F");
	    	list.add("G");
	    	list.add("A");
	    	list.add("A");
	    	list.add("A");
	    	list.add("A");
	    	list.add("A");
	        assertEquals(list.size(), mylist.size());

	        for (int i = 0; i < list.size(); i++) {
	            assertNotNull(mylist.get(i));
	            assertEquals(list.get(i), mylist.get(i));
	        }
	    
	    }

//	@Test
//    @DisplayName("[5] Test if your moveToBack is fast enough.")
//    public void testMoveToBackSpeed() {
//        int max = 10000000;
//        MyArrayList<String> mylist = new MyArrayList<String>(max);
//        for (int i = 0; i < max; i++)
//        {
//            if (i % 5 == 0 || i % 17 == 0)
//                mylist.add("A");
//            else
//                mylist.add("B");
//        }
//
//        int numAs = 2470588;
//        int numBs = max - numAs;
//
//        ExecutorService executor = Executors.newCachedThreadPool();
//        Callable<Object> task = new Callable<Object>() {
//        public Object call() {
//            mylist.moveToBack("A");
//
//            return mylist;
//        }
//        };
//        Future<Object> future = executor.submit(task);
//        try {
//            Object result = future.get(5, TimeUnit.SECONDS); 
//        } catch (TimeoutException ex) {
//            fail("Your test times out.");
//        } catch (InterruptedException e) {
//            // handle the interrupts
//        } catch (ExecutionException e) {
//            // handle other exceptions
//        } finally {
//            future.cancel(true); // may or may not desire this
//        }
//
//        assertEquals(mylist.size(), max);
//        System.out.println("Test");
//
//        for (int i = 0; i < numBs; i++)
//        {
//            assertEquals(mylist.get(i), "B");
//        }
//        for (int i = numBs; i < mylist.size(); i++) {
//            assertEquals(mylist.get(i), "A");
//        }
//        
//    }




}
