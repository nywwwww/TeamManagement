package Controller.Set;

import static Controller.MainAppController.s_uid;

import java.io.IOException;

import org.mybatis.domain.MemberDTO;

import TeamApp.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class SetController {
	@FXML private AnchorPane realViewPane;
	
	// 팀
	@FXML protected void initialize() throws IOException{
		boolean state = false;
		// Topbar에 이름 띄우기
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		// 시작페이지 결정
		int TID = dto.getLinkedTID();
		state = dao.isExistTID(TID); // user테이블의 LinkedTID를 가져와서, Team테이블의 TID중 있는지 비교.
		if(state == false) {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_NoTeam.fxml"));
			realViewPane.getChildren().setAll(pane);
			// 팀이 없을 경우 , 팀 만들기 / 초대 받기 페이지 (Page_Set_NoTeam) 으로 보냄.
		}else {
			// 팀이 있고, 팀장이라면 팀장 관리 페이지로
			// 팀이 있고, 멤버라면 팀 나가기 페이지로
		} 
	}
}