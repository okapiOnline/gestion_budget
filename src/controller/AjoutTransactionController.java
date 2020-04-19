package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.Model;
import model.Personne;
import model.Transaction;

public class AjoutTransactionController {
	
	 @FXML private TextField montant,personne;
	 @FXML private DatePicker date;
	 @FXML private ComboBox categorie;
	 String transaction_c,categorie_c,montant_c,personne_c;
	 @FXML private Button ajouter,annuler;
	 
	 public void initialize() {

		 	categorie.getItems().addAll("Dépense","Revenu");
	    }
	 
	 public void ajouterTreansaction(ActionEvent event) throws IOException {
		 
		 	categorie_c		=	(categorie.getValue().toString().equals("Dépense"))? "Depense": "Revenu";
		 	montant_c		=	montant.getText();
		 	personne_c		=	personne.getText();
		 	personne_c 		=  (personne_c.replace(" ", "").equals(""))? "Inconnu": personne_c;
		 	
		 	double montant_d;
		 	
		 	try {
		 		
		 		montant_d = Double.parseDouble(montant_c);
		 	
		 	} catch(NumberFormatException nfe) {
		 		
		 		montant_d = 0.0;
		 		return;
		 	}
		 	
		 	Date date_T = Date.valueOf(date.getValue());
		 	
		 	Personne personne_p = Model.getPersonneInstance().getPersonByName(personne_c);
		 	
		 	if(personne_p == null) {
		 		
		 		personne_p = new Personne(-1, personne_c);
		 		
		 		Model.getPersonneInstance().insertPersone(personne_p);
		 		
		 		personne_p.setId(Model.getPersonneInstance().getPersonByName(personne_c).getId());
		 	}
		 	
		 	System.out.println(transaction_c);
		 	System.out.println( categorie_c);
		 	System.out.println( montant_d);
		 	System.out.println (personne_p.getId());
		 	System.out.println(date_T);
		 	
		 	Transaction t = new Transaction(-1, montant_d, categorie_c, date_T, personne_p.getId());
		 	
		 	Model.getTransactionInstance().insert(t);

			Parent parent= FXMLLoader.load(getClass().getResource("/view/acceuil.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) ajouter.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();
	    	
		}
	   public void Annuler(ActionEvent event) throws IOException {
	    	Parent parent= FXMLLoader.load(getClass().getResource("/view/transactions.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) ajouter.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();

	    }
}
