package Controller.Set;

import static Controller.MainAppController.s_uid;

import java.io.IOException;

import org.mybatis.domain.MemberDTO;

import TeamApp.MemberDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

public class TeamMemberCotroller {
	@FXML private AnchorPane realViewPane;
	@FXML private Label teamTitle;
	@FXML private Label objectiveTitle;
	
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
}
