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
//		RandomizedQueue<T> deque;
		
		public DeqItertor(RandomizedQueue<T> deq) {
//			deque = deq;
		}

	    public boolean hasNext() {
	    	return head_.right != null;
	    }

	    public T next() {
	    	if (!hasNext())
	    		throw new java.util.NoSuchElementException();

	    	return (T) sample();
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
	   return removeNode(index).value;
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
   
   private Node<Item> removeNode(int index) {
	   assert index < size_;
	   Node<Item> node = getNode(index);

	   node.left.right = node.right;
	   --size_;
	   return node;
   }
   
	private Node<Item> head_ = null;
	// TODO: использовать для вставки нового элемента
	private Node<Item> current_ = null;
	private int size_ = 0;

// unit testing
   public static void main(String[] args) throws InterruptedException  { 
	   RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
	   queue.enqueue(5);
	   assert queue.dequeue() == 5;
	   
	   queue.enqueue(7);
	   queue.enqueue(9);

	   System.out.println(queue.dequeue());
	   Thread.sleep(2000);
	   System.out.println(queue.dequeue());
	   Thread.sleep(2000);
	   System.out.println(queue.dequeue());
	   Thread.sleep(2000);
   }

}