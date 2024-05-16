package service.imp;

import entity.Order;
import service.PaymentProcessorService;

public class ProcessStandardPaymentServiceImp implements PaymentProcessorService {

    public ProcessStandardPaymentServiceImp() {
    }

    @Override
    public boolean processPayment(Order order) {
        if (order.getAmount() > 0) {
            System.out.println("Processing standard order" + order.getAmount() + "Priority" + order.getPriority());
            return true;
        }else{
            throw new Error("Express payment failed");
        }

    }
}