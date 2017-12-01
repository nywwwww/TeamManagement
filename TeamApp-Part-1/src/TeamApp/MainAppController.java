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
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.domain.MemberDTO;

public class MainAppController {
	@FXML private Button RegActiontarget;
	@FXML private Button BackActiontarget;
	@FXML private Label ChangePWDActiontarget;
	@FXML private AnchorPane rootPane;	
	
	//회원가입 라벨정보
	@FXML private TextField UserName;
	@FXML private TextField PWD1;
	@FXML private TextField PWD2;
	@FXML private TextField RealName;
	@FXML private TextField Question;
	@FXML private TextField Answer;

// 로그인 / 회원가입 페이지 --------------------------------------------------------------------------------
	
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
	
	
	// 회원가입 버튼(회원가입 화면)
	@FXML
	protected void hRegistrationBackAction(ActionEvent event) {
		String username = UserName.getText();
		String pwd = PWD1.getText();
		String realname = RealName.getText();
		String question = Question.getText();
		String answer = Answer.getText();
		
		MemberDAO dao = new MemberDAO();
		dao.insert(username, pwd, realname, question, answer);
		
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
	
	// 로그인(임시)
	@FXML protected void  hSIButtonAction(ActionEvent event) {
		try {
			// 로그인할때는 Pane의 형태가 달라지므로, 기존 전환 코드 사용.
			Parent page = FXMLLoader.load(getClass().getResource("/xml/index.fxml"));
			Scene scene = new Scene(page);
			scene.getStylesheets().add(MainApp.class.getResource("/res/BlackStyle.css").toExternalForm());
			Stage appStage = (Stage)((Node)event.getSource()).getScene().getWindow();
            appStage.setScene(scene);
            appStage.show();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
// 메인 페이지 --------------------------------------------------------------------------------
	// 사이드 메뉴 --------------------------------------------------------------------------------
	@FXML private AnchorPane viewPane;
	public void hMenuTeamButtonAction(MouseEvent event) throws IOException  {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Team.fxml"));
		viewPane.getChildren().setAll(pane);
	}
	public void hMenuComButtonAction(MouseEvent event) throws IOException  {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com.fxml"));
		viewPane.getChildren().setAll(pane);
	}
	public void hMenuTodoButtonAction(MouseEvent event) throws IOException  {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo.fxml"));
		viewPane.getChildren().setAll(pane);
	}
	public void hMenuSetButtonAction(MouseEvent event) throws IOException  {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set.fxml"));
		viewPane.getChildren().setAll(pane);
	}
}


