package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Model;

public class StatistiquesController {

	@FXML private Button acceuil,transactions,statistiques,predictions;
	//@FXML private Label budget,label;
	
  @FXML private ComboBox categorie;
  
  @FXML
  private BarChart<?, ?> depense;

  @FXML
  private CategoryAxis x;

  @FXML
  private NumberAxis y;
  @FXML
  private BarChart<?, ?> revenu;

  @FXML
  private CategoryAxis xr;

  @FXML
  private NumberAxis yr;


  @FXML
  private Label labeldepenses;

  @FXML
  private Label labelrevenu;
  
  private final int LAST_MONTH = 1, SIX_MONTHS = 2, LAST_YEAR = 3, TWO_YEARS = 4;
	
  	public void initialize() {
  		
  			categorie.getItems().addAll("Le mois dernier","Les 6 derniers mois" ,"L'année dernière", "Les deux dernières années");
  		
  			depense.setVisible(false);
  		    revenu.setVisible(false);
  			labeldepenses.setVisible(false);
  			labelrevenu.setVisible(false);
	    }
    
    public void getCategorie(ActionEvent event) throws InterruptedException {
    		depense.setVisible(true);
			revenu.setVisible(true);
			labeldepenses.setVisible(true);
  			labelrevenu.setVisible(true);
  			Thread.sleep(500);
        String c=categorie.getSelectionModel().getSelectedItem().toString();
        //System.out.println(c);
        
        XYChart.Series d= new  	XYChart.Series<>();
        XYChart.Series r= new  	XYChart.Series<>();
        
        depense.getData().clear();
        revenu.getData().clear();
        
        TreeMap<String, String> m;
        Set<String> keys;
        
        switch(c) {
        
        case "Le mois dernier":
	        
    		m = Model.getTransactionInstance().Stats_D(LAST_MONTH);
    		keys = m.keySet();
  			
    		for(String key : keys) {
    			
    			d.getData().add(new XYChart.Data<>( key, Double.valueOf(m.get(key)) ));
    		}
  			
  			depense.getData().addAll(d);
  			
  			
  			m = Model.getTransactionInstance().Stats_R(LAST_MONTH);
    		keys = m.keySet();
  			
    		for(String key : keys) {
    			
    			r.getData().add(new XYChart.Data<>( key, Double.valueOf(m.get(key)) ));
    		}
  			
  			revenu.getData().addAll(r);
  			break;
	        	
	        
        case "Les 6 derniers mois":
	        
        	m = Model.getTransactionInstance().Stats_D(SIX_MONTHS);
    		keys = m.keySet();
  			
    		for(String key : keys) {
    			
    			d.getData().add(new XYChart.Data<>( key, Double.valueOf(m.get(key)) ));
    		}
  			
  			depense.getData().addAll(d);
  			
  			
  			m = Model.getTransactionInstance().Stats_R(SIX_MONTHS);
    		keys = m.keySet();
  			
    		for(String key : keys) {
    			
    			r.getData().add(new XYChart.Data<>( key, Double.valueOf(m.get(key)) ));
    		}
  			
  			revenu.getData().addAll(r);
  			break;
	        
        case "L'année dernière":
        	
        	m = Model.getTransactionInstance().Stats_D(LAST_YEAR);
    		keys = m.keySet();
  			
    		for(String key : keys) {
    			
    			d.getData().add(new XYChart.Data<>( key, Double.valueOf(m.get(key)) ));
    		}
  			
  			depense.getData().addAll(d);
  			
  			
  			m = Model.getTransactionInstance().Stats_R(LAST_YEAR);
    		keys = m.keySet();
  			
    		for(String key : keys) {
    			
    			r.getData().add(new XYChart.Data<>( key, Double.valueOf(m.get(key)) ));
    		}
  			
  			revenu.getData().addAll(r);
  			break;
        
        case "Les deux dernières années":
        	
        	m = Model.getTransactionInstance().Stats_D(TWO_YEARS);
    		keys = m.keySet();
  			
    		for(String key : keys) {
    			
    			d.getData().add(new XYChart.Data<>( key, Double.valueOf(m.get(key)) ));
    		}
  			
  			depense.getData().addAll(d);
  			
  			
  			m = Model.getTransactionInstance().Stats_R(TWO_YEARS);
    		keys = m.keySet();
  			
    		for(String key : keys) {
    			
    			r.getData().add(new XYChart.Data<>( key, Double.valueOf(m.get(key)) ));
    		}
  			
  			revenu.getData().addAll(r);
  			break;
	        	
        }
     
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
