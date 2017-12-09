package org.mybatis.domain;

public class MemberDTO {
	int UID;
	int LinkedTID;
	int TID;
	String PWD;
	String UserName;
	String RealName;
	String Question;
	String Answer;
	

	public int getTID() {
		return TID;
	}
	public int setTID(int TID) {
		return this.TID = TID;
	}
	public int getLinkedTID() {
		return LinkedTID;
	}
	public int setLinkedTID(int LinkedTID) {
		return this.LinkedTID = LinkedTID;
	}
	public int getUID() {
		return UID;
	}
	public int setUID(int uid) {
		return UID=uid;
	}
	public String getPWD() {
		return PWD;
	}
	public void setPWD(String PWD) {
		this.PWD = PWD;
	}
	public String getUserName() {
		return UserName;
	}
	public void setUserName(String UserName) {
		this.UserName = UserName;
	}
	public String getRealName() {
		return RealName;
	}
	public void setRealName(String RealName) {
		this.RealName = RealName;
	}
	public String getQuestion() {
		return Question;
	}
	public void setQuestion(String Question) {
		this.Question = Question;
	}
	public String getAnswer() {
		return Answer;
	}
	public void setAnswer(String Answer) {
		this.Answer = Answer;
	}
}
