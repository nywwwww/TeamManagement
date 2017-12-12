package Controller.Todo;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class TodoController {
	@FXML private AnchorPane realViewPane;
	@FXML protected void initialize() throws IOException{
		// Todo의 첫 메뉴 화면으로 이동
		 AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
}
