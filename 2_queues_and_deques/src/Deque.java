
public class Deque<Item> {// implements Iterable<Item> {
	private class Node<T>
	{
		public Node<T> left;
		public Node<T> right;
		
		public T value;
	}
	// construct an empty deque
   public Deque() {
	   head_ = new Node<Item>();
	   tail_ = head_;
   }
// is the queue empty?
	public boolean isEmpty() {
		return size_ == 0;
	}
// return the number of items on the queue
	public int size() {
		return size_;
	}

// add the item to the front
   public void addFirst(Item item)          {
	   
   }

// add the item to the end
   public void addLast(Item item)           {
		if (item == null)
		{
			throw new java.lang.NullPointerException();
		}
		
	   Node<Item> node = new Node<Item>();
	   node.value = item;
	   node.left = tail_;

	   tail_.right = node;
	   tail_ = node;
	   
	   ++size_;
   }
//   public Item removeFirst()                // remove and return the item from the front
//   public Item removeLast()                 // remove and return the item from the end
//   public Iterator<Item> iterator()         // return an iterator over items in order from front to end

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
	private Node<Item> tail_ = null;
	private int size_ = 0;
// unit testing
   public static void main(String[] args)   {
   

   }
}