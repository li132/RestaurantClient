package active;

import java.security.PublicKey;
import java.util.List;

import java.util.Map;
import admin.Customer;
import admin.Employee;
import admin.Menus;
import admin.Salnum;
import admin.Ticket;
import admin.Type;
import admin.Vip;

public interface restService {
	//登录
	public Employee login(int id,String password);
	//订单行信息增加
	public String addCurt(int id,Map<Integer, Integer> m);
	//订单行删除信息 根据id
	public String deleteCurt(int id);
	//订单行删除全部信息
	public String deleteAllCurt();
	//订单行查询全部信息
	public Map<Integer, Integer> selectAllCurt();
	//根据id更改物品数量
	public String updateCurt(int id);
	//录入员工信息
	public String addEmployee(Employee e);
	//删除员工信息
	public String deleteEmployee(int id);
	//修改员工信息
	public String updateEmployee(Employee e);
	//查询员工信息根基id
	public Employee selectEmployeeById(int id);
	//查询所有员工信息
	public List<Employee> selectAllEmployee();
	//查询所有客户id 返回list
	public List<Customer> selectAllCustimerId();
	//查询所有客户  list
	public List<Customer> selectAllCustimer();
	//录入客户信息
	public String addCustomer(Customer c);
	//根据id查找顾客
	public Customer selectCustomerById(int id);
	//删除客户信息 通过id
	public String deleteCustomer(int id);
	//修改客户信息
	public String updateCustomerById(Customer c);
	//录入vip信息
	public String addVip(Vip v);
	//删除vip信息
	public String deleteVipById(int id);
	//根据id查询vip信息
	public Vip selectVipById(int id);
	//修改vip信息
	public String updateVipById(Vip v);
	//查询vip信息 所有
	public List<Vip> selectAllVip();
	//录入菜品，价格，类型
	public String addMenu(Menus m);
	//删除菜品
	public String deleteMenu(int id);
	//修改菜品，价格，类型
	public String upadteMenu(Menus m);
	//查询所有菜品 list
	public List<Menus> selectAllMenu();
	//根据id查询 
	public Menus selectMenuById(int id);
	//查询所有菜品类别 id 名称
	public List<Type> selectAllType();
	//根据id查找具体菜类别
	public Type selectTypeById(int id);
	//根据菜品种类id查询所有菜品
	public List<Menus> selectAllMuByTp(int id);
	//查询菜品月销量 降序 list
	public List<Salnum> selectAllNum();
	//插入销量
	public String addSal(Salnum s);
	//更改销量
	public String updateSal(Salnum s);
	//查询销量是否存在
	public boolean selectSal(int id);
	//打印小票抬头
	public void printTicket(int id);
	//录入小票抬头
	public String addTicket(Ticket t);
	//返回最大id数+1；
	public int finTicketid();
}
