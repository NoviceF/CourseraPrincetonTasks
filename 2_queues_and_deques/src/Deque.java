
public class Deque<Item> //implements Iterable<Item> 
 {
	// construct an empty deque
	public Deque() {
		data_ = (Item[]) new Object[capacity_];
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
		
		if (tail_ == 0) {
			resize();
		}
		
		data_[++tail_] = item;
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
		
		Item ret = data_[tail_--];
		
		if (isNeedToTrim())
			trimCapacity();

		return ret;
		
	}

//	// return an iterator over items in order from front to end
//	public Iterator<Item> iterator() {
//	}

	private void resize() {
		Item[] oldData_ = data_;
		capacity_ *= growMultipler_;
		data_ = (Item[]) new Object[capacity_];
		
		int size = size();
		
		int insertIndex = GetInsertIndex();
		assert capacity_ - insertIndex > size;

		for (int i = insertIndex; i < size; ++i)
			data_[i] = oldData_[i];
		
		head_ = insertIndex;
		tail_ = head_ + size;
	}
	
	private void trimCapacity() {
		assert isNeedToTrim();
		
		int size = size();

		Item[] oldData_ = data_;
		data_ = (Item[]) new Object[capacity_];

		int insertIndex = GetInsertIndex();
		assert capacity_ - insertIndex > size;

		for (int i = insertIndex; i < size; ++i)
			data_[i] = oldData_[i];

		head_ = insertIndex;
		tail_ = head_ + size;
	}

	private int GetInsertIndex() {
		assert capacity_ != 0;
		assert size() != 0;
		
		int capMid = capacity_ / 2;
		int sizeMid = size() / 2;

		assert capMid > sizeMid;
		
		return capMid - sizeMid;
	}
	
	private boolean isNeedToTrim()
	{
		if (capacity_ / size() < growMultipler_ * 2)
			return false;
		else
			return true;
	}

	private Item[] data_;
	private int capacity_ = 10;
	private int head_ = 0;
	private int tail_ = 0;
	private int growMultipler_ = 3;

	public static void main(String[] args) // unit testing
	{

	}
}