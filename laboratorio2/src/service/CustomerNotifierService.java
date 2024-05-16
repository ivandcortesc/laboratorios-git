package service;
import entity.Order;
public interface CustomerNotifierService {
    void notifyCustomer(Order order);
}
