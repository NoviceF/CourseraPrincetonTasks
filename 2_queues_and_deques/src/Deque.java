
public class Deque<Item> implements Iterable<Item> 
{
   public Deque() 
   {
	   data_ = (Item[]) new Object[capacity_];
   }                          // construct an empty deque
   public boolean isEmpty(){ return curSize_ == 0; }                 // is the deque empty?
   public int size(){ return curSize_; }                        // return the number of items on the deque
   public void addFirst(Item item)        // add the item to the front
   {
	   if (curSize_ == capacity_)
	   {
	   }
   }  
   public void addLast(Item item){}           // add the item to the end
   public Item removeFirst(){ return ((Item)(new Object)) ;}                // remove and return the item from the front
   public Item removeLast(){}                 // remove and return the item from the end
   public Iterator<Item> iterator(){}         // return an iterator over items in order from front to end

   private void resize()
   {
       Item[] oldData_ = data_;
       capacity_ *= 3;
       data_ = (Item[]) new Object[capacity_];
       
       
       for (int i = 0; i < curSize_; ++i)
               data_[i] = oldData_[i];
   }

   private int curSize_;
   private Item[] data_;
   private int capacity_ = 10;
   private int head_ = 0;
   private int tail_ = 0;

   public static void main(String[] args)   // unit testing
   {
	   
   }
}