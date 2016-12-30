package src.concurrency;

import java.util.concurrent.atomic.AtomicMarkableReference;

public class BugUnknownList1 {
  Node head;

  public BugUnknownList1() {
    this.head  = new Node(Integer.MIN_VALUE);
    Node tail = new Node(Integer.MAX_VALUE);
    while (!head.next.compareAndSet(null, tail, false, false));
  }




  public boolean add(int item) {

    boolean splice;
    while (true) {
      // find predecessor and curren entries
      Window window = find(head, item);
      Node pred = window.pred, curr = window.curr;
      // is the key present?
      if (curr.item == item) {
        return false;
      } else {
        // splice in new node
        Node node = new Node(item);
        node.next = new AtomicMarkableReference(curr, false);
        if (pred.next.compareAndSet(curr, node, false, false)) {
          return true;
        }
      }
    }
  }

  public boolean remove(int item) {

    boolean snip;
    while (true) {
      // find predecessor and curren entries
      Window window = find(head, item);
      Node pred = window.pred, curr = window.curr;
      // is the key present?
      if (curr.item != item) {
        return false;
      } else {
        // snip out matching node
        Node succ = curr.next.getReference();
        snip = curr.next.attemptMark(succ, true);
        if (!snip)
          continue;
        pred.next.compareAndSet(curr, succ, false, false);
        return true;
      }
    }
  }

  public boolean contains(int item) {

    // find predecessor and curren entries
    Window window = find(head, item);
    Node pred = window.pred, curr = window.curr;
    return (curr.item == item);
  }

  private class Node {
    int item;
    AtomicMarkableReference<Node> next;
    Node(int item) {      // usual constructor
      this.item = item;
      this.next = new AtomicMarkableReference<Node>(null, false);
    }

  }
  
  class Window {
    public Node pred;
    public Node curr;
    Window(Node pred, Node curr) {
      this.pred = pred; this.curr = curr;
    }
  }
  
  public Window find(Node head, int key) {
	  Node pred = head;
	  Node curr = head.next.getReference();
	  while(curr.item < key){
		  pred = curr;
		  curr = curr.next.getReference();
	  }
	  return new Window(pred,curr);
  }
  

}
