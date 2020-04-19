package controller;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

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
import javafx.util.converter.LocalDateStringConverter;
import model.Model;
import model.Personne;
import model.Transaction;

public class ModifierSupprimerTransactionController {
	public static int IDtransaction = -1;
	public static double montantOLD = 0.0;
	public static String categorieOLD = "Depense";
	public static int personneOLD = -1;
	public static Date dateOLD = Date.valueOf(LocalDate.now());
	@FXML
    private Button modifier;
     @FXML
    private Button supprimer;
     
	 @FXML private TextField transaction,montant,personne;
	 @FXML private DatePicker date;
	 @FXML private ComboBox categorie;
	 String transaction_c,categorie_c,montant_c,personne_c;
	 @FXML private Button annuler;
	 
	 public void initialize() {

		 	categorie.getItems().addAll("Dépense","Revenu");
		 	
	        String m=String.valueOf(montantOLD);
			montant.setText(m);
			String p= Model.getPersonneInstance().getPersonById(personneOLD).getNom();
		 	personne.setText(p);
		 	String c=categorieOLD;
		 	categorie.setValue((c.equals("Depense")?"Dépense":"Revenu"));
		 	date.setValue(dateOLD.toLocalDate());
	    }

	 public void modifierTreansaction(ActionEvent event) throws IOException {

		 	Transaction t = new Transaction(IDtransaction, montantOLD, categorieOLD, dateOLD, personneOLD);
		 
		 	montant_c = montant.getText();
		 	double montant_d;
		 	
		 	try {
		 		
		 		montant_d = Double.parseDouble(montant_c);
		 	
		 	} catch(NumberFormatException nfe) {
		 		
		 		montant_d = 0.0;
		 		return;
		 	}
		 	
		 	String s = personne.getText();
		 	s = (s.replace(" ", "").equals(""))? "Inconnu": "s";
		 	Personne personne_p = Model.getPersonneInstance().getPersonByName(s);
		 	
		 	if(personne_p == null) {
		 		
		 		personne_p = new Personne(-1, personne.getText());
		 		
		 		Model.getPersonneInstance().insertPersone(personne_p);
		 		
		 		personne_p.setId(Model.getPersonneInstance().getPersonByName(personne.getText()).getId());
		 	}
		 	
		 	t.setMontant(montant_d);
		 	t.setCategorie((categorie.getValue().toString().equals("Dépense"))?"Depense":"Revenu");
		 	t.setDate_T(Date.valueOf(date.getValue()));
		 	t.setPersonne(personne_p.getId());
		 	
		 	Model.getTransactionInstance().update(t);
		 	
		 	Parent parent= FXMLLoader.load(getClass().getResource("/view/transactions.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) modifier.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();
	    }

	  public  void supprimerTreansaction(ActionEvent event) throws IOException {

		  	Model.getTransactionInstance().Delete(IDtransaction);
		  	
		  	Parent parent= FXMLLoader.load(getClass().getResource("/view/transactions.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) supprimer.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();
	    }

	
	   public void Annuler(ActionEvent event) throws IOException {
	    	Parent parent= FXMLLoader.load(getClass().getResource("/view/transactions.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) annuler.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();

	    }

}
