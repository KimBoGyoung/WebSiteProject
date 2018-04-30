package com.sist.board;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
// Servlet => Server+let
import java.util.*;
import com.sist.dao.*;
public class BoardListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ==> JSP
		// EUC-KR,UTF-8
		response.setContentType("text/html;charset=EUC-KR");// html,xml
	    PrintWriter out=response.getWriter();
	    // OutputStream out=s.getOutputStream()
	    // /BoardListServlet?page=2
	    String strPage=request.getParameter("page");
	    if(strPage==null)
	    	strPage="1";
	    int curpage=Integer.parseInt(strPage);
	    BoardDAO dao=new BoardDAO();
	    ArrayList<BoardVO> list=dao.boardAllData(curpage);
	    int totalpage=dao.boardTotalPage();
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
	    out.println("<h1>게시판목록</h1>");
	    out.println("<div class=\"row\">");
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr>");
	    out.println("<td class=\"text-left\">");
	    out.println("<a href=\"InsertServlet\" class=\"btn btn-sm btn-primary\">새글</a>");
	    out.println("</td>");
	    out.println("</tr>");
	    out.println("</table>");
	    
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr class=\"success\">");
	    out.println("<th width=10%>번호</th>");
	    out.println("<th width=45%>제목</th>");
	    out.println("<th width=15%>이름</th>");
	    out.println("<th width=20%>작성일</th>");
	    out.println("<th width=10%>조회수</th>");
	    out.println("</tr>");
	    for(BoardVO vo:list)
	    {
	    	out.println("<tr>");
		    out.println("<td width=10%>"+vo.getNo()+"</td>");
		    // detail.jsp?no=10
		    out.println("<td width=45%><a href=\"BoardDetailServlet?no="+vo.getNo()+"\">"+vo.getSubject()+"</a></td>");
		    out.println("<td width=15%>"+vo.getName()+"</td>");
		    out.println("<td width=20%>"+vo.getRegdate().toString()+"</td>");
		    out.println("<td width=10%>"+vo.getHit()+"</td>");
		    out.println("</tr>");
	    }
	    out.println("</table>");
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr>");
	    out.println("<td class=\"text-right\">");
	    out.println("<ul class=\"pagination pagination-sm\">");
	     for(int i=1;i<=totalpage;i++)
	     {
	    	 out.println("<li><a href=\"BoardListServlet?page="+i+"\">"+i+"</a></li>");
	     }
	    out.println("</ul>");
	    out.println("</td>");
	    out.println("</tr>");
	    out.println("</table>");
	    out.println("</div>");
	    out.println("</div>");
	    out.println("</body>");
	    out.println("</html>");
	}

}









