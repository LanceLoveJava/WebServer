package com.tedu.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;

import com.tedu.core.HttpServlet;
import com.tedu.http.HttpRequest;
import com.tedu.http.HttpResponse;

/**
 * 用来完成用户注册功能
 * @author adminitartor
 *
 */
public class RegServlet extends HttpServlet{
	
	
	public void service(HttpRequest request,HttpResponse response){
		System.out.println("开始处理注册!");
		/*
		 * 将用户的注册信息按行写入到
		 * userinfo.txt文件中。
		 * 每行为一条用户的信息，格式:
		 * username,password,nickname
		 * 例如:
		 * fanchuanqi,123456,fanfan
		 * liucangsong,223344,cangcang
		 * 
		 * userinfo.txt放在webapp目录下
		 * 
		 * 1:获取所有注册信息
		 * 2:创建输出流
		 * 3:将数据写入
		 */
		//获取用户名
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String nickname = request.getParameter("nickname");
		PrintWriter pw = null;
		try {
			pw = new PrintWriter(
				new FileOutputStream(
					"webapp"+File.separator+
					"userinfo.txt",true
				)	
			);
			pw.println(username+","+password+","+nickname);
			pw.flush();
			System.out.println("注册成功!");
			/*
			 * 响应注册成功的页面给客户端
			 */	
			forward("/reg_ok.html", response);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(pw!=null){
				pw.close();
			}
		}		
	}
	
	
}




