package services;

public class PaypalService implements OnlinePaymentService {
	
	private static final double PYMENT_FEE = 0.02;
	private static final double MONTHLY_INTEREST = 0.01;

	@Override
	public double paymentFee(double amount) {
		
		return amount * PYMENT_FEE;
	}

	@Override
	public double interest(double amount, int months) {
	
		return amount * months * MONTHLY_INTEREST;
	}

}
