package com.sist.board;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sist.dao.BoardDAO;
import com.sist.dao.BoardVO;
/*
 *  Web ====> ó�� , � ������ ===> ������ DBó��
 */
public class BoardDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");// html,xml
	    PrintWriter out=response.getWriter();
	    String strno=request.getParameter("no");
	    // BoardDeleteServlet?no=10
	    int no=Integer.parseInt(strno);
	    out.println("<html>");
	    out.println("<head>");
	    out.println("<link rel=\"stylesheet\" type=\"text/css\" href=\"board/css/bootstrap.min.css\">");
	    out.println("<style type=\"text/css\">");
	    out.println(".row{");
	    out.println("margin:0px auto;");
	    out.println("width:350px;");
	    out.println("}");
	    out.println("h1{text-align:center;}");
	    out.println("</style>");
	    out.println("</head>");
	    out.println("<body>");
	    
	    out.println("<div class=\"container\">");
	    out.println("<h1>�����ϱ�</h1>");
	    out.println("<div class=\"row\">");
	    
	    out.println("<form method=post action=\"BoardDeleteServlet\">");
	    out.println("<table class=\"table table-hover\">");
	    out.println("<tr>");
	    out.println("<td>");
	    out.println("��й�ȣ:");
	    out.println("<input type=password name=pwd size=12>");
	    out.println("<input type=hidden name=no value="+no+">");
	    out.println("</td>");
	    out.println("</tr>");
	    out.println("<tr>");
	    out.println("<td>");
	    out.println("<input type=submit value=���� class=\"btn btn-sm btn-success\">");
	    out.println("<input type=button class=\"btn btn-sm btn-danger\" value=��� onclick=\"javascript:history.back()\">");
	    out.println("</td>");
	    out.println("</tr>");
	    out.println("</table>");
	    out.println("</form>");
	    out.println("</div>");
	    out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=EUC-KR");
		// �ѱ��� ������
		// �ѱۺ�ȯ 
		
		String pwd=request.getParameter("pwd");
		String no=request.getParameter("no");
		
		
		BoardDAO dao=new BoardDAO();
		//dao.boardInsert(vo);
		boolean bCheck=dao.boardDelete(Integer.parseInt(no), pwd);
		// ȭ�� �̵� 
		PrintWriter out=response.getWriter();
		if(bCheck==true)
		{
		  response.sendRedirect("BoardListServlet");
		}
		else
		{
			out.println("<script>");
			out.println("alert(\"��й�ȣ�� Ʋ���ϴ�\");");
			out.println("history.back();");
			out.println("</script>");
		}
	}

}
