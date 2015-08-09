
public class RandomizedQueue<Item> {// implements Iterable<Item> {
// construct an empty randomized queue
   public RandomizedQueue()                 {
		data_ = (Item[]) new Object[capacity_];
   }
// is the queue empty?
   public boolean isEmpty() {
	   return size_ == 0;
   }
// return the number of items on the queue
   public int size() {
	   return size_;
   }
// add the item
   public void enqueue(Item item) {
		if (item == null) {
			throw new java.lang.NullPointerException();
		}
		
		if (capacity_ - size_ == 0) {
			resize();
		}
		
		data_[tail_++] = item;
   }
// remove and return a random item
   public Item dequeue() {
        if (isEmpty())
        throw new java.util.NoSuchElementException();
        
	   int index = StdRandom.uniform(size_);
	   Item current = data_[index];
	   
	   final int endPos = size_ - 1;
	   
	   if (index != endPos);
           data_[index] = data_[endPos];
           
       data_[endPos] = null;
       
       --size_;

	   return current;
   }
// return (but do not remove) a random item
   public Item sample() {
	   return data_[StdRandom.uniform(size_)];
   }
// return an independent iterator over items in random order
//   public Iterator<Item> iterator()         

	private void resize() {
		Item[] oldData_ = data_;
		capacity_ *= growMultipler_;
		data_ = (Item[]) new Object[capacity_];
		
		final int size = size_;

		
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

	private Item[] data_;
	private int capacity_ = 4;
	private int size_ = 0;
	private int tail_ = 0;
	private int growMultipler_ = 2;
	private int shrinkBorder_ = 4;
	private int shrinkDevider_ = 4;
// unit testing
   public static void main(String[] args)   {
   }
}