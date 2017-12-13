package Controller.Com;

import static Controller.MainAppController.s_uid;

import java.io.IOException;
import java.util.List;

import org.mybatis.domain.MemberDTO;

import Controller.Todo.TodoTableData;
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

public class SnsController {
	
	@FXML private AnchorPane realViewPane;
	@FXML private TextField inputField;
	
	//테이블 구성
	@FXML TableView<SnsTableData> SnsTableView;
	@FXML private TableColumn<SnsTableData, String> ContentsColumn;
    @FXML private TableColumn<SnsTableData, String> FromColumn;
	
	@FXML protected void initialize() throws IOException{
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
		
	}
	@FXML protected void hSnsAddButton(ActionEvent event) throws IOException {
		String contents = inputField.getText();//텍스트필드에서 contents를 받아오고
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		int currentTID = dto.getLinkedTID();
		dao.Social_insert(contents, s_uid, currentTID);

		// 새로고침
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com_Sns.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
}
