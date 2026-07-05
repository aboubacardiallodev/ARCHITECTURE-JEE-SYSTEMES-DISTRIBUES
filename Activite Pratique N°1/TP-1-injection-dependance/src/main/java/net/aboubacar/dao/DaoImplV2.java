package net.aboubacar.dao;

public class DaoImplV2 implements IDao {
    @Override
    public double getData() {
        System.out.println("Version capteurs...");
        return 37.2;
    }
}
