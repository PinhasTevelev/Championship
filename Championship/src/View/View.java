package View;

import java.util.ArrayList;

import Listeners.ViewListenable;
import javafx.event.ActionEvent;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class View {

	private ArrayList<ViewListenable> allListeners;
	private ArrayList<TextField> tfallNames;

	private GridPane gpMain;

	private TextField tfParticipantName;

	private Label lblTitle;
	private Label lblParticipantName;

	private Button btnAddParticipant;
	private Button btnStartChampionship;

	private RadioButton rbTennis;
	private RadioButton rbBasketball;
	private RadioButton rbSoccer;
	private Scene scene;
	private Stage stage;

	public View(Stage praimaryStage) {
		allListeners = new ArrayList<ViewListenable>();
		tfallNames = new ArrayList<TextField>();

		this.stage = praimaryStage;
		gpMain = new GridPane();
		gpMain.setPrefHeight(800);
		gpMain.setPrefWidth(1200);
		setGridCells();

		VBox vbNames = new VBox(50.0);
		vbNames.setAlignment(Pos.CENTER);
		vbNames.setMaxWidth(200.0);
		vbNames.setPrefHeight(200.0);

		createTextFields();

		vbNames.getChildren().addAll(tfallNames);
		gpMain.add(vbNames, 0, 1);
		GridPane.setHalignment(vbNames, HPos.LEFT);
		GridPane.setMargin(vbNames, new Insets(0, 0, 0, 50));

		VBox vbgameChoice = new VBox(15);
		vbgameChoice.setAlignment(Pos.CENTER_LEFT);
		vbgameChoice.setMaxWidth(100.0);
		vbgameChoice.setPrefWidth(100.0);
		vbgameChoice.setPrefHeight(200.0);
		vbgameChoice.setMaxHeight(200.0);
		vbgameChoice.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

		ToggleGroup tgrpCohice = new ToggleGroup();
		rbTennis = new RadioButton("Tennis");
		rbTennis.setSelected(true);
		rbTennis.setToggleGroup(tgrpCohice);
		rbBasketball = new RadioButton("BasketBall");
		rbBasketball.setToggleGroup(tgrpCohice);
		rbSoccer = new RadioButton("Soccer");
		rbSoccer.setToggleGroup(tgrpCohice);
		vbgameChoice.getChildren().addAll(rbTennis, rbBasketball, rbSoccer);
		gpMain.add(vbgameChoice, 2, 1);
		GridPane.setMargin(vbgameChoice, new Insets(0, 100, 0, 0));
		GridPane.setHalignment(vbgameChoice, HPos.CENTER);
		GridPane.setValignment(vbNames, VPos.CENTER);
		HBox hbfirst = new HBox(20);

		btnAddParticipant = new Button("Add Participant");
		btnStartChampionship = new Button("Start Championship");
		lblParticipantName = new Label("Participant Name: ");
		lblParticipantName.setStyle("-fx-font-size: 20;");
		lblParticipantName.setAlignment(Pos.CENTER);
		tfParticipantName = new TextField();
		tfParticipantName.setMaxWidth(150.0);
		tfParticipantName.setPrefWidth(150.0);

		hbfirst.getChildren().addAll(lblParticipantName, tfParticipantName);
		hbfirst.setAlignment(Pos.CENTER);

		HBox hbsecond = new HBox(50);

		hbsecond.getChildren().addAll(btnAddParticipant, btnStartChampionship);
		hbsecond.setAlignment(Pos.CENTER);

		VBox vbCenter = new VBox(50);
		vbCenter.getChildren().addAll(hbfirst, hbsecond);
		vbCenter.setAlignment(Pos.CENTER);
		gpMain.add(vbCenter, 1, 1);
		GridPane.setHalignment(vbCenter, HPos.CENTER);
		GridPane.setValignment(vbCenter, VPos.CENTER);

		lblTitle = new Label("Championship");
		lblTitle.setPadding(new Insets(30));
		lblTitle.setFont(new Font(40));
		;
		lblTitle.setStyle("-fx-text-fill: red;");
		gpMain.add(lblTitle, 1, 0);
		GridPane.setHalignment(lblTitle, HPos.CENTER);
		GridPane.setValignment(lblTitle, VPos.TOP);

		btnAddParticipant.setOnAction(e -> handleButtonActionAddParticipant(e));
		btnStartChampionship.setOnAction(e -> handleButtonActionStartChampionship(e));

		scene = new Scene(gpMain, 1200, 800);
		praimaryStage.setTitle("ChampionShip");
		praimaryStage.setScene(scene);
		praimaryStage.show();
	}

	public String getGameType() {
		if (rbBasketball.isSelected()) {
			return rbBasketball.getText();
		}
		if (rbSoccer.isSelected()) {
			return rbSoccer.getText();
		}
		if (rbTennis.isSelected()) {
			return rbTennis.getText();
		}
		return null;
	}

	private void setGridCells() {
		for (int i = 0; i < 3; i++) {
			ColumnConstraints c = new ColumnConstraints();
			c.setPercentWidth(33);
			c.setHgrow(Priority.SOMETIMES);
			gpMain.getColumnConstraints().add(c);
		}

		for (int i = 0; i < 3; i++) {
			RowConstraints r = new RowConstraints();
			r.setPercentHeight(33);
			r.setVgrow(Priority.SOMETIMES);
			gpMain.getRowConstraints().add(r);
		}

	}

	private void createTextFields() {
		for (int i = 0; i < 8; i++) {
			TextField tf = new TextField();
			tf.setEditable(false);
			tf.setStyle("-fx-border-color: blue;");
			tfallNames.add(tf);

		}
	}

	public void registerListener(ViewListenable lv) {
		allListeners.add(lv);
	}

	private void handleButtonActionAddParticipant(ActionEvent e) {
		allListeners.get(0).viewSendsNameToModel(tfParticipantName.getText());
		tfParticipantName.clear();
	}

	private void handleButtonActionStartChampionship(ActionEvent e) {
		if (allListeners.get(0).viewAsksIfGotPlace()) {
			showErrorCantStartChampionship();
		} else {
			allListeners.get(0).viewUpdateChampionshipStart(stage);
		}
	}

	public void viewAddParticipantName(String participantName, int index) {
		tfallNames.get(index).setText(participantName);
	}

	public void showErrorCantStartChampionship() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Participants Number Not valid");
		errorAlert.setContentText("Cannot Start Championship!!\\nNot Enough Partisipants");
		errorAlert.showAndWait();
	}

	public void showErrorParticipantNameIsEmpty() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Input not valid");
		errorAlert.setContentText("You Have Not Entered A Name!");
		errorAlert.showAndWait();
	}

	public void showErrorGroupIsFull() {
		Alert errorAlert = new Alert(AlertType.ERROR);
		errorAlert.setHeaderText("Participants Number full valid");
		errorAlert.setContentText("You Cant Add More Then 8 Participants!");
		errorAlert.showAndWait();
	}

	public ArrayList<TextField> getTfallNames() {
		return tfallNames;
	}

}
