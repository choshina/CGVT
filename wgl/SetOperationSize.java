package wgl;

public class SetOperationSize extends SetOperation{
	
	private int opRet;
	
	public SetOperationSize(int ot,int or){
		super(-2,ot,-2,-2,-2);
		opRet = or;
	}
	
	public SetOperationSize(int opid, int ot, int tid, int or, 
			int bt, int et){
		super(opid,ot,tid,bt,et);
		opRet = or;
	}

	@Override
	public int getOpArg() {
		// TODO Auto-generated method stub
		return -1;
	}

	@Override
	public Operation deepCopy() {
		// TODO Auto-generated method stub
		SetOperationSize so = new SetOperationSize(opID, opType, threadId,opRet, 
				begintime, endtime);
		return so;
	}

	@Override
	public int getOpRet() {
		// TODO Auto-generated method stub
		return opRet;
	}

	@Override
	public void setOpRet(int i) {
		// TODO Auto-generated method stub
		opRet = i;
	}
	
	
}
