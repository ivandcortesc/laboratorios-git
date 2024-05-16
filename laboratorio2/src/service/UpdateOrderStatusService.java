package service;
import entity.Order;
public interface UpdateOrderStatusService {
    void updateOrderStatus(Order order, String status);
}
