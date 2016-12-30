package wgl;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;

public class DoubleLinkedList {
	private HistoryNode head;
	private HistoryNode tail;
	
	private int threadNum;
	private int[] traceNum;
	
	public DoubleLinkedList(){
		head = new HistoryNode(-1,-1,null);
		tail = head;
	}
	
	public int getThreadNum() {
		return threadNum;
	}




	public void setThreadNum(int threadNum) {
		this.threadNum = threadNum;
	}




	public int[] getTraceNum() {
		return traceNum;
	}




	public void setTraceNum(int[] traceNum) {
		this.traceNum = traceNum;
	}




	public void lift(HistoryNode n){
		
		n.prev.next = n.next;
		n.next.prev = n.prev;
		HistoryNode matchNode = n.match;
		matchNode.prev.next = matchNode.next;
		if(matchNode.next!=null)
			matchNode.next.prev = matchNode.prev;
		
	}
	
	public void unlift(HistoryNode n){
		
		HistoryNode matchNode = n.match;
		//System.out.println(n.match);
		matchNode.prev.next = matchNode;
		if(matchNode.next!=null)
			matchNode.next.prev = matchNode;
		n.prev.next = n;
		n.next.prev = n;
	}
	
	public void insert(HistoryNode node){
		node.prev = tail;
		tail.next = node;
		tail = node;
		
	}
	
	public HistoryNode getHead(){
		return head;
	}
	
	
	
	public int length(){
		return tail.getNodeId()+1;
	}

	public void print() {
		// TODO Auto-generated method stub
		
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("o"));
				HistoryNode n = head;
				while(n.hasNext()){
					n = n.getNext();
					bw.write(n.toString()+"\n");
					
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
	public void outputHistory(){
		
		try {
			BufferedWriter bw = new BufferedWriter(new FileWriter("methodInfo"));
			HistoryNode n = head;
			while(n.hasNext()){
				n = n.getNext();
				if(n.isCall()){
					bw.write(""+n.getOpId()+" ");
					bw.write(""+n.getType()+" ");
					bw.write(""+n.getArg()+" ");
					bw.write(""+n.getThreadId()+" \n");
				}
			}
			bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public int[] getThreadOrder(){
		int length = length()/2;//由于tail中nodeID没有改变，所以即使history被lift，
		                        //这里的length()仍然会返回history本身的length。
		                        //但这是不对的，无法反应history在各个时刻的变化。
		int[] tho = new int[length];
		for(int i = 0;i < length;i++){
			tho[i] = -1;
		}
		HistoryNode hn = head;
		
		while(true){
			hn = hn.next;
			if(hn==null){
				break;
			}
			if(hn.isCall()){
				tho[hn.getOpId()] = hn.getThreadId();		
			}
//			if(!hn.hasNext()){
//				break;
//			}
		}
		return tho;
	}

	public DoubleLinkedList copy() {
		HistoryNode hn = head;
		DoubleLinkedList d = new DoubleLinkedList();
		while(hn.hasNext()){
			//System.out.println("a");
			HistoryNode h = (HistoryNode) hn.getNext().deepCopy();
			hn = hn.getNext();
//			System.out.println(h.toString());
			d.insert(h);
		}
		
		d.threadNum = this.threadNum;
		d.traceNum = this.traceNum;
		return d;
	}
	
	public HistoryNode getOperationByOpID(int opID){
		HistoryNode hn = head;
		while(hn.hasNext()){
			hn = hn.getNext();
			if(hn.isCall()&&hn.getOpId()==opID){
				return hn;
			}
		}
		return null;
	}
	

	
	
	
/*	public int[][] formOpID(){
		HistoryNode hn = head;
		
		int [][] thopid = new int[threadNum][traceNum];
		int[] index = new int[threadNum];
		for(int i = 0;i < threadNum;i++){
			index[i]= 0;
		}
		while(hn.hasNext()){
			hn = hn.getNext();
			if(hn.isCall()){
				int thr = hn.getThreadId();
				
				int op = hn.getOpId();
				thopid[thr][index[thr]] = op;
				index[thr]++;
			}else{
				
			}
		}
		return thopid;
	}*/

	/*
	 * 忽略了call早于o,且ret晚于o的方法
	 */
	public Queue<Operation> getConcurrentOperation(Operation first0) {
		int oid = first0.getOpId();
		int top = -1;
		HistoryNode hn = head;
		Queue<Operation> re = new LinkedList<Operation>();
		
		boolean record = false;
		while(hn.hasNext()){
			hn = hn.getNext();
			if(hn.getOpId() == oid&&hn.isCall()){
				record = true;
			}else if(hn.getOpId()==oid&&hn.isCall()==false){
				record = false;
			}else if(record ==true){
				Operation o = hn.getOperation();
				if(o.getOpId() == top){
					continue;
				}else{
					top = o.getOpId();
					re.offer(o);
				}
			}
		}
		return re;
	}
	
	public HistoryNode getCallNode(int opid){
		HistoryNode hn = head;
		while(head.hasNext()){
			hn = hn.getNext();
			if(hn.getOpId()==opid&&hn.isCall()){
				return hn;
			}
		}
		return null;
	}
	
	public void deleteOp(Operation op) {
		HistoryNode hn = head;
		while(head.hasNext()){
			hn = hn.getNext();
			if(hn.getOpId()==op.getOpId()){
				break;
			}
		}
		this.lift(hn);
	}

	public void modifyOpReturn(Operation op) {
		HistoryNode hn = head;
		int count = 0;
		while(head.hasNext()){
			hn = hn.getNext();
			if(hn.getOpId()==op.getOpId()){
				count++;
				hn.setOpRet(op.getOpRet());
			}
			if(count==2)
				break;
		}
		//
	}
	
	public void printCurrent() {
		// TODO Auto-generated method stub
		
			try {
				BufferedWriter bw = new BufferedWriter(new FileWriter("current"));
				HistoryNode n = head;
				while(n.hasNext()){
					n = n.getNext();
					bw.write(n.toString()+"\n");
					
				}
				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
	}
	
}
