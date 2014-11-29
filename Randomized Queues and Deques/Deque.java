import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Deque<Item> implements Iterable<Item> {

   private List<Item> deque;

   public Deque() {
   	this.deque = new LinkedList<Item>();
   }

   public boolean isEmpty() {
   	return deque.isEmpty();
   }

   public int size() {
   	return deque.size();
   }

   public void addFirst(Item item) {
   	if (item == null) {
   		throw new java.lang.NullPointerException();
   	}
   	deque.add(0, item);
   }

   public void addLast(Item item) {
   	if (item == null) {
   		throw new java.lang.NullPointerException();
   	}
   	deque.add(item);
   }

   public Item removeFirst() {
   	if (isEmpty()) {
   		throw new java.util.NoSuchElementException();
   	}
   	return (Item) deque.remove(0);
   }

   public Item removeLast() {
   	if (isEmpty()) {
   		throw new java.util.NoSuchElementException();
   	}
   	return (Item) deque.remove(deque.size() - 1);
   }

   private class DequeItor implements Iterator<Item> {
   	private int i;
   	public DequeItor() {
   		this.i = 0;
   	}
   	public boolean hasNext() {
   		return i < deque.size();
   	}
   	public Item next() {
   		if (!hasNext()) {
   			throw new java.util.NoSuchElementException();
   		}
   		return deque.get(i++);
   	}
   	public void remove() {
   		throw new java.lang.UnsupportedOperationException();
   	}
   }

   public Iterator<Item> iterator() {
   	return this.new DequeItor();
   }

   public static void main(String[] args) {
   	Deque<Integer> deque = new Deque<Integer>();
   	StdOut.println(deque.isEmpty());
   	deque.addFirst(1);
   	deque.addFirst(2);
   	deque.addFirst(3);
   	deque.addFirst(4);
   	StdOut.println(deque.size());
   	deque.addLast(5);
   	deque.addLast(6);
   	deque.addLast(7);
   	deque.addLast(8);
   	deque.addLast(9);
   	deque.removeLast();
   	deque.removeFirst();
   	for (int i : deque) {
   		StdOut.println(i);
   	}
   }
}
