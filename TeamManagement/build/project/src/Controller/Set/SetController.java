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
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_TeamMember.fxml"));
			realViewPane.getChildren().setAll(pane);
			// 팀이 있을 경우, Page_Set_TeamMember 로 보냄.
			
		} 
	}
	//팀관리 버튼
	@FXML protected void hSetSubMenuButton1(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_TeamMember.fxml"));
		realViewPane.getChildren().setAll(pane);		
	}
	//개인관리 버튼
	@FXML protected void hSetSubMenuButton2(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_Info.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	//프로그램 정보 버튼
	@FXML protected void hSetSubMenuButton3(ActionEvent event) throws IOException {
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_OSSN.fxml"));
		realViewPane.getChildren().setAll(pane);	
	}
	
}