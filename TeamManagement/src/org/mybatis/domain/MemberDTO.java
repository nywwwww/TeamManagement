package org.mybatis.domain;

public class MemberDTO {
	
	// User 테이블
	int UID;
	int LinkedTID;
	String PWD;
	String UserName;
	String RealName;
	String Question;
	String Answer;
	
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
	

	// Team 테이블
	int TID;
	String TeamName;
	int Auth;
	int Leader;
	String Objective;

	public int getTID() {
		return TID;
	}
	public int setTID(int TID) {
		return this.TID = TID;
	}
	public int getAuth() {
		return Auth;
	}
	public int setAuth(int Auth) {
		return this.Auth = Auth;
	}
	public String getTeamName() {
		return TeamName;
	}
	public String setTeamName(String TeamName) {
		return this.TeamName = TeamName;
	}
	public int getLeader() {
		return Leader;
	}
	public int setLeader(int Leader) {
		return this.Leader = Leader;
	}
	public String getObjective() {
		return Objective;
	}
	public String setObjective(String Objective) {
		return this.Objective = Objective;
	}
	
	// Invite 테이블
	int ToUID;
	int FromUID;
	int IID;
	
	public int getFromUID() {
		return FromUID;
	}
	public int setFromUID(int FromUID) {
		return this.FromUID = FromUID;
	}
	public int getToUID() {
		return ToUID;
	}
	public int setToUID(int ToUID) {
		return this.ToUID = ToUID;
	}

	public int getIID() {
		return IID;
	}
	public int setIID(int IID) {
		return this.IID = IID;
	}
	
	
	// ToDo테이블

	int AddUID;
	int DoUID;
	int IsEnd;

	public int getAddUID() {
		return AddUID;
	}
	public int setAddUID(int AddUID) {
		return this.AddUID = AddUID;
	}
	public int getDoUID() {
		return DoUID;
	}
	public int setDoUID(int DoUID) {
		return this.DoUID = DoUID;
	}

	public int getIsEnd() {
		return IsEnd;
	}
	public int setIsEnd(int IsEnd) {
		return this.IsEnd = IsEnd;
	}
	
}
