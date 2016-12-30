package wgl;

public class LinearState {
	Linearized linearized;
	CloneableSpecification cs;
	RecordOrder opOrder; 
	Operation op;///////////////////new 04.29//use 06.11
	LinearState(Linearized l,CloneableSpecification c,RecordOrder r,Operation o){
		
		linearized = l.copy();
		cs = c.deepCopy();
		opOrder = r.deepCopy();
		op = (Operation) o.deepCopy();
	}
	
	/*public void setOpOrder(RecordOrder r){
		opOrder = r.copy();
	}*/
	
	public Operation getOperation(){
		return op;
	}
	
	public Linearized getLinearized(){
		return linearized;
	}
	
	public CloneableSpecification getSpecification(){
		return cs;
	}
	
	public LinearState copy(){
		LinearState l = new LinearState(linearized,cs,opOrder,op);
		//this.setOpOrder(opOrder);
		return l;
	}
	
	/*public boolean shorterthan(LinearState ls){
		return this.linearized.shorterthan(ls.linearized);
	}*/
	
	public String toString(){
		return linearized.toString()+cs.toString();
	}
	
	public String toString2(){
		String s = "";
		//s+=" ";
		s+= opOrder.toString();
		return s;
	}
}
