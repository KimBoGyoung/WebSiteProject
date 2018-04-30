package com.sist.dao;
import java.util.*;
import java.sql.*;
// Serlvet => JSP (HTML+JAVA) => (HTML)(JAVA)(연결) MVC 
// Spring 
public class BoardDAO {
  private Connection conn;
  private PreparedStatement ps;
  private final String URL="jdbc:oracle:thin:@211.238.142.46:1521:ORCL";
  // 드라이버 등록 
  public BoardDAO()
  {
	  try
	  {
		  Class.forName("oracle.jdbc.driver.OracleDriver");
	  }catch(Exception ex) 
	  {
		  System.out.println(ex.getMessage());
	  }
  }
  // 연결
  public void getConnection()
  {
	  try
	  {
		  conn=DriverManager.getConnection(URL,"scott","tiger");
	  }catch(Exception ex) {}
  }
  // 닫기
  public void disConnection()
  {
	  try
	  {
		  if(ps!=null)ps.close();
		  if(conn!=null) conn.close();
	  }catch(Exception ex) {}
  }
  // BoardDAO dao=new BoardDAO() => dao.boardAllData(1)
  // 최적화 : 패턴  =>  View
  // 페이지 
  public ArrayList<BoardVO> boardAllData(int page)
  {
	  ArrayList<BoardVO> list=
			  new ArrayList<BoardVO>();
	  try
	  {
		  getConnection();
		  int rowSize=10;
		  int i=0;// 10개씩 
		  int j=0;// while횟수 
		  int pagecnt=(page*rowSize)-rowSize;
		  /*
		   *   0  ====> 9
		   *   10 ====> 19
		   *   20
		   */
		  String sql="SELECT no,subject,name,regdate,hit "
				    +"FROM board "
				    +"ORDER BY no DESC";
		  // rownum>=1 and rownum<=10 view => TopN
		  ps=conn.prepareStatement(sql);
		  ResultSet rs=ps.executeQuery();
		  while(rs.next())
		  {
			  if(i<10 && j>=pagecnt)
			  {
				  BoardVO vo=new BoardVO();
				  vo.setNo(rs.getInt(1));
				  vo.setSubject(rs.getString(2));
				  vo.setName(rs.getString(3));
				  vo.setRegdate(rs.getDate(4));
				  vo.setHit(rs.getInt(5));
				  list.add(vo);
				  i++;
			  }
			  j++;
		  }
		  
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
	  finally
	  {
		  disConnection();
	  }
	  
	  return list;
  }
  public void boardInsert(BoardVO vo)
  {
	  try
	  {
		  getConnection();
		  String sql="SELECT MAX(no)+1 FROM board";
		  ps=conn.prepareStatement(sql);
		  ResultSet rs=ps.executeQuery();//SELECT
		  rs.next();
		  int max=rs.getInt(1);
		  rs.close();
		  
		  sql="INSERT INTO board VALUES(?,?,?,?,?,SYSDATE,0)";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, max);
		  ps.setString(2, vo.getName());
		  ps.setString(3, vo.getSubject());
		  ps.setString(4, vo.getContent());
		  ps.setString(5, vo.getPwd());
		  
		  // 실행명령
		  ps.executeUpdate();// commit()
		  // executeUpdate() : 데이터 변경 INSERT,UPDATE,DELETE
		  
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
	  finally
	  {
		  disConnection();
	  }
  }// JSP
  public BoardVO boardDetailData(int no)
  {
	  BoardVO vo=new BoardVO();
	  try
	  {
		  getConnection();
		  String sql="UPDATE board SET "
				    +"hit=hit+1 "
				    +"WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  // 실행
		  ps.executeUpdate();// 조회수 증가 
		  
		  sql="SELECT no,name,subject,content,regdate,hit "
			 +"FROM board "
		     +"WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  vo.setNo(rs.getInt(1));
		  vo.setName(rs.getString(2));
		  vo.setSubject(rs.getString(3));
		  vo.setContent(rs.getString(4));
		  vo.setRegdate(rs.getDate(5));
		  vo.setHit(rs.getInt(6));
		  rs.close();
		  
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
	  finally
	  {
		  disConnection();
	  }
	  return vo;
  }
  public BoardVO boardUpdateData(int no)
  {
	  BoardVO vo=new BoardVO();
	  try
	  {
		  getConnection();
		  String sql="SELECT no,name,subject,content,regdate,hit "
			 +"FROM board "
		     +"WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  vo.setNo(rs.getInt(1));
		  vo.setName(rs.getString(2));
		  vo.setSubject(rs.getString(3));
		  vo.setContent(rs.getString(4));
		  vo.setRegdate(rs.getDate(5));
		  vo.setHit(rs.getInt(6));
		  rs.close();
		  
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
	  finally
	  {
		  disConnection();
	  }
	  return vo;
  }
  public boolean boardUpdate(BoardVO vo)
  {
	  boolean bCheck=false;
	  try
	  {
		  getConnection();
		  String sql="SELECT pwd FROM board WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, vo.getNo());
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  String pwd=rs.getString(1);
		  System.out.println(pwd+"|"+vo.getPwd());
		  rs.close();
		  
		  if(pwd.equals(vo.getPwd()))
		  {
			  bCheck=true;
			  // 수정
			  sql="UPDATE board SET "
			     +"name=?,subject=?,content=? "
			     +"WHERE no=?";
			  ps=conn.prepareStatement(sql);
			  ps.setString(1, vo.getName());
			  ps.setString(2, vo.getSubject());
			  ps.setString(3, vo.getContent());
			  ps.setInt(4, vo.getNo());
			  ps.executeUpdate();
		  }
		  else
		  {
			  bCheck=false;
		  }
		  
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
	  finally
	  {
		  disConnection();
	  }
	  return bCheck;
  }
  
  public int boardTotalPage()
  {
	  // CEIL
	  int total=0;
	  try
	  {
		  getConnection();
		  String sql="SELECT CEIL(COUNT(*)/10) FROM board";
		  ps=conn.prepareStatement(sql);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  total=rs.getInt(1);
		  rs.close();
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
	  finally
	  {
		  disConnection();
	  }
	  return total;
  }
  public boolean boardDelete(int no,String pwd)
  {
	  boolean bCheck=false;
	  try
	  {
		  getConnection();
		  String sql="SELECT pwd FROM board WHERE no=?";
		  ps=conn.prepareStatement(sql);
		  ps.setInt(1, no);
		  ResultSet rs=ps.executeQuery();
		  rs.next();
		  String db_pwd=rs.getString(1);
		  
		  rs.close();
		  
		  if(pwd.equals(db_pwd))
		  {
			  bCheck=true;
			  // 수정
			  sql="DELETE FROM board WHERE no=?";
			  ps=conn.prepareStatement(sql);
			  ps.setInt(1, no);
			  ps.executeUpdate();
		  }
		  else
		  {
			  bCheck=false;
		  }
		  
	  }catch(Exception ex)
	  {
		  System.out.println(ex.getMessage());
	  }
	  finally
	  {
		  disConnection();
	  }
	  return bCheck;
  }
  
}










