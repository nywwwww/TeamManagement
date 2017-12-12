package Controller.Set;

import static Controller.MainAppController.s_uid;

import java.io.IOException;

import org.mybatis.domain.MemberDTO;

import TeamApp.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class TeamMemberCotroller {
	@FXML private AnchorPane realViewPane;
	@FXML private Label teamTitle;
	@FXML private Label objectiveTitle;
	@FXML private Label RegLabel;
	@FXML private TextField ExitField;
	@FXML private TextField InviteField;
	
	@FXML protected void initialize() throws IOException{
		// DB로부터 자신이 참여중인 팀의 이름과 목표 가져오기
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		int currentTID = dto.getLinkedTID();
		dto = dao.checkTID(currentTID);
		String currentTeamName = dto.getTeamName();
		String currentObjective = dto.getObjective();
		
		// Label에 반영하기
		teamTitle.setText(currentTeamName);
		objectiveTitle.setText(currentObjective);
	}
	
	//초대하기 버튼
	@FXML protected void hInviteButton(ActionEvent event) throws IOException{
		
		//텍스트필드가 비어있는지 검증
		if (InviteField.getText().trim().isEmpty()) {
			RegLabel.setText("사용자 이름을 입력하세요");
			return;
		}
		
		//해당아이디가 있는지 검증
		boolean state=false;
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		state = dao.isExist(InviteField.getText());
		
		
		//있을 경우
		if(state==true) { 
		// 초대를 보낼 UID검색	
		dto = dao.checkUsername(InviteField.getText()); 
		int ToUID = dto.getUID(); 
		// 자신의 TID검색
		dto = dao.checkUID(s_uid);
		int currentTID = dto.getLinkedTID();
	
		//해당 아이디를 ToUID로, 자신을 FromUID로 Invite 테이블에 추가
		dao.Invite_insert(s_uid, ToUID, currentTID);
		
		RegLabel.setText("초대가 발송되었습니다");
		}else {
		RegLabel.setText("해당 사용자가 존재하지 않습니다");	
		}
		
	}
	
	// 팀 나가기 버튼
	@FXML protected void hExitButton(ActionEvent event) throws IOException{
		
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = null;
			dto = dao.checkUID(s_uid);
			dao.updateLinkedTID(s_uid, -1); // 자신의 LinkedUID를 -1로 바꿔준다.
			
			// 팀이 없을 경우의 화면으로 리디렉션
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_NoTeam.fxml"));
			realViewPane.getChildren().setAll(pane);
	}
}
