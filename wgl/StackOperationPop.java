package wgl;

public class StackOperationPop extends StackOperation{
	private int opRet;

	public StackOperationPop(int opid, int ot, int ti, int or, int bt, int et) {
		super(opid, ot, ti, bt, et);
		opRet = or;
	}

	public StackOperationPop(int type) {
		// TODO Auto-generated constructor stub
		super(-2,type,-2,-2,-2);
	}

	@Override
	public int getOpArg() {
		// TODO Auto-generated method stub
		return -1;
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

	@Override
	public Operation deepCopy() {
		// TODO Auto-generated method stub
		StackOperationPop s = new StackOperationPop(opID,opType,threadID,opRet,
				begintime,endtime);
		return s;
	}
	
	

}
