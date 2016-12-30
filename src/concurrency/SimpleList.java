package src.concurrency;

public class SimpleList {
	private class Node{
		public int val = Integer.MAX_VALUE;
		public Node next = null;
	}

	private Node head;
	private Node tail;

	public SimpleList(){
		head = new Node();
		tail = new Node();
		head.val = Integer.MIN_VALUE;
		tail.val = Integer.MAX_VALUE;
		head.next = tail;
	}

	public SimpleList clone() {
		SimpleList l = new SimpleList() ;
		Node curr = head.next;
		while (curr!=tail) {
			l.add(curr.val);
			curr = curr.next;
		}
		return l ;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append('{');
		Node curr = head.next;
		while (curr!=tail) {
			s.append(curr.val);
			curr = curr.next;
		}
		s.append('}');
		return s.toString() ;
	}

	public Boolean add(int v){
		Node pred = head,curr = pred.next;
		while(curr.val<v){
			pred = curr;
			curr = curr.next;
		}
		synchronized (this){
			if(curr.val == v)
				return false;
			Node node = new Node();
			node.val = v;
			node.next = curr;
			pred.next = node;
			return true;
		}
	}

	public Boolean remove(int v){
		synchronized (this){
			Node pred = head,curr = pred.next;
			while(curr.val<v){
				pred = curr;
				curr = curr.next;
			}
			if(curr.val == v){
				pred.next = curr.next;
				return true;
			}else{
				return false;
			}
		}
	}
}
