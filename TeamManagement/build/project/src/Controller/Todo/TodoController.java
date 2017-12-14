package Controller.Todo;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class TodoController {
	@FXML private AnchorPane realViewPane;
	@FXML protected void initialize() throws IOException{
		// Todo의 첫 메뉴 화면으로 이동
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	public void hMenuButton1(ActionEvent event) throws IOException {
		 AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	public void hMenuButton2(ActionEvent event) throws IOException {
		 AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_All.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
}
