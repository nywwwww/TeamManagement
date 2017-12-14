package Controller.Set;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.StringProperty;

public class InviteTableData {
	 	public StringProperty team;
	 	public StringProperty from;
	 	public IntegerProperty IID;
	 	public IntegerProperty FromUID;
	 	public IntegerProperty ToUID;
	 	public IntegerProperty LinkedTID;
	    public InviteTableData(StringProperty team,StringProperty from, IntegerProperty IID, IntegerProperty FromUID, IntegerProperty ToUID, IntegerProperty LinkedTID){
	        this.team = team;
	    	this.from = from;
	    	this.IID = IID;
	    	this.FromUID = FromUID;
	    	this.ToUID = ToUID;
	    	this.LinkedTID = LinkedTID;
	    }
	    public StringProperty teamProperty() {
	        return team;
	    }
	    public StringProperty fromProperty() {
	        return from;
	    }
	    
}
