package TeamApp;

import java.util.*;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.mybatis.domain.MemberDTO;

import java.io.IOException;
import java.io.Reader;
import java.sql.*;

public class MemberDAO {
	private static SqlSessionFactory sqlSessionFactory;
	static {
		try {
			String resource = "mybatis/config-mybatis.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	
	// User 테이블
	
	public void insertMember(String PWD, String UserName, String RealName, String Question, String Answer) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setPWD(PWD);
			member.setUserName(UserName);
			member.setRealName(RealName);
			member.setQuestion(Question);
			member.setAnswer(Answer);
			sqlSession.insert("org.mybatis.persistence.Membermanage.User_insert", member);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
	
	public MemberDTO checkUsername(String name) {	
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO member = new MemberDTO();
		try {
			member.setUserName(name);
			member = sqlSession.selectOne("org.mybatis.persistence.Membermanage.User_selectUserName", member);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return member;
	}
	
	public MemberDTO checkUID(int uid) {	
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO member = new MemberDTO();
		try {
			member.setUID(uid);
			member = sqlSession.selectOne("org.mybatis.persistence.Membermanage.User_selectUID", member);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return member;
	}
	
	public boolean isExist(String name) {
		boolean result = false;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setUserName(name);
			member = sqlSession.selectOne("org.mybatis.persistence.Membermanage.User_selectUserName", member);
			if(member.getUserName().equals(name))
				result = true;
		} catch (Exception e) {
			System.out.println("isExcist에서 값을 찾지 못해 False예외처리. ");
		} finally {
			sqlSession.close();
		}
		return result;
	}

	public void updateLinkedTID(int uid, int linkedTID) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setUID(uid);
			member.setLinkedTID(linkedTID);
			sqlSession.update("org.mybatis.persistence.Membermanage.User_LinkedTID_update", member);
			sqlSession.commit();
		}catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
	
	
	
	// Team 테이블
	
	public void insertTeam(String TeamName, int Auth, int Leader, String Objective) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setTeamName(TeamName);
			member.setAuth(Auth);
			member.setLeader(Leader);
			member.setObjective(Objective);
			sqlSession.insert("org.mybatis.persistence.Membermanage.Team_insert", member);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
	
	public MemberDTO checkTID(int TID) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO member = new MemberDTO();
		try {
			member.setTID(TID);
			member = sqlSession.selectOne("org.mybatis.persistence.Membermanage.Team_selectTID", member);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return member;
	}
	
	public boolean isExistTID(int TID) {
		boolean result = false;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setTID(TID);
			member = sqlSession.selectOne("org.mybatis.persistence.Membermanage.Team_selectTID", member);
			if((member.getTID() == TID))
				result = true;
		} catch (Exception e) {
			System.out.println("isExcistTID에서 값을 찾지 못해 False예외처리. ");
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	public void Team_DeleteIID(int IID) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setIID(IID);
			sqlSession.insert("org.mybatis.persistence.Membermanage.Invite_delete", member);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
	
	
	// Invite 테이블
	
	public List<MemberDTO> Invite_checkToUID(int ToUID) {	
		SqlSession sqlSession = sqlSessionFactory.openSession();
		List<MemberDTO> member = null;
		MemberDTO input = new MemberDTO();
		try {
			input.setToUID(ToUID);
			member = sqlSession.selectList("org.mybatis.persistence.Membermanage.Invite_selectToUID", input);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return member;
	}
	
	public boolean Invite_isExist(int ToUID) {
		boolean result = false;
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setToUID(ToUID);
			member = sqlSession.selectOne("org.mybatis.persistence.Membermanage.Invite_selectToUID", member);
			if((member.getToUID() == ToUID))
				result = true;
		} catch (Exception e) {
			System.out.println("Invite_isExist에서 값을 찾지 못해 False예외처리. ");
		} finally {
			sqlSession.close();
		}
		return result;
	}
	
	
	
	
	// Todo 테이블
	
	
	
	
	
	

	
	public MemberDTO checkLeader(int leader) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		MemberDTO member = new MemberDTO();
		try {
			member.setLeader(leader);
			member = sqlSession.selectOne("org.mybatis.persistence.Membermanage.Team_selectLeader", member);
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return member;
	}


	

	
	
	

	
	

	
	
	
	public void updatePWD(String name, String PWD) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setUserName(name);
			member.setPWD(PWD);
			sqlSession.update("org.mybatis.persistence.Membermanage.update", member);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	}
	
}
