package net.aboubacar.metier;


import net.aboubacar.dao.IDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("metier")
public class MetierImpl implements IMetier {

    // Couplage faible : on dépend de l'interface, pas de l'implémentation
    private IDao dao;

    // Injection via constructeur
    @Autowired
    public MetierImpl(IDao dao) {
        this.dao = dao;
    }

    // Constructeur par défaut
    public MetierImpl() {}

    // Setter pour une injection via setter
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double data = dao.getData();
        return data * 12 * Math.PI;
    }
}
