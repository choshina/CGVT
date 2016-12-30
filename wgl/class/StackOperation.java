package wgl;

public abstract class StackOperation extends Operation implements Cloneable{
	protected int opID;
	protected int opType;//0 push 1 pop
	protected int threadID;
	private String opName;
	

	protected int begintime;
	protected int endtime;
	
	public StackOperation(int opid,int ot,int ti,int bt,int et){
		opID = opid;
		opType = ot;
		threadID = ti;
		begintime = bt;
		endtime = et;
		
	}
	
	public abstract int getOpArg();

	@Override
	public int getOpType() {

		return opType;
	}

	@Override
	public abstract int getOpRet();

	@Override
	public int getThreadId() {
		// TODO Auto-generated method stub
		return threadID;
	}

	

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
	
	public abstract Operation deepCopy();

}
