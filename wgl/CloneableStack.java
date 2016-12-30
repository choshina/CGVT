package wgl;

import java.util.Stack;

public class CloneableStack extends CloneableSpecification{
	
	private Stack<Integer> stack;
	
	public CloneableStack(){
		stack = new Stack<Integer>();
	}

	@Override
	CloneableSpecification deepCopy() {
		CloneableStack cs = new CloneableStack();
		CloneableStack t = new CloneableStack();
		while(!stack.empty()){
			int tp = this.stack.pop();
			t.stack.push(tp);
		}
		while(!t.stack.empty()){
			int tp = t.stack.pop();
			this.stack.push(tp);
			cs.stack.push(tp);
		}
		return cs;
	}

	@Override
	boolean apply(Operation op) {
		int type = op.getOpType();
		int arg = op.getOpArg();
		int ret = op.getOpRet();
		int hereRet = 0;
		switch(type){
		case 0:
			this.stack.push(arg);
			break;
			
		case 1:
			if(this.stack.empty())
				hereRet = -1;
			else
				hereRet = this.stack.pop();
			break;
			
		}
		if(type == 0)
			return true;
		else
			return ret == hereRet;
		
	}

	@Override
	boolean equal(CloneableSpecification cs) {
		CloneableStack t1= (CloneableStack)this.deepCopy();
		CloneableStack tt2 = (CloneableStack)cs;
		CloneableStack t2 = (CloneableStack) tt2.deepCopy();
		if(t1.stack.size()!= t2.stack.size())
			return false;
		while(!t1.stack.empty()){

			if(t1.stack.pop()!=t2.stack.pop())
				return false;
		}
		return true;
		
	}
	
	public void push(int v){
		this.stack.push(v);
	}
	
	public int pop(){
		if(this.stack.empty())
			return -1;
		else
			return this.stack.pop();
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "";
		CloneableStack t = new CloneableStack();
		while(!stack.empty()){
			int tp = this.stack.pop();
			t.stack.push(tp);
		}
		while(!t.stack.empty()){
			int tp = t.stack.pop();
			this.stack.push(tp);
			s=s+" "+tp;
		}
		return s;
	}

}
