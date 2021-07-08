package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import view.BruteView;

public class SnimiController implements EventHandler<ActionEvent>{

	private static final String outputFile = "brute_output.txt";
	private BruteView bruteView;
	
	public SnimiController(BruteView bruteView) {
		this.bruteView = bruteView;
	}
	
	@Override
	public void handle(ActionEvent arg0) {
		
		String output = bruteView.getTaOutput().getText();
		
		if(output.isEmpty()) {
			new Alert(AlertType.WARNING,"TextArea je prazna").show();
			return;
		}
		
		PrintWriter pw = null;
		
		try {
			pw = new PrintWriter(new File(outputFile));
			pw.print(output);
			
			new Alert(AlertType.INFORMATION,"Ispis je gotov").show();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			if(pw != null){
				pw.close();
			}
		}
	}

}
