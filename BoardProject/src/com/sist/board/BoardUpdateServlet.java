package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;

public class BoardUpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    // EUC-KR => EUC-KR(UTF-8)
	// UTF-8 => UTF-8
	// pwd=1234
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");// html,xml
	    PrintWriter out=response.getWriter();
	    String strno=request.getParameter("no");
	    int no=Integer.parseInt(strno);
	    BoardDAO dao=new BoardDAO();
	    BoardVO vo=dao.boardUpdateData(no);
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
	    out.println("<h1>수정하기</h1>");
	    out.println("<div class=\"row\">");
	    out.println("<form method=post action=\"BoardUpdateServlet\">");
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr>");
	    out.println("<td class=\"text-right success\" width=15%>");
	    out.println("이름");
	    out.println("</td>");
	    out.println("<td class=\"text-left\" width=85%>");
	    out.println("<input type=text name=name size=15 value=\""+vo.getName()+"\">");
	    out.println("<input type=hidden name=no value=\""+no+"\">");
	    out.println("</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td class=\"text-right success\" width=15%>");
	    out.println("제목");
	    out.println("</td>");
	    out.println("<td class=\"text-left\" width=85%>");
	    out.println("<input type=text name=subject size=50 value=\""+vo.getSubject()+"\">");
	    out.println("</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td class=\"text-right success\" width=15%>");
	    out.println("내용");
	    out.println("</td>");
	    out.println("<td class=\"text-left\" width=85%>");
	    out.println("<textarea rows=10 cols=53 name=content>"+vo.getContent()+"</textarea>");
	    out.println("</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td class=\"text-right success\" width=15%>");
	    out.println("비밀번호");
	    out.println("</td>");
	    out.println("<td class=\"text-left\" width=85%>");
	    out.println("<input type=password name=pwd size=15>");
	    out.println("</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td class=\"text-center\" colspan=2>");
	    out.println("<input type=submit class=\"btn btn-sm btn-success\" value=수정하기>");
	    out.println("<input type=button class=\"btn btn-sm btn-danger\" value=취소 onclick=\"javascript:history.back()\">");
	    out.println("</td>");
	    out.println("</tr>");
	    out.println("</table>");
	    out.println("</form>");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		// 한글이 깨진다
		// 한글변환 
		request.setCharacterEncoding("EUC-KR");
		// => Byte (%A^SA) 인코딩 => 정상(디코딩)
		String name=request.getParameter("name");
		String subject=request.getParameter("subject");
		String content=request.getParameter("content");
		String pwd=request.getParameter("pwd");
		String no=request.getParameter("no");
		
		BoardVO vo=new BoardVO();
		vo.setNo(Integer.parseInt(no));
		vo.setName(name);
		vo.setSubject(subject);
		vo.setContent(content);
		vo.setPwd(pwd);
		
		BoardDAO dao=new BoardDAO();
		//dao.boardInsert(vo);
		boolean bCheck=dao.boardUpdate(vo);
		// 화면 이동 
		PrintWriter out=response.getWriter();
		if(bCheck==true)
		{
		  response.sendRedirect("BoardDetailServlet?no="+no);
		}
		else
		{
			out.println("<script>");
			out.println("alert(\"비밀번호가 틀립니다\");");
			out.println("history.back();");
			out.println("</script>");
		}
	}

}
