package com.potato.project.service.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.potato.project.member.vo.MemberVO;
import com.potato.project.service.vo.ReadingRecordVO;
import com.potato.project.service.vo.ReadingSeatVO;
import com.potato.project.service.vo.RequestBoardVO;
//천화 
@Service("serviceService")
public class ServiceServiceImpl implements ServiceService {
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	//시트 리스트
	@Override
	public List<ReadingSeatVO> selectReadingSeat() {
		return sqlSession.selectList("serviceMapper.selectReadingSeat");
	}

	@Override
	public ReadingSeatVO chooseSeat(String seatCode) {
		return sqlSession.selectOne("serviceMapper.chooseSeat", seatCode);
	}

	@Override
	public void seatUpdate(ReadingSeatVO seatVO) {
		sqlSession.update("serviceMapper.seatUpdate", seatVO);
		
	}

	@Override
	public String seatIdCheck(String id) {
		return sqlSession.selectOne("serviceMapper.seatIdCheck", id);
	}

	@Override
	public String seatMemberIdCheck(String id) {
		return sqlSession.selectOne("serviceMapper.seatMemberIdCheck", id);
	}

	@Override
	public void seatInRecord(ReadingSeatVO seatVO) {
		sqlSession.insert("serviceMapper.seatInRecord", seatVO);
		
	}

	@Override
	public void seatOutRecord(ReadingSeatVO seatVO) {
		sqlSession.update("serviceMapper.seatOutRecord", seatVO);
	}

	@Override
	public List<ReadingRecordVO> seatRecordList() {
		return sqlSession.selectList("serviceMapper.seatRecordList");
		
	}

	@Override
	public List<ReadingRecordVO> searchRecord(ReadingRecordVO recordVO) {
		return sqlSession.selectList("serviceMapper.searchRecord", recordVO);
		
	}

	@Override
	public List<RequestBoardVO> requestBoardList(MemberVO loginInfo) {
		return sqlSession.selectList("serviceMapper.requestBoardList",loginInfo);
	}

	@Override
	public MemberVO requestIdAndIsAdminCheck(MemberVO loginInfo) {
		return sqlSession.selectOne("serviceMapper.requestIdAndIsAdminCheck",loginInfo);
	}

	@Override
	public void regBookRequest(RequestBoardVO rbVO) {
		sqlSession.insert("serviceMapper.regBookRequest", rbVO);
	}

	@Override
	public int checkRequestCnt(MemberVO loginInfo) {
		return sqlSession.selectOne("serviceMapper.checkRequestCnt", loginInfo);
	}

	@Override
	public String checkISBN(String isbn) {
		return sqlSession.selectOne("serviceMapper.checkISBN",isbn);

	}

	@Override
	public void deleteBookRequest(RequestBoardVO rbVO) {
		sqlSession.delete("serviceMapper.deleteBookRequest" , rbVO);
	}

	@Override
	public List<RequestBoardVO> requestBoardListAdmin() {
		return sqlSession.selectList("serviceMapper.requestBoardListAdmin");
	}

	@Override
	public int ajaxRegRequest(RequestBoardVO rbVO) {
		return sqlSession.insert("serviceMapper.ajaxRegRequest", rbVO);
	}

	@Override
	public String checkISBNboolean(String isbn) {
		return sqlSession.selectOne("serviceMapper.checkISBN",isbn);
	}

	@Override
	public int ajaxRequestStatusChange(RequestBoardVO rbVO) {
		return sqlSession.update("serviceMapper.ajaxRequestStatusChange", rbVO);
	}

	@Override
	public String endDateForSearchRecord(ReadingRecordVO recordVO) {
		return sqlSession.selectOne("serviceMapper.endDateForSearchRecord", recordVO);
	}
	
	
}
