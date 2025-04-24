package com.idsd.projetinjectiondependances;

public class MetierImpl implements IMetier {
    private IDao dao; // Couplage faible via interface

    // Injection via Setter
    public void setDao(IDao dao) {
        this.dao = dao;
    }

    @Override
    public double calcul() {
        double data = dao.getData();
        double resultat = data * 5; // logique métier
        return resultat;
    }
}
