package Model;

import java.util.ArrayList;

import Listeners.ModelListenable;

public class Model implements GameModelToModelable {

	private GameModel gm;

	private ArrayList<ModelListenable> allListeners;

	private ArrayList<String> allParticipants;

	private String gameType;

	public Model() {
		allListeners = new ArrayList<ModelListenable>();
		allParticipants = new ArrayList<String>();
	}

	public void registerListener(ModelListenable lm) {
		allListeners.add(lm);
	}

	public void addParticipant(String participantName) {
		if (allParticipants.size() != 8) {
			if (!participantName.isEmpty()) {
				allParticipants.add(participantName);
				updateListenerParticipantAdded(participantName, allParticipants.size() - 1);
			} else {
				UpdateListenerNotStringEntered();
			}
		} else {
			updateListenerFullGroup();
		}
	}

	private void updateListenerFullGroup() {
		for (ModelListenable l : allListeners) {
			l.modelUpdatesNoPlace();
		}
	}

	private void updateListenerParticipantAdded(String participantName, int index) {
		for (ModelListenable l : allListeners) {
			l.modelAddParticipantName(participantName, index);
		}
	}

	private void UpdateListenerNotStringEntered() {
		for (ModelListenable l : allListeners) {
			l.modelUpdatesNotName();
		}
	}

	public boolean checkIfgotPlace() {
		if (allParticipants.size() == 8) {
			return false;
		}
		return true;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public void startGame(String name1, String name2, ArrayList<Integer> score1, ArrayList<Integer> score2) {
		gm = new GameModel(gameType, name1, name2);
		gm.registerListener(this);
		gm.startGame(score1, score2);
	}

	@Override
	public void gameModelSetnsWinner(String participantwinner) {
		allListeners.get(0).modelUpdatesWinnerName(participantwinner);

	}

	@Override
	public void gameModelUpdatesNeedForScore() {
		allListeners.get(0).addMoreTextFields();
	}

	@Override
	public void gameModelUpdatesNotWinnerUpdateScore() {
		allListeners.get(0).modelUpdatesnoWinner();
	}

	@Override
	public void gameModelUpdatesNoWinner() {
		allListeners.get(0).modelUpdatesnoWinner();
	}

	@Override
	public void gameModelUpdatesNeedMoreScoreFroSoccer() {
		allListeners.get(0).modelUpdatesNeedMoreScoreForSoccer();

	}

}
