package service.imp;

import entity.Order;
import service.OrderProcessorService;
import service.PaymentProcessorService;

public class ProcessExpressPaymentServiceImp implements PaymentProcessorService {

    public ProcessExpressPaymentServiceImp() {
    }

    @Override
    public boolean processPayment(Order order) {
        if (order.getAmount() > 0) {
            System.out.println("Processing express order" + order.getAmount() + "Priority" + order.getPriority());
            return true;
        }else{
            throw new Error("Express payment failed");
        }

    }

}
