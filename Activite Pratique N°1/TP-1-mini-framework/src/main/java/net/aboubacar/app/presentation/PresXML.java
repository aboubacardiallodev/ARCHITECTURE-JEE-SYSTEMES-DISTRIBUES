package net.aboubacar.app.presentation;


import net.aboubacar.app.metier.IMetier;
import net.aboubacar.framework.context.ApplicationContext;
import net.aboubacar.framework.context.XMLApplicationContext;

public class PresXML {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new XMLApplicationContext("beans.xml");
        IMetier metier = (IMetier) context.getBean("metier");

        System.out.println("=== Mini Framework - Version XML ===");
        System.out.println("Résultat : " + metier.calcul());
    }
}
