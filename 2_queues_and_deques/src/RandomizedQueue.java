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
		private RandomizedQueue<T> deque;
		
		public DeqItertor(RandomizedQueue<T> deq) {
			deque = new RandomizedQueue<T>();

			Node<T> curNode = (RandomizedQueue<Item>.Node<T>) head_;
			
			while (curNode.right != null)
			{
				curNode = curNode.right;
				deque.enqueue(curNode.value);
			}
		}

	    public boolean hasNext() {
	    	return !deque.isEmpty();
	    }

	    public T next() {
	    	if (!hasNext())
	    		throw new java.util.NoSuchElementException();

	    	return deque.dequeue();
	    }
	    
	    public void remove() {
	    	throw new java.lang.UnsupportedOperationException();
	    }
	}
// construct an empty randomized queue
   public RandomizedQueue()  {
	   head_ = new Node<Item>();
	   current_ = head_;
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
		if (item == null)
		{
			throw new java.lang.NullPointerException();
		}
		
	   Node<Item> node = new Node<Item>();
	   node.value = item;
	   node.left = current_;

	   current_.right = node;
	   current_ = node;
	   
	   ++size_;
   }

// remove and return a random item
   public Item dequeue() {
        if (isEmpty())
        throw new java.util.NoSuchElementException();
        
	   int index = StdRandom.uniform(size_);
	   return removeNode(index).value;
   }
// return (but do not remove) a random item
   public Item sample() {
        if (isEmpty())
        throw new java.util.NoSuchElementException();
        
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
	private Node<Item> current_ = null;
	private int size_ = 0;

// unit testing
   public static void main(String[] args) throws InterruptedException  { 
	   RandomizedQueue<Integer> queue = new RandomizedQueue<Integer>();
//	   queue.enqueue(5);
//	   assert queue.dequeue() == 5;
//	   
//	   queue.enqueue(7);
//	   queue.enqueue(9);

//	   System.out.println(queue.dequeue());
//	   System.out.println(queue.dequeue());
//	   System.out.println(queue.dequeue());
	   
	   queue.enqueue(5);
	   queue.enqueue(7);
	   queue.enqueue(9);
	   
	   Iterator<Integer> it = queue.iterator();
       System.out.println(it.next());
       Thread.sleep(2000);
	   System.out.println(it.next());
       Thread.sleep(2000);
	   System.out.println(it.next());
	   
	   
	   }

}