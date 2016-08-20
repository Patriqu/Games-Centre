package com.patrick;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.text.Font;


public class TicTacToeController extends GamesController implements Initializable {
	@FXML private Button button0;
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;
	@FXML private Button button4;
	@FXML private Button button5;
	@FXML private Button button6;
	@FXML private Button button7;
	@FXML private Button button8;
	
	private List<Button> buttons = new ArrayList<>();
	
	private final int WIN_COMBINATIONS[][] = {
			{ 0, 1, 2},
			{ 3, 4, 5},
			{ 6, 7, 8},
			{ 0, 3, 6},
			{ 1, 4, 7},
			{ 2, 5, 8},
			{ 0, 4, 8},
			{ 6, 4, 3}
	};
	
	private final String PLAYER_X = "X";
	private final String PLAYER_Y = "Y";
	private String currentPlayer = PLAYER_X;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		super.initialize(location, resources);
		initButtons();
	}
	
	private void initButtons() {
		buttons.add(button0);
		buttons.add(button1);
		buttons.add(button2);
		buttons.add(button3);
		buttons.add(button4);
		buttons.add(button5);
		buttons.add(button6);
		buttons.add(button7);
		buttons.add(button8);
		
		addEventHandlersToButtons();
	}
	
	private void addEventHandlersToButtons() {
		for (int i = 0; i < buttons.size(); i++) {
			final Integer innerI = new Integer(i);
			final Button button = buttons.get(innerI);
			
			button.setOnMousePressed(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					if (button.getText().equals("")) {
						button.setFont(new Font(64));
						button.setText(currentPlayer);
						System.out.println("Button " + innerI  + " clicked!");
						
						checkIfPlayerWins();
					}
				}
			});
		}
	}
	
	private void checkIfPlayerWins() {
		for (int i = 0; i < WIN_COMBINATIONS.length; i++) {
			if (buttons.get(WIN_COMBINATIONS[i][0]).getText().equals(currentPlayer) &&
					buttons.get(WIN_COMBINATIONS[i][1]).getText().equals(currentPlayer) &&
					buttons.get(WIN_COMBINATIONS[i][2]).getText().equals(currentPlayer)) {
				winHandle();
				return;
			}
		}
		changeCurrentPlayer();
	}
	
	private void changeCurrentPlayer() {
		if (currentPlayer == PLAYER_X) {
			currentPlayer = PLAYER_Y;
		}
		else {
			currentPlayer = PLAYER_X;
		}
	}
	
	private void winHandle() {
		showWinDialog();
		resetGame();
	}
	
	private void showWinDialog() {
		final String WIN_TEXT = "Player " + currentPlayer + " wins!";
		
		Dialog<String> winDialog = new Dialog<>();
		DialogPane dialogPane = winDialog.getDialogPane();
		ButtonType okButton = new ButtonType("Ok", ButtonData.OK_DONE);
		ButtonType cancelButton = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);
		
		dialogPane.setContentText(WIN_TEXT);
		dialogPane.getButtonTypes().add(okButton);
		dialogPane.getButtonTypes().add(cancelButton);
		
		winDialog.setTitle("You win!");
		winDialog.setDialogPane(dialogPane);
		winDialog.show();
		
		System.out.println(WIN_TEXT);
	}
	
	private void resetGame() {
		for (Button button : buttons) {
			button.setText("");
		}
		currentPlayer = PLAYER_X;
	}
}
