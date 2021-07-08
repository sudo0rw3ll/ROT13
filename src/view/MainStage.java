package view;

import controller.ClearController;
import controller.IzaberiFajlController;
import controller.ROT13Controller;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MainStage extends Stage{
	
	private static MainStage instance = null;
	
	private VBox root = new VBox(10);
	private Scene scene;
	
	private HBox topHb = new HBox(10);
	private RadioButton rbText = new RadioButton("Text");
	private RadioButton rbFile = new RadioButton("Fajl");
	private ToggleGroup tgIzbor = new ToggleGroup();
	private Button btnFajl = new Button("Izaberi fajl");
	
	private HBox midBox = new HBox(10);
	private TextArea taInput = new TextArea();
	private TextArea taOutput = new TextArea();
	private Label lblPath = new Label("Putanja do fajla: ");
	private Button btnRot = new Button("ROT13");
	private Button btnClear = new Button("Reset");
	
	private MainStage() {
		init();
		build();
		addActions();
	}
	
	private void addActions() {
		btnFajl.setOnAction(new IzaberiFajlController());
		btnRot.setOnAction(new ROT13Controller());
		btnClear.setOnAction(new ClearController());
	}
	
	private void init() {
		rbText.setUserData("Text");
		rbFile.setUserData("Fajl");
		rbText.setToggleGroup(tgIzbor);
		rbFile.setToggleGroup(tgIzbor);
		
		tgIzbor.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

			@Override
			public void changed(ObservableValue<? extends Toggle> arg0, Toggle arg1, Toggle arg2) {
				if(tgIzbor.getSelectedToggle() != null) {
					if(tgIzbor.getSelectedToggle().getUserData().toString().equalsIgnoreCase("fajl")) {
						btnFajl.setDisable(false);
						lblPath.setDisable(false);
						
					}else {
						btnFajl.setDisable(true);
						lblPath.setDisable(true);
					}
				}
			}
			
		});
		
		rbText.setSelected(true);
		topHb.setPadding(new Insets(10));
		topHb.setAlignment(Pos.CENTER);
		
		midBox.setPadding(new Insets(10));
		midBox.setSpacing(10);
		midBox.setAlignment(Pos.CENTER);
		
		root.setPadding(new Insets(10));
		root.setAlignment(Pos.CENTER);
		
		scene = new Scene(root);
		this.setScene(scene);
		this.setWidth(800);
		this.setHeight(600);
		this.setTitle("ROT13");
		this.show();
	}
	
	private void build() {
		
		topHb.getChildren().addAll(rbText,rbFile,btnFajl);
		taInput.setPrefWidth(300);
		taOutput.setPrefWidth(300);
		midBox.getChildren().addAll(taInput,new Label("->"),taOutput);
		
		root.getChildren().add(topHb);
		root.getChildren().add(lblPath);
		root.getChildren().add(midBox);
		root.getChildren().add(btnRot);
		root.getChildren().add(btnClear);
	}
	
	public static MainStage getInstance() {
		if(instance == null) {
			instance = new MainStage();
		}
		return instance;
	}

	public TextArea getTaInput() {
		return taInput;
	}

	public void setTaInput(TextArea taInput) {
		this.taInput = taInput;
	}

	public TextArea getTaOutput() {
		return taOutput;
	}

	public void setTaOutput(TextArea taOutput) {
		this.taOutput = taOutput;
	}

	public Label getLblPath() {
		return lblPath;
	}

	public void setLblPath(Label lblPath) {
		this.lblPath = lblPath;
	}

	public Button getBtnFajl() {
		return btnFajl;
	}

	public void setBtnFajl(Button btnFajl) {
		this.btnFajl = btnFajl;
	}

}
