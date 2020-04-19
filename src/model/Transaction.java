package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Date;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author e1802484
 */
public class Transaction {

            private int ID;
            private double montant ;
            private  String categorie ;
            private Date date_T;
            private int personne;
            private String nomPersonne;

    public Transaction(int id,double montant, String dategorie, Date date_T, int personne) {
        this.ID=id;
        this.montant = montant;
        this.categorie = dategorie;
        this.date_T = date_T;
        this.personne = personne;
        Personne p = Model.getPersonneInstance().getPersonById(this.personne);
        nomPersonne = p.getNom();
    }

    public int getID() {
        return ID;
    }

    public double getMontant() {
        return montant;
    }

    public String getCategorie() {
        return categorie;
    }

    public Date getDate_T() {
        return date_T;
    }

    public String getPersonne() {
        return nomPersonne;
    }

    public int getPersonneID() {
        return personne;
    }


    public void setMontant(double montant) {
        this.montant = montant;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public void setDate_T(Date date_T) {
        this.date_T = date_T;
    }

    public void setPersonne(int personne) {
        this.personne = personne;
    }

}
