package TeamApp;

import java.io.IOException;

import org.mybatis.domain.MemberDTO;

import static TeamApp.MainAppController.s_uid;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class PageController {
	
	@FXML private Label TitleLabel;
	@FXML protected void initialize(){
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		dto = dao.checkUID(s_uid);
		TitleLabel.setText(dto.getRealName());
	}
	// 메인 페이지 --------------------------------------------------------------------------------
		// 사이드 메뉴 --------------------------------------------------------------------------------
		@FXML private AnchorPane viewPane;
		public void hMenuTeamButtonAction(MouseEvent event) throws IOException  {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Team.fxml"));
			viewPane.getChildren().setAll(pane);
		}
		public void hMenuComButtonAction(MouseEvent event) throws IOException  {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com.fxml"));
			viewPane.getChildren().setAll(pane);
		}
		public void hMenuTodoButtonAction(MouseEvent event) throws IOException  {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Todo.fxml"));
			viewPane.getChildren().setAll(pane);
		}
		public void hMenuSetButtonAction(MouseEvent event) throws IOException  {
			AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Set.fxml"));
			viewPane.getChildren().setAll(pane);
		}
}
