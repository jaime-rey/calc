package task1v02;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TermController {
	private static ArrayList<PolRevNot> parenthesis = new ArrayList<>();

	public static PolRevNot evaluate(String term) {

		boolean currentTerm = true;
		char[] termArray = term.toCharArray();
		PolRevNot currentPRN = new PolRevNot();

		for (int position = 0; position < termArray.length; position++) {
			switch (termArray[position]) {
			case '+':

				if (currentTerm) {
					currentTerm = false;
				} else {
					float value = CalculatorPRN.calculate(currentPRN);
					currentPRN = new PolRevNot();
					currentPRN.setOp1(value);
				}
				currentPRN.setOperation(Operation.SUM);
				break;
			case '-':

				if (currentTerm) {
					currentTerm = false;
				} else {
					float value = CalculatorPRN.calculate(currentPRN);
					currentPRN = new PolRevNot();
					currentPRN.setOp1(value);
				}
				currentPRN.setOperation(Operation.SUBTRACTION);
				break;
			case '*':
				/*
				 * si suma o resta creamos auxiliart para calcular el segundo operador de la
				 * actual
				 */
				if (currentPRN.getOperation() == Operation.SUM || currentPRN.getOperation() == Operation.SUBTRACTION) {

					PolRevNot auxPRN = new PolRevNot(currentPRN.getOp2(),
							Float.parseFloat(String.valueOf(termArray[++position])), Operation.MULTIPLICATION);
					Pattern p = Pattern.compile("[\\d]");
					if (position > termArray.length - 2) {
						currentPRN.setOp2(CalculatorPRN.calculate(auxPRN));
						break;
					} else {
						Matcher m = p.matcher(String.valueOf(term.charAt(++position)));
						while (m.matches() && position <= termArray.length) {

							auxPRN.setOp2(generateTerm(auxPRN.getOp2(),
									Float.parseFloat(String.valueOf(termArray[position++]))));

							if (position > termArray.length - 1) {
								break;
							} else {
								m = p.matcher(String.valueOf(term.charAt(position--)));
							}
						}
					}
					currentPRN.setOp2(CalculatorPRN.calculate(auxPRN));

				} else {
					if (currentTerm) {
						currentTerm = false;
					} else {
						float value = CalculatorPRN.calculate(currentPRN);
						currentPRN = new PolRevNot();
						currentPRN.setOp1(value);
					}
					currentPRN.setOperation(Operation.MULTIPLICATION);
				}
				break;
			case '/':
				if (currentPRN.getOperation() == Operation.SUM || currentPRN.getOperation() == Operation.SUBTRACTION) {

					PolRevNot auxPRN = new PolRevNot(currentPRN.getOp2(),
							Float.parseFloat(String.valueOf(termArray[++position])), Operation.DIVISION);
					Pattern p = Pattern.compile("[\\d]");
					if (position > termArray.length - 2) {
						currentPRN.setOp2(CalculatorPRN.calculate(auxPRN));
						break;
					} else {
						Matcher m = p.matcher(String.valueOf(term.charAt(++position)));
						while (m.matches() && position <= termArray.length) {

							auxPRN.setOp2(generateTerm(auxPRN.getOp2(),
									Float.parseFloat(String.valueOf(termArray[position++]))));

							if (position > termArray.length - 1) {
								break;
							} else {
								m = p.matcher(String.valueOf(term.charAt(position--)));
							}
						}
					}
					currentPRN.setOp2(CalculatorPRN.calculate(auxPRN));

				} else {
					if (currentTerm) {
						currentTerm = false;
					} else {
						float value = CalculatorPRN.calculate(currentPRN);
						currentPRN = new PolRevNot();
						currentPRN.setOp1(value);
					}
					currentPRN.setOperation(Operation.DIVISION);
				}
				break;
			case '(':
				if (currentPRN.getOperation() != Operation.DEFAULT) {
					parenthesis.add(currentPRN);
				} else {
					parenthesis.add(new PolRevNot(1, 0, Operation.MULTIPLICATION));
				}

				currentPRN = new PolRevNot();
				currentTerm = true;
				break;
			case ')':
				parenthesis.get(parenthesis.size() - 1).setOp2(CalculatorPRN.calculate(currentPRN));
				currentPRN = parenthesis.get(parenthesis.size() - 1);
				parenthesis.remove(parenthesis.size() - 1);
				break;
			default:
				if (currentTerm) {
					currentPRN.setOp1(
							generateTerm(currentPRN.getOp1(), Float.parseFloat(String.valueOf(termArray[position]))));
				} else {
					currentPRN.setOp2(
							generateTerm(currentPRN.getOp2(), Float.parseFloat(String.valueOf(termArray[position]))));
				}
				break;
			}
		}
		return currentPRN;
	}

	private static float generateTerm(float d, float e) {

		return (10 * d + e);
	}

	public static void main(String[] args) {
		System.out.println(evaluate("2*222/2"));
		System.out.println(CalculatorPRN.calculate(evaluate("2*(2+2)")));
		System.out.println(CalculatorPRN.calculate(evaluate("(2+2)*2")));
		System.out.println(CalculatorPRN.calculate(evaluate("2*23+2")));
		System.out.println(CalculatorPRN.calculate(evaluate("3+1/10")));
		System.out.println(CalculatorPRN.calculate(evaluate("1/10+3")));
		System.out.println(CalculatorPRN.calculate(evaluate("-3+1/10-1/10")));

		System.out.println(CalculatorPRN.calculate(evaluate("3/9*2/8*7")));
		System.out.println(CalculatorPRN.calculate(evaluate("2+2*2")));
		System.out.println(CalculatorPRN.calculate(evaluate("2+2/2")));
		System.out.println(CalculatorPRN.calculate(evaluate("2+(2/(2+2))")));
		// t1.evaluate("2+(2/(2+2))");
	}
}
