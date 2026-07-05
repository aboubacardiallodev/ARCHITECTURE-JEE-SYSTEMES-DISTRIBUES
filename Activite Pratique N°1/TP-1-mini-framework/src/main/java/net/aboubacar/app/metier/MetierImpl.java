package net.aboubacar.app.metier;

import net.aboubacar.app.dao.IDao;
import net.aboubacar.framework.annotations.Autowired;
import net.aboubacar.framework.annotations.Component;

@Component("metier")
public class MetierImpl implements IMetier {

    @Autowired
    private IDao dao;

    public MetierImpl() {}

    @Autowired
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        return dao.getData() * 2 + 10;
    }
}
