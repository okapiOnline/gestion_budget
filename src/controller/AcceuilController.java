package controller;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Model;

public class AcceuilController implements Initializable{

	@FXML private Button acceuil,transactions,statistiques,predictions;
	@FXML private Label budget;
	@FXML private Label moyenneMensuelleDepense;
	@FXML private Label moyenneAnnuelleDepense;
	@FXML private Label moyenneMensuelleGains;
	@FXML private Label moyenneAnnuelleGains;
	@FXML private Label  moyenneHebdomadaireGains;
	@FXML private Label moyenneHebdomadaireDepense;
	@FXML private Label personne1;
	@FXML private Label personne2;
	@FXML private Label personne3;
	@FXML private ComboBox<String> annee;
 
	
	
	@Override
	 public void initialize(URL location, ResourceBundle ressources) {
		
		 try {
			 ArrayList<String> listYears = (ArrayList<String>) Model.getTransactionInstance().getYears();
			 
			 for(String year : listYears) {
				 annee.getItems().add(year);
			 }
		 
			
			double budgetValue = Model.getTransactionInstance().BudgetGlobal();
			
			budget.setText(String.format("%.2f", budgetValue)+" €");
			chargementDonneesinit();
   
		} catch(NullPointerException npe) {
			 
			budget.setText(String.format("%.2f", 0.0)+" €");
			moyenneMensuelleDepense.setText(String.format("%.2f", 0.0)+" €");
			moyenneHebdomadaireDepense.setText(String.format("%.2f", 0.0)+ " €");
			moyenneMensuelleGains.setText(String.format("%.2f", 0.0)+ " €");
			moyenneAnnuelleGains.setText(String.format("%.2f", 0.0)+ " €");
			moyenneAnnuelleDepense.setText(String.format("%.2f", 0.0)+ " €");
			moyenneHebdomadaireGains.setText(String.format("%.2f", 0.0)+ " €");
		}
	}
	
	private void chargementDonneesinit() {
		 annee.getSelectionModel().selectFirst();
	     int year = Integer.parseInt(annee.getSelectionModel().getSelectedItem().toString());
	     System.out.println(year);
		 double moyMensuelleDepense = Model.getTransactionInstance().AVG_Depense_M(year);
		 double moyAnnuelleDepense  = Model.getTransactionInstance().SUM_D(year);
		 double moyHebdoDepense = Model.getTransactionInstance().AVG_Revenu_H(year);
		 
		 double moyMensuelleGains = Model.getTransactionInstance().AVG_Revenu_M(year);
		 double moyAnnuelleGains = Model.getTransactionInstance().SUM_R(year);
		 
		 double moyHebdoGains = Model.getTransactionInstance().AVG_Revenu_H(year);
		 
		 moyenneMensuelleDepense.setText(String.format("%.2f", moyMensuelleDepense)+" €");
		 moyenneHebdomadaireDepense.setText(String.format("%.2f", moyHebdoDepense)+ " €");
		 moyenneMensuelleGains.setText(String.format("%.2f", moyMensuelleGains)+ " €");
		 moyenneAnnuelleGains.setText(String.format("%.2f", moyAnnuelleGains)+ " €");
		 moyenneAnnuelleDepense.setText(String.format("%.2f", moyAnnuelleDepense)+ " €");
		 moyenneHebdomadaireGains.setText(String.format("%.2f", moyHebdoGains)+ " €");
		 
		 
		 Map<String, String> personnes = Model.getTransactionInstance().MAX_depense(year);
	
		 String eachpersonne[] = new String[5];
		 int i=0;
		 for(Map.Entry<String,String> p: personnes.entrySet()) {
			 eachpersonne[i] = " " +p.getKey() + " a dépensé(e) "+ p.getValue()+" €";
			++i;
		
		 }
			
		 	
			personne1.setText(eachpersonne[0]);
			personne2.setText(eachpersonne[1]);
			personne3.setText(eachpersonne[2]);
		 
	}
	private void chargementDonnees() {
		
	    // annee.getSelectionModel().selectFirst();
	     int year = Integer.parseInt(annee.getSelectionModel().getSelectedItem().toString());
	     System.out.println(year);
		 double moyMensuelleDepense = Model.getTransactionInstance().AVG_Depense_M(year);
		 double moyAnnuelleDepense  = Model.getTransactionInstance().SUM_D(year);
		 double moyHebdoDepense = Model.getTransactionInstance().AVG_Revenu_H(year);
		 
		 double moyMensuelleGains = Model.getTransactionInstance().AVG_Revenu_M(year);
		 double moyAnnuelleGains = Model.getTransactionInstance().SUM_R(year);
		 
		 double moyHebdoGains = Model.getTransactionInstance().AVG_Revenu_H(year);
		 
		 moyenneMensuelleDepense.setText(String.format("%.2f", moyMensuelleDepense)+" €");
		 moyenneHebdomadaireDepense.setText(String.format("%.2f", moyHebdoDepense)+ " €");
		 moyenneMensuelleGains.setText(String.format("%.2f", moyMensuelleGains)+ " €");
		 moyenneAnnuelleGains.setText(String.format("%.2f", moyAnnuelleGains)+ " €");
		 moyenneAnnuelleDepense.setText(String.format("%.2f", moyAnnuelleDepense)+ " €");
		 moyenneHebdomadaireGains.setText(String.format("%.2f", moyHebdoGains)+ " €");
		 
		 
		 Map<String, String> personnes = Model.getTransactionInstance().MAX_depense(year);
	
		 String eachpersonne[] = new String[5];
		 int i=0;
		 for(Map.Entry<String,String> p: personnes.entrySet()) {
			 eachpersonne[i] = " " +p.getKey() + " a dépensé(e) "+ p.getValue()+" €";
			++i;
		
		 }
			
		 	
			personne1.setText(eachpersonne[0]);
			personne2.setText(eachpersonne[1]);
			personne3.setText(eachpersonne[2]);
		 
			
	}
	public void getyear(ActionEvent event)throws IOException {
		chargementDonnees();	
	}
	
	 public void goToAcceuil(ActionEvent event) throws IOException {
			
			Parent parent= FXMLLoader.load(getClass().getResource("/view/acceuil.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) acceuil.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();
	    	
		}
	 public void goToTransaction(ActionEvent event) throws IOException {
			
			Parent parent= FXMLLoader.load(getClass().getResource("/view/transactions.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) acceuil.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();
	    	
		} public void goToStatistiques(ActionEvent event) throws IOException {
			
			Parent parent= FXMLLoader.load(getClass().getResource("/view/statistiques.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) acceuil.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();
	    	
		}
	 public void goToPrediction(ActionEvent event) throws IOException {
			
			Parent parent= FXMLLoader.load(getClass().getResource("/view/predictions.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) acceuil.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();
	    	
		}
	
	    
}
