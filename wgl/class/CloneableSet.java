package wgl;

import java.util.HashSet;
import java.util.Iterator;

public class CloneableSet extends CloneableSpecification {
	
	private HashSet<Integer> hs;
	
	public CloneableSet(){
		hs = new HashSet<Integer>();
	}

	@Override
	public CloneableSpecification deepCopy() {
		// TODO Auto-generated method stub
		CloneableSet chs = new CloneableSet();
		Iterator<Integer> it = this.hs.iterator();
		while(it.hasNext()){
			chs.hs.add(it.next());
		}
		return chs;
	}

	@Override
	boolean apply(Operation op) {
		// TODO Auto-generated method stub
		int opType = op.getOpType();
		int arg = op.getOpArg();
		int ret = op.getOpRet();
		boolean retB = (ret == 0?false:true);//CloneableSet-specific
		boolean ifSucc = false;
		switch(opType){
		case 0:
			
			ifSucc = this.hs.add(arg);
			break;
		case 1:
			ifSucc = this.hs.remove(arg);
			break;
		case 2:
			ifSucc = this.hs.contains(arg);
			break;
		default:
			break;
		}
		return ifSucc == retB;
	}
	
	public int gainReturn(Operation op){
		int opType = op.getOpType();
		int arg = op.getOpArg();
		boolean ifSucc = false;
		switch(opType){
		case 0:
			ifSucc = this.hs.add(arg);
			break;
		case 1:
			ifSucc = this.hs.remove(arg);
			break;
		case 2:
			ifSucc = this.hs.contains(arg);
			break;
		default:
			break;
		}
		return ifSucc==false?0:1;
	}


	@Override
	public boolean equal(CloneableSpecification cs) {
		// TODO Auto-generated method stubi
		
		CloneableSet c = (CloneableSet) cs;
		Iterator<Integer> it1 = hs.iterator();
		Iterator<Integer> it2 = c.hs.iterator();
		while(true){
			boolean b1 = it1.hasNext();
			boolean b2 = it2.hasNext();
			if(b1==true&&b2==true){
				if(it1.next()!=it2.next()){
					return false;
				}else{
					continue;
				}
			}else if((b1==true&&b2==false)||(b1==false&&b2==true)){
				return false;
			}else if(b1==false&&b2==false){
				break;
			}else{
				return false;
			}
		}
		return true;
		
	}
	
	public boolean add(int e){
		return hs.add(e);
	}
	
	public boolean remove(int e){
		return hs.remove(e);
	}
	
	public boolean contains(int e){
		return hs.contains(e);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		String s = "{";
		Iterator<Integer> i = hs.iterator();
		
		while(i.hasNext()){
			s+=i.next();
			s+=" ";
		}
		s+="}";
		return s;
		
	}
	
}
