package com.idsd.projetinjectiondependances;
public class DaoImpl implements IDao {
    @Override
    public double getData() {
        System.out.println("[DAO] Accès à la base de données...");
        return 42; // Une valeur simulée
    }
}
