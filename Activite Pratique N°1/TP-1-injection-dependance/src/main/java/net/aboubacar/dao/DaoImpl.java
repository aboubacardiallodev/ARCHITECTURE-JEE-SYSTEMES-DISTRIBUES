package net.aboubacar.dao;

import org.springframework.stereotype.Component;

@Component("dao")
public class DaoImpl implements IDao {

    @Override
    public double getData() {
        System.out.println("Version de base de données");
        return 26.7;
    }
}
