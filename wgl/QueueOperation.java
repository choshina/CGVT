package wgl;

public abstract class QueueOperation extends Operation implements Cloneable{
	
	protected int opID;
	protected int opType;
	protected int threadID;
	protected String opName;
	
	protected int begintime;
	protected int endtime;
	
	
	public QueueOperation(int oi,int ot,int ti,int bt,int et){
		opID = oi;
		opType = ot;
		threadID = ti;
		
		begintime = bt;
		endtime = et;
		if(ot == 0){
			opName = "enQueue";
		}else if(ot == 1){
			opName = "deQueue";
		}
	}
	
	public int getOpType() {
		return opType;
	}

	public int getThreadId() {
		return threadID;
	}

	public String getOpName() {
		return opName;
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
	
	public abstract int getOpArg();
	
	public abstract int getOpRet();

	@Override
	public abstract Operation deepCopy();


	
	
	
}
