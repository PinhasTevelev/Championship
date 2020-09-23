package Model;

import java.util.ArrayList;

public class GameModel {

	private GameModelToModelable gameViewListener;

	private ArrayList<Integer> scorename1;
	private ArrayList<Integer> scorename2;
	private String name1;
	private String name2;
	private String gameType;

	private String WinnerName;

	private int scorerounds1;
	private int scorerounds2;

	public GameModel(String gameType, String name1, String name2) {
		this.name1 = name1;
		this.name2 = name2;
		this.gameType = gameType;
		this.scorerounds1 = 0;
		this.scorerounds2 = 0;
	}

	public void startGame(ArrayList<Integer> score1, ArrayList<Integer> score2) {
		this.scorename1 = score1;
		this.scorename2 = score2;

		if (gameType.equals("Tennis")) {

			for (int i = 0; i < 5; i++) {
				if (score1.get(i) > score2.get(i)) {
					scorerounds1++;
				} else if (score1.get(i) < score2.get(i)) {
					scorerounds2++;
				}
			}

			if (scorerounds1 == 3 && scorerounds2 == 0 || scorerounds1 == 4 && scorerounds2 == 1
					|| scorerounds1 == 3 && scorerounds2 == 2) {
				gameViewListener.gameModelSetnsWinner(name1);

			} else if (scorerounds1 == 0 && scorerounds2 == 3 || scorerounds1 == 1 && scorerounds2 == 4
					|| scorerounds1 == 2 && scorerounds2 == 3) {
				gameViewListener.gameModelSetnsWinner(name2);
			} else {
				updateListenerToUpdateForMoreScoreTennis();
			}
		}

		if (gameType.equals("BasketBall")) {
			for (int i = 0; i < 4; i++) {
				if (score1.get(i) > score2.get(i)) {
					scorerounds1++;

				} else if (score1.get(i) < score2.get(i)) {
					scorerounds2++;
				}
			}
			if (scorerounds1 > scorerounds2) {
				gameViewListener.gameModelSetnsWinner(name1);
				;
			} else if (scorerounds1 < scorerounds2) {
				gameViewListener.gameModelSetnsWinner(name2);
			} else {
				updateListenerThereIsNoWinner();
			}
		}

		if (gameType.equals("Soccer")) {
			int temp1 = 0;
			int temp2 = 0;
			for (int i = 0; i < 4; i++) {
				temp1 = temp1 + score1.get(i);
				temp2 = temp2 + score2.get(i);
			}
			if (temp1 == temp2) {
				updateListeneraddMoreRoundsForSoccer();
			} else if (temp1 > temp2) {
				gameViewListener.gameModelSetnsWinner(name1);

			} else {
				gameViewListener.gameModelSetnsWinner(name2);
			}
		}
	}

	private void updateListeneraddMoreRoundsForSoccer() {
		gameViewListener.gameModelUpdatesNeedMoreScoreFroSoccer();

	}

	private void updateListenerThereIsNoWinner() {
		gameViewListener.gameModelUpdatesNoWinner();
	}

	private void updateListenerToUpdateForMoreScoreTennis() {
		gameViewListener.gameModelUpdatesNeedForScore();
	}

	public ArrayList<Integer> getScorename1() {
		return scorename1;
	}

	public ArrayList<Integer> getScorename2() {
		return scorename2;
	}

	public String getWinnerName() {
		return WinnerName;
	}

	public void registerListener(GameModelToModelable lgv) {
		gameViewListener = lgv;
	}

}
