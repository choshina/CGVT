package src.concurrency;



public class BugUnknownList3 {
	public class Node{
		int item;
		Node next;
		public Node(int v){
			item = v;
		}
	}
	
	Node head;
//	Node tail;
	
	public BugUnknownList3(){
		head = new Node(-1);
		Node tail = new Node(Integer.MAX_VALUE);
		head.next = tail;
	}
	
	public boolean add(int key){
		Window w = locate(head, key);
		Node pred = w.pred;
		Node curr = w.curr;
		synchronized(this){
			if(pred.next == curr){
				if(curr.item == key)
					return false;
				Node n = new Node(key);
				n.next = curr;
				pred.next= n;
				return true;
			}
			return false;
		}
	}
	
	public boolean remove(int key){
		Window w = locate(head, key);
		Node pred = w.pred;
		Node curr = w.curr;
		synchronized(this){
			if(pred.next == curr){
				if(curr.item != key)
					return false;
				Node r = pred.next;
				pred.next = r;
				return true;
			}else
				return false;
		}
	}
	
	public boolean contain(int key){
		Window w = locate(head, key);
		Node curr = w.curr;
		synchronized(this){
			if(curr.item == key)
				return true;
			else
				return false;
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
