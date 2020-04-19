package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ResourceBundle;

import com.mysql.cj.jdbc.exceptions.SQLError;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;
import model.Model;
import model.Personne;
import model.Transaction;
import model.TransactionDAO;

public class Main extends Application implements Initializable {

	@FXML TextField erreur;
	@FXML Button ok;
	
    @Override
  public void start(Stage primaryStage) throws Exception
    {      

    	Boolean exist;
    	
    	try {
    		
        	exist = Model.getUserInstance().verifyUser();
        	
    	} catch(SQLSyntaxErrorException ssee) {
    		
    		Parent root = FXMLLoader.load(getClass().getResource("/view/erreurbdd.fxml"));
            Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
            primaryStage.setTitle("Gestionnaire financier");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
            
    		return;
    	}
    	
    	if(exist) {
    
    		Parent root = FXMLLoader.load(getClass().getResource("/view/connexion.fxml"));
            Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
            primaryStage.setTitle("Gestionnaire financier");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
    	}
    	else {
    		
    		Parent root = FXMLLoader.load(getClass().getResource("/view/creationcompte.fxml"));
            Scene scene = new Scene(root);
    		scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
    	
            primaryStage.setTitle("Gestionnaire financier");
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);
            primaryStage.show();
    	}
    }
  

    public static void main(String[] args) {
    	
       launch(args);
    }
    
    public void quitter(ActionEvent event) throws IOException {
		
		 Stage stage = (Stage) ok.getScene().getWindow();
		  
		    stage.close();
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}