package wgl;

import java.util.Stack;

public class RecordOrder {
	private Stack<Integer> opOrder;
	
	public int size(){
		return opOrder.size();
		
	}
	public RecordOrder(){
		opOrder = new Stack<Integer>();
	}
	
	public void push(int i){
		opOrder.push(i);
	}
	
	public void pop(){
		opOrder.pop();
	}
	
	public String toString(){
		Stack<Integer> t = new Stack<Integer>();
		Stack<Integer> cp = this.deepCopy().opOrder;
		while(!cp.empty()){
			t.push(cp.pop());
		}
		String s = "";
		if(t.empty()){
			s+="None";
			return s;
		}
		else{
			while(true){
				
				s = s+ t.pop();
				
				if(t.empty()){
					break;
				}else{
					s = s+ ",";
				}
			}
			//s = s+"]";
			return s;
		}
		
		
	}
	
	
	/*public RecordOrder copy(){
		RecordOrder re = new RecordOrder();
		Stack<Integer> s = (Stack<Integer>) this.opOrder.clone();
		re.opOrder = s;
		return re;
	}*/
	
	public RecordOrder deepCopy(){
		RecordOrder re = new RecordOrder();
		Stack<Integer> t = new Stack<Integer>();
		while(!this.opOrder.empty()){
			int temp = this.opOrder.pop();
			t.push(temp);
		}
		while(!t.empty()){
			int temp = t.pop();
			this.opOrder.push(temp);
			re.opOrder.push(temp);
		}
		return re;
	}
	
	
	
	
	
}
