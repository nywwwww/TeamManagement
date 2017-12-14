package Controller.Set;

import static Controller.MainAppController.s_uid;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.mybatis.domain.MemberDTO;

import Controller.indexController;
import TeamApp.MemberDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

// 팀 관리 - 팀이 없을 경우 화면의 컨트롤러
public class NoTeamController {
	@FXML private TextField TeamNameField;
	@FXML private TextField LeaderField;
	@FXML private TextField ObjectiveField;
	@FXML private Label setRegLabel;
	@FXML private AnchorPane realViewPane;
	
	// 권한설정 체크박스
	@FXML private CheckBox AuthCheckBox1;
	@FXML private CheckBox AuthCheckBox2;
	@FXML private CheckBox AuthCheckBox3;
	
	// 초대 테이블 구성
	@FXML TableView<InviteTableData> InviteTableView;
	@FXML private TableColumn<InviteTableData, String> FromColumn;
    @FXML private TableColumn<InviteTableData, String> TeamColumn;
    
	// 초기화
	@FXML protected void initialize() throws IOException{

		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		
		//팀장 필드 채워주기
		String realname = dto.getRealName();
		LeaderField.setPromptText("팀장 : "+realname);
		
		// 초대받은 팀 동적 출력하기
		TeamColumn.setCellValueFactory(cellData -> cellData.getValue().teamProperty()); // 출력하는 두 개의 Column 설정
		FromColumn.setCellValueFactory(cellData -> cellData.getValue().fromProperty());
		List<MemberDTO> resultSet; // Invite 정보를 받아올 List(배열)
        
		resultSet = dao.Invite_checkToUID(s_uid); // 일단 값을 읽어보고
		if(!resultSet.isEmpty()) { // 만약 값이 비어있지 않다면
			for(int i=0; i<resultSet.size(); i++){ // 배열 길이만큼 반복출력
				
			// Invite 테이블의 값 저장
			resultSet = dao.Invite_checkToUID(s_uid);
			int CurrentFromUID = resultSet.get(i).getFromUID();
			int CurrentToUID = resultSet.get(i).getToUID();
			int CurrentLinkedTID = resultSet.get(i).getLinkedTID();
			int CurrentIID = resultSet.get(i).getIID();
			
			// 해당하는 TID로 팀 이름 찾아서 저장
			dto = dao.checkTID(CurrentLinkedTID);
			String CurrentTeamName = dto.getTeamName();

			// 해당하는 UID로 초대자 이름 찾아서 저장
			dto = dao.checkUID(CurrentFromUID);
			String CurrentFromName = dto.getRealName();
	
			// 최종적으로, TableView에 출력
		    InviteTableView.getItems().add(new InviteTableData(
		    		new SimpleStringProperty(CurrentTeamName),
		    		new SimpleStringProperty("초대 : "+CurrentFromName), // 표시는 여기까지만 된다.
		    		new SimpleIntegerProperty(CurrentIID),
		    		new SimpleIntegerProperty(CurrentFromUID), // 나머지는 저장만 되어있고 표시는되지않는다
		    		new SimpleIntegerProperty(CurrentToUID),
		    		new SimpleIntegerProperty(CurrentLinkedTID)
		    		));
			 }
		}
	}
	
	// 초대 수락
	@FXML protected void hInviteAcceptButton(ActionEvent event) throws IOException{
		//선택되어있는 fromTID값을 가져옴
		int CurrentLinkedTID = InviteTableView.getSelectionModel().getSelectedItem().LinkedTID.getValue().intValue();
		int CurrentIID = InviteTableView.getSelectionModel().getSelectedItem().IID.getValue().intValue();
		
		// LinkedTID를 해당 TID로 바꿈.
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		dao.updateLinkedTID(s_uid, CurrentLinkedTID);
		
		// 해당 데이터를 테이블로부터 삭제
		dao.Team_DeleteIID(CurrentIID);
		
		// 리디렉션 (팀이 있음 -> 멤버일 경우)
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_TeamMember.fxml"));
		realViewPane.getChildren().setAll(pane);
		
	}
	
	// 초대 거절
	@FXML protected void hInviteRefuseButton(ActionEvent event) throws IOException{
		
		// 해당 데이터를 테이블로부터 삭제
		int CurrentIID = InviteTableView.getSelectionModel().getSelectedItem().IID.getValue().intValue();
		MemberDAO dao = new MemberDAO();
		dao.Team_DeleteIID(CurrentIID);
		// 새로고침 
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_NoTeam.fxml"));
		realViewPane.getChildren().setAll(pane);
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
		
		// 권한정보 설정 체크값으로 넘겨주기
		int auth = 111;
		/*
		if(AuthCheckBox1.isSelected())
			auth = auth + 100;
		if(AuthCheckBox2.isSelected())
			auth = auth + 10;
		if(AuthCheckBox3.isSelected())
			auth = auth + 1;
		*/
		
		// INSERT
		dao.insertTeam(teamname, auth, leaderuid, objective);
		
		
		// User테이블에 붙여주기 (LinkedTID)
		dto = dao.Team_CheckTeamName(teamname);
		int tid = dto.getTID();
		System.out.println(s_uid+"|"+tid);
		dao.updateLinkedTID(s_uid, tid);
		
		// 리디렉션
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set_TeamMember.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
}
