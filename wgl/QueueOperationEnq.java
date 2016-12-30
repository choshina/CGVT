package wgl;

public class QueueOperationEnq extends QueueOperation{
	private int opArg;
	public QueueOperationEnq(int opid, int ot, int ti, int oa,int bt, int et){
		super(opid,ot,ti,bt,et);
		opArg = oa;
	}
	
	public QueueOperationEnq(int type, int argu) {
		// TODO Auto-generated constructor stub
		super(-2,type,-2,-2,-2);
		opArg = argu;
	}

	public int getOpArg(){
		return opArg;
	}
	
	public int getOpRet(){
		return -1;
	}
	
	public void setOpRet(int i){
		return;
	}
	
	public Operation deepCopy(){
		QueueOperationEnq qoe = new QueueOperationEnq(opID,opType,threadID,opArg,
				begintime,endtime);
		return qoe;
	}
}
