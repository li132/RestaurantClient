package view;

import java.util.List;

public class View {
	public void show(List list) {
		for (Object object : list) {
			System.out.println(object);
		}
	}
	public void login() {
		System.out.println();
		System.out.println("********欢迎使用餐厅管理系统********");
		System.out.println("1.登录系统");
		System.out.println("2.退出系统");
	}
	public void managerMenu() {
		System.out.println();
		System.out.println("********欢迎使用餐厅管理系统********");
		System.out.println("1.管理员工");
		System.out.println("2.补卡");
		System.out.println("3.客户冻结");
		System.out.println("4.菜品管理");
		System.out.println("5.会员管理");
		System.out.println("6.统计");
		System.out.println("0.退出系统");
	}
	public void employeeMenu() {
		System.out.println();
		System.out.println("********欢迎使用餐厅管理系统********");
		System.out.println("1.点菜");
		System.out.println("2.管理购物车");
		System.out.println("3.开卡");
		System.out.println("4.挂失");
		System.out.println("5.充值");
		System.out.println("0.退出系统");
	}
	public void buy(){
		System.out.println();
		System.out.println("1.开始购买");
		System.out.println("2.管理购物车");
		System.out.println("3.结账");
		System.out.println("0.取消订单");
	}
	public void curt(){
		System.out.println();
		System.out.println("********购物车管理********");
		System.out.println("1.查看购物车");
		System.out.println("2.修改购买数量");
		System.out.println("3.删除商品");
		System.out.println("0.返回");
	}
	public void buyWay() {
		System.out.println();
		System.out.println("请选择支付方式：");
		System.out.println("1.现金支付");
		System.out.println("2.会员卡支付");
	}
}
