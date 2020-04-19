package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import model.Model;

public class CreationController {

 @FXML PasswordField mdp;
 @FXML Button creer;
    public void initialize()
    {
       
    }
    public void creer(ActionEvent event) throws IOException {
    	String mdpc=mdp.getText();
    	if(!mdpc.equals("")) {
    		 Model.getUserInstance().addUser(mdpc);
    	/// recuperer le mdp et l'enregister dans la base puis on passe a l'acceuil
		Parent parent= FXMLLoader.load(getClass().getResource("/view/acceuil.fxml"));
    	Scene scene=new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("/view/application.css").toExternalForm());
    	Stage window = (Stage) mdp.getScene().getWindow();
        window.setResizable(false);
    	window.setScene(scene);
    	window.show();
    	}
    }
	public void quitterApp(ActionEvent event) {
		
		 Stage stage = (Stage) mdp.getScene().getWindow();
		    // do what you have to do
		    stage.close();
	}
}
