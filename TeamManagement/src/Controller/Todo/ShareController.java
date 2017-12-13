package Controller.Todo;

import java.io.IOException;

import org.mybatis.domain.MemberDTO;

import Controller.Set.InviteTableData;

import static Controller.MainAppController.s_uid;

import TeamApp.MemberDAO;
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
	
	//초기화
	@FXML protected void initialize() throws IOException{
		
	//위쪽테이블(전체업무테이블) 초기화
	ObjectiveColumn.setCellValueFactory(cellData -> cellData.getValue().Objective);	
	AddColumn.setCellValueFactory(cellData -> cellData.getValue().AddName);

	// !!!!! ToDo : 여기서부터 시작. 일단 리스트로 받는 DAO만들고, 그걸로 테이블 초기화해줘야함.
	
	
	
	//아래쪽테이블(내업무테이블) 초기화
		
	}
	
	
	//업무를 새로 추가하는 버튼
	@FXML protected void hTodoCreateButtonAction(ActionEvent event) throws IOException{	
		String objective = TodoField.getText();//텍스트필드에서 Objective를 받아오고
		
		//자신의 UID를 AddUID에, doUID는 -1로, isEnd은 0(거짓)으로 Insert한다.
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dao.Todo_insert(objective, s_uid,-1,0);
		
		// 새로고침
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo_Share.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	
	// 업무를 자신의 것으로 만드는 버튼
	@FXML protected void hTodoAcceptButton(ActionEvent event) throws IOException{	
		
	}
	
	// 자신의 업무를 팀에 반환하는 버튼
	@FXML protected void hTodoRefuseButton(ActionEvent event) throws IOException{	
	}
	
	// 업무를 끝냈을 경우의 버튼
	@FXML protected void hTodoEndButton(ActionEvent event) throws IOException{	
	}
}
