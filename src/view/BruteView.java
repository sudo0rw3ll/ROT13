package view;

import controller.SnimiController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class BruteView extends Stage{

	private VBox root = new VBox(10);
	private Scene scene;
	
	private TextArea taOutput = new TextArea();
	private Button btnSave = new Button("Sacuvaj");
	
	private HBox topHb = new HBox(10);
	private Label lblShift = new Label("Izaberi shift");
	private ComboBox<String> cbShift = new ComboBox<String>();
	
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
		
		ObservableList<String> obShifts = FXCollections.observableArrayList();
		
		for(int i=1;i<=26;i++) {
			obShifts.add(String.valueOf(i));
		}
		
		obShifts.add("Sve");
		
		int index = obShifts.indexOf("Sve");
		
		cbShift.setItems(obShifts);
		cbShift.setValue(obShifts.get(index));
		
		topHb.setPadding(new Insets(10));
		topHb.setAlignment(Pos.CENTER);
		
		taOutput.setPrefWidth(500);
		taOutput.setPrefHeight(500);
		
		scene = new Scene(root);
		this.setScene(scene);
		this.setWidth(800);
		this.setTitle("Brute-force window");
		this.setHeight(800);
	}
	
	private void build() {
		topHb.getChildren().addAll(lblShift,cbShift);
		root.getChildren().add(topHb);
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
