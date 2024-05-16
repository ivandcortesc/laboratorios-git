package service.imp;

import entity.Order;
import service.OrderProcessorService;


public class OrderProcessorServiceImp implements OrderProcessorService {

    private final VerifyInventoryServiceImp verifyInventoryServiceImp = new VerifyInventoryServiceImp();
    private NotifyCustomerServiceImp notifyCustomerServiceImp  = new NotifyCustomerServiceImp();
    private ProcessExpressPaymentServiceImp processExpressPaymentServiceImp = new ProcessExpressPaymentServiceImp();

    public OrderProcessorServiceImp() {
    }

    @Override
    public void processOrder(Order order) {

        if (order.getType().equals("express")) {
            System.out.println("Processing express order");
            boolean result= processExpressPaymentServiceImp.processPayment(order);
        } else  if (order.getType().equals("standard")) {
            System.out.println("Processing standard order");
        }
        verifyInventoryServiceImp.verifyInventory(order);
        notifyCustomerServiceImp.notifyCustomer(order);

    }
}
