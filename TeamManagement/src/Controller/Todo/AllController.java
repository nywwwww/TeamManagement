package Controller.Todo;

import static Controller.MainAppController.s_uid;

import java.io.IOException;
import java.util.List;

import org.mybatis.domain.MemberDTO;

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

public class AllController {
	@FXML private AnchorPane realViewPane;
	@FXML private TextField TodoField;

	//테이블 구성
	@FXML TableView<TodoTableData> TeamAllTableView;
	@FXML private TableColumn<TodoTableData, String> ObjectiveColumn;
    @FXML private TableColumn<TodoTableData, String> AddColumn;
    @FXML private TableColumn<TodoTableData, String> DoColumn;
    @FXML private TableColumn<TodoTableData, String> IsEndColumn;
	
	//초기화
	@FXML protected void initialize() throws IOException{

		//테이블 초기화
		ObjectiveColumn.setCellValueFactory(cellData -> cellData.getValue().Objective);	
		AddColumn.setCellValueFactory(cellData -> cellData.getValue().AddName);
		DoColumn.setCellValueFactory(cellData -> cellData.getValue().DoName);	
		IsEndColumn.setCellValueFactory(cellData -> cellData.getValue().IsEndText);
		
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
				
				//System.out.println(currentObjective+"!!"+currentAddName+"!!"+currentDoName+"!!"+currentIsEndText);
				TeamAllTableView.getItems().add(new TodoTableData(
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
	@FXML protected void hTodoDeleteButton(ActionEvent event) throws IOException{	
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		int currentDID = TeamAllTableView.getSelectionModel().getSelectedItem().DID.getValue().intValue();
		dao.Todo_delete(currentDID);
		// 새로고침
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_All.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
}
