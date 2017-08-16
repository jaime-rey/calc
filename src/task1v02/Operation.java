package task1v02;

public enum Operation {
	LEFTPARENTHESIS('(', 1), RIGHTPARENTHESIS(')', 1), MULTIPLICATION('*', 2), DIVISION('/', 2), SUM('+',
			3), SUBTRACTION('-', 3), DEFAULT(' ', -1);

	private final Character symbol;
	private final int priority;

	private Operation(char symbol, int priority) {
		this.symbol = symbol;
		this.priority = priority;
	}

	public char symbol() {
		return symbol;
	}

	public int priority() {
		return priority;
	}
}
