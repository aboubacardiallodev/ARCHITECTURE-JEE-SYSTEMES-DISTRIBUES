package net.aboubacar.presentation;

import net.aboubacar.dao.IDao;
import net.aboubacar.metier.MetierImpl;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

public class Pres2 {
    public static void main(String[] args) throws FileNotFoundException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        // Lire les noms de classes depuis le fichier de configuration
        // Utile quand ton package le l'application pour ne pas perdre le fichier
        InputStream configStream = Pres2.class.getClassLoader().getResourceAsStream("config.txt");
        if (configStream == null) {
            throw new FileNotFoundException("config.txt introuvable dans le classpath (src/main/resources)");
        }
        Scanner scanner = new Scanner(configStream);
        String daoClassName    = scanner.nextLine();
        String metierClassName = scanner.nextLine();
        scanner.close();

        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.newInstance();

        MetierImpl metier = (MetierImpl) Class.forName(metierClassName)
                .getConstructor()
                .newInstance();
        metier.setDao(dao);

        System.out.println("DAO utilisé : " + dao.getClass().getSimpleName());
        System.out.println("Résultat : " + dao.getData());
    }
}
