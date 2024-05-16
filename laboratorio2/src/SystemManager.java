import entity.*;
import service.OrderProcessorService;

import java.util.Map;

public class SystemManager {
    private final Map<String, OrderProcessorService> orderProcessors;

    public SystemManager(Map<String, OrderProcessorService> orderProcessors) {
        this.orderProcessors = orderProcessors;
    }

    public void processOrder(Order order) {
        orderProcessors.get(order.getType()).processOrder(order);
    }
}