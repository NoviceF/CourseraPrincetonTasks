
public class Subset {
   public static void main(String[] args) {
	   final int k = Integer.parseInt(args[0]);
	   
	   RandomizedQueue_old<String> queue = new RandomizedQueue_old<String>();
	   
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