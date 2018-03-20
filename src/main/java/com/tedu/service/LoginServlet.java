package com.tedu.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import com.tedu.common.HttpContext;
import com.tedu.core.HttpServlet;
import com.tedu.http.HttpRequest;
import com.tedu.http.HttpResponse;

public class LoginServlet extends HttpServlet{
	public void service(HttpRequest request,HttpResponse response){
		System.out.println("开始登录...");
		//获取用户名，密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+","+password);
		/*
		 * 读取userinfo.txt文件中的所有用户
		 * 并且逐个比较
		 */
		BufferedReader br = null;
		try {
			br = new BufferedReader(
				new InputStreamReader(
					new FileInputStream(
						"webapp"+File.separator+
						"userinfo.txt"	
					)	
				)	
			);		
			String line = null;
			boolean flag = false;//登录是否成功
			while((line = br.readLine())!=null){
				System.out.println("line:"+line);
				String[] userinfos = line.split(",");
				/*
				 * 判断用户名和密码是否一致
				 */
				if(username.equals(userinfos[0])&&
				   password.equals(userinfos[1])){
					//登录成功!
					flag = true;
					break;
				}
			}
			if(flag){
				System.out.println("登录成功!");
				/*
				 * 响应登录成功的页面给客户端
				 */
				forward("/login_ok.html", response);
			}else{
				System.out.println("登录失败!");
				forward("/login_error.html", response);
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			if(br!=null){
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}






