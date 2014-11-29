import java.lang.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

   private Item[] queue;
   private int last;
   private int size;

   public RandomizedQueue() {
   	this.queue = (Item[]) new Object[1];
   	this.size = 0;
   	this.last = 0;
   }

   public boolean isEmpty() {
   	return size == 0;
   }

   public int size() {
   	return this.size;
   }

   public void enqueue(Item item) {
   	if (item == null) {
   		throw new NoSuchElementException();
   	}
   	queue[last++] = item;
   	size++;
   	if (size == queue.length) {
   		Item[] temp = (Item[]) new Object[2 * queue.length];
   		for (int i = 0; i < queue.length; i++) {
   			temp[i] = queue[i];
   		}
   		queue = temp;
   	}
   }

   public Item dequeue() {
   	if (isEmpty()) {
   		throw new java.lang.NullPointerException();
   	}
   	int i = StdRandom.uniform(size);
   	Item temp = queue[i];
   	queue[i] = queue[--last];
   	queue[last] = null;
   	size--;
   	return temp;
   }

   public Item sample() {
   	if (isEmpty()) {
   		throw new java.lang.NullPointerException();
   	}
   	return queue[StdRandom.uniform(size)];
   }

   private class RQiterator implements Iterator<Item> {
   	private int i;
   	public RQiterator() {
   		this.i = 0;
   	}
   	public boolean hasNext() {
   		return i < size;
   	}

   	public Item next() {
   		if (!hasNext()) {
   			throw new java.util.NoSuchElementException();
   		}
   		return queue[i++];
   	}

   	public void remove() {
   		throw new java.lang.UnsupportedOperationException();
   	}
   }

   public Iterator<Item> iterator() {
   	return this.new RQiterator();
   }

   public static void main(String[] args) {
   	RandomizedQueue<Integer> rq = new RandomizedQueue();
   	StdOut.println(rq.isEmpty());
   	//rq.dequeue();
   	rq.enqueue(1);
   	rq.enqueue(2);
   	rq.enqueue(3);
   	rq.enqueue(4);
   	rq.enqueue(5);
   	rq.enqueue(6);
   	StdOut.println("size :" + rq.size());
   	rq.enqueue(7);
   	rq.enqueue(8);
   	rq.enqueue(9);
   	StdOut.println("Sample Element:" + rq.sample());
   	StdOut.println("Dequeue Element:" + rq.dequeue());
   	StdOut.println("Dequeue Element:" + rq.dequeue());
   	StdOut.println("Dequeue Element:" + rq.dequeue());
   	StdOut.println("size :" + rq.size());
   	for (int i : rq ) {
   		StdOut.println(i);
   	}
   }
}


