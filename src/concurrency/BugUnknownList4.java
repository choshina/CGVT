package src.concurrency;



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
	
	public BugUnknownList4(){
		head = new Node(-1);
		Node tail = new Node(Integer.MAX_VALUE);
		head.next = tail;
	}
	
	
	
	public boolean add(int v){
		while(true){
			Window w = locate(head,v);
			Node pred = w.pred;
			Node curr = w.curr;
			if(curr.item == v){
				return false;
			}
			Node n = new Node(v);
			n.next = curr;
			synchronized(this){
				if(pred.marked || pred.next!=curr)
					continue;
				pred.next = n;
				return true;
			}
		}
	}
	
	public boolean remove(int v){
		while(true){
			Window w = locate(head,v);
			Node pred = w.pred;
			Node curr = w.curr;

			if(curr.item != v)
				return false;
			curr.marked = true;
			Node r = curr.next;
			synchronized(this){
				if(pred.marked || pred.next!= curr)
					continue;
				pred.next = r;
				return true;
			}
		}
	}
	
	public Window locate(Node head, int v){
		Node pred = head;
		  Node curr = head.next;
		  while(curr.item < v){
			  pred = curr;
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
	

}
