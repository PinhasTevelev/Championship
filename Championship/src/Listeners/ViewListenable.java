package Listeners;

import javafx.scene.control.Button;
import javafx.stage.Stage;

public interface ViewListenable {
	
	
	void viewSendsNameToModel(String participantName);
	boolean viewAsksIfGotPlace();
	void viewUpdateChampionshipStart(Stage stage);
	void viewTellsGoToGameView(Button b, String name1, String name2);
	void viewGameSendsScoresToGetWinner();

}
