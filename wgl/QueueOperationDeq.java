package wgl;

public class QueueOperationDeq extends QueueOperation{
	
	private int opRet;
	
	public QueueOperationDeq(int opid, int ot, int ti, int or, int bt, int et){
		super(opid, ot, ti, bt, et);
		opRet = or;
	}

	public QueueOperationDeq(int type) {
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
	public Operation deepCopy() {
		// TODO Auto-generated method stub
		QueueOperationDeq qod = new QueueOperationDeq(opID,opType,threadID,opRet,
				begintime,endtime);
		return qod;
	}

	@Override
	public void setOpRet(int i) {
		// TODO Auto-generated method stub
		opRet = i;
	}
	
	
}
