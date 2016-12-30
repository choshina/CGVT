package test.BugUnknownList4AddRemove ; 




public class BugUnknownList4 {
	public class Node{
		int item;
		boolean marked;
		Node next;
		public Node(int v){
			item = v;
			marked = false;
		}
	}
	
	Node head;
	
	Scheduler scheduler;
	
	public BugUnknownList4(){
		head = new Node(-1);
		Node tail = new Node(Integer.MAX_VALUE);
		head.next = tail;
		
		scheduler = new Scheduler(1);
	}
	
	
	
	public boolean add(int v){
		while(true){
			Window w = locate(head,v);
			scheduler.schedule();
			Node pred = w.pred;
			Node curr = w.curr;
			scheduler.schedule();
			if(curr.item == v){
				return false;
			}
			scheduler.schedule();
			Node n = new Node(v);
			n.next = curr;
			scheduler.schedule();
			synchronized(this){
				if(pred.marked || pred.next!=curr)
					continue;
				scheduler.schedule();
				pred.next = n;
				scheduler.schedule();
				return true;
			}
		}
	}
	
	public boolean remove(int v){
		while(true){
			Window w = locate(head,v);
			scheduler.schedule();
			Node pred = w.pred;
			Node curr = w.curr;
			
			if(curr.item != v)
				return false;
			curr.marked = true;
			Node r = curr.next;
			scheduler.schedule();
			synchronized(this){
				if(pred.marked || pred.next!= curr)
					continue;
				scheduler.schedule();
				pred.next = r;
				scheduler.schedule();
				return true;
			}
		}
	}
	
	public Window locate(Node head, int v){
		Node pred = head;
		  Node curr = head.next;
		  scheduler.schedule();
		  while(curr.item < v){
			  scheduler.schedule();
			  pred = curr;
			  scheduler.schedule();
			  curr = curr.next;
		  }
		  return new Window(pred,curr);
	}
	
	class Window{
		public Node pred;
		public Node curr;
		Window(Node p, Node c){
			pred = p;
			curr = c;
		}
	}
	
	public void resetScheduler(Scheduler s){
		scheduler = s;
	}
	

}
