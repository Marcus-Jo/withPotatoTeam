package com.potato.project.member.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.potato.project.common.vo.RentalVO;
import com.potato.project.common.vo.ReserveVO;
import com.potato.project.content.vo.QnaVO;
import com.potato.project.member.vo.MemberVO;

@Service("myPageService")
public class MyPageServiceImpl implements MyPageService {
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	
	//나의 도서관 대출 예약 반납 카운트용 추가 --1025 봉
	@Override
	public MemberVO memberBookSituation(MemberVO memberVO) {
		return sqlSession.selectOne("myPageMapper.memberBookSituation",memberVO);
	}
	
	//----------------------------------내정보 시작-----------------------------------
	//회원 정보 조회
	@Override
	public MemberVO selectMemberInfo(MemberVO memberVO) {
		return sqlSession.selectOne("myPageMapper.selectMemberInfo", memberVO);
	}
	
	//전화번호 길이 구하기
	@Override
	public MemberVO selectTellCnt(MemberVO myPageVO) {
		return sqlSession.selectOne("myPageMapper.selectTellCnt", myPageVO);
	}
	
	//전화번호 다시 하나씩 뺴오기
	@Override
	public MemberVO selectTell(MemberVO memberVO) {
		return sqlSession.selectOne("myPageMapper.selectTell", memberVO);
	}
	
	//회원정보 수정
	@Override
	public void updateMyInfo(MemberVO memberVO) {
		sqlSession.update("myPageMapper.updateMyInfo", memberVO);
		
	}
	//비밀번호변경시 기존비밀번호 확인
	@Override
	public boolean updatePw(MemberVO memberVO) {
		int result = sqlSession.update("myPageMapper.updatePw",memberVO);
		return result == 0 ? true : false ;
	}

	//회원탈퇴시 비밀번호 확인
	@Override
	public boolean checkMemberquit(MemberVO memberVO) {
		int result = sqlSession.update("myPageMapper.checkMemberquit", memberVO);
		return result == 0 ? false : true ;
	}
	//---------------------------------------내정보 끝-----------------------------------
	
	
	//-------------------------------------내문의 시작----------------------------------
	//내 문의내역 조회
	@Override
	public List<QnaVO> myQnaList(MemberVO memberVO) {
		return sqlSession.selectList("myPageMapper.selectMyQnaList", memberVO);
	}
	//내 문의 갯수 구하기 페이징 위해
	@Override
	public int myQnaCnt(MemberVO memberVO) {
		return sqlSession.selectOne("myPageMapper.myQnaCnt");
	}
	//---------------------------------------내 문의 끝----------------------------------

	
	//---------------------------------------내 대출/예역 현황 시작-----------------------
	//내 대출 현황 조회
	@Override
	public List<RentalVO> selectRentalList(MemberVO memberVO) {
		
		return sqlSession.selectList("myPageMapper.selectRentalList", memberVO);
	}
	
	//내 대출 갯수 조회 페이징 위해
	@Override
	public int myRentalCnt(MemberVO memberVO) {
		return sqlSession.selectOne("myPageMapper.myRentalCnt", memberVO);
	}
	
	//내 예약 현황 조회
	@Override
	public List<ReserveVO> selectReserveList(MemberVO memberVO) {
		return sqlSession.selectList("myPageMapper.selectReserveList", memberVO);
	}



	
	
	

}
