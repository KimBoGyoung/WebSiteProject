package com.sist.board;
import java.lang.reflect.Method;
import java.util.*;
class A
{
	public void doGet()
	{
		System.out.println("A:doGet() Call");
	}
}
class B
{
	public void doGet()
	{
		System.out.println("B:doGet() Call");
	}
}
class C
{
	public void doGet()
	{
		System.out.println("C:doGet() Call");
	}
}
class Tomcat
{
	Map map=new HashMap();
	public Tomcat()
	{
		map.put("list", "com.sist.board.A");
		map.put("delete", "com.sist.board.B");
		map.put("update", "com.sist.board.C");
	}
}
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Tomcat t=new Tomcat();
        String url="list";
        String s=(String)t.map.get(url);
        System.out.println(s);
        try
        {
        	Class c=Class.forName(s);
        	Object o=c.newInstance();
        	Method[] m=c.getDeclaredMethods();
        	m[0].invoke(o, null);
        }catch(Exception ex) {}
	}

}



