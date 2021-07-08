package view;

import controller.SnimiController;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BruteView extends Stage{

	private VBox root = new VBox(10);
	private Scene scene;
	
	private TextArea taOutput = new TextArea();
	private Button btnSave = new Button("Sacuvaj");
	
	public BruteView() {
		init();
		build();
		addActions();
	}
	
	private void addActions() {
		btnSave.setOnAction(new SnimiController(this));
	}
	
	private void init() {
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		
		taOutput.setPrefWidth(500);
		taOutput.setPrefHeight(500);
		
		scene = new Scene(root);
		this.setScene(scene);
		this.setWidth(800);
		this.setTitle("Brute-force window");
		this.setHeight(800);
	}
	
	private void build() {
		root.getChildren().add(taOutput);
		root.getChildren().add(btnSave);
	}

	public TextArea getTaOutput() {
		return taOutput;
	}

	public void setTaOutput(TextArea taOutput) {
		this.taOutput = taOutput;
	}
	
	
	
}
