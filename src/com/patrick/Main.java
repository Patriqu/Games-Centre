package com.patrick;

import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
	@Override
	public void start(Stage stage) {
		try {
			stage.setTitle("Games centre");
			GamesController controller = new GamesController(stage);
			controller.setScene("TicTacToe");
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
