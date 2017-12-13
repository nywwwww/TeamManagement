package Controller.Todo;

import java.io.IOException;
import java.util.List;

import org.mybatis.domain.MemberDTO;

import Controller.Set.InviteTableData;

import static Controller.MainAppController.s_uid;

import TeamApp.MemberDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class ShareController {
	@FXML private AnchorPane realViewPane;
	@FXML private TextField TodoField;
	
	//위쪽테이블 구성
	@FXML TableView<TodoTableData> AllTableView;
	@FXML private TableColumn<TodoTableData, String> ObjectiveColumn;
    @FXML private TableColumn<TodoTableData, String> AddColumn;
    
    //아래쪽테이블 구성

	@FXML TableView<TodoTableData> MyTableView;
	@FXML private TableColumn<TodoTableData, String> ObjectiveColumn2;
    @FXML private TableColumn<TodoTableData, String> AddColumn2;
	
	//초기화
	@FXML protected void initialize() throws IOException{
		
	//위쪽테이블(전체업무테이블) 초기화
	ObjectiveColumn.setCellValueFactory(cellData -> cellData.getValue().Objective);	
	AddColumn.setCellValueFactory(cellData -> cellData.getValue().AddName);
	
	//아래쪽테이블(내업무테이블) 초기화
	ObjectiveColumn2.setCellValueFactory(cellData -> cellData.getValue().Objective);	
	AddColumn2.setCellValueFactory(cellData -> cellData.getValue().AddName);

	// !!!!! ToDo : 여기서부터 시작. 일단 리스트로 받는 DAO만들고, 그걸로 테이블 초기화해줘야함.
	
	// 자신의 TID가져오기
	MemberDAO dao = new MemberDAO();
	MemberDTO dto = null;
	dto = dao.checkUID(s_uid);
	int myTID = dto.getLinkedTID();
	
	// 자신의 TID로 Todo테이블에서 검색해서 가져오기
	List<MemberDTO> resultSet;
	resultSet = dao.Todo_checkLinkedTID(myTID);
	
	//해당 리스트로 출력
	if(!resultSet.isEmpty()) { // 비어있는지 확인
		for(int i=0; i<resultSet.size(); i++){ // 배열 길이만큼 반복출력
			
			//일단 DB에서 INT값으로 다 받아오고
			int currentDID = resultSet.get(i).getDID();
			int currentAddUID = resultSet.get(i).getAddUID();
			int currentDoUID = resultSet.get(i).getDoUID();
			int currentIsEnd = resultSet.get(i).getIsEnd();
			//System.out.println(currentDID+"!!"+currentAddUID+"!!"+currentDoUID+"!!"+currentIsEnd);
			
			//실제 출력에 쓸 String값 설정 -------------- 
			// Objective
			String currentObjective = resultSet.get(i).getObjective();
			// AddName
			dto = dao.checkUID(currentAddUID);
			String currentAddName = dto.getRealName();
			// DoName
			String currentDoName ="없음";
			if(!(currentDoUID == -1)) {
			dto = dao.checkUID(currentDoUID);
			currentDoName = dto.getRealName();
			}else {
			currentDoName = "없음";
			}
			// IsEndText
			String currentIsEndText ="";
			if(currentIsEnd == 0) {
				currentIsEndText = "대기/진행";
			}else {
				currentIsEndText = "완료";
			}
			
			// 실제 출력
			if(currentIsEnd == 0&&!(currentDoUID==s_uid)) { // 아직 끝나지 않은 업무, 자신의 것이 아닌 업무만 위에 표시한다.
			
			//System.out.println(currentObjective+"!!"+currentAddName+"!!"+currentDoName+"!!"+currentIsEndText);
			AllTableView.getItems().add(new TodoTableData(
					new SimpleStringProperty(currentObjective),
					new SimpleStringProperty(currentAddName),
					new SimpleStringProperty(currentDoName),
					new SimpleStringProperty(currentIsEndText),
					new SimpleIntegerProperty(currentDID),
					new SimpleIntegerProperty(currentAddUID),
					new SimpleIntegerProperty(currentDoUID),
					new SimpleIntegerProperty(currentIsEnd)
					));
			
			}else if(currentIsEnd == 0&&currentDoUID==s_uid) { // 자신의 업무는 아래 테이블에 표시한다.
				MyTableView.getItems().add(new TodoTableData(
						new SimpleStringProperty(currentObjective),
						new SimpleStringProperty(currentAddName),
						new SimpleStringProperty(currentDoName),
						new SimpleStringProperty(currentIsEndText),
						new SimpleIntegerProperty(currentDID),
						new SimpleIntegerProperty(currentAddUID),
						new SimpleIntegerProperty(currentDoUID),
						new SimpleIntegerProperty(currentIsEnd)
						));	
			}
			
		}
	}
	
	
	}
	
	
	//업무를 새로 추가하는 버튼
	@FXML protected void hTodoCreateButtonAction(ActionEvent event) throws IOException{	
		String objective = TodoField.getText();//텍스트필드에서 Objective를 받아오고
		
		//자신의 UID를 AddUID에, doUID는 -1로, isEnd은 0(거짓)으로 Insert한다.
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		int currentTID = dto.getLinkedTID();
		dao.Todo_insert(objective, s_uid,-1,0, currentTID);
		
		// 새로고침
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	
	// 업무를 자신의 것으로 만드는 버튼
	@FXML protected void hTodoAcceptButton(ActionEvent event) throws IOException{
		// AllTableView에서 선택되어있는 DID값을 가져온다.
		int currentDID = AllTableView.getSelectionModel().getSelectedItem().DID.getValue().intValue();
		// DID로 todo테이블 검색해서, 해당하는 값의 DoUID를 s_uid로 업데이트해준다.
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dao.Todo_updateDoUID(currentDID,s_uid);
		// 새로고침
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	
	// 자신의 업무를 팀에 반환하는 버튼
	@FXML protected void hTodoRefuseButton(ActionEvent event) throws IOException{	
		// MyTableView에서 선택되어있는 DID값을 가져온다.
		int currentDID = MyTableView.getSelectionModel().getSelectedItem().DID.getValue().intValue();
		// DID로 todo테이블 검색해서, 해당하는 값의 DoUID를 -1로 업데이트해준다
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dao.Todo_updateDoUID(currentDID,-1);
		// 새로고침
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	
	// 업무를 삭제하는 버튼
	@FXML protected void hTodoDeleteButton(ActionEvent event) throws IOException{	
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		int currentDID = AllTableView.getSelectionModel().getSelectedItem().DID.getValue().intValue();
		dao.Todo_delete(currentDID);
		// 새로고침
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	
	// 업무를 끝냈을 경우의 버튼
	@FXML protected void hTodoEndButton(ActionEvent event) throws IOException{	
		// MyTableView에서 선택되어있는 DID값을 가져온다.
		int currentDID = MyTableView.getSelectionModel().getSelectedItem().DID.getValue().intValue();
		// DID로 todo테이블 검색해서, 해당하는 값의 DoUID를 -1로 업데이트해준다
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dao.Todo_updateIsEnd(currentDID,1);
		// 새로고침
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
}
