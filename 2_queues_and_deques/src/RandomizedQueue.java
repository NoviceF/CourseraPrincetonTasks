import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {

	private class DeqItertor implements Iterator<Item>
	{
		private int current = 0;

		public DeqItertor() {
		}

	    public boolean hasNext() {
	    	return current < size_;
	    }

	    public Item next() {
	    	if (!hasNext())
	    		throw new java.util.NoSuchElementException();

            final int index = StdRandom.uniform(size_);
            ++current;
	    	return data_[index];
	    }
	    
	    public void remove() {
	    	throw new java.lang.UnsupportedOperationException();
	    }
	}
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
		
		data_[size_++] = item;
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
       
       if (isNeedToTrim())
    	   trimCapacity();

	   return current;
   }
// return (but do not remove) a random item
   public Item sample() {
	   return data_[StdRandom.uniform(size_)];
   }
// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new DeqItertor();
	}

	private void resize() {
		Item[] oldData_ = data_;
		capacity_ *= growMultipler_;
		data_ = (Item[]) new Object[capacity_];

		for (int i = 0; i < size_; ++i)
			data_[i] = oldData_[i];
	}
	
	private void trimCapacity() {
		assert isNeedToTrim();
		
		Item[] oldData_ = data_;
		capacity_ /= shrinkDevider_;
		data_ = (Item[]) new Object[capacity_];

		assert capacity_ >= size_;

		for (int i = 0; i < size_; ++i)
			data_[i] = oldData_[i];
	}

	private boolean isNeedToTrim()
	{
		if (size_ == 0)
			return false;

		if (capacity_ / size_ < shrinkBorder_)
			return false;
		else
			return true;
	}

	private Item[] data_;
	private int capacity_ = 4;
	private int size_ = 0;
	private int growMultipler_ = 2;
	private int shrinkBorder_ = 4;
	private int shrinkDevider_ = 4;
// unit testing
   public static void main(String[] args) throws InterruptedException   {
	   RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
	   queue.enqueue(5);
	   assert queue.dequeue() == 5;
//	   
	   queue.enqueue(7);
	   queue.enqueue(9);
//
//	   System.out.println(queue.dequeue());
//	   System.out.println(queue.dequeue());
//	   System.out.println(queue.dequeue());

       queue.dequeue();
       queue.dequeue();
       queue.dequeue();

	   queue.enqueue(1);
	   queue.enqueue(2);
	   queue.enqueue(3);

	   Iterator<Integer> it = queue.iterator();
       System.out.println(it.next());
	   System.out.println(it.next());
	   System.out.println(it.next());
	   assert !it.hasNext();
   }
}