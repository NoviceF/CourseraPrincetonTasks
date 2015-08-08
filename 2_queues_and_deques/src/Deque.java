import java.util.Iterator;

public class Deque<Item>  implements Iterable<Item> {
	private class Node<T>
	{
		public Node<T> left;
		public Node<T> right;
		
		public T value;
	}

	private class DeqItertor<T> implements Iterator<T>
	{
        private Node<T> curNode;

		public DeqItertor() {

			curNode = (Deque<Item>.Node<T>) head_;
		}

	    public boolean hasNext() {
	    	return curNode != null;
	    }

	    public T next() {
	    	if (!hasNext())
	    		throw new java.util.NoSuchElementException();
	    	
	    	T value = curNode.value;
	    	curNode = curNode.right;

	    	return value;
	    }
	    
	    public void remove() {
	    	throw new java.lang.UnsupportedOperationException();
	    }
	}
	// construct an empty deque
   public Deque() {
   }
// is the queue empty?
	public boolean isEmpty() {
		return size_ == 0;
	}
// return the number of items on the queue
	public int size() {
		return size_;
	}
	
	private void initHead(Item item) {
        head_ = new Node<Item>();
        tail_ = head_;
        head_.value = item;
        ++size_;
	}

	private void deinitHead() {
        head_ = null;
        tail_ = head_;
        size_ = 0;
	}

// add the item to the front
   public void addFirst(Item item)          {
		if (item == null)
			throw new java.lang.NullPointerException();

	   if (size_ == 0)
	   {
		   initHead(item);
		   return;
	   }

	   Node<Item> node = new Node<Item>();
	   node.value = item;

	   node.right = head_;
	   head_.left = node;
	   head_ = node;
	   
	   ++size_;
	   
   }

// add the item to the end
   public void addLast(Item item)           {
		if (item == null)
		{
			throw new java.lang.NullPointerException();
		}
		
	   if (size_ == 0)
	   {
		   initHead(item);
		   return;
	   }

	   Node<Item> node = new Node<Item>();
	   node.value = item;
	   node.left = tail_;

	   tail_.right = node;
	   tail_ = node;
	   
	   ++size_;
   }
// remove and return the item from the front
   public Item removeFirst()                {
        if (isEmpty())
        throw new java.util.NoSuchElementException();
        
        Item value = head_.value;
        removeHead();
        return value;
   }
   
   // remove and return the item from the end
   public Item removeLast()                 {
        if (isEmpty())
        throw new java.util.NoSuchElementException();
        
        Item value = tail_.value;
        removeTail();
        return value;
   }
// return an iterator over items in order from front to end
	public Iterator<Item> iterator() {
		return new DeqItertor<Item>();
	}
   
   private void removeHead() {
	   if (head_.right == null) {
		   deinitHead();
		   return;
	   }
	   head_.right.left = null;
	   head_ = head_.right;
	   
	   --size_;
   }

   private void removeTail() {
	   if (tail_.left == null) {
		   deinitHead();
		   return;
	   }
	   
	   tail_.left.right = null;
	   tail_ = tail_.left;  

	   --size_;
   }

	private Node<Item> head_ = null;
	private Node<Item> tail_ = null;
	private int size_ = 0;
// unit testing
   public static void main(String[] args)   {
   
		Deque<Integer> deq = new Deque<Integer>();
		assert deq.isEmpty();
		int baseCap = 4;
//		assert deq.getCap() == baseCap;
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
//		int oldCap = deq.getCap();
		deq.addFirst(first);
		removed = deq.removeLast();
//		assert deq.getCap() == oldCap * deq.getGrowMultipler();
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