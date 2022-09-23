
/* See ArrayList documentation here:
 * http://docs.oracle.com/javase/7/docs/api/java/util/ArrayList.html
 */

/*
 * Your indexed functions should throw IndexOutOfBoundsException if index is invalid!
 */

public class MyArrayList<E> {

	/* Internal Object counter */
	protected int objectCount;

	/* Internal Object array */
	protected E [] internalArray;

	protected int addAmount = 2;

	/* Constructor: Create it with whatever capacity you want? */
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		objectCount = 0;
		this.internalArray = (E[])new Object[100];
	}

	/* Constructor with initial capacity */
	@SuppressWarnings("unchecked")
	public MyArrayList(int initialCapacity){
		objectCount = 0;
		this.internalArray = (E[])new Object[initialCapacity];
	}

	/* Return the number of active slots in the array list */
	// O(1)
	public int size() {
		return objectCount;
	}

	/* Are there zero objects in the array list? */
	// O(1)
	public boolean isEmpty() {
		return objectCount == 0;
	}

	/* Get the index-th object in the list. */
	// O(1)
	public E get(int index) {
		if (index >= objectCount) throw new IndexOutOfBoundsException();

		return internalArray[index];
	}

	/* Replace the object at index with obj.  returns object that was replaced. */
	// O(1)
	public E set(int index, E obj) {
		if (index >= objectCount) throw new IndexOutOfBoundsException();

		E e = internalArray[index];
		internalArray[index] = obj;

		return e;
	}

	/* Returns true if this list contains an element equal to obj;
	 otherwise returns false. */
	// O(n)
	public boolean contains(E obj) {	
		for (int i = 0; i < objectCount; i++) {
			if (internalArray[i].equals(obj))
				return true;
		}

		return false;
	}

	/* Insert an object at index */
	// O(n)
	@SuppressWarnings("unchecked")
	public void add(int index, E obj) {
		if (index >= objectCount) throw new IndexOutOfBoundsException();

		if (objectCount >= internalArray.length - 1) {
			addToLength(addAmount);
			addAmount *= 2;
		}

		shiftOneRightAtIndex(index);

		internalArray[index] = obj;

		objectCount++;
	}

	// O(n)
	private void addToLength(int indices) {
		E[] newInternalArray = (E[]) new Object[internalArray.length + indices];
		for (int i = 0; i < internalArray.length; i++) {
			newInternalArray[i] = internalArray[i];
		}
		internalArray = newInternalArray;
	}

	// O(n)
	private void shiftOneRightAtIndex(int index) {
		objectCount++;

		E[] objs = (E[])new Object[objectCount];

		for (int i = index; i < objs.length; i++) {
			objs[i] = internalArray[i];
		}

		internalArray[index] = null;

		for (int i = index + 1; i < objs.length; i++) {
			internalArray[i] = objs[i - 1]; 
		}
	}

	// O(n)
	private void shiftOneLeftAtIndex(int index) {
		objectCount--;

		E[] objs = (E[])new Object[internalArray.length];

		for (int i = index; i < internalArray.length; i++) {
			objs[i] = internalArray[i];
		}

		for (int i = index; i < objs.length - 1; i++) {
			internalArray[i] = objs[i + 1]; 
		}
	}

	/* Add an object to the end of the list; returns true */
	// O(n)
	@SuppressWarnings("unchecked")
	public boolean add(E obj) {
		if (objectCount >= internalArray.length - 1) {
			addToLength(addAmount);
			addAmount *= 2;
		}

		internalArray[objectCount] = obj;
		objectCount++;

		return true;
	}

	/* Remove the object at index and shift.  Returns removed object. */
	// i=0: O(n)	i=n-1: O(1)
	public E remove(int index) {
		if (index > objectCount - 1) throw new IndexOutOfBoundsException();

		Object e = internalArray[index];

		shiftOneLeftAtIndex(index);

		return (E) e;
	}

	/* Removes the first occurrence of the specified element from this list, 
	 * if it is present. If the list does not contain the element, it is unchanged. 
	 * More formally, removes the element with the lowest index i such that
	 * (o==null ? get(i)==null : o.equals(get(i))) (if such an element exists). 
	 * Returns true if this list contained the specified element (or equivalently, 
	 * if this list changed as a result of the call). */
	// O(n)
	public boolean remove(E obj) {
		int index = -1;

		for (int i = 0; i < internalArray.length; i++) {
			if (obj.equals(internalArray[i])) {
				index = i;
				break;
			}
		}

		if (index != -1) {
			remove(index);
			return true;
		}

		return false;
	}

	// This method will search list for all occurrences of obj and move them to the end
	// of the list without disrupting the order of the other elements.
	// O(n2)
	// deal with nulls
	@SuppressWarnings("unchecked")
	public void moveToBack(E obj)
	{
		E[] occs = (E[])new Object[objectCount];

		int occ = 0;
		for (int i = 0; i < objectCount; i++) {
			if (obj.equals(internalArray[i])) {
				occs[occ] = internalArray[i];
				occ++;
			}
		}
		
		E[] newInternalArray = (E[])new Object[objectCount];
		
		int k = 0;
		for (int i = 0; i < objectCount; i++) {
			if (!obj.equals(internalArray[i])) {
				newInternalArray[k] = internalArray[i];
				k++;
			}
		}
		
		int j = 0;
		for (int i = objectCount - occ; i < objectCount; i++) {
			newInternalArray[i] = occs[j];
			j++;
		}
		
		internalArray = newInternalArray;
	}

	/* For testing; your string should output as "[X, X, X, X, ...]" where X, X, X, X, ... are the elements in the ArrayList.
	 * If the array is empty, it should return "[]".  If there is one element, "[X]", etc.
	 * Elements are separated by a comma and a space. */
	// O(n)
	public String toString() {
		StringBuilder str = new StringBuilder("[");

		for (int i = 0; i < objectCount; i++) {
			str.append(internalArray[i]);
			if (i != objectCount - 1)
				str.append(", ");
		}

		str.append("]");

		return str.toString();
	}

}