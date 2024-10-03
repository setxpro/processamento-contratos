package services;

import java.util.Calendar;
import java.util.Date;

import entities.Contract;
import entities.Installment;

public class ContractService {
	
	private OnlinePaymentService onlinePaymentService;
	
	public ContractService(OnlinePaymentService onlinePaymentService) {
		this.onlinePaymentService = onlinePaymentService;
	}
	
	public void processContract(Contract contract, int months) {
		
		// Valor integral do contrato / quantidade de meses (600 / 3) = 200
		double basicQuota = contract.getTotalValue() / months;
		
		for (int i = 1; i <= months; i++) {
			
			// 200 + interest(200, meses) = 200 + 1% * 1 = 202
			double updatedQuota = basicQuota + onlinePaymentService.interest(basicQuota, i);
			
			
			// 202 + 2% = 206.04
			double fullQuota = updatedQuota + onlinePaymentService.paymentFee(updatedQuota);
			
			Date dueDate = addMonths(contract.getDate(), i);
			
			contract.getInstallments().add(new Installment(dueDate, fullQuota));
		}
	}
	
	
	// Add months 
	private Date addMonths(Date date, int N) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, N);
		
		return calendar.getTime();
	}
}
