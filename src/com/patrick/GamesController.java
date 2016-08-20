package com.patrick;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

public class GamesController implements Initializable {
	@FXML protected MenuItem ticTacToeLink;
	@FXML protected MenuItem tetrisLink;
	@FXML protected MenuItem checkersLink;
	
	private MenuItem menuItems[] = new MenuItem[3];
	
	private static Stage storedStage;
	
	public GamesController() {}
	
	public GamesController(Stage stage) {
		storedStage = stage;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		initMenus();
	}
	
	private void initMenus() {
		menuItems[0] = ticTacToeLink;
		menuItems[1] = tetrisLink;
		menuItems[2] = checkersLink;
		
		addEventHandlersToMenu();
	}
	
	public void setScene(String sceneName) {		
		try {
			Parent root = FXMLLoader.load(getClass().getResource(sceneName + ".fxml"));
			Scene scene = new Scene(root,600,600);
			
			storedStage.setScene(scene);
			storedStage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void addEventHandlersToMenu() {
		for (int i = 0; i < menuItems.length; i++) {
			final Integer innerI = new Integer(i);
			
			menuItems[innerI].setOnAction(new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
					String txt = menuItems[innerI].getText();
					String txt2 = txt.replaceAll(" ", "");
					System.out.println(txt + " menu link" + " clicked!");
					setScene(txt2);
				}
			});
		}
	}
}
