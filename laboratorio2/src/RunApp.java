import entity.Order;
import service.imp.OrderProcessorServiceImp;
import service.OrderProcessorService;
import service.imp.ProcessExpressPaymentServiceImp;
import service.imp.ProcessStandardPaymentServiceImp;

import java.util.HashMap;
import java.util.Map;

public class RunApp {
    public static void main(String[] args) {
       Map<String, OrderProcessorService> orderProcessors = new HashMap<>();
       orderProcessors.put("express", new OrderProcessorServiceImp());
       orderProcessors.put("standard",  new OrderProcessorServiceImp());
       //CReo el objeto con datos de la orden
       SystemManager systemManager = new SystemManager(orderProcessors);
       Order order = new Order();
       order.setType("express");
       order.setAmount(100);
       order.setPriority("highPriority");
       order.setOrderId(2345);
       order.setEmail("icortes@digitalfemsa.com");
       systemManager.processOrder(order);
    }
}