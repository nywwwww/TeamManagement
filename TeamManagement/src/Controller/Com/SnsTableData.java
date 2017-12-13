package Controller.Com;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class SnsTableData {
	public StringProperty Contents;
 	public StringProperty FromText;
 	public IntegerProperty SID;
 	public IntegerProperty FromUID;
 	public SnsTableData(StringProperty Contents,StringProperty FromText,IntegerProperty SID,IntegerProperty FromUID) {
 		this.Contents = Contents;
 		this.FromText = FromText;
 		this.SID = SID;
 		this.FromUID = FromUID;
 	}
}
