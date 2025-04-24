package com.idsd.projetinjectiondependances;

public class MainStatic {
    public static void main(String[] args) {
        DaoImpl dao = new DaoImpl(); // instanciation statique
        MetierImpl metier = new MetierImpl();
        metier.setDao(dao); // injection via setter
        System.out.println("Résultat = " + metier.calcul());
    }
}
