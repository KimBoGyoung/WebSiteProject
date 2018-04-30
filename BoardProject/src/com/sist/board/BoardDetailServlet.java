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
		// ��ȯ Ÿ��  text/html,text/xml (���� => �������� ������)
		response.setContentType("text/html;charset=EUC-KR");
		// ISO8859:byte 
		PrintWriter out=response.getWriter();
		// ��û�� Ŭ���̾�Ʈ�� ��¸޸�
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
	    out.println("<h1>���뺸��</h1>");
	    out.println("<div class=\"row\">");
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr>");
	    out.println("<td width=20% class=\"text-center success\">��ȣ</td>");
	    out.println("<td width=30%>"+vo.getNo()+"</td>");
	    out.println("<td width=20% class=\"text-center success\">�ۼ���</td>");
	    out.println("<td width=30%>"+vo.getRegdate().toString()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td width=20% class=\"text-center success\">�̸�</td>");
	    out.println("<td width=30%>"+vo.getName()+"</td>");
	    out.println("<td width=20% class=\"text-center success\">��ȸ��</td>");
	    out.println("<td width=30%>"+vo.getHit()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td width=20% class=\"text-center success\">����</td>");
	    out.println("<td colspan=3>"+vo.getSubject()+"</td>");
	    out.println("</tr>");
	    
	    out.println("<tr>");
	    out.println("<td colspan=4 class=\"text-left\" valign=top height=200>"+vo.getContent()+"</td>");
	    out.println("</tr>");
	    
	    out.println("</table>");
	    
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr>");
	    out.println("<td class=\"text-right\">");
	    out.println("<a href=\"BoardUpdateServlet?no="+vo.getNo()+"\" class=\"btn btn-sm btn-primary\">����</a>");
	    out.println("<a href=\"BoardDeleteServlet?no="+vo.getNo()+"\" class=\"btn btn-sm btn-info\">����</a>");
	    out.println("<a href=\"BoardListServlet\" class=\"btn btn-sm btn-danger\">���</a>");
	    out.println("</td>");
	    out.println("</tr>");
	    out.println("</table>");
		out.println("</div>");// row end
		out.println("</div>");// container end
	    
	}

}







