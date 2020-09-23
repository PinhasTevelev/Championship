package Controller;

import Listeners.ModelListenable;
import Listeners.ViewListenable;
import Model.Model;
import View.GameView;
import View.View;
import View.View2;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainController implements ModelListenable, ViewListenable {
	Model model;
	View2 view2;
	View view;
	GameView gview;

	public MainController(Model m, View v) {
		this.model = m;
		this.view = v;

		model.registerListener(this);
		view.registerListener(this);
	}

	@Override
	public void viewSendsNameToModel(String participantName) {
		model.addParticipant(participantName);
	}

	@Override
	public boolean viewAsksIfGotPlace() {
		return model.checkIfgotPlace();
	}

	public void modelUpdatesNotName() {
		view.showErrorParticipantNameIsEmpty();
	}

	@Override
	public void modelAddParticipantName(String ParticipantName, int index) {
		view.viewAddParticipantName(ParticipantName, index);
	}

	@Override
	public void modelUpdatesNoPlace() {
		view.showErrorGroupIsFull();
	}

	@Override
	public void viewUpdateChampionshipStart(Stage stage) {
		model.setGameType(view.getGameType());
		view2 = new View2(stage);
		view2.registerListener(this);
		view2.updateNames(view.getTfallNames());
		model.setGameType(view.getGameType());

	}

	@Override
	public void viewTellsGoToGameView(Button b, String name1, String name2) {
		gview = new GameView(b, name1, name2, view.getGameType());
		gview.registerListener(this);
	}

	@Override
	public void viewGameSendsScoresToGetWinner() {
		model.startGame(gview.getName1(), gview.getName2(), gview.getScore1(), gview.getScore2());
	}

	@Override
	public void modelUpdatesWinnerName(String participantWinnerName) {
		view2.setWinnerTextField(gview.getbTemp(), participantWinnerName);
		gview.close();
	}

	@Override
	public void addMoreTextFields() {
		gview.unGreyTextFields();
	}

	@Override
	public void modelUpdatesnoWinner() {
		gview.shownoWinnerEnterAgainError();
	}

	@Override
	public void modelUpdatesNeedMoreScoreForSoccer() {
		gview.addMoreTextFieldsSoccer();
	}

}
