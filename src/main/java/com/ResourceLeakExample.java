package com;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ResourceLeakExample {
   public static void main1(String[] args) {
       BufferedReader reader = null;
       try {
           reader = new BufferedReader(new FileReader("test.txt"));
           String firstLine = reader.readLine();
           System.out.println(firstLine);
           // The reader is not closed in this execution path
           if (firstLine == null) {
               return;
           }
       } catch (IOException e) {
           e.printStackTrace();
       }
       // Resource leak: 'reader' is never closed on normal or exceptional execution paths
   }
    
    
   public static void main2(String[] args) {
       // 使用try-with-resources确保BufferedReader正常关闭
       try (BufferedReader reader = new BufferedReader(new FileReader("test.txt"))) {
           String firstLine = reader.readLine();
           System.out.println(firstLine);
       } catch (IOException e) {
           e.printStackTrace();
       }
       // 不需要显式关闭BufferedReader，try-with-resources会自动处理
   }
	
	public void main3() {
		// 不要硬编码密码,应该从环境变量或配置文件中读取
		String password = System.getenv("USER_PASSWORD"); 
		if (password == null) {
			System.err.println("Password not found in environment variables");
			return;
		}
        System.out.println("Password loaded successfully");
    }

}
