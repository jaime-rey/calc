package task1v02;

public class CalculatorPRN {

	public CalculatorPRN() {
		// TODO Auto-generated constructor stub
	}
	
	public static float calculate(PolRevNot prn) {
		switch (prn.getOperation()) {
		case SUM:
			return prn.getOp1()+prn.getOp2();
		case SUBTRACTION:
			return prn.getOp1()-prn.getOp2();
		case MULTIPLICATION:
			return prn.getOp1()*prn.getOp2();
		case DIVISION:
			return prn.getOp1()/prn.getOp2();
		default:
			return 0;
		}
		
		
	}
	
}
