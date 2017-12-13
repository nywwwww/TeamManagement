package Controller.Team;

import static Controller.MainAppController.s_uid;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.mybatis.domain.MemberDTO;

import TeamApp.MemberDAO;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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
	
	@FXML protected void initialize() throws IOException{
		List<Label> labelList = new ArrayList();
			labelList.add(label1);
			labelList.add(label2);
			labelList.add(label3);
			labelList.add(label4);

			MemberDAO dao = new MemberDAO();
			MemberDTO dto = null;
			dto = dao.checkUID(s_uid);
			
			int TID = dto.getLinkedTID();
			
			List<MemberDTO> resultSet = null;
			resultSet = dao.Contact_CheckLinkedTID(TID);	
			
			if(!resultSet.isEmpty()) {
				for(int i = 0; i<resultSet.size(); i++) {
					String name = resultSet.get(i).getConName();
					labelList.get(i).setText(name);
				}
			}
	}
}
