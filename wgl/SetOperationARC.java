package wgl;

//Set Operation Add, Remove

public class SetOperationARC extends SetOperation{
	private int opArg;
	private boolean opRet;
	
	public SetOperationARC(int ot, int oa){
		
		super(-2, ot, -2 ,-2,-2);
		opArg = oa;
		opRet = false;
	}
	
	public SetOperationARC(int opid, int ot, int tid, int oarg, boolean or, 
			int bt, int et){
		super(opid,ot,tid,bt,et);
		opArg = oarg;
		opRet = or;

	}

	@Override
	public int getOpArg() {
		// TODO Auto-generated method stub
		return opArg;
	}

	@Override
	public Operation deepCopy() {
		// TODO Auto-generated method stub
		SetOperationARC so = new SetOperationARC(opID, opType, threadId, opArg,opRet, 
				begintime, endtime);
		return so;
	}

	@Override
	public int getOpRet() {
		// TODO Auto-generated method stub
		return opRet==true?1:0;
	}

	@Override
	public void setOpRet(int i) {
		// TODO Auto-generated method stub
		opRet = (i==1?true:false);
	}
}
