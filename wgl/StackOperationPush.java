package wgl;

public class StackOperationPush extends StackOperation{
	private int opArg;
	public StackOperationPush(int opid, int ot, int ti, int oa,int bt, int et) {
		super(opid, ot, ti, bt, et);
		opArg = oa;
	}
	public StackOperationPush(int type, int argu) {
		// TODO Auto-generated constructor stub
		super(-2,type,-1,-1,-1);
		opArg = argu;
	}
	@Override
	public int getOpArg() {
		// TODO Auto-generated method stub
		return opArg;
	}
	@Override
	public int getOpRet() {
		// TODO Auto-generated method stub
		return -1;
	}
	@Override
	public void setOpRet(int i) {
		// TODO Auto-generated method stub
		return;
	}
	@Override
	public Operation deepCopy() {
		// TODO Auto-generated method stub
		StackOperationPush s = new StackOperationPush(opID,opType,threadID,opArg,
				begintime,endtime);
		return s;
	}

}
