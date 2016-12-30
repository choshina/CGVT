package wgl;

public abstract class CloneableSpecification{
	
	abstract CloneableSpecification deepCopy();
	abstract boolean apply(Operation op);
	abstract boolean equal(CloneableSpecification cs);
	public abstract String toString();
	
}
