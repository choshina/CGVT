package src.concurrency;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

public class OptimisticLinkedQueue {
	private static class Node {
		private volatile int item;
		private volatile Node next;
		private volatile Node prev;
		Node(int x) { item = x; next = null; prev = null; }
		Node(int x, Node n) { item = x; next = n; prev = null; }
		int getItem() {
			return item;
		}
		void setItem(int val) {
			this.item = val;
		}
		Node getNext() {
			return next;
		}
		void setNext(Node val) {
			next = val;
		}
		Node getPrev() {
			return prev;
		}
		void setPrev(Node val) {
			prev = val;
		}
	}
	private static final AtomicReferenceFieldUpdater<OptimisticLinkedQueue, Node> tailUpdater =AtomicReferenceFieldUpdater.newUpdater(OptimisticLinkedQueue.class, Node.class, "tail");
	private static final AtomicReferenceFieldUpdater<OptimisticLinkedQueue, Node> headUpdater =AtomicReferenceFieldUpdater.newUpdater(OptimisticLinkedQueue.class, Node.class, "head");
	private boolean casTail(Node cmp, Node val) {
		return tailUpdater.compareAndSet(this, cmp, val);
	}
	private boolean casHead(Node cmp, Node val) {
		return headUpdater.compareAndSet(this, cmp, val);
	}
	/**
	 * Pointer to the head node, initialized to a dummy node. The first
	 * actual node is at head.getPrev().
	 */
	private transient volatile Node head = new Node(-1, null);
	/** Pointer to last node on list **/
	private transient volatile Node tail = head;
	/**
	 * Creates a <tt>ConcurrentLinkedQueue</tt> that is initially empty.
	 */
	public OptimisticLinkedQueue() {}
	/**
	 * Enqueues the specified element at the tail of this queue.
	 */
	public void offer(int e) {
		if (e == -1) throw new NullPointerException();
		Node n = new Node(e, null);
		for (;;) {
			Node t = tail;
			n.setNext(t);
			if (casTail(t, n)) {
				t.setPrev(n);
			//	return true;
				return;
			}
		}
	}
	/**
	 * Dequeues an element from the queue. After a successful
casHead, the prev and next pointers of the dequeued node are
set to null to allow garbage collection.
	 */
	public int poll() {
		for (;;) {
			Node h = head;
			Node t = tail;
			Node first = h.getPrev();
			if (h == head) {
				if (h != t) {
					if (first == null){
						fixList(t,h);
						continue;
					}
					//E item = first.getItem();
					if (casHead(h,first)) {
						h.setNext(null);
						h.setPrev(null);
						return head.getItem();
					}
				}
				else
					return -1;
			}
		}
	}
	/**
	 * Fixing the backwords pointers when needed
	 */
	private void fixList(Node t, Node h){
		Node curNodeNext;
		Node curNode = t;
		while (h == this.head && curNode != h){
			curNodeNext = curNode.getNext();
			curNodeNext.setPrev(curNode);
			curNode = curNode.getNext();
		}
	}

	/*public OptimisticLinkedQueue clone() {
		OptimisticLinkedQueue newq = new OptimisticLinkedQueue<>();
		if(head==tail)
			return newq;
		newq.tail = new Node<>(tail.getItem(),null);
		Node<E> curNode = tail.getNext();
		Node<E> curNodenl = newq.tail;
		while(curNode!=head){
			Node<E> nNode = new Node<>(curNode.getItem(),null);
			if(curNode.getPrev()!=null)
				nNode.setPrev(curNodenl);
			curNodenl.setNext(nNode);
			curNodenl = curNodenl.getNext();
			curNode = curNode.getNext();
		}
		curNodenl.setNext(newq.head);
		if(head.getPrev()!=null)
			newq.head.setPrev(curNodenl);
		return newq;
	}*/

	/*public String toString() {
		StringBuilder sb = new StringBuilder("{");
		if(head!=tail){
			sb.append(tail.getItem().toString());
			Node<E> curNode = tail.getNext();
			while(curNode!=head){
				sb.append(',');
				sb.append(curNode.getItem().toString());
				curNode = curNode.getNext();
			}		
		}
		sb.append("}");
		return sb.toString() ;
	}*/

}
