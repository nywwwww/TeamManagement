package Controller.Team;

import static Controller.MainAppController.s_uid;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.mybatis.domain.MemberDTO;

import Controller.Com.SnsTableData;
import Controller.Todo.TodoTableData;
import TeamApp.MemberDAO;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class InfoController {
	
	@FXML private AnchorPane realViewPane;
	
	@FXML private Label label1;
	@FXML private Label label2;
	@FXML private Label label3;
	@FXML private Label label4;

	@FXML private Label outName;
	@FXML private Label outNumber;
	@FXML private Label outMail;
	
	@FXML private Label TodayTodoLabel;
	@FXML private Label TodayAllLabel;
	@FXML private Label DateYearLabel;
	@FXML private Label DateLabel;

	//테이블 구성
	@FXML TableView<SnsTableData> SnsTableView;
	@FXML private TableColumn<SnsTableData, String> ContentsColumn;
    @FXML private TableColumn<SnsTableData, String> FromColumn;
    

    //테이블 구성
	@FXML TableView<TodoTableData> MyTableView;
	@FXML private TableColumn<TodoTableData, String> ObjectiveColumn2;
    @FXML private TableColumn<TodoTableData, String> AddColumn2;
	
	
	@FXML protected void initialize() throws IOException{	
		
		// SNS 테이블 ------------------------------------------------------------------------
		// SNS테이블 초기화
		SnsTableView.setSelectionModel(null); 
		ContentsColumn.setCellValueFactory(cellData -> cellData.getValue().Contents);	
		FromColumn.setCellValueFactory(cellData -> cellData.getValue().FromText);

		// 자신의 TID가져오기
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		int myTID = dto.getLinkedTID();
		
		// 자신의 TID로 Social테이블에서 검색해서 가져오기
		List<MemberDTO> resultSet;
		resultSet = dao.Social_checkLinkedTID(myTID);

		if(!resultSet.isEmpty()) { 
			for(int i=resultSet.size()-1; i>-1; i--){

				//일단 DB에서  받아오고
				int currentSID = resultSet.get(i).getSID();
				String currentContents = resultSet.get(i).getContents();
				int currentFromUID = resultSet.get(i).getFromUID();
				
				dto = dao.checkUID(currentFromUID);
				String currentFromText = dto.getRealName();

				// 실제 출력
				SnsTableView.getItems().add(new  SnsTableData(
						new SimpleStringProperty(currentContents),
						new SimpleStringProperty(currentFromText),
						new SimpleIntegerProperty(currentFromUID),
						new SimpleIntegerProperty(currentSID)
						));
			}
		}

		// 내업무 테이블 ------------------------------------------------------------------------
		
		// 정보출력용 누적값
		int MyTodoCount = 0;
		int AllTodoCount = 0;
		
		MyTableView.setSelectionModel(null); 
		ObjectiveColumn2.setCellValueFactory(cellData -> cellData.getValue().Objective);	
		AddColumn2.setCellValueFactory(cellData -> cellData.getValue().AddName);

		dto = dao.checkUID(s_uid);
		int myTID2 = dto.getLinkedTID();
		
		List<MemberDTO> resultSet3 = null;
		resultSet3 = dao.Todo_checkLinkedTID(myTID2);
		
		//해당 리스트로 출력
		if(!resultSet3.isEmpty()) { // 비어있는지 확인
			for(int i=0; i<resultSet3.size(); i++){ // 배열 길이만큼 반복출력
				
				//일단 DB에서 INT값으로 다 받아오고
				int currentDID = resultSet3.get(i).getDID();
				int currentAddUID = resultSet3.get(i).getAddUID();
				int currentDoUID = resultSet3.get(i).getDoUID();
				int currentIsEnd = resultSet3.get(i).getIsEnd();
				//System.out.println(currentDID+"!!"+currentAddUID+"!!"+currentDoUID+"!!"+currentIsEnd);
				
				//실제 출력에 쓸 String값 설정 -------------- 
				// Objective
				String currentObjective = resultSet3.get(i).getObjective();
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
				AllTodoCount = AllTodoCount + 1;
				// 실제 출력
				if(currentIsEnd == 0&&currentDoUID==s_uid) { // 자신의 업무는 아래 테이블에 표시한다.
					MyTodoCount = MyTodoCount + 1;
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
		
		// 화면 정리 출력 -----------------------------------------------------------
		
		TodayTodoLabel.setText(Integer.toString(MyTodoCount));
		TodayAllLabel.setText(Integer.toString(AllTodoCount));
		
		String strTime;
		Date d3 = new Date(); 
		SimpleDateFormat transFormat = new SimpleDateFormat("yyyy"); 
		strTime = transFormat.format(d3);
		DateYearLabel.setText(strTime);
		
		d3 = new Date(); 
		transFormat = new SimpleDateFormat("MM월 dd일"); 
		strTime = transFormat.format(d3);
		DateLabel.setText(strTime);	
		
		// 연락처 -----------------------------------------------------------------
		List<Label> labelList = new ArrayList();
			labelList.add(label1);
			labelList.add(label2);
			labelList.add(label3);
			labelList.add(label4);

			dto = dao.checkUID(s_uid);
			
			int TID = dto.getLinkedTID();
			
			List<MemberDTO> resultSet2 = null;
			resultSet2 = dao.Contact_CheckLinkedTID(TID);	
			
			if(!resultSet2.isEmpty()) {
				for(int i = 0; i<resultSet2.size(); i++) {
					String name = resultSet2.get(i).getConName();
					labelList.get(i).setText(name);
				}
			}
	}
}
