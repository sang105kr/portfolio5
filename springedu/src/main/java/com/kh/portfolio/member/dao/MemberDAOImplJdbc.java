package com.kh.portfolio.member.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.apache.jasper.compiler.JDTCompiler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.kh.portfolio.member.vo.MemberVO;

@Repository
public class MemberDAOImplJdbc implements MemberDAO {

	private static final Logger logger = LoggerFactory.getLogger(MemberDAOImplJdbc.class);

	@Inject
	JdbcTemplate jdbcTemplate;

	// 회원 등록
	@Override
	public int joinMember(MemberVO memberVO) {
//		logger.info("MemberDAOImpl.joinMember(MemberVO memberVO) 호출됨!");
//		logger.info(memberVO.toString());
		int result = 0;
		// sql문작성
		// StringBuffer sql = new StringBuffer(); //동기화 환경에서 사용
		StringBuilder sql = new StringBuilder(); // 비동기 환경에서 사용
		sql.append("insert into member(id,pw,tel,nickname,gender,region,birth) ");
		sql.append("       values(?,?,?,?,?,?,?) ");

		// sql실행
		result = jdbcTemplate.update(sql.toString(), memberVO.getId(), memberVO.getPw(), memberVO.getTel(),
				memberVO.getNickname(), memberVO.getGender(), memberVO.getRegion(), memberVO.getBirth());

		return result;
	}

	// 회원 수정
	@Override
	public int modifyMember(MemberVO memberVO) {
//		logger.info("MemberDAOImpl.modifyMember(MemberVO memberVO) 호출됨");
		int result = 0;

		StringBuilder sql = new StringBuilder();

		sql.append("update member ");
		sql.append("   set tel 			= ?, ");
		sql.append("       nickname = ?, ");
		sql.append("       gender 	= ?, ");
		sql.append("       region 	= ?, ");
		sql.append("       birth 		= ?, ");
		sql.append("       udate 		= systimestamp ");
		sql.append(" where id 			= ?   ");
		sql.append("   and pw 			= ?   ");

		result = jdbcTemplate.update(sql.toString(), memberVO.getTel(), memberVO.getNickname(), memberVO.getGender(),
				memberVO.getRegion(), memberVO.getBirth(), memberVO.getId(), memberVO.getPw());

		return result;
	}

	// 회원 전체조회
	@Override
	public List<MemberVO> listAllMember() {
//		logger.info("MemberDAOImpl.listAllMember() 호출됨");
		List<MemberVO> list = null; 
		
		StringBuilder sql = new StringBuilder();
		sql.append("select id,pw,tel,nickname,gender,region,birth,cdate,udate  ");
		sql.append("  from member ");
		
		list = jdbcTemplate.query(sql.toString(),getRowMapper());
		
		return list;
	}

	private RowMapper<MemberVO> getRowMapper() {
		RowMapper<MemberVO> rowMapper = new RowMapper<MemberVO>() {
			@Override
			public MemberVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				MemberVO memberVO = new MemberVO();
				memberVO.setId(rs.getString("id"));
				memberVO.setPw(rs.getString("pw"));
				memberVO.setTel(rs.getString("tel"));
				memberVO.setNickname(rs.getString("nickname"));
				memberVO.setGender(rs.getString("gender"));
				memberVO.setRegion(rs.getString("region"));
				memberVO.setBirth(rs.getDate("birth"));
				memberVO.setCdate(rs.getTimestamp("birth"));
				memberVO.setUdate(rs.getTimestamp("birth"));
				return memberVO;
			}
		};
		return rowMapper;
	}

	// 회원 개별조회
	@Override
	public MemberVO listOneMember(String id) {
//		logger.info("MemberDAOImpl.listOneMember() 호출됨");
	
		StringBuilder sql = new StringBuilder();		
		sql.append("select  id,pw,tel,nickname,gender,region,birth,cdate,udate ");
		sql.append("  from  member ");
		sql.append(" where  id = ? ");
		
		MemberVO memberVO = jdbcTemplate.queryForObject(
				sql.toString(),
				//테이블 컬럼 이름과 자바VO객체 속성이름이 동일하면 자동 매핑해줌.
				new BeanPropertyRowMapper<MemberVO>(MemberVO.class),
				id
				);
		
		return memberVO;
	}

	// 회원 탈퇴
	@Override
	public int outMember(String id, String pw) {
//		logger.info("MemberDAOImpl.outMember(String id, String pw) 호출됨");
		int result = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("delete from member where id = ? and pw = ? ");
		
		result = jdbcTemplate.update(sql.toString(), id, pw);
		
		return result;
	}

	// 로그인
	@Override
	public MemberVO login(String id, String pw) {

//		logger.info("MemberDAOImpl.login(String id, String pw) 호출됨");
		
		StringBuilder sql = new StringBuilder();		
		sql.append("select  id,pw,tel,nickname,gender,region,birth,cdate,udate ");
		sql.append("  from  member ");
		sql.append(" where  id = ? ");
		sql.append("   and  pw = ? ");
		
		MemberVO memberVO = jdbcTemplate.queryForObject(
				sql.toString(),
				//테이블 컬럼 이름과 자바VO객체 속성이름이 동일하면 자동 매핑해줌.
				new BeanPropertyRowMapper<MemberVO>(MemberVO.class),
				id,pw
				);
		
		return memberVO;
	}

	// 아이디 찾기
	@Override
	public String findID(String tel, Date birth) {
//		logger.info("MemberDAOImpl.findID(String tel, String birth) 호출됨");		
		String id = null;
		
		StringBuilder sql = new StringBuilder();	
		sql.append("select id ");
		sql.append("  from member ");
		sql.append(" where tel=? ");
		sql.append("   and birth=? ");		
		
		id = jdbcTemplate.queryForObject(
						sql.toString(), 
						String.class, 
						tel,birth);
		
		return id;
	}

	// 비밀번호 찾기
	@Override
	public String findPW(String id, String tel, Date birth) {
//		logger.info("MemberDAOImpl.findPW(String id, String tel, String birth) 호출됨");
		String pw = null;
		
		StringBuilder sql = new StringBuilder();	
		sql.append("select pw ");
		sql.append("  from member ");
		sql.append(" where id=? ");
		sql.append("   and tel=? ");		
		sql.append("   and birth=? ");		
		
		pw = jdbcTemplate.queryForObject(
						sql.toString(), 
						String.class, 
						id,tel,birth);
		
		return pw;
	}

	// 비밀번호 변경
	@Override
	public int changePW(String id, String prepw, String postpw) {
//		logger.info("MemberDAOImpl.changePWchangePW(String id, String prepw, String postpw) 호출됨");
		int result = 0;
		
		StringBuilder sql = new StringBuilder();
		sql.append("update member ");
		sql.append("   set pw = ? ");
		sql.append(" where id = ? ");
		sql.append("   and pw = ? ");
		
		result = jdbcTemplate.update(sql.toString(), postpw, id, prepw);
		
		return result;
	}

	// 프로파일 이미지 조회
	@Override
	public byte[] findProfileImg(String id) {
		return null;
	}
}
