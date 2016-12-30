package wgl;

public class SetOperation extends Operation{
	private int opID;
	private int opType;
	private int threadId;
	private String opName;
	
	private int opArg;
	private boolean opRet;
	
	private int begintime;
	private int endtime;
	
	public SetOperation(int ot,int oa){
		opType = ot;
		opArg = oa;
		opRet = false;
	}
	
	public SetOperation(int opid,int ot,int ti,int oa,boolean or,int bt,int et){
		opID = opid;
		opType = ot;
		threadId = ti;
		opArg = oa;
		opRet = or;
		
		begintime = bt;
		endtime = et;
		switch(opType){
		case 0:
			opName = "add";
			break;
		case 1:
			opName = "remove";
			break;
		case 2:
			opName = "contain";
			break;
			
		}
	}

//	public int getBegintime() {
//		return begintime;
//	}
//
//	public int getEndtime() {
//		return endtime;
//	}

	public int getOpType() {
		return opType;
	}

	public int getThreadId() {
		return threadId;
	}

	public String getOpName() {
		return opName;
	}

	public int getOpArg() {
		return opArg;
	}

	
	
	public Operation deepCopy(){
		Operation so = null;
		try {
			so = (Operation) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return so;
	}

	public int getOpRet(){
		// TODO Auto-generated method stub
		int r = (opRet == true? 1:0);
		return r;
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
	public void setOpRet(int i) {
		// TODO Auto-generated method stub
		opRet = i==1?true:false;
	}

	@Override
	public int getOpId() {
		// TODO Auto-generated method stub
		return opID;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return opName+opID+opArg+opRet+begintime+endtime;
	}
	
	
}
