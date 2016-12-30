package wgl;

public abstract class Operation{
	
	public abstract Operation deepCopy();

	public abstract int getOpArg();
	public abstract int getOpType();
	public abstract int getOpRet();//TODO:
	public abstract int getThreadId();
	public abstract void setOpRet(int i);
	public abstract int getBegintime();
	public abstract int getEndtime();
	public abstract int getOpId();
	public abstract String toString();
	
	
}
