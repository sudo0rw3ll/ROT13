package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.MainStage;

public class ClearController implements EventHandler<ActionEvent>{

	@Override
	public void handle(ActionEvent arg0) {
		MainStage.getInstance().getTaInput().clear();
		MainStage.getInstance().getTaOutput().clear();
		MainStage.getInstance().getLblPath().setText("Putanja do fajla:");
		MainStage.getInstance().getLblPath().setVisible(false);
		MainStage.getInstance().getRbText().setSelected(true);
	}

}
