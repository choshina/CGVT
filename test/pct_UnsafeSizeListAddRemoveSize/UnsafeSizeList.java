package test.pct_UnsafeSizeListAddRemoveSize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import pct.Scheduler;





public class UnsafeSizeList {

	class Node{
		int data;
		Node next;
	}
	
	private Node head;
	
	private Node tail;
	
	private Scheduler scheduler;
	
	private Lock lock = new ReentrantLock();
	
	public UnsafeSizeList(){
		head = new Node();
		head.data = Integer.MIN_VALUE;
		tail = new Node();
		tail.data = Integer.MAX_VALUE;
		head.next = tail;
		tail.next = null;
		scheduler = new Scheduler(false);
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
			scheduler.schedule();
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
			scheduler.schedule();
		}
	}
	
	public int size(){
		int count = 0;
		Node n = head.next;
		while(n.data < Integer.MAX_VALUE){
			
			count ++;
			scheduler.schedule();
			n = n.next;
		}
		return count;
	}
	
	public void resetScheduler(Scheduler s){
		scheduler = s;
	}
	

}

