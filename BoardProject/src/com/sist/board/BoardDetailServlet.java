package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class BoardDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // ?no=1  => name=content
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 변환 타입  text/html,text/xml (응답 => 브라우저로 보낼때)
		response.setContentType("text/html;charset=EUC-KR");
		// ISO8859:byte 
		PrintWriter out=response.getWriter();
		// 요청한 클라이언트의 출력메모
		// request.getRemoteAddr() 
		String strno=request.getParameter("no");
		int no=Integer.parseInt(strno);
		BoardDAO dao=new BoardDAO();
		BoardVO vo=dao.boardDetailData(no);
		out.println("<html>");
	    out.println("<head>");
	    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"board/css/bootstrap.min.css\">");
	    out.println("<style type=\"text/css\">");
	    out.println(".row{");
	    out.println("margin:0px auto;");
	    out.println("width:700px;");
	    out.println("}");
	    out.println("h1{text-align:center;}");
	    out.println("</style>");
	    out.println("</head>");
	    out.println("<body>");
	    
	    out.println("<div class=\"container\">");
	    out.println("<h1>내용보기</h1>");
	    out.println("<div class=\"row\">");
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr>");
	    out.println("<td width=20% class=\"text-center success\">번호</td>");
	    out.println("<td width=30%>"+vo.getNo()+"</td>");
	    out.println("<td width=20% class=\"text-center success\">작성일</td>");
	    out.println("<td width=30%>"+vo.getRegdate().toString()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td width=20% class=\"text-center success\">이름</td>");
	    out.println("<td width=30%>"+vo.getName()+"</td>");
	    out.println("<td width=20% class=\"text-center success\">조회수</td>");
	    out.println("<td width=30%>"+vo.getHit()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td width=20% class=\"text-center success\">제목</td>");
	    out.println("<td colspan=3>"+vo.getSubject()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td colspan=4 class=\"text-left\" valign=top height=200>"+vo.getContent()+"</td>");
	    out.println("</tr>");
	    
	    out.println("</table>");
	    
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr>");
	    out.println("<td class=\"text-right\">");
	    out.println("<a href=\"BoardUpdateServlet?no="+vo.getNo()+"\" class=\"btn btn-sm btn-primary\">수정</a>");
	    out.println("<a href=\"BoardDeleteServlet?no="+vo.getNo()+"\" class=\"btn btn-sm btn-info\">삭제</a>");
	    out.println("<a href=\"BoardListServlet\" class=\"btn btn-sm btn-danger\">목록</a>");
	    out.println("</td>");
	    out.println("</tr>");
	    out.println("</table>");
		out.println("</div>");// row end
		out.println("</div>");// container end
	    
	}

}







