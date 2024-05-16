package service.imp;

import entity.Order;
import service.InventoryVerifierService;

public class VerifyInventoryServiceImp implements InventoryVerifierService {
    @Override
    public void verifyInventory(Order order) {
        int inventory  = 10;
        if (inventory < order.getQuantity()) {
                throw new Error("Out of stock");
        } else {
            System.out.println("Inventory OK");
        }

    }
}
