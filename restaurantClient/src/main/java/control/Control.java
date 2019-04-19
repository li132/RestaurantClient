package control;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;


import active.restService;
import admin.Employee;
import admin.Menus;
import admin.Ticket;
import admin.Type;
import util.UserInput;
import view.View;

public class Control {
	private View v;
	private static final String IP="10.10.49.45";
	private static final int POST=9999;
	restService service;
	private UserInput ui;
	Employee emp;
	private Map<Integer, Integer> curt=new HashMap<Integer, Integer>();
	public Control() {
		emp=new Employee();
		ui=new UserInput();
		v=new View();
		service=ProxyClient.getClient(restService.class, IP, POST);
	}

	public void start() {

		while (true) {
			v.login();
			int select = this.ui.getInt("请选择：");
			if (select==2) {
				System.out.println("系统退出！");
				System.exit(0);
			}else if (select==1) {
				if (!login()) {
					System.out.println(emp.getEmpname()+",欢迎您登录本系统！");
					break;
				}else {
					System.out.println("登录失败，请重新登录！");
				}
			}
		}
		while (true) {
			int i=emp.getEmplevel();
			if (i==1) {
				v.employeeMenu();
				int se = this.ui.getInt("请选择：");
				if (se==0) {
					System.out.println("退出系统！");
					System.exit(0);
				}else if (se==1) {
					while (true) {
						int menu = showMenu();
						choseFood(menu);
						v.buy();
						int se3 = this.ui.getInt("请选择：");
						if (se3==1) {
							continue;
						}else if (se3==2) {
							curt();
						}else if (se3==3) {
							settle();
						}else if (se3==0) {
							curt.clear();
							System.out.println("订单已取消！");
							break;
						}else {
							System.out.println("输入错误！");
							continue;
						}
					}
				}else if (se==2) {
					curt();
				}else if (se==3) {
					
				}else if (se==4) {
					
				}else if (se==5) {
					
				}
			}else if (i==2) {
				v.managerMenu();
			}
		}
	}
	//登录的方法
	public boolean login() {
		int id = this.ui.getInt("请输入员工号：");
		String password = this.ui.getString("请输入密码：");
		Employee e = service.login(id, password);
		if (e!=null) {
			emp=e;
			return false;
		}else {
			return true;
		}
	}
	//显示菜品种类及菜品信息
	public int showMenu() {
		System.out.println();
		System.out.println("\t菜品种类如下：");
		List<Type> list = service.selectAllType();
		System.out.println("菜品种类编号\t菜品种类名称");
		for (Type type : list) {
			System.out.println(type.toString());
		}
		while (true) {
			int se = this.ui.getInt("请输入菜品类别：");
			List<Menus> list2 = service.selectAllMuByTp(se);
			if (!list2.isEmpty()) {
				System.out.println();
				System.out.println("菜品信息如下：");
				System.out.println("菜品编号\t菜品名称\t库存\t菜品单价");
				for (Menus menus : list2) {
					System.out.println(menus.getEatid()+"\t"+menus.getEatname()+"\t"+menus.getEatstock()+"\t"+menus.getEatprice());
				}
				//v.show(list2);
				return se;
			}else {
				System.out.println("该类别内无菜品，请重新选择！");
				continue;
			}
		}		
	}
	//选择菜品
	public void choseFood(int menus) {
		while (true) {
			boolean flag=false;
			int select = this.ui.getInt("请选择菜品：");
			List<Menus> list = service.selectAllMuByTp(menus);
			for (Menus m : list) {
				if (m.getEatid()==select) {
					flag=true;
				}
			}
			if (!flag) {
				System.out.println("菜品编号输入错误，请重新输入！");
				continue;
			}
			int num=this.ui.getInt("请输入购买数量：");
			if (num>service.selectMenuById(select).getEatstock() || num<0) {
				System.out.println("输入数量错误！");
				continue;
			}
			curt.put(select, num);
			System.out.println("加入购物车成功！");
			break;
		}		
	}
	//管理购物车
	public void curt(){
		while (true) {			
			if (curt.isEmpty()) {			
				System.out.println("购物车为空，请开始点菜吧~");
				break;
			}
			showAllFood();
			v.curt();
			int select = this.ui.getInt("请选择：");
			if (select==1) {
				continue;
			}else if (select==2) {
				updateCurt();
			}else if (select==3) {
				deleteCurt();
			}else if (select==0) {
				break;
			}
		}
	}
	//修改购物车内商品数量
	public void updateCurt() {
		while (true) {
			int id = this.ui.getInt("请输入要更改的商品编号：");
			Set<Integer> set = curt.keySet();
			if(set.contains(id)){
				showFoodById(id);
				int num = this.ui.getInt("请输入修改的数量：");
				if (num>service.selectMenuById(id).getEatstock() || num<0) {
					System.out.println("输入错误，请重新输入！");
					continue;
				}
				if (num==0) {
					curt.remove(id);
					System.out.println("商品已删除！");
					break;
				}
				curt.replace(id, num);
				System.out.println("修改成功！");
				break;
			}else {
				System.out.println("请重新输入！");
				continue;
			}
		}	
	}
	//删除购物车内的商品
	public void deleteCurt() {
		while (true) {
			int id = this.ui.getInt("请输入要删除的商品编号：");
			if (curt.containsKey(id)) {
				showFoodById(id);
				String chose = this.ui.getString("是否确认删除（y/n）?");
				if ("y".equals(chose)) {
					curt.remove(id);
					System.out.println("删除完成！");
					break;
				}else {
					String s = this.ui.getString("是否继续删除？（y/n）");
					if ("y".equals(s)) {
						continue;
					}else {
						break;
					}
				}
			}
		}
	}
	//结账的方法
	public void settle() {
		while (true) {
			System.out.println("编号\t名称\t数量\t单价");
			Set<Integer> set = curt.keySet();
			double sum = 0;
			for (Integer id : set) {
				System.out.println(id+"\t"+service.selectMenuById(id).getEatname()+"\t"+curt.get(id)+"\t"+service.selectMenuById(id).getEatprice());
				sum+=curt.get(id)*service.selectMenuById(id).getEatprice();
			}
			System.out.println("总价为："+sum);
			String s = this.ui.getString("是否确认结账？(y/n)");
			if ("y".equals(s)) {
				 v.buyWay();
				 int type = this.ui.getInt("请选择：");
				 if (type==1) {
					 while (true) {
						 double pay = this.ui.getDouble("请输入支付金额：");
						 if (pay<sum) {
							System.out.println("输入错误，请重新输入！");
							continue;
						}else {
							ticket(sum,pay);
							Set<Integer> ids = curt.keySet();
							for (Integer id : ids) {
								updateStack(id, curt.get(id));
							}
							break;
						}
					}					
				}else if (type==2) {
					
				}
			}else {
				break;
			}
		}
	}
	public void ticket(double num,double pay) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int in = this.ui.getInt("请输入订单ID：");
		try {
			Ticket t = new Ticket(in, emp.getEmpid(),df.parse(df.format(new Date())),UUID.randomUUID());
			System.out.println("******欢迎下次光临*******");
			System.out.println("订单编号："+in);
			System.out.println("收银员："+emp.getEmpid());
			System.out.println("开票时间："+df.format(new Date()));
			System.out.println("-------------------------");
			showAllFood();
			System.out.println("-------------------------");
			System.out.println("总价："+num);
			System.out.println("支付："+pay+"\t找零："+(pay-num));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void showAllFood() {
		System.out.println("编号\t名称\t数量\t单价");
		Set<Integer> set = curt.keySet();
		for (Integer id : set) {
			System.out.println(id+"\t"+service.selectMenuById(id).getEatname()+"\t"+curt.get(id)+"\t"+service.selectMenuById(id).getEatprice());
		}
	}
	//显示单一商品信息
	public void showFoodById(int id) {
		System.out.println("商品信息如下：");
		System.out.println("编号\t名称\t数量\t单价");
		System.out.println(id+"\t"+service.selectMenuById(id).getEatname()+"\t"+curt.get(id)+"\t"+service.selectMenuById(id).getEatprice());
	}
	//更改库存
	public void updateStack(int id,int num) {
		Menus m = service.selectMenuById(id);
		service.upadteMenu(new Menus(id, m.getEatname(), m.getTypeid(), m.getEatlevel(), num, m.getEatprice()));
	}
	//更改销量
	public void updateSal(){
		Set<Integer> set = curt.keySet();
		for (Integer mid : set) {
			
		}
	}
	public void addVIP(){
		
	}
}
