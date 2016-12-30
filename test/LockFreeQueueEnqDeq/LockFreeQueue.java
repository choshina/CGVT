/*
 * LockFreeQueue.java
 *
 * Created on December 29, 2005, 2:05 PM
 *
 * The Art of Multiprocessor Programming, by Maurice Herlihy and Nir Shavit.
 * by Maurice Herlihy and Nir Shavit.
 * Copyright 20065 Elsevier Inc. All rights reserved.
 */

package test.LockFreeQueueEnqDeq;
import java.util.concurrent.atomic.AtomicReference;
/**
 * Lock-free queue.
 * Based on Michael and Scott http://doi.acm.org/10.1145/248052.248106
 * @param T item type
 * @author Maurice Herlihy
 */
public class LockFreeQueue {
  private AtomicReference<Node> head;
  private AtomicReference<Node> tail;
	Scheduler  scheduler;
  public LockFreeQueue() {
    Node sentinel = new Node(-2);
    this.head = new AtomicReference<Node>(sentinel);
    this.tail = new AtomicReference<Node>(sentinel);
	scheduler = new Scheduler(1);
  }
  /**
   * Append item to end of queue.
   * @param item
   */
  public void enq(int item) {
  //  if (item == null) throw new NullPointerException();
    Node node = new Node(item); // allocate & initialize new node
//	scheduler.schedule();
    while (true) {		 // keep trying
      Node last = tail.get();
//	scheduler.schedule();    // read tail
      scheduler.schedule();
      Node next = last.next.get(); // read next
//      if (last == tail.get()) { // are they consistent?
    	  scheduler.schedule();
        if(next == null){
		scheduler.schedule();
        	
        	if(last.next.compareAndSet(next, node)){
        		tail.compareAndSet(last, node);
        		return;
        	}
	//	scheduler.schedule();
        }else{
        	tail.compareAndSet(last, next);
        }
  //    }
    }
  }
  /**
   * Remove and return head of queue.
   * @return remove first item in queue
   * @throws queue.EmptyException
   */
  public int deq() {
    while (true) {
      Node first = head.get();
      Node last = tail.get();

      Node next = first.next.get();
	scheduler.schedule();
      if (first == head.get()) {// are they consistent?
        if (first == last) {	// is queue empty or tail falling behind?
          if (next == null) {	// is queue empty?
            return -1;
          }
	//	scheduler.schedule();
        //  // tail is behind, try to advance
          tail.compareAndSet(last, next);
        } else {
          int value = next.value; // read value before dequeuing
          if (head.compareAndSet(first, next))
            return value;
        }
      }
    }
  }
  public class Node {
    public int value;
    public AtomicReference<Node> next;
    
    public Node(int value) {
      this.value = value;
      this.next  = new AtomicReference<Node>(null);
    }
  }
	public void resetScheduler(Scheduler s){
		scheduler = s;
	}
  
  public static void main(String args[]){
	  LockFreeQueue l = new LockFreeQueue();
	  l.enq(5);
	
	  System.out.println(l.deq());
  }
 
}
