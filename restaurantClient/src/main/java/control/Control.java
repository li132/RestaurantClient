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
import admin.Customer;
import admin.Employee;
import admin.Menus;
import admin.Salnum;
import admin.Ticket;
import admin.Type;
import admin.Vip;
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
	long nowLong;
	public Control() {
		nowLong=new Date().getTime();
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
							break;
						}else if (se3==3) {
							settle();
							break;
						}else if (se3==0) {
							Set<Integer> set = curt.keySet();
							for (Integer id : set) {
								int n = 0-curt.get(id);
								updateStack(id, n);
							}
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
					addVIP();
				}else if (se==4) {
					freeze();
				}else if (se==5) {
					recharge();
				}else {
					System.out.println("输入错误！");
					continue;
				}
			}else if (i==2) {
				v.managerMenu();
				int select = this.ui.getInt("请选择：");
				if (select==0) {
					System.exit(0);
				}else if (select==1) {
					while (true) {
						v.emp();
						int input = this.ui.getInt("请选择：");
						if (input==0) {
							break;
						}else if (input==1) {
							addEmp();
						}else if (input==2) {
							deleteEmp();
						}else if (input==3) {
							while (true) {
								v.updateEmp();
								int in = this.ui.getInt("请选择：");
								if (in==0) {
									break;
								}else {
									updateEmp(in);
								}
							}
						}else if (input==4) {
							selectAllEmp();
						}else if (input==5) {
							selectEmpById();
						}else {
							System.out.println("输入错误！");
							continue;
						}
					}					
				}else if (select==2) {
					repair();
				}else if (select==3) {
					freeze();
				}else if (select==4) {
					v.foods();
					int sc=this.ui.getInt("请选择：");
					if (sc==0) {
						break;
					}else if (sc==1) {
						addMenu();
					}else if (sc==2) {
						deleteMenu();
					}else if (sc==3) {
						while (true) {
							v.updateFoods();
							int ss = this.ui.getInt("请选择：");
							if (ss==0) {
								break;
							}
							updateFood(ss);
							continue;
						}						
					}else if (sc==4) {
						v.show(service.selectAllMenu());
					}else if (sc==5) {
						selectMenById();
					}
				}else if (select==5) {				
					while (true) {
						v.vip();
						int ii = this.ui.getInt("请选择：");
						if (ii==0) {
							break;
						}else if (ii==1) {
							v.show(service.selectAllVip());
						}else if (ii==2) {
							updateVip();
						}else if (ii==3) {
							selectVipById();
						}else {
							System.out.println("输入错误！");
							continue;
						}
					}
				}else if (select==6) {
					while (true) {
						v.statistic();
						int desc=this.ui.getInt("请选择：");
						if (desc==0) {
							break;
						}else if (desc==1) {
							salDesc();
						}else if (desc==2) {
							printTick();
						}else {
							System.out.println("输入错误！");
							continue;
						}
					}					
				}else {
					System.out.println("输入错误！");
					continue;
				}
			}
		}
	}
	private void selectVipById() {
		while (true) {
			int vipId = this.ui.getInt("请输入会员卡号：");
			Vip vip = service.selectVipById(vipId);
			if (vip==null) {
				System.out.println("此会员不存在！");
				continue;
			}
			System.out.println(vip.toString());
			break;
		}

	}

	private void updateVip() {
		int level = this.ui.getInt("请输入要更改的会员级别：");
		double pr = this.ui.getDouble("请输入优惠额度：");
		List<Vip> list = service.selectAllVip();
		for (Vip vip : list) {
			if (vip.getViplevel()==level) {
				service.updateVipById(new Vip(vip.getVipid(), vip.getCurid(), vip.getViplevel(), vip.getVipstate(), pr, vip.getVipdalance()));
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
					if (menus.getEatlevel()!=0) {
						System.out.println(menus.getEatid()+"\t"+menus.getEatname()+"\t"+menus.getEatstock()+"\t"+menus.getEatprice());
					}					
				}
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
			if (curt.containsKey(select)) {
				int n=curt.get(select)+num;
				curt.replace(select, n);
				updateStack(select, n);
				System.out.println("加入购物车成功！");
				break;
			}			
			curt.put(select, num);
			updateStack(select, num);
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
			}else if (select==4) {
				settle();
				break;
			}else if (select==0) {
				break;
			}else {
				System.out.println("输入错误！");
				continue;
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
					int n=num-curt.get(id);
					curt.remove(id);
					updateStack(id, n);
					System.out.println("商品已删除！");
					break;
				}
				int n=num-curt.get(id);
				curt.replace(id, num);
				updateStack(id, n);
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
					int n=0-curt.get(id);
					curt.remove(id);
					updateStack(id, n);
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
		loop:	while (true) {
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
							updateSal();							
							ticket(sum,pay);
							curt.clear();
							break loop;
						}
					}					
				}else if (type==2) {
					while (true) {
						int vipId = this.ui.getInt("请输入vip卡号:");
						Vip vip = service.selectVipById(vipId);
						if (vip==null) {
							System.out.println("此编号不存在，请重新输入！");
							continue;
						}
						if (vip.getVipstate()==0) {
							System.out.println("此会员卡已冻结，请进行挂失！");
							break;
						}
						if (vip.getVipdalance()<Math.round(vip.getVipdiscount()*sum)) {
							System.out.println("账户余额不足，请使用现金支付！");
							break;
						}
						updateSal();
						service.updateVipById(new Vip(vip.getVipid(), vip.getCurid(), vip.getViplevel(), vip.getVipstate(), vip.getVipdiscount(), vip.getVipdalance()-Math.round(vip.getVipdiscount()*sum)));					
						ticket2(sum, vip);
						curt.clear();
						break loop;

					}
				}else {
					System.out.println("输入错误！");
					continue;
				}			
			}else {
				break;
			}
		}
	}
	//打印现金支付小票
	public void ticket(double num,double pay) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			Ticket t = new Ticket(emp.getEmpid(),df.parse(df.format(new Date())),uuid, null);
			service.addTicket(t);
			service.addCurt(curt);
			System.out.println("******欢迎下次光临*******");
			System.out.println(t.getUuid());
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
	//打印会员支付小票
	public void ticket2(double num,Vip v) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");		
		try {	
			String uuid = UUID.randomUUID().toString().replaceAll("-", "");
			Ticket t = new Ticket(emp.getEmpid(),df.parse(df.format(new Date())),uuid, v.getVipid());
			service.addTicket(t);
			service.addCurt(curt);
			System.out.println("******欢迎下次光临*******");	
			System.out.println(t.getUuid());
			System.out.println("收银员："+emp.getEmpid());
			System.out.println("开票时间："+df.format(new Date()));
			System.out.println("-------------------------");
			showAllFood();
			System.out.println("-------------------------");
			System.out.println("会员卡号："+v.getVipid());
			System.out.println("总价："+num+"\t应付："+Math.round(v.getVipdiscount()*num));
			System.out.println("支付："+Math.round(v.getVipdiscount()*num)+"\t账户余额:"+(v.getVipdalance()-Math.round(v.getVipdiscount()*num)));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//显示购物车中的全部商品
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
		int n =m.getEatstock()-num;
		service.upadteMenu(new Menus(id, m.getEatname(), m.getTypeid(), m.getEatlevel(), n, m.getEatprice()));
	}
	//更改销量
	public void updateSal(){
		Set<Integer> set = curt.keySet();
		for (Integer mid : set) {
			Salnum sal = service.selectSal(mid);
			if (sal==null) {
				service.addSal(new Salnum(mid, curt.get(mid)));
			}else {
				service.updateSal(new Salnum(mid, sal.getSalnum()+curt.get(mid)));
			}						
		}
	}
	//开卡的方法
	public void addVIP(){
		System.out.println();
		int curId = this.ui.getInt("请输入顾客编号：");
		String name = this.ui.getString("请输入顾客姓名：");
		String sex = this.ui.getString("请输入顾客性别：");
		int phone = this.ui.getInt("请输入顾客联系方式：");
		service.addCustomer(new Customer(curId, name, sex, phone));
		while (true) {
			int vipid = this.ui.getInt("请输入会员卡号：");
			Vip vip = service.selectVipById(vipid);
			if (vip!=null) {
				System.out.println("此卡号已注册！");
				continue;
			}		
			double money = this.ui.getDouble("请输入充值数额(首次开卡，充值800元即可成为超级会员，享受超多优惠！)：");
			if (money<=0) {
				System.out.println("输入错误，请重新输入！");
				continue;
			}else if (money<800) {
				service.addVip(new Vip(vipid, curId, 1, 1, 0.98, money));
				break;
			}else {
				service.addVip(new Vip(vipid, curId, 2, 1, 0.92, money));
				break;
			}		
		}
		System.out.println("开卡成功，恭喜您成为本店会员！");
	}
	//挂失的方法
	public void freeze() {
		while (true) {
			int vipId = this.ui.getInt("请输入要挂失的会员卡号：");
			Vip vip = service.selectVipById(vipId);
			if (vip==null) {
				System.out.println("✖输入错误✖，该会员不存在！");
				continue;
			}
			Customer cur = service.selectCustomerById(vip.getCurid());
			String name = this.ui.getString("请输入该会员的姓名：");
			if (!name.equals(cur.getCurname())) {
				System.out.println("✖姓名验证失败！");
				continue;
			}
			service.deleteVipById(vipId);
			System.out.println("账号已冻结！");
			break;
		}
	}
	//充值的方法
	public void recharge() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		String min="2019-04-22";
		String max="2019-04-29";
		System.out.println("充值满1000元可成为超级会员享受更多优惠！");
		while (true) {
			System.out.println("惊天大优惠！只在4月22日到2月29日，充值满500返50，充值1000返200！");
			int vipId = this.ui.getInt("请输入要充值的会员卡号：");
			Vip vip = service.selectVipById(vipId);
			if (vip==null) {
				System.out.println("✖输入错误✖，该会员不存在！");
				continue;
			}if (vip.getVipstate()==0) {
				System.out.println("该账户已冻结，请补卡后充值！");
				break;
			}
			double money = this.ui.getDouble("请输入充值的数额：");
			try {
				Date d = df.parse(min);
				Date date = df.parse(max);
				if (d.getTime()<= nowLong && date.getTime()>=nowLong) {
					if (money<=0) {
						System.out.println("输入数额错误，请重新充值！");
						continue;
					}else if (money<500) {
						service.updateVipById(new Vip(vip.getVipid(), vip.getCurid(), vip.getViplevel(), 1, vip.getVipdiscount(), vip.getVipdalance()+money));
						System.out.println("充值成功！");
						break;
					}else if (money<1000) {
						service.updateVipById(new Vip(vip.getVipid(), vip.getCurid(), vip.getViplevel(), 1, vip.getVipdiscount(), vip.getVipdalance()+money+50));
						System.out.println("充值成功！");
						break;
					}else {
						service.updateVipById(new Vip(vip.getVipid(), vip.getCurid(), vip.getViplevel(), 1, vip.getVipdiscount(), vip.getVipdalance()+money+200));
						System.out.println("充值成功！");
						break;
					}
				}else {
					service.updateVipById(new Vip(vip.getVipid(), vip.getCurid(), vip.getViplevel(), 1, vip.getVipdiscount(), vip.getVipdalance()+money));
					System.out.println("充值成功！");
					break;
				}				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}			
		}
	}
	//补卡的方法
	public void repair() {
		while (true) {
			int vipId = this.ui.getInt("请输入要补的会员卡Id");
			Vip vip = service.selectVipById(vipId);
			if (vip==null) {
				System.out.println("✖输入错误✖，该会员不存在！");
				continue;
			}
			String name = this.ui.getString("请输入姓名：");
			Customer cur = service.selectCustomerById(vip.getCurid());
			if (!name.equals(cur.getCurname())) {
				System.out.println("姓名验证失败！请重新输入！");
				continue;
			}
			int newVipId = this.ui.getInt("请输入新的会员卡号：");
			Vip vip2 = service.selectVipById(newVipId);
			if (vip2!=null) {
				System.out.println("✖输入错误✖，该会员已存在！");
				continue;
			}
			service.deleteVipById(vipId);
			service.addVip(new Vip(newVipId, vip.getCurid(), vip.getViplevel(), 1, vip.getVipdiscount(), vip.getVipdalance()));
			System.out.println("补卡成功！");
			break;
		}	
	}
	//添加员工
	public void addEmp() {
		while (true) {
			int empId = this.ui.getInt("请输入员工账号：");
			Employee emp = service.selectEmployeeById(empId);
			if (emp!=null) {
				System.out.println("该账号已被注册，请重新输入！");
				continue;
			}
			String password = this.ui.getString("请输入登录密码：");
			String name = this.ui.getString("请输入员工姓名：");
			String sex = this.ui.getString("请输入员工性别：");
			int phone = this.ui.getInt("请输入员工联系方式：");
			service.addEmployee(new Employee(empId, name, sex, phone, 1, password));
			break;
		}		
	}
	//删除员工
	public void deleteEmp() {
		while (true) {
			int empId = this.ui.getInt("请输入要删除的员工ID：");
			Employee employee = service.selectEmployeeById(empId);
			if (employee==null) {
				System.out.println("该员工不存在！");
				continue;
			}
			service.deleteEmployee(empId);
			System.out.println("员工删除成功！");
			break;
		}
	}
	//更改员工信息
	public void updateEmp(int n) {
		while (true) {
			int empId = this.ui.getInt("请输入要更改的员工ID：");
			Employee employee = service.selectEmployeeById(empId);
			if (employee==null) {
				System.out.println("该员工不存在！");
				continue;
			}if (n==0) {
				break;
			}
			if (n==1) {
				String name = this.ui.getString("请输入姓名：");
				service.updateEmployee(new Employee(empId, name, employee.getEmpsex(), employee.getEmpphone(), emp.getEmplevel(), employee.getEmppassword()));
				break;
			}else if (n==2) {
				int phone = this.ui.getInt("请输入联系方式：");
				service.updateEmployee(new Employee(empId, employee.getEmpname(), employee.getEmpsex(), phone, emp.getEmplevel(), employee.getEmppassword()));
				break;
			}else if (n==3) {
				String password = this.ui.getString("请输入密码：");
				service.updateEmployee(new Employee(empId, employee.getEmpname(), employee.getEmpsex(), employee.getEmpphone(), emp.getEmplevel(),password));
				break;
			}else {
				System.out.println("输入错误！");
				continue;
			}
		}
	}
	//查询所有员工信息
	public void selectAllEmp() {
		List<Employee> list = service.selectAllEmployee();
		v.show(list);
	}
	//根据员工id查找员工
	public void selectEmpById() {
		while (true) {
			int empId = this.ui.getInt("请输入要查询的员工ID：");
			Employee employee = service.selectEmployeeById(empId);
			if (employee==null) {
				System.out.println("该员工不存在！");
				continue;
			}
			System.out.println(employee.toString());
			break;
		}
	}
	//添加菜品的方法
	public void addMenu() {
		String name = this.ui.getString("请输入菜名：");
		double price = this.ui.getDouble("请输入菜品单价：");
		int type = this.ui.getInt("请输入菜品种类ID");
		int stock = this.ui.getInt("请输入库存：");
		service.addMenu(new Menus(name, type, 1, stock, price));
	}
	//下架菜品
	public void deleteMenu() {
		int mId = this.ui.getInt("请输入要删除的菜品ID");
		Menus m = service.selectMenuById(mId);
		if (m==null) {
			System.out.println("该菜品不存在！");
		}else {
			service.deleteMenu(mId);
		}
	}
	//更改菜品
	public void updateFood(int n) {
		while (true) {
			int mId = this.ui.getInt("请输入要修改的菜品ID");
			Menus m = service.selectMenuById(mId);
			Menus m1=new Menus();
			if (m==null) {
				System.out.println("该菜品不存在！");
				continue;
			}
			if (n==0) {
				break;
			}else if (n==1) {
				double price = this.ui.getDouble("请输入价格：");
				m1=new Menus(mId,m.getEatname(), m.getTypeid(), m.getEatlevel(), m.getEatstock(),price);
			}else if (n==2) {
				String name = this.ui.getString("请输入名称：");
				m1=new Menus(mId,name, m.getTypeid(), m.getEatlevel(), m.getEatstock(), m.getEatprice());
			}else if (n==3) {
				double price = this.ui.getDouble("请输入特价：");
				m1=new Menus(mId,m.getEatname()+"(特价菜)", m.getTypeid(), m.getEatlevel(), m.getEatstock(),price);		
			}else if (n==4) {
				int stock = this.ui.getInt("请输入库存：");
				m1=new Menus(mId,m.getEatname(), m.getTypeid(), m.getEatlevel(),stock,m.getEatprice());
			}else {
				System.out.println("输入错误！");
				continue;
			}
			service.upadteMenu(m1);
			break;
		}		
	}
	//
	public void selectMenById() {
		while (true) {
			int mId = this.ui.getInt("请输入要查找的菜品ID");
			Menus m = service.selectMenuById(mId);
			if (m==null) {
				System.out.println("该菜品不存在！");
				continue;
			}
			System.out.println(m.toString());
			break;
		}
	}
	//菜品销量排行
	public void salDesc() {
		List<Salnum> list = service.selectAllNum();
		System.out.println("菜品名称\t菜品销量");
		v.show(list);
	}
	public void printTick() {
		List<Ticket> list = service.printTicket();
		for (Ticket ticket : list) {
			System.out.println(ticket.getId()+"\t"+ticket.getEmpid()+"\t"+ticket.getDate()+"\t"+ticket.getVipid());
		}
		int id = this.ui.getInt("请输入订单ID：");
		Map<Integer, Integer> map = service.selectAllCurt(id);
		Set<Integer> set = map.keySet();
		for (Integer i : set) {
			System.out.println(i+"\t"+service.selectMenuById(i).getEatname()+"\t"+map.get(i));
		}
	}
}
