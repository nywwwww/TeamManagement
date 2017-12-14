package Controller;

import static Controller.MainAppController.s_uid;

import java.io.IOException;

import org.mybatis.domain.MemberDTO;

import TeamApp.MemberDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;


// 메인의 전체 레이아웃(index) 컨트롤러 클래스
public class indexController {
	// 전체 레이아웃 initialize (초기화)
	@FXML private Label TitleLabel;
	@FXML private Label TeamLabel;
	@FXML private AnchorPane viewPane;
	
	@FXML protected void initialize() throws IOException{
		boolean state = false;
		// Topbar에 이름 띄우기
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		TitleLabel.setText("안녕하세요, "+dto.getRealName()+"님!");
		
		
		// 팀 정보 표시하기

		// 자신의 TID가져오기
		dto = dao.checkUID(s_uid);
		int myTID = dto.getLinkedTID();
		dto = dao.checkTID(myTID);

		TeamLabel.setText("현재 참여중인 팀 : "+dto.getTeamName());
		
		dto = dao.checkUID(s_uid);
		// 시작페이지 결정
		int TID = dto.getLinkedTID();
		state = dao.isExistTID(TID); // user테이블의 LinkedTID를 가져와서, Team테이블의 TID중 있는지 비교.
		if(state == false) {
			// 팀이 없을 경우 , 관리 페이지로 보냄s
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set.fxml"));
			viewPane.getChildren().setAll(pane);
		}else {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Team.fxml"));
			viewPane.getChildren().setAll(pane);
			// 팀이 있을 경우, 팀 정보 페이지
		} 
	}
	
	// 메인 페이지 --------------------------------------------------------------------------------
		// 사이드 메뉴 --------------------------------------------------------------------------------
	
		public void hMenuTeamButtonAction(MouseEvent event) throws IOException  {
			
			// 팀에 속해있는지 아닌지 검증하고, 아니면 Set으로 보내서 팀을 만들거나 초대를 받도록 처리함.
			// 이부분 원래는 메소드로 분리하여 통합처리하는것이 맞지만, 시간이 부족하여 그냥 각 코드에 다 집어넣음 ㅠㅠ
			boolean state = false;
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = null;
			dto = dao.checkUID(s_uid);
			int TID = dto.getLinkedTID();
			state = dao.isExistTID(TID); 
			if(state == true) {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Team.fxml"));
			viewPane.getChildren().setAll(pane);
			}else {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set.fxml"));
			viewPane.getChildren().setAll(pane);	
			}
		}
		public void hMenuComButtonAction(MouseEvent event) throws IOException  {
			boolean state = false;
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = null;
			dto = dao.checkUID(s_uid);
			int TID = dto.getLinkedTID();
			state = dao.isExistTID(TID); 
			if(state == true) {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com.fxml"));
			viewPane.getChildren().setAll(pane);
			}else {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set.fxml"));
				viewPane.getChildren().setAll(pane);	
			}
		}
		public void hMenuTodoButtonAction(MouseEvent event) throws IOException  {
			boolean state = false;
			MemberDAO dao = new MemberDAO();
			MemberDTO dto = null;
			dto = dao.checkUID(s_uid);
			int TID = dto.getLinkedTID();
			state = dao.isExistTID(TID); 
			if(state == true) {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo.fxml"));
			viewPane.getChildren().setAll(pane);
			}else {
				AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set.fxml"));
				viewPane.getChildren().setAll(pane);	
			}
		}
		public void hMenuSetButtonAction(MouseEvent event) throws IOException  {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set.fxml"));
			viewPane.getChildren().setAll(pane);

		}
}

