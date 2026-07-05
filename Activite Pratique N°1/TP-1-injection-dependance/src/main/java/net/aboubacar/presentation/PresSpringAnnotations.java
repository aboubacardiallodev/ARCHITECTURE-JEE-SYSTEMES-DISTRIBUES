package net.aboubacar.presentation;

import net.aboubacar.metier.IMetier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Injection des dépendances avec Spring Annotations
 */
public class PresSpringAnnotations {

    @Configuration
    @ComponentScan("net.aboubacar")
    static class AppConfig {}

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        IMetier metier = context.getBean("metier", IMetier.class);

        System.out.println("Injection à travers Spring (Annotations)");
        System.out.println("Résultat : " + metier.calcul());
    }
}
