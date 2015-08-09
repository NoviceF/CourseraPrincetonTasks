
public class Subset {
   public static void main(String[] args) {
	   final int k = Integer.parseInt(args[0]);
	   
	   RandomizedQueue<String> queue = new RandomizedQueue<String>();
	   
	   while (!StdIn.isEmpty())
	   {
           String str = StdIn.readString();
		   queue.enqueue(str);
	   }
	   
	   for (int i = 0; i < k; ++i) {
           System.out.println(queue.dequeue());
       } 
   }
}