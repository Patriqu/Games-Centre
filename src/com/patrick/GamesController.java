package com.patrick;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Font;

public class GamesController implements Initializable {
	@FXML private Button button0;
	@FXML private Button button1;
	@FXML private Button button2;
	@FXML private Button button3;
	@FXML private Button button4;
	@FXML private Button button5;
	@FXML private Button button6;
	@FXML private Button button7;
	@FXML private Button button8;
	
	@FXML private MenuItem ticTacToeLink;
	@FXML private MenuItem tetrisLink;
	@FXML private MenuItem checkersLink;
	
	private Button buttons[] = new Button[9];
	private MenuItem menuItems[] = new MenuItem[3];
	
	private final int winCombinations[][] = {
			{ 0, 1, 2},
			{ 3, 4, 5},
			{ 6, 7, 8},
			{ 0, 3, 6},
			{ 1, 4, 7},
			{ 2, 5, 8},
			{ 0, 4, 8},
			{ 6, 4, 3}
	};
	
	private final String playerX = "X";
	private final String playerY = "Y";
	private String currentPlayer = playerX;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initButtons();
		initMenus();
	}
	
	private void initButtons() {
		buttons[0] = button0;
		buttons[1] = button1;
		buttons[2] = button2;
		buttons[3] = button3;
		buttons[4] = button4;
		buttons[5] = button5;
		buttons[6] = button6;
		buttons[7] = button7;
		buttons[8] = button8;
		
		addEventHandlersToButtons();
	}
	
	private void initMenus() {
		menuItems[0] = ticTacToeLink;
		menuItems[1] = tetrisLink;
		menuItems[2] = checkersLink;
		
		addEventHandlersToMenu();
	}
	
	private void addEventHandlersToButtons() {
		for (int i = 0; i < buttons.length; i++) {
			final Integer innerI = new Integer(i);
			
			buttons[innerI].setOnMousePressed(new EventHandler<Event>() {
				@Override
				public void handle(Event event) {
					buttons[innerI].setFont(new Font(64));
					buttons[innerI].setText(currentPlayer);
					System.out.println("Button " + innerI  + " clicked!");
					
					checkIfPlayerWins();
				}
			});
		}
	}
	
	private void addEventHandlersToMenu() {
		for (int i = 0; i < menuItems.length; i++) {
			final Integer innerI = new Integer(i);
			
			menuItems[innerI].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String txt = menuItems[innerI].getText();
					System.out.println(txt + " menu link" + " clicked!");
				}
			});
		}
	}
	
	private void checkIfPlayerWins() {
		for (int i = 0; i < winCombinations.length; i++) {
			if (buttons[winCombinations[i][0]].getText().equals(currentPlayer) &&
					buttons[winCombinations[i][1]].getText().equals(currentPlayer) &&
					buttons[winCombinations[i][2]].getText().equals(currentPlayer)) {
				win();
				return;
			}
		}
		changeCurrentPlayer();
	}
	
	private void win() {
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
	
	private void changeCurrentPlayer() {
		if (currentPlayer == playerX) {
			currentPlayer = playerY;
		}
		else {
			currentPlayer = playerX;
		}
	}
}
