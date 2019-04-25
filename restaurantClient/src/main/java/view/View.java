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
		System.out.println("1.员工管理");
		System.out.println("2.补卡");
		System.out.println("3.会员卡冻结");
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
		System.out.println("4.结账");
		System.out.println("0.返回");
	}
	public void buyWay() {
		System.out.println();
		System.out.println("请选择支付方式：");
		System.out.println("1.现金支付");
		System.out.println("2.会员卡支付");
	}
	public void emp() {
		System.out.println();
		System.out.println("1.添加员工");
		System.out.println("2.删除员工");
		System.out.println("3.更改员工信息");
		System.out.println("4.查询所有员工信息");
		System.out.println("5.根据员工id查找员工");
		System.out.println("0.返回");
	}
	public void updateEmp() {
		System.out.println();
		System.out.println("1.更改员工姓名");
		System.out.println("2.更改员工联系方式");
		System.out.println("3.更改员工登录密码");
		System.out.println("0.返回上一层");
	}
	public void foods() {
		System.out.println();
		System.out.println("1.添加菜品类别");
		System.out.println("2.添加菜品");
		System.out.println("3.下架菜品");
		System.out.println("4.更改菜品");
		System.out.println("5.查询所有菜品");
		System.out.println("6.根据id查询菜品");
		System.out.println("0.返回");
	}
	public void updateFoods() {
		System.out.println();
		System.out.println("1.更改菜品价格");
		System.out.println("2.更改菜品名称");
		System.out.println("3.设置特价菜");
		System.out.println("4.更改菜品库存");
		System.out.println("0.返回");
	}
	public void vip() {
		System.out.println();
		System.out.println("1.查看所有会员");
		System.out.println("2.修改会员优惠");
		System.out.println("3.根据id查询会员");
		System.out.println("0.返回");
	}
	public void statistic() {
		System.out.println();
		System.out.println("1.统计菜品销量排行");
		System.out.println("2.打印所有订单");
		System.out.println("3.EXCEL导出菜品销量排行");
		System.out.println("0.返回");
	}
}
