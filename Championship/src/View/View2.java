package View;

import java.util.ArrayList;

import Listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View2 {
	private ArrayList<ViewListenable> allListeners;

	ArrayList<TextField> tfAllNames;
	private GridPane gpMain;

	private Button btnQFGame1;
	private Button btnQFGame2;
	private Button btnQFGame3;
	private Button btnQFGame4;

	private Button btnSFGame1;
	private Button btnSFGame2;

	private Button btnFinalGame;

	private TextField tfQFWinner1;
	private TextField tfQFWinner2;
	private TextField tfQFWinner3;
	private TextField tfQFWinner4;
	private TextField tfSFWinner1;
	private TextField tfSFWinner2;
	private TextField tfFinalWinner;

	private Label lblTitle;

	private VBox vbNames;

	private VBox vbQFButtons;
	private VBox vbQFTextFieldWinners;

	private VBox vbSFButtons;
	private VBox vbSFTextFieldWinners;

	private HBox hbQF;
	private HBox hbSF;
	private HBox hbFinal;

	Scene scene;

	public View2(Stage primatyStage) {
		allListeners = new ArrayList<ViewListenable>();
		tfAllNames = new ArrayList<TextField>();

		gpMain = new GridPane();
		gpMain.setPrefHeight(800);
		gpMain.setMinWidth(1200);
		gpMain.setPrefWidth(1200);
		gpMain.setPadding(new Insets(20));

		setAllValues();
		setColumsAndRows();

		// Box that contains the names
		vbNames = new VBox(40);
		vbNames.setAlignment(Pos.CENTER);
		vbNames.setFillWidth(false);
		vbNames.getChildren().addAll(tfAllNames);
		gpMain.add(vbNames, 0, 1, 1, 8);

		// Box for QuaterFinal Buttons
		hbQF = new HBox(20);
		vbQFButtons = new VBox(110);
		vbQFButtons.setFillWidth(false);
		vbQFButtons.setAlignment(Pos.CENTER_LEFT);
		vbQFButtons.getChildren().addAll(btnQFGame1, btnQFGame2, btnQFGame3, btnQFGame4);

		vbQFTextFieldWinners = new VBox(110);
		vbQFTextFieldWinners.setFillWidth(false);
		vbQFTextFieldWinners.setAlignment(Pos.CENTER_RIGHT);
		vbQFTextFieldWinners.getChildren().addAll(tfQFWinner1, tfQFWinner2, tfQFWinner3, tfQFWinner4);
		hbQF.getChildren().addAll(vbQFButtons, vbQFTextFieldWinners);
		gpMain.add(hbQF, 1, 1, 1, 8);// add to main pane

		// Box for Semi Finals
		hbSF = new HBox(20);
		vbSFButtons = new VBox(250);
		vbSFButtons.setAlignment(Pos.CENTER_LEFT);
		vbSFButtons.setFillWidth(false);
		vbSFButtons.getChildren().addAll(btnSFGame1, btnSFGame2);

		vbSFTextFieldWinners = new VBox(250);
		vbSFTextFieldWinners.setAlignment(Pos.CENTER_RIGHT);
		vbSFTextFieldWinners.setFillWidth(false);
		vbSFTextFieldWinners.getChildren().addAll(tfSFWinner1, tfSFWinner2);

		hbSF.getChildren().addAll(vbSFButtons, vbSFTextFieldWinners);
		gpMain.add(hbSF, 2, 1, 1, 8);

		// box of Final
		hbFinal = new HBox(20);
		hbFinal.setAlignment(Pos.CENTER_LEFT);
		hbFinal.getChildren().addAll(btnFinalGame, tfFinalWinner);
		gpMain.add(hbFinal, 3, 1, 1, 8);

		// title
		lblTitle = new Label("ChampionShip");
		lblTitle.setFont(new Font(30));
		lblTitle.setTextFill(Color.RED);
		lblTitle.setStyle("");
		lblTitle.setAlignment(Pos.CENTER);
		gpMain.add(lblTitle, 1, 0, 2, 1);
		GridPane.setValignment(lblTitle, VPos.CENTER);
		GridPane.setHalignment(lblTitle, HPos.CENTER);

		btnQFGame1.setOnAction(e -> eventHandlerGames(e));
		btnQFGame2.setOnAction(e -> eventHandlerGames(e));
		btnQFGame3.setOnAction(e -> eventHandlerGames(e));
		btnQFGame4.setOnAction(e -> eventHandlerGames(e));
		btnSFGame1.setOnAction(e -> eventHandlerGames(e));
		btnSFGame2.setOnAction(e -> eventHandlerGames(e));
		btnFinalGame.setOnAction(e -> eventHandlerGames(e));

		scene = new Scene(gpMain, 1200, 800);
		primatyStage.setScene(scene);
		primatyStage.setTitle("ChampionShip");
		primatyStage.show();
	}

	private void eventHandlerGames(ActionEvent e) {
		Button b = (Button) e.getSource();
		if (b.equals(btnQFGame1)) {
			updateListenerNewGameStarts(b, tfAllNames.get(0).getText(), tfAllNames.get(1).getText());
		}
		if (b.equals(btnQFGame2)) {
			updateListenerNewGameStarts(b, tfAllNames.get(2).getText(), tfAllNames.get(3).getText());

		}
		if (b.equals(btnQFGame3)) {
			updateListenerNewGameStarts(b, tfAllNames.get(4).getText(), tfAllNames.get(5).getText());

		}
		if (b.equals(btnQFGame4)) {
			updateListenerNewGameStarts(b, tfAllNames.get(6).getText(), tfAllNames.get(7).getText());
		}

		if (b.equals(btnSFGame1)) {
			if (!tfQFWinner1.getText().isEmpty() && !tfQFWinner2.getText().isEmpty()) {
				updateListenerNewGameStarts(b, tfQFWinner1.getText(), tfQFWinner2.getText());
			} else {
				updateNotFinishedYet();
			}
		}
		if (b.equals(btnSFGame2)) {
			if (!tfQFWinner3.getText().isEmpty() && !tfQFWinner4.getText().isEmpty()) {
				updateListenerNewGameStarts(b, tfQFWinner3.getText(), tfQFWinner4.getText());
			} else {
				updateNotFinishedYet();
			}
		}
		if (b.equals(btnFinalGame)) {
			if (!tfSFWinner1.getText().isEmpty() && !tfSFWinner2.getText().isEmpty()) {
				updateListenerNewGameStarts(b, tfSFWinner1.getText(), tfSFWinner2.getText());
			} else {
				updateNotFinishedYet();
			}
		}

	}

	private void updateListenerNewGameStarts(Button b, String name1, String name2) {
		allListeners.get(0).viewTellsGoToGameView(b, name1, name2);
	}

	private void updateNotFinishedYet() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("no Participant Error");
		errorAlert.setContentText("there are not Enough participants to start the game!! finish the previous game!!");
		errorAlert.showAndWait();

	}

	private void setColumsAndRows() {
		for (int i = 0; i < 4; i++) {
			ColumnConstraints c = new ColumnConstraints();
			c.setHgrow(Priority.SOMETIMES);
			c.setPrefWidth(100.0);
			gpMain.getColumnConstraints().add(c);
		}

		for (int i = 0; i < 10; i++) {
			RowConstraints r = new RowConstraints();
			r.setVgrow(Priority.SOMETIMES);
			r.setPrefHeight(30.0);
			gpMain.getRowConstraints().add(r);
		}

	}

	private void setAllValues() {
		for (int i = 0; i < 8; i++) {
			TextField tf = new TextField();
			tf.setEditable(false);
			tf.setStyle("-fx-border-color: blue;");
			tfAllNames.add(tf);
		}

		tfQFWinner1 = new TextField();
		tfQFWinner1.setEditable(false);
		tfQFWinner1.setStyle("-fx-border-color: blue;");

		tfQFWinner2 = new TextField();
		tfQFWinner2.setEditable(false);
		tfQFWinner2.setStyle("-fx-border-color: blue;");

		tfQFWinner3 = new TextField();
		tfQFWinner3.setEditable(false);
		tfQFWinner3.setStyle("-fx-border-color: blue;");

		tfQFWinner4 = new TextField();
		tfQFWinner4.setEditable(false);
		tfQFWinner4.setStyle("-fx-border-color: blue;");

		tfSFWinner1 = new TextField();
		tfSFWinner1.setEditable(false);
		tfSFWinner1.setStyle("-fx-border-color: blue;");

		tfSFWinner2 = new TextField();
		tfSFWinner2.setEditable(false);
		tfSFWinner2.setStyle("-fx-border-color: blue;");

		tfFinalWinner = new TextField();
		tfFinalWinner.setEditable(false);
		tfFinalWinner.setStyle("-fx-border-color: red;");

		btnQFGame1 = new Button("Play Game");
		btnQFGame2 = new Button("Play Game");
		btnQFGame3 = new Button("Play Game");
		btnQFGame4 = new Button("Play Game");
		btnSFGame1 = new Button("Play Game");
		btnSFGame2 = new Button("Play Game");
		btnFinalGame = new Button("Play Game");

	}

	public void registerListener(ViewListenable lv) {

		allListeners.add(lv);
	}

	public void updateNames(ArrayList<TextField> tfallNames2) {
		for (int i = 0; i < 8; i++) {
			tfAllNames.get(i).setText(tfallNames2.get(i).getText());
		}
	}

	public void setWinnerTextField(Button b, String participantWinnerName) {

		if (b.equals(btnQFGame1)) {
			tfQFWinner1.setText(participantWinnerName);
			btnQFGame1.setDisable(true);
		}
		if (b.equals(btnQFGame2)) {
			tfQFWinner2.setText(participantWinnerName);
			btnQFGame2.setDisable(true);
		}
		if (b.equals(btnQFGame3)) {
			tfQFWinner3.setText(participantWinnerName);
			btnQFGame3.setDisable(true);
		}
		if (b.equals(btnQFGame4)) {
			tfQFWinner4.setText(participantWinnerName);
			btnQFGame4.setDisable(true);
		}
		if (b.equals(btnSFGame1)) {
			tfSFWinner1.setText(participantWinnerName);
			btnSFGame1.setDisable(true);
		}
		if (b.equals(btnSFGame2)) {
			tfSFWinner2.setText(participantWinnerName);
			btnSFGame2.setDisable(true);
		}
		if (b.equals(btnFinalGame)) {
			tfFinalWinner.setText(participantWinnerName);
			btnFinalGame.setDisable(true);
			updateWinnerofChampionShip();
		}

	}

	private void updateWinnerofChampionShip() {
		Alert errorAlert = new Alert(AlertType.INFORMATION);
		errorAlert.setHeaderText("Championship Winner:");
		errorAlert.setContentText(tfFinalWinner.getText());
		errorAlert.showAndWait();

	}

}
