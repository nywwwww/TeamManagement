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
	
	//회원가입 멤버정보삽입 함수
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
	
	//비밀번호변경 아이디확인함수
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
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return result;
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
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
		return result;
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
