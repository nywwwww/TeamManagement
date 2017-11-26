package TeamApp;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;



public class MainAppController {
	@FXML private Button RegActiontarget;
	@FXML private Button BackActiontarget;
	@FXML private Label ChangePWDActiontarget;
	@FXML private AnchorPane rootPane;	
	
	/*
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
	
	*/
	// 회원가입 버튼(로그인 화면)
	@FXML protected void  hRegButtonAction(ActionEvent event) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Registration.fxml"));
			rootPane.getChildren().setAll(pane);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	// 돌아가기 버튼(회원가입 화면)
	@FXML 
	protected void hBackButtonAction(ActionEvent event) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/SignIn.fxml"));
			rootPane.getChildren().setAll(pane);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	// 비밀번호변경 버튼(로그인 화면)
	@FXML protected void hChangePWDButtonAction(MouseEvent event) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/ChangePWD.fxml"));
			rootPane.getChildren().setAll(pane);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML
	protected void  hSIButtonAction(ActionEvent event) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/ChangePWD.fxml"));
			rootPane.getChildren().setAll(pane);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	@FXML Pane secPane;
	public void loadFxml (ActionEvent event) throws IOException  {

	}
}


