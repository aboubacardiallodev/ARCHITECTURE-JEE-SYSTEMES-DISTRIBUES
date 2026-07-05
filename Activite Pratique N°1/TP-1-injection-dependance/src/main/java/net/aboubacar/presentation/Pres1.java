package net.aboubacar.presentation;

import net.aboubacar.dao.DaoImpl;
import net.aboubacar.metier.MetierImpl;


public class Pres1 {

    public static void main(String[] args) {

        DaoImpl dao = new DaoImpl();
        MetierImpl metier = new MetierImpl(dao); // Injection de dépendance via le constructeur
        // metier.setDao(dao); // Injection de dépendance via le setter
        System.out.println("Résultat : " + metier.calcul());
    }
}
