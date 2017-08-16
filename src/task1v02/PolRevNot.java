package task1v02;

public class PolRevNot {
	private float op1 = 0;
	private float op2 = 0;
	private Operation operation = Operation.DEFAULT;
	public PolRevNot() {
		// TODO Auto-generated constructor stub
	}
	public PolRevNot(float op1, float op2, Operation operation) {
		super();
		this.op1 = op1;
		this.op2 = op2;
		this.operation = operation;
	}
	public float getOp1() {
		return op1;
	}
	public void setOp1(float op1) {
		this.op1 = op1;
	}
	public float getOp2() {
		return op2;
	}
	public void setOp2(float op2) {
		this.op2 = op2;
	}
	public Operation getOperation() {
		return operation;
	}
	public void setOperation(Operation operation) {
		this.operation = operation;
	}
	@Override
	public String toString() {
		return "PolRevNot [op1=" + op1 + ", op2=" + op2 + ", operation=" + operation + "]";
	}
	
}
