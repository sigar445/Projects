package org.sigar.designPatterns.StructuralPatterns.Proxy.Warehouse;

public class WarehouseTest {
    public void testAdd(){
        Warehouse warehouse = new Warehouse();
        String itemName = "Water";
        warehouse.addItems(itemName,34);
        warehouse.addItems(itemName,40);
        warehouse.fulfillOrder(itemName,60);
    }

    public static void main(String[] args) {
        WarehouseTest test = new WarehouseTest();
        test.testAdd();
    }
}
