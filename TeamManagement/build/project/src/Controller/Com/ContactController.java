package Controller.Com;

import static Controller.MainAppController.s_uid;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import org.mybatis.domain.MemberDTO;
import TeamApp.MemberDAO;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class ContactController {
	
	@FXML private AnchorPane realViewPane;
	
	@FXML private TextField conName;
	@FXML private TextField conNumber;
	@FXML private TextField conMail;
	
	@FXML private Label label1;
	@FXML private Label label2;
	@FXML private Label label3;
	@FXML private Label label4;
	@FXML private Label label5;
	@FXML private Label label6;
	@FXML private Label label7;
	@FXML private Label label8;
	@FXML private Label label9;
	@FXML private Label label10;
	@FXML private Label label11;
	@FXML private Label label12;
	@FXML private Label label13;
	@FXML private Label label14;
	
	@FXML private Label outName;
	@FXML private Label outNumber;
	@FXML private Label outMail;
	
	//static public List<Label> labelList = new ArrayList();

	@FXML protected void initialize() throws IOException {
		
		
		List<Label> labelList = new ArrayList();
		labelList.add(label1);
		labelList.add(label2);
		labelList.add(label3);
		labelList.add(label4);
		labelList.add(label5);
		labelList.add(label6);
		labelList.add(label7);
		labelList.add(label8);
		labelList.add(label9);
		labelList.add(label10);
		labelList.add(label11);
		labelList.add(label12);
		labelList.add(label13);
		labelList.add(label14);
		
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
	
	@FXML protected void hCreateContactAddress(ActionEvent event) throws IOException {
		String name = conName.getText();
		String number = conNumber.getText();
		String email = conMail.getText();
		
		if(name.equals(""))
			return;
		else if(number.equals(""))
			return;
		else if(email.equals(""))
			return;
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.checkUID(s_uid);
		int TID = dto.getLinkedTID();
		
		System.out.println(TID);
		dao.Contact_insert(name, number, email, TID);
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com_Contact.fxml"));
		realViewPane.getChildren().setAll(pane);
	}
	
	@FXML protected void hDeleteContactAddres(ActionEvent event) throws IOException {
		String name = outName.getText();
		
		MemberDAO dao = new MemberDAO();
		
		dao.Contact_Delete(name);
		
		AnchorPane pane = FXMLLoader.load(getClass().getResource("/xml/Page_Com_Contact.fxml"));
		realViewPane.getChildren().setAll(pane);
		
	}
	
	@FXML protected void hContactMouseAction1(MouseEvent event) {
		String name = label1.getText();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.Contact_CheckConName(name);
		
		outName.setText(dto.getConName());
		outNumber.setText(dto.getConNumber());
		outMail.setText(dto.getConMail());
	}
	
	@FXML protected void hContactMouseAction2(MouseEvent event) {
		String name = label2.getText();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.Contact_CheckConName(name);
		
		outName.setText(dto.getConName());
		outNumber.setText(dto.getConNumber());
		outMail.setText(dto.getConMail());
	}
	
	@FXML protected void hContactMouseAction3(MouseEvent event) {
		String name = label3.getText();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.Contact_CheckConName(name);
		
		outName.setText(dto.getConName());
		outNumber.setText(dto.getConNumber());
		outMail.setText(dto.getConMail());
	}
	
	@FXML protected void hContactMouseAction4(MouseEvent event) {
		String name = label4.getText();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.Contact_CheckConName(name);
		
		outName.setText(dto.getConName());
		outNumber.setText(dto.getConNumber());
		outMail.setText(dto.getConMail());
	}
	
	@FXML protected void hContactMouseAction5(MouseEvent event) {
		String name = label5.getText();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.Contact_CheckConName(name);
		
		outName.setText(dto.getConName());
		outNumber.setText(dto.getConNumber());
		outMail.setText(dto.getConMail());
	}
	
	@FXML protected void hContactMouseAction6(MouseEvent event) {
		String name = label6.getText();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.Contact_CheckConName(name);
		
		outName.setText(dto.getConName());
		outNumber.setText(dto.getConNumber());
		outMail.setText(dto.getConMail());
	}
	
	@FXML protected void hContactMouseAction7(MouseEvent event) {
		String name = label7.getText();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.Contact_CheckConName(name);
		
		outName.setText(dto.getConName());
		outNumber.setText(dto.getConNumber());
		outMail.setText(dto.getConMail());
	}
	
	@FXML protected void hContactMouseAction8(MouseEvent event) {
		String name = label8.getText();
		
		MemberDAO dao = new MemberDAO();
		MemberDTO dto = null;
		
		dto = dao.Contact_CheckConName(name);
		
		outName.setText(dto.getConName());
		outNumber.setText(dto.getConNumber());
		outMail.setText(dto.getConMail());
	}
}
