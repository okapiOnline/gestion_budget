package controller;




import java.util.List;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;

import org.apache.poi.hwpf.model.PropertyModifier;
import org.apache.poi.ss.format.CellNumberFormatter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import model.Personne;
import model.Model;
import model.Transaction;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;


public class TransactionsController implements Initializable{
	
	
	private ObservableList<Transaction> transacts;

	
	@FXML private Button acceuil,transactions,statistiques,predictions;
	@FXML private TableView<Transaction> tvData;
	@FXML public TableColumn<Transaction, Integer> id;
	@FXML public TableColumn<Transaction, Double> montant;
	@FXML public TableColumn<Transaction, String> categorie;
	@FXML public TableColumn<Transaction, Personne> personne;
	@FXML public TableColumn<Transaction, Date> date;
	
	
	@Override
	 public void initialize(URL location, ResourceBundle resources) {
	        tvData.setOnMouseClicked(new EventHandler<MouseEvent>() {
	        	@Override
	        	public void handle(MouseEvent event) {
	        		
	        		if(tvData.getSelectionModel().getSelectedItem() == null) return;
	        		
	        		ModifierSupprimerTransactionController.IDtransaction=tvData.getSelectionModel().getSelectedItem().getID();
	        		ModifierSupprimerTransactionController.montantOLD=tvData.getSelectionModel().getSelectedItem().getMontant();
	        		ModifierSupprimerTransactionController.personneOLD=tvData.getSelectionModel().getSelectedItem().getPersonneID();
	        		ModifierSupprimerTransactionController.categorieOLD=tvData.getSelectionModel().getSelectedItem().getCategorie();
	        		ModifierSupprimerTransactionController.dateOLD=tvData.getSelectionModel().getSelectedItem().getDate_T();
	        		Parent parent = null;
					try {
						parent = FXMLLoader.load(getClass().getResource("/view/modifiersupprimer.fxml"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	    	Scene scene=new Scene(parent);
	    	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	    	Stage window = (Stage) acceuil.getScene().getWindow();
	    	        window.setResizable(false);
	    	    	window.setScene(scene);
	    	    	window.show();
	        	}
	        	
	        	
	        });
		
		 id.setCellValueFactory(new PropertyValueFactory<>("ID"));
		 montant.setCellValueFactory(new PropertyValueFactory<>("montant"));
		 categorie.setCellValueFactory(new PropertyValueFactory<>("categorie"));
		 personne.setCellValueFactory(new PropertyValueFactory<>("personne"));
		 date.setCellValueFactory(new PropertyValueFactory<>("date_T"));
		
		
		 ObservableList<Transaction> listTransaction = returnTransact();
		 tvData.setItems(listTransaction);
		 
		
		
	 }
	

	 public void goToAjoutTransaction(ActionEvent event) throws IOException {
			
			Parent parent= FXMLLoader.load(getClass().getResource("/view/ajouttransaction.fxml"));
	    	Scene scene=new Scene(parent);
	    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
	    	Stage window = (Stage) acceuil.getScene().getWindow();
	        window.setResizable(false);
	    	window.setScene(scene);
	    	window.show();
	    	
	    	
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
	    	
		} 
	 
	 public void goToStatistiques(ActionEvent event) throws IOException {
			
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
	 
	 private ObservableList<Transaction> returnTransact() {
		
		ArrayList<Transaction> lists = (ArrayList<Transaction>) Model.getTransactionInstance().getListeTransaction();
		transacts = FXCollections.observableArrayList();
		
		for(Transaction t : lists) {
			
			
			  transacts.add(new Transaction(t.getID(), t.getMontant(), t.getCategorie(), t.getDate_T(), t.getPersonneID()));
		}
		
		return transacts;
	}
	 
	 public void ajouterviaCSV(ActionEvent event) throws IOException {
		 FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/view/filechooser.fxml"));
	    	Parent root1 = (Parent) fxmlLoader.load();
	    	Stage stage = new Stage();
	        stage.setResizable(false);
	    	stage.setScene(new Scene(root1));  
	    	stage.show();
	 }

}
