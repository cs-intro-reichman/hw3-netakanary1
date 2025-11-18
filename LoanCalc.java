public class LoanCalc {

	static double epsilon = 0.001; // accuracy expected by tests
	static int iterationCounter;

	public static void main(String[] args) {
		double loan = Double.parseDouble(args[0]);
		double rate = Double.parseDouble(args[1]);
		int n = Integer.parseInt(args[2]);

		System.out.println("Loan = " + loan + ", interest rate = " + rate + "%, periods = " + n);

		// Brute force
		System.out.print("\nPeriodical payment, using brute force: ");
		System.out.println((int) bruteForceSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);

		// Bisection
		System.out.print("\nPeriodical payment, using bi-section search: ");
		System.out.println((int) bisectionSolver(loan, rate, n, epsilon));
		System.out.println("number of iterations: " + iterationCounter);
	}

	private static double endBalance(double loan, double rate, int n, double payment) {
		double sum = loan;
		for (int i = 0; i < n; i++) {
			sum = (sum - payment) * (1 + rate / 100.0);
		}
		return sum;
	}

	public static double bruteForceSolver(double loan, double rate, int n, double epsilon) {
		double guess = loan / n;
		iterationCounter = 0;

		while (endBalance(loan, rate, n, guess) > 0) {
			guess += epsilon;
			iterationCounter++;
		}
		return guess;
	}

	public static double bisectionSolver(double loan, double rate, int n, double epsilon) {
		iterationCounter = 0;

		double lo = 0;
		double hi = loan;

		while (hi - lo >= epsilon) {
			iterationCounter++;
			double mid = (lo + hi) / 2;

			if (endBalance(loan, rate, n, mid) > 0) {
				lo = mid;
			} else {
				hi = mid;
			}
		}

		return (lo + hi) / 2;
	}
}