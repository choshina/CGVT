package test.KStackPushPop;

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
	Scheduler scheduler;
	
	public KStack(){
		top = new AtomicReference<Node>();
		scheduler = new Scheduler(1);
	}
	
	public void push(int item){
		Node newHead = new Node(item);
		Node oldHead;

		do{
			scheduler.schedule();
			oldHead = top.get();
			newHead.next = oldHead;
			scheduler.schedule();
			top.compareAndSet(oldHead, newHead);
			scheduler.schedule();
		}while(top.get().next != oldHead);
	}
	
	public int pop(){
		Node oldHead;
		Node newHead;
		do{
			oldHead = top.get();
			scheduler.schedule();
			if(oldHead == null)
				return -1;
			newHead = oldHead.next;
		}while(!top.compareAndSet(oldHead, newHead));
		return oldHead.item;
	}
	public void resetScheduler(Scheduler s){
		scheduler = s;
	}

}
