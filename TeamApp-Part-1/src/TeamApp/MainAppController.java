package TeamApp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class MainAppController {
	@FXML private Button RegActiontarget;
	@FXML private Button BackActiontarget;
	
	
	@FXML protected void hRegButtonAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/Registration.fxml"));
			Parent page = loader.load();
			((Stage)RegActiontarget.getScene().getWindow()).setScene(new Scene(page));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML protected void hBackButtonAction(ActionEvent event) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/xml/SignIn.fxml"));
			Parent page = loader.load();
			((Stage)BackActiontarget.getScene().getWindow()).setScene(new Scene(page));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
