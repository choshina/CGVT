package wgl;

import java.util.Stack;

public class CacheOrder {
	
	private Stack<LinearState> co;
	
	public CacheOrder(){
		co = new Stack<LinearState>();
	}
	
	public void push(LinearState l){
		co.push(l);
	}
	
	public LinearState pop(){
		return co.pop();
	}
	
	public int size(){
		return co.size();
	}
	
	public CacheOrder copy(){
		CacheOrder c = new CacheOrder();
		@SuppressWarnings("unchecked")
		Stack<LinearState> s = (Stack<LinearState>) co.clone();
		c.co = s;
		return c;
	}
	
	public LinearState getLSbeforeOP(Operation op){
		int length = co.size();
		Stack<LinearState> tst = new Stack<LinearState>();
		LinearState ret = null;
		for(int i = 0;i < length;i++){
			LinearState t = co.pop();
			tst.push(t);
			if(t.getOperation().getOpId()==op.getOpId()){
				ret = t;
			}
			co.push(t);
		}
		co.push(tst.pop());
		return ret;
	}
	
	/*
	public LinearState getLSafterOP(){
		
	}*/
	public void print(){
		int leng = co.size();
		Stack<LinearState> t = new Stack<LinearState>();
		for(int i = 0;i < leng;i++){
			LinearState l = co.pop();
			System.out.println(l.toString());
			t.push(l);
		}
		for(int i = 0;i < leng;i++){
			co.push(t.pop());
		}
	}
	
	
}

