package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.BruteView;
import view.MainStage;

public class BruteForceController implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		BruteView bw = new BruteView();
		bw.show();
		
		String cipher = MainStage.getInstance().getTaInput().getText();
		
		String lines[] = cipher.split("\n");
		
		if(cipher.isEmpty()) {
			new Alert(AlertType.WARNING,"Morate uneti text").show();
			return;
		}
		
		String out = "";
		
		out = bruteForce(lines);
		
		bw.getTaOutput().setText(out);
	}
	
	private String bruteForce(String lines[]) {
		char alphabet[] = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		
		String out = "";
		
		for(int i=1;i<=26;i++) {
			out += "Shift -> " + i + "\n";
			for(int j=0;j<lines.length;j++) {
				char input[] = lines[j].toCharArray();
				for(char ch : input) {
					int index = getIndex(ch,alphabet);
					
					if(index == -1) {
						out += Character.toString(ch);
					}else {
						index = (index + i) % 26;
						if(Character.isUpperCase(ch)) {
							out += Character.toUpperCase(alphabet[index]);
						}else {
							out += Character.toString(alphabet[index]);
						}
					}
				}
				out += "\n";
			}
		}
		return out;
	}
	
	private int getIndex(char ch, char alphabet[]) {
		for(int i=0;i<alphabet.length;i++) {
			if(alphabet[i] == ch) {
				return i;
			}
		}
		return -1;
	}

}
