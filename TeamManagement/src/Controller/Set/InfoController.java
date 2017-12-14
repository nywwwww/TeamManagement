package Controller.Set;

import static Controller.MainAppController.s_uid;
import org.mybatis.domain.MemberDTO;
import TeamApp.MemberDAO;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class InfoController {

	@FXML private AnchorPane realViewPane;
	@FXML Label InfoID;
	@FXML TextField InfoName;
	@FXML TextField InfoPWD1;
	@FXML TextField InfoPWD2;
	@FXML TextField InfoQuestion;
	@FXML TextField InfoAnswer;
	
	@FXML Label InfoLabel;
	
	@FXML protected void initialize() throws IOException {
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		
		InfoID.setText(dto.getUserName());
		InfoName.setPromptText(dto.getRealName());
		InfoQuestion.setPromptText(dto.getQuestion());
	}
	
	@FXML protected void hSettingInfoButtonAction(ActionEvent event) throws IOException {
		boolean state = false;
		String name = InfoName.getText();
		String pwd1 = InfoPWD1.getText();
		String pwd2 = InfoPWD2.getText();
		String question = InfoQuestion.getText();
		String answer = InfoAnswer.getText();
		
		if(pwd1.equals("")) {
			InfoLabel.setText("비밀번호를 입력하세오");
			return;
		}
		else if(pwd2.equals("")) {
			InfoLabel.setText("비밀번호 확인을 입력하세요");
			return;
		}
		else if(!pwd1.equals(pwd2)) {
			InfoLabel.setText("비밀번호가 일치하지 않습니다");
			return;
		}
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		int TID = dto.getLinkedTID();
		state = dao.isExistTID(TID);
		
		if(name.equals("")) {
			name = dto.getRealName();
		}
		if(question.equals("")) {
			question = dto.getQuestion();
		}
		if(answer.equals("")) {
			answer = dto.getAnswer();
		}
		
		dao.Info_Update(s_uid, name, pwd1, question, answer);
		
		if(state == false) {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_NoTeam.fxml"));
			realViewPane.getChildren().setAll(pane);
		}
		else {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_TeamMember.fxml"));
			realViewPane.getChildren().setAll(pane);
		}
	}
}
