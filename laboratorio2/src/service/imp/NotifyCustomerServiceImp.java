package service.imp;

import entity.Order;
import service.CustomerNotifierService;

public class NotifyCustomerServiceImp implements CustomerNotifierService {
    @Override
    public void notifyCustomer(Order order) {
        System.out.println("Sending email "+ order.getEmail() + "Your order has been processed.");
    }
}
