package application;
	
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

import javafx.application.Application;
import javafx.stage.Stage;
import view.MainStage;

public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		MainStage.getInstance();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

	public static void rot13(String file) {
		
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
	
	private static int getIndex(char c, char alphabet[]) {
		for(int i=0;i<alphabet.length;i++) {
			if(alphabet[i] == c) {
				return i;
			}
		}
		return -1;
	}
}
