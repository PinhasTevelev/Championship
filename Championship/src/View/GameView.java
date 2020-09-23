package View;

import java.util.ArrayList;

import Listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameView {
	private ArrayList<ViewListenable> allListeners;

	private ArrayList<TextField> tf1;
	private ArrayList<TextField> tf2;

	private ArrayList<Integer> score1;
	private ArrayList<Integer> score2;
	private Stage stage;

	private Scene scene;

	private GridPane gppane;

	private Label lblGameType;
	private Label name1;
	private Label name2;

	private HBox hbTF1;
	private HBox hbTF2;

	private Button btnDone;
	private Button bTemp;

	public GameView(Button b, String participantName1, String participantName2, String gameType) {

		allListeners = new ArrayList<ViewListenable>();
		bTemp = b;
		stage = new Stage();

		gppane = new GridPane();
		gppane.setAlignment(Pos.CENTER);
		gppane.setPrefHeight(500);
		gppane.setPrefWidth(400);

		createRowsAndColums();

		createTextField(gameType);

		GridPane gpCenter = new GridPane();

		ColumnConstraints c1 = new ColumnConstraints();
		c1.setPercentWidth(100);
		c1.setHgrow(Priority.SOMETIMES);
		gpCenter.getColumnConstraints().add(c1);

		RowConstraints r1 = new RowConstraints();
		r1.setPercentHeight(50);
		r1.setVgrow(Priority.SOMETIMES);
		gpCenter.getRowConstraints().add(r1);

		RowConstraints r2 = new RowConstraints();
		r2.setPercentHeight(50);
		r2.setVgrow(Priority.SOMETIMES);
		gpCenter.getRowConstraints().add(r2);

		hbTF1 = new HBox(10);
		name1 = new Label(participantName1);
		name1.setMaxWidth(50);
		name1.setPrefWidth(50);

		hbTF1.getChildren().add(name1);
		hbTF1.getChildren().addAll(tf1);

		gpCenter.add(hbTF1, 0, 0, 2, 1);

		hbTF2 = new HBox(10);
		name2 = new Label(participantName2);
		name2.setMaxWidth(50);
		name2.setPrefWidth(50);

		hbTF2.getChildren().add(name2);
		hbTF2.getChildren().addAll(tf2);

		gpCenter.add(hbTF2, 0, 1, 2, 1);
		gppane.add(gpCenter, 0, 1, 2, 1);

		btnDone = new Button("Done!");
		gppane.add(btnDone, 0, 2, 2, 1);
		GridPane.setHalignment(btnDone, HPos.CENTER);

		lblGameType = new Label(gameType);
		lblGameType.setFont(new Font(40));
		lblGameType.setTextFill(Color.RED);

		gppane.add(lblGameType, 0, 0, 2, 1);
		GridPane.setHalignment(lblGameType, HPos.CENTER);
		btnDone.setOnAction(e -> sendScoresEventHandler(e));

		scene = new Scene(gppane, 500, 400);

		stage.setScene(scene);
		stage.setTitle("New Game");
		stage.show();

	}

	private void sendScoresEventHandler(ActionEvent e) {
		score1 = new ArrayList<Integer>();
		score2 = new ArrayList<Integer>();

		for (int i = 0; i < tf1.size(); i++) {
			score1.add(Integer.parseInt(tf1.get(i).getText()));
			score2.add(Integer.parseInt(tf2.get(i).getText()));
		}
		allListeners.get(0).viewGameSendsScoresToGetWinner();
	}

	private void createTextField(String gameType) {

		if (gameType.equals("Tennis")) {

			makeTextFieldForGame(5);
			tf1.get(3).setEditable(false);
			tf1.get(3).setText("0");
			tf1.get(4).setEditable(false);
			tf1.get(4).setText("0");
			tf2.get(3).setEditable(false);
			tf2.get(3).setText("0");
			tf2.get(4).setEditable(false);
			tf2.get(4).setText("0");
		}
		if (gameType.equals("BasketBall")) {
			makeTextFieldForGame(4);
		}
		if (gameType.equals("Soccer")) {
			makeTextFieldForGame(4);
			tf1.get(2).setEditable(false);
			tf1.get(2).setText("0");
			tf2.get(2).setEditable(false);
			tf2.get(2).setText("0");
			tf1.get(3).setEditable(false);
			tf1.get(3).setText("0");
			tf2.get(3).setEditable(false);
			tf2.get(3).setText("0");
		}
	}

	private void makeTextFieldForGame(int size) {
		tf1 = new ArrayList<TextField>();
		tf2 = new ArrayList<TextField>();
		for (int i = 0; i < size; i++) {
			TextField tf = new TextField();
			tf.setMaxWidth(30);
			tf.setPrefWidth(30);
			tf1.add(tf);
		}
		for (int i = 0; i < size; i++) {
			TextField tf = new TextField();
			tf.setMaxWidth(30);
			tf.setPrefWidth(30);
			tf2.add(tf);
		}

	}

	public void unGreyTextFields() {

		tf1.get(4).setEditable(true);
		tf1.get(3).setEditable(true);
		tf2.get(4).setEditable(true);
		tf2.get(3).setEditable(true);

		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Still no Winner");
		errorAlert.setContentText("Now lets add 2 more");
		errorAlert.showAndWait();

	}

	public void shownoWinnerEnterAgainError() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("No Winner");
		errorAlert.setContentText("There is no Winner\nTry Again!");
		errorAlert.showAndWait();

	}

	private void createRowsAndColums() {
		for (int i = 0; i < 2; i++) {
			ColumnConstraints c = new ColumnConstraints();
			c.setPercentWidth(25);
			c.setHgrow(Priority.SOMETIMES);
			gppane.getColumnConstraints().add(c);
		}
		for (int i = 0; i < 3; i++) {
			RowConstraints r = new RowConstraints();
			r.setPercentHeight(33);
			r.setVgrow(Priority.SOMETIMES);
			gppane.getRowConstraints().add(r);
		}
	}

	public void addMoreTextFieldsSoccer() {

		if (tf1.get(2).isEditable()) {
			tf1.get(3).setEditable(true);
			tf2.get(3).setEditable(true);
			showUpdateNeedPenaltiesSoccer();
		} else {
			tf1.get(2).setEditable(true);
			tf2.get(2).setEditable(true);
			showUpdateNeedMoreRound();
		}
	}

	private void showUpdateNeedPenaltiesSoccer() {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setHeaderText("Soccer Penalties Round");
		errorAlert.setContentText("its Still Even. Now Enter Penalties!");
		errorAlert.showAndWait();
	}

	private void showUpdateNeedMoreRound() {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setHeaderText("Soccer Third Round");
		errorAlert.setContentText("its Even. Now Enter Another Round!");
		errorAlert.showAndWait();
	}

	public void close() {
		stage.close();
	}

	public void registerListener(ViewListenable lvGame) {
		allListeners.add(lvGame);
	}

	public String getName1() {
		return name1.getText();
	}

	public String getName2() {
		return name2.getText();
	}

	public ArrayList<TextField> getTf1() {
		return tf1;
	}

	public ArrayList<TextField> getTf2() {
		return tf2;
	}

	public ArrayList<Integer> getScore1() {
		return score1;
	}

	public ArrayList<Integer> getScore2() {
		return score2;
	}

	public Button getbTemp() {
		return bTemp;
	}

}
