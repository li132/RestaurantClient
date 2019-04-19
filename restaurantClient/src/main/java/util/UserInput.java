package util;

import java.util.Scanner;

public class UserInput {
	public String getString(String msg) {
		System.out.println(msg);
		Scanner sc=new Scanner(System.in);
		return sc.next();
	}
	
	public int getInt(String msg) {
		while (true) {
			try {
				System.out.println(msg);
				Scanner sc=new Scanner(System.in);
				return sc.nextInt();
			} catch (Exception e) {
				System.out.println("输入内容格式不正确，请输入整数类型！");
			}
		}
	}
	
	public double getDouble(String msg) {
		while (true) {
			try {
				System.out.println(msg);
				Scanner sc=new Scanner(System.in);
				return sc.nextDouble();
			} catch (Exception e) {
				System.out.println("输入内容格式不正确，请输入小数类型！");
			}
		}
	}
}
