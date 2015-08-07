import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private class Node<T>
	{
		public Node<T> left;
		public Node<T> right;
		
		public T value;
	}
	
	private class DeqItertor<T> implements Iterator<T>
	{
		private int head_;
		private int curPos;
		Node<T> head;
		
		public DeqItertor(RandomizedQueue<T> deque) {
			curPos = head_;
			head = (RandomizedQueue<Item>.Node<T>) deque.head_;
		}
	    public boolean hasNext() {
	    	return false;
	    }

	    // TODO: реализовать
	    public T next() {
	    	if (!hasNext())
	    		throw new java.util.NoSuchElementException();

	    	return head.value;
	    }
	    
	    public void remove() {
	    	throw new  java.lang.UnsupportedOperationException();
	    }
	}
// construct an empty randomized queue
   public RandomizedQueue()  {
	   head_ = new Node<Item>();
   }
// is the queue empty?
	public boolean isEmpty() {
		return size() == 0;
	}
// return the number of items on the queue
	public int size() {
		return size_;
	}
// add the item
   public void enqueue(Item item) {
	   Node<Item> node = new Node<Item>();
	   node.value = item;
	   node.left = head_;

	   head_.right = node;
	   
	   ++size_;
   }

// remove and return a random item
   public Item dequeue() {
	   int index = StdRandom.uniform(size_);
	   // TODO: реализовать.
//	   removeNode();
	   return getNode(index).value;
	   
   }
// return (but do not remove) a random item
   public Item sample() {
	   int index = StdRandom.uniform(size_);
	   return getNode(index).value;
   }

// return an independent iterator over items in random order
	public Iterator<Item> iterator() {
		return new DeqItertor<Item>(this);
	}
   
   private Node<Item> getNode(int pos) {
	   assert size_ > 0;
	   assert pos < size_;

	   Node<Item> current = head_.right;
	   int currIndex = 0;
	   
	   while (currIndex != pos) {
		   current = current.right;
		   ++currIndex;
		   assert current != null;
	   }
	   
	   return current;
   }
   
	private Node<Item> head_ = null;
	private Node<Item> tail_ = null;
	private int size_ = 0;
// unit testing
   public static void main(String[] args)  { 
}
}