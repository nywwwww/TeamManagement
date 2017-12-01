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
			String resource = "resources/mybatis/config-mybatis.xml";
			Reader reader = Resources.getResourceAsReader(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public MemberDAO() {
		
	}
	
	public void insert(String PWD, String UserName, String RealName, String Question, String Answer) {
		SqlSession sqlSession = sqlSessionFactory.openSession();
		try {
			MemberDTO member = new MemberDTO();
			member.setPWD(PWD);
			member.setUserName(UserName);
			member.setRealName(RealName);
			member.setQuestion(Question);
			member.setAnswer(Answer);
			sqlSession.insert("org.mybatis.persistence.Membermanage.insert", member);
			sqlSession.commit();
		} catch (Exception e) {
			e.printStackTrace();
			sqlSession.rollback();
		} finally {
			sqlSession.close();
		}
	} 
}
