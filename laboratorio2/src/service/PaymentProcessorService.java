package service;
import entity.Order;
public interface PaymentProcessorService {
    boolean processPayment(Order order);
}
