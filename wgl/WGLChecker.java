package wgl;

import java.util.HashSet;
import java.util.Stack;

public abstract class WGLChecker {
	
	class CallsNode{
		HistoryNode entry;
		CloneableSpecification cs;
		CallsNode(HistoryNode e,CloneableSpecification c){
			/*if(e.getNodeId() == 66)
				System.out.println("sfs"+e.toString());*/
			entry = e;
			//entry = (HistoryNode) e.deepCopy();
			cs = c.deepCopy();
		}
	}
	
	
	
	protected Stack<CallsNode> calls;

	protected Cache cache;
	CacheOrder cacheOrder;
	HashSet<CacheOrder> cachePath ;
	
	protected CloneableSpecification sTemp;
	protected CloneableSpecification sequentialSpec;
	
	public WGLChecker(){
		calls = new Stack<CallsNode>();
		cache = new Cache();
		cacheOrder = new CacheOrder();
		cachePath = new HashSet<CacheOrder>();
	}
	
	public boolean check(DoubleLinkedList history){
		HistoryNode head = history.getHead();
		HistoryNode entry = head.getNext();
		boolean islinear = false;
		
		int [] thro = history.getThreadOrder();
		Linearized.setTho(thro);
		
		int thrNum = history.getThreadNum();
		int[] traNum = history.getTraceNum();
		Linearized linearized = new Linearized(thrNum,traNum);
		Linearized linearized2 = new Linearized(thrNum,traNum);
		
		RecordOrder ro = new RecordOrder();
		//Stack<LinearState> cacheOrder = new Stack<LinearState>();
		int coFlags = 0;//两种，0为在增加，1为在退栈
		
		
		boolean exeFlag = false;
		LinearState ls = null;
		int iii = 0;
		while(head.hasNext()){
			
		//	System.out.println(entry);
			
			/*if(entry.getNodeId() == 1152){
				System.out.println(entry.hashCode());
				System.out.println(iii+sequentialSpec.toString());
				System.out.println(entry.next.prev.hashCode());
			}*/
			
			
		/*	if(entry.next.prev != entry){
				System.out.println(iii+sequentialSpec.toString());
				System.out.println(entry);
			}*/
			
		/*	if(entry.getOpId() == 753){
				System.out.println(sequentialSpec.toString());
				System.out.println(entry);
			}*/

			exeFlag = false;
	
			if(entry.isCall()){
				
				sTemp = sequentialSpec.deepCopy();
				islinear = sTemp.apply(entry.getOperation());
		
				ro.push(entry.getOpId());//160908改 不一定对
				
				if (islinear) {
	
					linearized2 = linearized.copy();
					linearized2.modifyLinearByIndex(entry.getOpId(), true);
					
					
					
					ls = new LinearState(linearized2, sTemp,ro,entry.getOperation());
					exeFlag = true;
				}
				
				if (exeFlag&&(!cache.exist(ls))) {

					if(coFlags==1){
						coFlags = 0;
					}
					cacheOrder.push(ls);
					cache.add(ls);
					calls.push(new CallsNode(entry, sequentialSpec));
					
					sequentialSpec = sTemp.deepCopy();// 有可能出错，需要做实验
					linearized.modifyLinearByIndex(entry.getOpId(), true);
					
					history.lift(entry);
		
					entry = head.next;
		
				} else {
					ro.pop();
					entry = entry.next;
				}
			}else{//handle return entry
				
			
				if(calls.empty()){
					//System.out.println("asf"+iii);
					return false;
				}
				
				CallsNode cn = calls.pop();	
				
				
				if(coFlags==0){
					coFlags = 1;
					cachePath.add(cacheOrder.copy());
				}
				cacheOrder.pop();

				ro.pop();
				
//				System.out.println("prev:"+entry.getNodeId());
				entry = cn.entry;

				linearized.modifyLinearByIndex(entry.getOpId(), false);
				sequentialSpec = cn.cs;
				history.unlift(entry);

				entry = entry.getNext();
			}
			
			iii++;
			
		}
		
		
		if(calls.size() == history.length()/2)
			return true;
		return false;
	}

	public Cache deepCopyCache() {
		return cache.copy();
	}
	
	/*
	 * 必须在check()之后使用
	 */
	public HashSet<CacheOrder> getcachepath(){
		
		return cachePath;
		
	}
	
	public abstract void preprocess(String p);
	
}
