package src.concurrency;

import java.util.concurrent.atomic.AtomicReference;





public class KStack {
	private class Node{
		public int item;
		public Node next;
		public Node(int it){
			this.item = it;
		}
	}
	
AtomicReference<Node> top;
	
	public KStack(){
		top = new AtomicReference<Node>();
	}
	
	public void push(int item){
		Node newHead = new Node(item);
		Node oldHead;
		do{
			oldHead = top.get();
			newHead.next = oldHead;
			top.compareAndSet(oldHead, newHead);
		}while(top.get().next != oldHead);
	}
	
	public int pop(){
		Node oldHead;
		Node newHead;
		do{
			oldHead = top.get();
			if(oldHead == null)
				return -1;
			newHead = oldHead.next;
		}while(!top.compareAndSet(oldHead, newHead));
		return oldHead.item;
	}
	

}
