package service.imp;

import entity.Order;
import service.UpdateOrderStatusService;

public class UpdateOrderStatusServiceImp implements UpdateOrderStatusService {
    @Override
    public void updateOrderStatus(Order order, String status) {
        System.out.println("Actualizando DB estatus " +  order.getOrderId()+ status);
    }


}
