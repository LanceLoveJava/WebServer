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
		System.out.println("��ʼ��¼...");
		//��ȡ�û���������
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username+","+password);
		/*
		 * ��ȡuserinfo.txt�ļ��е������û�
		 * ��������Ƚ�
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
			boolean flag = false;//��¼�Ƿ�ɹ�
			while((line = br.readLine())!=null){
				System.out.println("line:"+line);
				String[] userinfos = line.split(",");
				/*
				 * �ж��û����������Ƿ�һ��
				 */
				if(username.equals(userinfos[0])&&
				   password.equals(userinfos[1])){
					//��¼�ɹ�!
					flag = true;
					break;
				}
			}
			if(flag){
				System.out.println("��¼�ɹ�!");
				/*
				 * ��Ӧ��¼�ɹ���ҳ����ͻ���
				 */
				forward("/login_ok.html", response);
			}else{
				System.out.println("��¼ʧ��!");
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






