package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.FileChooser;
import view.MainStage;

public class IzaberiFajlController implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		FileChooser fileChooser = new FileChooser();
		
		File fajl = fileChooser.showOpenDialog(MainStage.getInstance());
		
		if(fajl != null) {
			MainStage.getInstance().getLblPath().setText("Putanja do fajla:" + fajl.getAbsolutePath());
			readFile(fajl);
		}
	}
	
	private void readFile(File file) {
		BufferedReader br = null;
		
		try {
			br = new BufferedReader(new FileReader(file));
			
			String line = "";
			String out = "";
			
			while((line = br.readLine()) != null) {
				out += line + "\n";
			}
			
			MainStage.getInstance().getTaInput().setText(out);
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(br != null) {
				try {
					br.close();
				}catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
