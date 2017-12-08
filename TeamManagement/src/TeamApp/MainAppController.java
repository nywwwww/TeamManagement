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
import javafx.scene.control.PasswordField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
	
	//로그인 라벨정보
	@FXML private TextField LoginId;
	@FXML private PasswordField LoginPWD;
	@FXML private Label signLabel;
	@FXML private ImageView signImg;
	
	//회원가입 라벨정보
	@FXML private TextField UserName;
	@FXML private TextField PWD1;
	@FXML private TextField PWD2;
	@FXML private TextField RealName;
	@FXML private TextField Question;
	@FXML private TextField Answer;
	@FXML private Label regLabel;
	@FXML private ImageView regImg;
	
	//비밀번호변경 라벨정보
	@FXML private TextField changeUserName;
	@FXML private TextField changeAnswer;
	@FXML private TextField changePWD1;
	@FXML private TextField changePWD2;
	@FXML private Label changeLabel;
	@FXML private ImageView changeImg;
	
	//Image image = new Image("file:res/Warnlcon.png");

// 로그인 / 회원가입 페이지 / 비밀번호변경 페이지--------------------------------------------------------------------------------
	
	// 로그인 버튼(로그인 화면)
	@FXML protected void hSIButtonAction(ActionEvent event) {
			signLabel.setText(null);
			signImg.setVisible(false);
			boolean state = false;
			String username = LoginId.getText();
			String pwd = LoginPWD.getText();
			
			if(username.equals("")) {
				signLabel.setText("계정명을 입력하세요");
				signImg.setVisible(true);
				return;
			}
			else if(pwd.equals("")) {
				signLabel.setText("암호를 입력하세요");
				signImg.setVisible(true);
				return;
			}
			
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = null;
			state = dao.isExist(username);
			if(state == true) {
				dto = dao.checkUsername(username);
				if(dto.getPWD().equals(pwd)) {
					try {
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
				else {
					signLabel.setText("비밀번호가 일치하지 않습니다");
					signImg.setVisible(true);
					return;
				}
			}
			else {
				signLabel.setText("존재하지 않는 계정입니다.");
				signImg.setVisible(true);
				return;
			}
	}
	// 회원가입 버튼(로그인 화면)
	@FXML protected void  hRegButtonAction(ActionEvent event) {
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Registration.fxml"));
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
		String pwd1 = PWD1.getText();
		String pwd2 = PWD2.getText();
		String realname = RealName.getText();
		String question = Question.getText();
		String answer = Answer.getText();
		
		if (username.equals("")) {
			regLabel.setText("사용자이름을 입력하시오");
			regImg.setVisible(true);
			return;
		}
		else if(pwd1.equals("") || pwd2.equals("")) {
			regLabel.setText("비밀번호를 입력하시오");
			regImg.setVisible(true);
			return;
		}
		else if(question.equals("")) {
			regLabel.setText("질문을 입력하시오");
			regImg.setVisible(true);
			return;
		}
		else if (answer.equals("")) {
			regLabel.setText("답변을 입력하시오");
			regImg.setVisible(true);
			return;
		}
		
		if (!pwd1.equals(pwd2)) {
			regLabel.setText("비밀번호가 일치하지 않습니다");
			regImg.setVisible(true);
			return;
		}
		
		if(realname.equals("")) {
			realname = username;
		}
		
		MemberDAO dao = new MemberDAO();
		dao.insertMember(pwd1, username, realname, question, answer);
		
		try {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/SignIn.fxml"));
			rootPane.getChildren().setAll(pane);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	// 계정명 확인 버튼(비밀번호변경 화면)
	@FXML
	protected void hChangeCheckUsernameAction(ActionEvent event) {
		changeLabel.setText(null);
		changeImg.setVisible(false);
		changeAnswer.setPromptText("당신의 출신 초등학교는 어디입니까");
		boolean state = false;
		String username = changeUserName.getText();
		if(username.equals("")) {
			changeLabel.setText("사용자계정을 입력하세요");
			changeImg.setVisible(true);
			return;
		}
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		state = dao.isExist(username);
		if(state == true) {
			dto = dao.checkUsername(username);
			changeAnswer.setPromptText(dto.getQuestion());
		}
		else {
			changeLabel.setText("존재하지 않는 계정입니다");
			changeImg.setVisible(true);
		}

	}
		
	// 비밀번호 변경 버튼(비밀번호 변경 화면)
	@FXML
	protected void hChangeButtonAction(ActionEvent event) {
		changeLabel.setText(null);
		changeImg.setVisible(false);
		
		String username = changeUserName.getText();
		String answer = changeAnswer.getText();
		String pwd1 = changePWD1.getText();
		String pwd2 = changePWD2.getText();
		
		boolean state = false;
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		if(username.equals("")) {
			changeLabel.setText("사용자계정을 입력하세요");
			changeImg.setVisible(true);
			return;
		}
		else if(answer.equals("")) {
			changeLabel.setText("답변을 입력하세요");
			changeImg.setVisible(true);
			return;
		}
		else if(pwd1.equals("") || pwd2.equals("")) {
			changeLabel.setText("비밀번호를 입력하세요");
			changeImg.setVisible(true);
			return;
		}
		if(!pwd1.equals(pwd2)) {
			changeLabel.setText("비밀번호가 일치하지않습니다");
			changeImg.setVisible(true);
			return;
		}
		state = dao.isExist(username);
		if(state == true) {
			dto = dao.checkUsername(username);
			if(dto.getAnswer().equals(answer)) {
				dao.updatePWD(username, pwd1);
				try {
					AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/SignIn.fxml"));
					rootPane.getChildren().setAll(pane);
				} catch (IOException ex) {
					ex.printStackTrace();
				}
			}
			else {
				changeLabel.setText("답변이 일치하지 않습니다");
				changeImg.setVisible(true);
			}
		}
		else {
			changeLabel.setText("존재하지 않는 계정입니다");
			changeImg.setVisible(true);
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


