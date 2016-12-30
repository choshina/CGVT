package src.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class UnsafeSizeList {

	class Node{
		int data;
		Node next;
	}
	
	private Node head;
	
	private Node tail;
	
	private Lock lock = new ReentrantLock();
	
	public UnsafeSizeList(){
		head = new Node();
		head.data = Integer.MIN_VALUE;
		tail = new Node();
		tail.data = Integer.MAX_VALUE;
		head.next = tail;
		tail.next = null;
		
	}
	
	public boolean add(int v){
		Node pred,curr;
		lock.lock();
		try{
			pred = head;
			curr = pred.next;
			while(curr.data<v){
				pred = curr;
				curr = curr.next;
			}
			if(curr.data == v){
				return false;
			}else{
				Node node = new Node();
				node.data = v;
				node.next = curr;
				pred.next = node;
				return true;
			}
		}finally{
			lock.unlock();
		}
	}
	
	public boolean remove(int v){
		Node pred,curr;
		lock.lock();
		try{
			pred = head;
			curr = pred.next;
			while(curr.data<v){
				pred = curr;
				curr = curr.next;
			}
			if(curr.data == v){
				pred.next = curr.next;
				return true;
			}else{
				return false;
			}
		}finally{
			lock.unlock();
		}
	}
	
	public int size(){
		int count = 0;
		Node n = head.next;
		while(n.data < Integer.MAX_VALUE){
			count ++;
			n = n.next;
		}
		return count;
	}
	
/*	public static void main(String[] s){
		UnsafeSizeList usl = new UnsafeSizeList();
		System.out.println(usl.add(4));
		System.out.println(usl.add(5));
		System.out.println(usl.add(4));
		System.out.println(usl.remove(4));
		System.out.println(usl.remove(7));
		System.out.println(usl.add(6));
		System.out.println(usl.size());
	}*/
	
}

