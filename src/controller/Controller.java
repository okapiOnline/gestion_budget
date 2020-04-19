  
package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Model;
import javafx.scene.Parent;

public class Controller {
  @FXML private Label label;
   @FXML private Button quiter,authentification;
   @FXML private PasswordField mdp;

    public void initialize() {
       
    }
    
	public void authentifier(ActionEvent event) throws IOException {
		
		Boolean exist= Model.getUserInstance().verifyPass(mdp.getText());
		if(exist) {
		Parent parent= FXMLLoader.load(getClass().getResource("/view/acceuil.fxml"));
    	Scene scene=new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
    	Stage window = (Stage) authentification.getScene().getWindow();
        window.setResizable(false);
    	window.setScene(scene);
    	window.show();
		}
    	
	}
	public void quitterApp(ActionEvent event) {
		
		 Stage stage = (Stage) authentification.getScene().getWindow();
		    // do what you have to do
		    stage.close();
	}
	
}