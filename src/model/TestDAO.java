package model;

import java.io.IOException;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author e1802484
 */
public class TestDAO {
    
    public static void main(String[] args) throws IOException {
        //Personne dounya = new Personne("ennech");
    Transaction salaire = new Transaction(0,1000, "income",Date.valueOf("2015-03-31"), 1);        
      TransactionDAO bd = new TransactionDAO();
       System.out.println(bd.BudgetGlobal());
    // bd.importTransaction("Excel_Importation/transaction.xlsx");
    // bd.Stats_D(1);
    	// salaire.setMontant(954878987);'rgeg
   
    	// System.out.println(bd.getTransaction(0).getCategorie());
    	//bd.getYears();
    	System.out.println(Arrays.asList(bd.Stats_R(1)));
		
    	//PersonneDAO p = new PersonneDAO();
    	//p.insertPersone(dounya);
    	//System.out.println(p.getPersonById(7).getNom());
    	//System.out.println(bd.AVG_Depense_H(2020));
       
    	// t.setMontant(7445445);
    	//bd.update(t);
        //bd.insert(salaire);
    	// bd.getListeTransaction();
    	// bd.getTransaction(1);
       

    	// bd.Delete(5);
    }
    
}
