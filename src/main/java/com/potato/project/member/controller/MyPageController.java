package com.potato.project.member.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.potato.project.common.vo.MenuVO;
import com.potato.project.common.vo.RentalVO;
import com.potato.project.common.vo.SideMenuVO;
import com.potato.project.content.vo.QnaVO;
import com.potato.project.member.service.MyPageService;
import com.potato.project.member.vo.MemberVO;

@Controller
@RequestMapping("/myPage")
public class MyPageController {
	@Resource(name = "myPageService")
	private MyPageService myPageService;

	// 나의 도서비치신청현황 가는 페이지

	@GetMapping("/bookRequestStatus")
	public String bookRequestStatus(Model model, MenuVO menuVO, HttpSession session, SideMenuVO sideMenuVO,MemberVO memberVO) {
		// 로그인정보 MemberVO에 담아서 맵퍼로 보내고 결과값 다시 받아와서 화면에 뿌림
		model.addAttribute("memberBookSituationCnt",myPageService.memberBookSituation((MemberVO)session.getAttribute("loginInfo")));

		return "service/bookRequestStatus";

	}
	
	
	
	// 나의 도서관 클릭시 오는 페이지--추가--봉
	@GetMapping("/myPage")
	public String myLib(Model model, MenuVO menuVO, HttpSession session, SideMenuVO sideMenuVO,MemberVO memberVO) {
		// 로그인정보 MemberVO에 담아서 맵퍼로 보내고 결과값 다시 받아와서 화면에 뿌림
		model.addAttribute("memberBookSituationCnt",myPageService.memberBookSituation((MemberVO)session.getAttribute("loginInfo")));

		return "member/my_page";

	}
	//내정보 시작
	// 나의 정보 클릭시 오는 페이지--추가--봉
	@GetMapping("/myInfo")
	public String myInfo(Model model, MenuVO menuVO, HttpSession session, SideMenuVO sideMenuVO) {
		// 로그인정보 MemberVO(id만있으면됨)에 담아서 맵퍼로 보내고 결과값 다시 받아와서 화면에 뿌림
		//전하번호 따로 뺴기위해 조회함
		model.addAttribute("memberInfo", myPageService.selectMemberInfo((MemberVO)session.getAttribute("loginInfo")));
		//전화번호 빼고 또 다시 담고 보냄
		model.addAttribute("myTells", myPageService.selectTell(myPageService.selectTellCnt((MemberVO)session.getAttribute("loginInfo"))));
		return "member/my_info";

	}

	// 나의 정보 수정하기 ajax
	@ResponseBody
	@RequestMapping("/myInfoAjax")
	public MemberVO myInfoAjax(MemberVO memberVO) {
		memberVO = myPageService.selectTell(myPageService.selectTellCnt(memberVO));
		
		return memberVO;
	}
	
	//나의 정보수정
	@PostMapping("/updateMyInfo")
	public String updateMyInfo(MenuVO menuVO, MemberVO memberVO) {
		//회원정보 업데이트하기
		myPageService.updateMyInfo(memberVO);
		return "redirect:/myPage/myInfo?menuCode="+menuVO.getMenuCode();
	}
	
	//비밀번호 수정하기
	@ResponseBody
	@PostMapping("/updatePw")
	public boolean updatePw(MemberVO memberVO) { //아이디 비밀번호
		//비밀번호 다른걸로 변경
		return myPageService.updatePw(memberVO);
	}
	//회원탈퇴
	@ResponseBody
	@PostMapping("/checkMemberquit")
	public boolean checkMemberquit(HttpSession session,MemberVO memberVO) {
		//회원 로그아웃 시키고 메인메뉴로
		session.removeAttribute("loginInfo");
		
		return myPageService.checkMemberquit(memberVO);
	}
	//내정보 끝
	
	//내문의내역 시작
	//내 문의내역 조회하기
	@GetMapping("/myQnaList")
	public String goQna(Model model,MenuVO menuVO,SideMenuVO sideMenuVO, HttpSession session,QnaVO qnaVO) {
		//페이징 위한 내문의 총 갯수
		int totalCnt = myPageService.myQnaCnt((MemberVO)session.getAttribute("loginInfo"));
		
		//페이징 위해
		qnaVO.setTotalCnt(totalCnt);
		//메소드 호출 시 페이징 처리의 모든 정보 셋팅
		qnaVO.setPageInfo();
		
		//로그인정보 MemberVO(id만있으면됨)에 담아서 맵퍼로 보내고 결과값 받아와서 내문의내역 리스트 화면에 뿌림
		model.addAttribute("qnaList",myPageService.myQnaList((MemberVO)session.getAttribute("loginInfo")));
		return "member/my_qnaList";
	}
	//내 문의 끝
	
	//내 대출 예약 현황
	//내 대출 내역,내 예약 현황 조회
	@GetMapping("/myLibrary")
	public String goMyLibrary(Model model,MenuVO menuVO,SideMenuVO sideMenuVO, HttpSession session,RentalVO rentalVO) {
		//페이징 위한 내대출 총 갯수
		int totalCnt = myPageService.myRentalCnt((MemberVO)session.getAttribute("loginInfo"));
		
		//페이징 위해
		rentalVO.setTotalCnt(totalCnt);
		//메소드 호출 시 페이징 처리의 모든 정보 셋팅
		rentalVO.setPageInfo();
		
		//로그인정보 MemberVO(id만있으면됨)에 담아서 맵퍼로 보내고 결과값 받아와서 내대출내역 예약내역 리스트 화면에 뿌림
		model.addAttribute("rentalList",myPageService.selectRentalList((MemberVO)session.getAttribute("loginInfo")));
		model.addAttribute("reserveList",myPageService.selectReserveList((MemberVO)session.getAttribute("loginInfo")));
		return "member/my_library";
		
	}
	

}
