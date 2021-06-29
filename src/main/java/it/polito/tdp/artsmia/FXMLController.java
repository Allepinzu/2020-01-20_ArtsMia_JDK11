package it.polito.tdp.artsmia;

import java.net.URL;
import java.util.ResourceBundle;

import it.polito.tdp.artsmia.model.Artist;
import it.polito.tdp.artsmia.model.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class FXMLController {
	
	private Model model;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnCreaGrafo;

    @FXML
    private Button btnArtistiConnessi;

    @FXML
    private Button btnCalcolaPercorso;

    @FXML
    private ComboBox<String> boxRuolo;

    @FXML
    private TextField txtArtista;

    @FXML
    private TextArea txtResult;

    @FXML
    void doArtistiConnessi(ActionEvent event) {
    	this.txtResult.appendText( model.edgeSet());
    }

    @FXML
    void doCalcolaPercorso(ActionEvent event) {
    	
         this.txtResult.clear();
         if(this.txtArtista.getText().isEmpty()) {
        	 this.txtResult.setText("INSERISCI");
          return;}
         for(Artist s1:model.ListaMigliori(Integer.parseInt(this.txtArtista.getText()))) {
        	 this.txtResult.appendText(s1.toString()+"\n");
        	 
        	 
        	 
        	 
        	 //ciaoooooooooooooooooo
         }
    	
    	
    }

    @FXML
    void doCreaGrafo(ActionEvent event) {

    	if(this.boxRuolo.getValue()==null) {
    		this.txtResult.setText("seleziona");
    	}
    	else {
    		this.txtResult.clear();
    		model.creaGrafo(this.boxRuolo.getValue());
    		this.txtResult.appendText( model.VA());
    		this.btnArtistiConnessi.setDisable(false);
    		
    		
    	}
    }

    @FXML
    void initialize() {
        assert btnCreaGrafo != null : "fx:id=\"btnCreaGrafo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnArtistiConnessi != null : "fx:id=\"btnArtistiConnessi\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert btnCalcolaPercorso != null : "fx:id=\"btnCalcolaPercorso\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert boxRuolo != null : "fx:id=\"boxRuolo\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtArtista != null : "fx:id=\"txtArtista\" was not injected: check your FXML file 'Artsmia.fxml'.";
        assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'Artsmia.fxml'.";

    }

	public void setModel(Model model) {
		this.model = model;
		this.boxRuolo.getItems().addAll(model.getRole());
		this.btnArtistiConnessi.setDisable(true);
	}
}

