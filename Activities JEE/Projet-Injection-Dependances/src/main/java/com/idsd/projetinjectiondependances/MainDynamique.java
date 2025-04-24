package com.idsd.projetinjectiondependances;
import java.io.File;
import java.util.Scanner;

public class MainDynamique {
    public static void main(String[] args) throws Exception {
        Scanner scanner = new Scanner(new File("config.txt"));
        String daoClassName = scanner.nextLine();
        String metierClassName = scanner.nextLine();

        Class cDao = Class.forName(daoClassName);
        IDao dao = (IDao) cDao.getConstructor().newInstance();

        Class cMetier = Class.forName(metierClassName);
        MetierImpl metier = (MetierImpl) cMetier.getConstructor().newInstance();

        metier.setDao(dao); // injection dynamique
        System.out.println("Résultat Dynamique = " + metier.calcul());
    }
}
