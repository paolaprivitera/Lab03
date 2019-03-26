package it.polito.tdp.spellchecker.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import it.polito.tdp.spellchecker.model.Dictionary;
import it.polito.tdp.spellchecker.model.RichWord;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SpellCheckerController {

	private Dictionary d;

	@FXML
	private ResourceBundle resources;

	@FXML
	private URL location;

	@FXML
	private ComboBox<String> btnMenu; // <String>

	@FXML
	private TextField txtField;

	@FXML
	private Button btnCheck;

	@FXML
	private TextArea txtResult;

	@FXML
	private Text txtError;

	@FXML
	private Button btnClear;

	@FXML
	private Text txtTime;

	@FXML
	void doClearText(ActionEvent event) {
		txtField.clear();
		txtResult.clear();
		txtError.setText("");
		txtTime.setText("");
		btnCheck.setDisable(true);
		btnMenu.setDisable(false);	
		// BISOGNA RIPRISTINARE BTNMENU !!!!!!!!!!!
	}

	@FXML
	void doMenu(ActionEvent event) { // Se non seleziona la lingua non attivo il btxCheck
		btnMenu.getValue();
		btnCheck.setDisable(false);

	}

	@FXML
	void doSpellCheck(ActionEvent event) {

		List<String> paroleInserite = new ArrayList<String>();

		String lingua = btnMenu.getValue();
		btnMenu.setDisable(true);

		String s = txtField.getText().replaceAll("[.,\\/#!$%\\^&\\*;:{}=\\-_`~()\\[\\]\"]?","");
		String arrayStringhe [] = s.split(" ");

		for (String s1 : arrayStringhe) {
			paroleInserite.add(s1.toLowerCase());
		}

		d.loadDictionary(lingua);
		d.spellCheckText(paroleInserite);

		int contatore=0;
		
		for (RichWord r : d.spellCheckText(paroleInserite)) {
			if(!r.isCorretta()) {
				txtResult.appendText(r.getParola()+"\n");
				contatore++;
			}
		}
		
		txtError.setText("The text contains "+contatore+" errors");
		// STAMPARE TXTTIME
	}

	@FXML
	void initialize() {
		assert btnMenu != null : "fx:id=\"btnMenu\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtField != null : "fx:id=\"txtField\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert btnCheck != null : "fx:id=\"btnCheck\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtResult != null : "fx:id=\"txtResult\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtError != null : "fx:id=\"txtError\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert btnClear != null : "fx:id=\"btnClear\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		assert txtTime != null : "fx:id=\"txtTime\" was not injected: check your FXML file 'SpellChecker.fxml'.";
		btnMenu.getItems().addAll("English", "Italian"); // setModel
	}

	public void setModel(Dictionary model) {
		d = model;	
	}
}
