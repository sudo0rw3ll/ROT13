package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.MainStage;

public class ROT13Controller implements EventHandler<ActionEvent>{
	
	@Override
	public void handle(ActionEvent arg0) {
		
		if(!MainStage.getInstance().getBtnFajl().isDisable()) {
			String pathParts[] = MainStage.getInstance().getLblPath().getText().split(":");
				
			File file = new File(pathParts[1]);

			MainStage.getInstance().getTaOutput().setText(file.getAbsolutePath());
			
			rot13(file.getAbsolutePath());
			
			BufferedReader br = null;
			
			try {
				br = new BufferedReader(new FileReader(new File("out.txt")));
				
				String line = "";
				String out = "";
				
				while((line = br.readLine()) != null) {
					out += line + "\n";
				}
				
				MainStage.getInstance().getTaOutput().setText(out);
				
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
		}else {
			rot13ta();
		}
	}
	
	public void rot13ta() {
		String inputTa = MainStage.getInstance().getTaInput().getText();
		
		if(inputTa.isEmpty()) {
			new Alert(AlertType.WARNING,"Morate uneti text").show();
			return;
		}
		
		char input[] = inputTa.toCharArray();
		char alphabet[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		String out = "";
		for(char ch : input) {
			int index = getIndex(ch,alphabet);
			
			if(index == -1) {
				out += Character.toString(ch);
			}else {
				index = (getIndex(ch, alphabet) + 13) % 26;
				if(Character.isUpperCase(ch)) {
					out += Character.toUpperCase(alphabet[index]);
				}else {
					out += Character.toString(alphabet[index]);	
				}
			}
		}
		MainStage.getInstance().getTaOutput().setText(out);
	}
	
	public void rot13(String file) {
		
		BufferedReader br = null;
		PrintWriter pw = null;
		char alphabet[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		
		try {
			br = new BufferedReader(new FileReader(new File(file)));
			pw = new PrintWriter(new File("out.txt"));
			
			String line = "";
			String out = "";
			
			char input[];
			
			while((line = br.readLine()) != null) {
				
				input = line.toCharArray();
				
				for(char ch : input) {
					int index = getIndex(ch,alphabet);
					
					if(index == -1) {
						out += Character.toString(ch);
					}else {
						index = (getIndex(ch, alphabet) + 13) % 26;
						if(Character.isUpperCase(ch)) {
							out += Character.toUpperCase(alphabet[index]);
						}else {
							out += Character.toString(alphabet[index]);	
						}
					}
				}
				pw.println(out);
				out = "";
			}
			
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
			
			if(pw != null) {
				pw.close();
			}
		}
	}
	
	private int getIndex(char c, char alphabet[]) {
		for(int i=0;i<alphabet.length;i++) {
			if(alphabet[i] == c) {
				return i;
			}
		}
		return -1;
	}

}
