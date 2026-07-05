package net.aboubacar.app.dao;


import net.aboubacar.framework.annotations.Component;

@Component("dao")
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("[Mini Framework] Accès base de données");
        return 23.5;
    }
}
