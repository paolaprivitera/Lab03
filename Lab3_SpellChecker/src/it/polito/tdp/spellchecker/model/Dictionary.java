package it.polito.tdp.spellchecker.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Dictionary {

	List<String> dizionario = new ArrayList<String>();

	public void loadDictionary(String language) {

		if(language.compareTo("English")==0) {
			try {
				FileReader fr = new FileReader("rsc/English.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {

					// Aggiungere parola alla struttura dati
					dizionario.add(word);			

				}
				br.close();
			} catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}
		}
		else if(language.compareTo("Italian")==0) {
			try {
				FileReader fr = new FileReader("rsc/Italian.txt");
				BufferedReader br = new BufferedReader(fr);
				String word;
				while ((word = br.readLine()) != null) {

					// Aggiungere parola alla struttura dati
					dizionario.add(word);		

				}
				br.close();
			} catch (IOException e){
				System.out.println("Errore nella lettura del file");
			}			
		}


	}

	public List<RichWord> spellCheckText(List<String> inputTextList) {

		List<RichWord> paroleControllate = new ArrayList<RichWord>();
		RichWord parola;
		
		for(String s1 : inputTextList) {
			
			if(dizionario.contains(s1)) {
				parola = new RichWord(s1, true);
			}
			else {
				parola = new RichWord(s1, false);
			}
			
			paroleControllate.add(parola);			
		}


		return paroleControllate;
	}


}
