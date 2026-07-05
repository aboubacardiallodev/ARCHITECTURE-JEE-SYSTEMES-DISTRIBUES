package net.aboubacar.app.presentation;

import net.aboubacar.framework.context.ApplicationContext;
import net.aboubacar.framework.context.AnnotationApplicationContext;
import net.aboubacar.app.metier.IMetier;

public class PresAnnotations {
    public static void main(String[] args) throws Exception {
        ApplicationContext context = new AnnotationApplicationContext("net.aboubacar.app");
        IMetier metier = (IMetier) context.getBean("metier");

        System.out.println("=== Mini Framework - Version Annotations ===");
        System.out.println("Résultat : " + metier.calcul());
    }
}
