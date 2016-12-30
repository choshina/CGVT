package src.concurrency;

import java.util.concurrent.atomic.AtomicReference;


public class LFTreiberStack {
	private class Node{
		public int item;
		public Node next;
		public Node(int it){
			this.item = it;
		}
	}
	
	AtomicReference<Node> top;
	
	public LFTreiberStack(){
		top = new AtomicReference<Node>();
	}
	
	public void push(int item){
		Node newHead = new Node(item);
		Node oldHead;
		do{
			oldHead = top.get();
			newHead.next = oldHead;
			
		}while(!top.compareAndSet(oldHead, newHead));
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
