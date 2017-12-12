package Controller.Todo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class TodoTableData {
	//출력되는값
 	public StringProperty Objective;
 	public StringProperty AddName;
 	public StringProperty DoName;
 	public StringProperty IsEndText;
 	//내부값
 	public IntegerProperty DID;
 	public IntegerProperty AddUID;
 	public IntegerProperty DoUID;
 	public IntegerProperty IsEnd;
 	
 	 public TodoTableData(
 		 	StringProperty Objective,
 		 	StringProperty AddName,
 		 	StringProperty DoName,
 		 	StringProperty IsEndText,
 		 	IntegerProperty DID,
 		 	IntegerProperty AddUID,
 		 	IntegerProperty DoUID,
 		 	IntegerProperty IsEnd) {
 		 
 		 this.Objective = Objective;
 		 this.AddName = AddName;
 		 this.DoName = DoName;
 		 this.IsEndText = IsEndText;
 		 this.DID = DID;
 		 this.AddUID = AddUID;
 		 this.DoUID = DoUID;
 		 this.IsEnd = IsEnd;
 		 
 	 }
 	
}
