package Controller.Com;

import static Controller.MainAppController.s_uid;
import org.mybatis.domain.MemberDTO;

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
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ComController {
	@FXML private Button ContactButton;
	@FXML private AnchorPane realViewPane;
	@FXML protected void initialize() throws IOException{
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com_Sns.fxml"));
		realViewPane.getChildren().setAll(pane);		
	}
	@FXML protected void hSnsButtonAction(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com_Sns.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	@FXML protected void hContactButtonAction(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com_Contact.fxml"));
		realViewPane.getChildren().setAll(pane);

	}

}
