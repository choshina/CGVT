package wgl;

public abstract class SetOperation extends Operation{
	
	//operation type: 0-add 1-remove 2-contain 3-size
	
	protected int opID;
	protected int opType;
	protected int threadId;
	protected String opName;
	
	
	protected int begintime;
	protected int endtime;
	
	
	
	public SetOperation(int opid,int ot,int ti,int bt,int et){
		opID = opid;
		opType = ot;
		threadId = ti;
		//opArg = oa;
		//opRet = or;
		
		begintime = bt;
		endtime = et;
		
			
		
	}


	public int getOpType() {
		return opType;
	}

	public int getThreadId() {
		return threadId;
	}

	

	/*public int getOpArg() {
		return opArg;
	}*/
	
	public abstract int getOpArg();

	
	public abstract Operation deepCopy();
	/*public Operation deepCopy(){
		SetOperation so = new SetOperation(opID, opType, threadId, opArg,opRet, 
				begintime, endtime);
		return so;
		
	}*/

	public abstract int getOpRet();
	/*public int getOpRet(){
		// TODO Auto-generated method stub
		int r = (opRet == true? 1:0);
		return r;
	}*/

	@Override
	public int getBegintime() {
		// TODO Auto-generated method stub
		return begintime;
	}

	@Override
	public int getEndtime() {
		// TODO Auto-generated method stub
		return endtime;
	}

	

	@Override
	public int getOpId() {
		// TODO Auto-generated method stub
		return opID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return opName+opID+begintime+endtime;
	}
	
	
}
