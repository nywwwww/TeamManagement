package Controller.Set;

import static Controller.MainAppController.s_uid;

import java.io.IOException;

import org.mybatis.domain.MemberDTO;

import Controller.indexController;
import TeamApp.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

// 팀 관리 - 팀이 없을 경우 화면의 컨트롤러
public class NoTeamController {
	@FXML private TextField TeamNameField;
	@FXML private TextField LeaderField;
	@FXML private TextField ObjectiveField;
	@FXML private Label setRegLabel;
	@FXML private AnchorPane realViewPane;
	
	
	// 초기화
	@FXML protected void initialize() throws IOException{
		boolean state = false;
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		
		//팀장 필드 채워주기
		String realname = dto.getRealName();
		LeaderField.setPromptText("팀장 : "+realname);
		
		//!!!!Todo: 초대받은 팀 출력하기
	}
	
	// 팀 만들기 버튼
	@FXML protected void hSetCreateButtonAction(ActionEvent event) throws IOException {
		
		// 유효성검증
		if (TeamNameField.getText().trim().isEmpty()) {
			setRegLabel.setText("팀명을 다시 입력하세요");
			return;
		}
		if (ObjectiveField.getText().trim().isEmpty()) {
			setRegLabel.setText("목표를 다시 입력하세요");
			return;
		}
		
		// UID로 일단 User테이블의 해당 유저정보 불러오고
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		
		// 각 값을 받아와서 정리해주고
		String teamname = TeamNameField.getText();
		String objective = ObjectiveField.getText();
		int leaderuid = s_uid; // 리더는 uid로 저장한다.
		
		// !!!Todo: 권한정보 설정 체크값으로 넘겨주기
		int auth = 111;
		
		// INSERT
		dao.insertTeam(teamname, auth, leaderuid, objective);
		
		
		// !!!! Todo: User테이블에 붙여주기 (LinkedTID)
		// 새로고침	
	}
}
