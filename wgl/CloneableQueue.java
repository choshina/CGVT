package wgl;

import java.util.LinkedList;
import java.util.Queue;

public class CloneableQueue extends CloneableSpecification{
	
	private Queue<Integer> queue;
	
	public CloneableQueue(){
		queue = new LinkedList<Integer>();
	}

	@Override
	CloneableSpecification deepCopy() {
		// TODO Auto-generated method stub
		CloneableQueue cq = new CloneableQueue();
		CloneableQueue t = new CloneableQueue();
		while(!this.queue.isEmpty()){
			int temp = this.queue.poll();
			t.queue.offer(temp);
		}
		while(!t.queue.isEmpty()){
			int tp = t.queue.poll();
			this.queue.offer(tp);
			cq.queue.offer(tp);
		}
		return cq;
	}

	@Override
	boolean apply(Operation op) {
		// TODO Auto-generated method stub
		int type = op.getOpType();
		int arg = op.getOpArg();
		int ret = op.getOpRet();
		int hereRet = 0;
		switch(type){
		case 0:
			this.queue.offer(arg);
			break;
		case 1:
			if(this.queue.isEmpty())
				hereRet = -1;
			else
				hereRet = this.queue.poll();
			break;
		}
		
		if(type == 0)
			return true;
		else
			return ret == hereRet;
	}



	@Override
	boolean equal(CloneableSpecification cs) {
		// TODO Auto-generated method stub
		CloneableQueue t1 = (CloneableQueue) this.deepCopy();
		CloneableQueue tt2 = (CloneableQueue) cs;
		CloneableQueue t2 = (CloneableQueue) tt2.deepCopy();
		if(t1.queue.size()!=t2.queue.size()){
			return false;
		}
		while(!t1.queue.isEmpty()){
			if(t1.queue.poll()!=t2.queue.poll()){
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "";
		CloneableQueue cq  = (CloneableQueue) this.deepCopy();
		while(!cq.queue.isEmpty()){
			s = s+" "+cq.queue.poll();
		}
		return s;
	}
	
	public void offer(int v){
		this.queue.offer(v);
	}
	
	public int poll(){
		if(this.queue.isEmpty())
			return -1;
		else
			return this.queue.poll();
	}

}
