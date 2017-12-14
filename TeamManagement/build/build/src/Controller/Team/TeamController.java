package Controller.Team;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class TeamController {

	@FXML private AnchorPane realViewPane;
	@FXML protected void initialize() throws IOException{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Team_Info.fxml"));
		realViewPane.getChildren().setAll(pane);	
	}
	public void hTeamSubMenuButton1(ActionEvent event) throws IOException{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Team_Info.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
}
