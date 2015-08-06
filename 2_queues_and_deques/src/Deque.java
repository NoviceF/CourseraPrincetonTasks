import java.util.Iterator;

public class Deque<Item> //implements Iterable<Item> 
 {
	private class DeqItertor<T> implements Iterator<T>
	{
		private T[] array_;
		private int head_;
		private int tail_;
		private int curPos;
		
		public DeqItertor(Deque<T> deque) {
			array_ = (T[])deque.data_;
			head_ = deque.head_;
			tail_ = deque.tail_;
			curPos = head_;
		}
	    public boolean hasNext() {
	    	return curPos < tail_;
	    }

	    public T next() {
	    	if (!hasNext())
	    		throw new java.util.NoSuchElementException();

	    	return array_[curPos++];
	    }
	    
	    public void remove() {
	    	throw new  java.lang.UnsupportedOperationException();
	    }
	}
	// construct an empty deque
	public Deque() {
		data_ = (Item[]) new Object[capacity_];
		head_ = GetInsertIndex();
		tail_ = head_;
	}

	// is the deque empty?
	public boolean isEmpty() {
		return size() == 0;
	}

	// return the number of items on the deque
	public int size() {
		assert tail_ >= head_;
		return tail_ - head_;
	}

	public void addFirst(Item item) // add the item to the front
	{
		if (item.equals(0))
		{
			throw new java.lang.NullPointerException();
		}
		
		if (head_ == 0) {
			resize();
		}
		
		data_[--head_] = item;
	}

	// add the item to the end
	public void addLast(Item item) {

		if (item.equals(0))
		{
			throw new java.lang.NullPointerException();
		}
		
		if (tail_ == data_.length) {
			resize();
		}
		
		data_[tail_++] = item;
	} 

	// remove and return the item from the front
	public Item removeFirst(){ 

		if (isEmpty())
			throw new java.util.NoSuchElementException();
		
		Item ret = data_[head_++];
		
		if (isNeedToTrim())
			trimCapacity();

		return ret;
		
	}

	// remove and return the item from the end
	public Item removeLast() {

		if (isEmpty())
			throw new java.util.NoSuchElementException();
		
		Item ret = data_[--tail_];
		
		if (isNeedToTrim())
			trimCapacity();

		return ret;
		
	}

//	// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DeqItertor<Item>(this);
	}

	private void resize() {
		Item[] oldData_ = data_;
		capacity_ *= growMultipler_;
		data_ = (Item[]) new Object[capacity_];
		
		int size = size();
		
		int insertIndex = GetInsertIndex();
		assert capacity_ - insertIndex > size;
		int sequenceLen = insertIndex + size;

		for (int i = insertIndex, j = head_; i < sequenceLen; ++i, ++j)
			data_[i] = oldData_[j];
		
		head_ = insertIndex;
		tail_ = sequenceLen;
	}
	
	private void trimCapacity() {
		assert isNeedToTrim();
		
		int size = size();

		Item[] oldData_ = data_;
		capacity_ /= shrinkDevider_;
		data_ = (Item[]) new Object[capacity_];

		int insertIndex = GetInsertIndex();
		assert capacity_ - insertIndex >= size;
		int sequenceLen = insertIndex + size;

		for (int i = insertIndex, j = head_; i < sequenceLen; ++i, ++j)
			data_[i] = oldData_[j];

		head_ = insertIndex;
		tail_ = head_ + size;
	}

	private int GetInsertIndex() {
		int size = size();

		assert capacity_ >= size;
		
		if (capacity_ == 1 || capacity_ == size) 
			return 0;
		
		int capMid = capacity_ / 2;
		int sizeMid = 0;


		if (size > 1 && (size / 2 < capacity_ /2))
			sizeMid = size / 2 + 1;

		assert capMid >= sizeMid;
		
		return capMid - sizeMid;
	}
	
	private boolean isNeedToTrim()
	{
		int size = size();
		
		if (size == 0)
			return false;

		if (capacity_ / size < shrinkBorder_)
			return false;
		else
			return true;
	}
	
	int getCap()
	{
		return capacity_;
	}
	
	int getGrowMultipler()
	{
		return growMultipler_;
	}

	private Item[] data_;
	private int capacity_ = 4;
	private int head_ = 0;
	private int tail_ = 0;
	private int growMultipler_ = 3;
	private int shrinkBorder_ = 4;
	private int shrinkDevider_ = 4;

	public static void main(String[] args) // unit testing
	{
		Deque<Integer> deq = new Deque<Integer>();
		assert deq.isEmpty();
		int baseCap = 4;
		assert deq.getCap() == baseCap;
		int first = 1;
		int second = 2;
		deq.addFirst(first);
		deq.addLast(second);
		int size = deq.size();
		assert size == 2;
		assert !deq.isEmpty();
		int removed = deq.removeFirst();
		assert deq.size() == 1;
		assert removed == first;
		int oldCap = deq.getCap();
		deq.addFirst(first);
		removed = deq.removeLast();
		assert deq.getCap() == oldCap * deq.getGrowMultipler();
		assert removed == second;
		assert deq.size() == 1;
		
		int third = 3;
		int fourth = 4;
		deq.addLast(third);
		deq.addLast(fourth);
		assert deq.size() == 3;
		deq.removeLast();
		deq.removeLast();
		deq.removeLast();
		
		deq.addFirst(first);
		deq.addFirst(second);
		deq.addFirst(third);

		deq.removeFirst();
		deq.removeFirst();
		deq.removeFirst();
		
		deq.addFirst(second);
		deq.addFirst(first);
		Iterator<Integer> it = deq.iterator();
		assert it.hasNext();
		assert it.next() == first;
		assert it.hasNext();
		assert it.next() == second;
		assert !it.hasNext();
	}
}













