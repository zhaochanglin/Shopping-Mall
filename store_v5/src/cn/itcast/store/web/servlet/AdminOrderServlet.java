package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.mail.iap.Literal;

import cn.itcast.store.domain.Order;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.service.serviceImpl.OrderServiceImpl;
import cn.itcast.store.web.base.BaseServlet;
import net.sf.json.JSONArray;

/**
 * 
 * <p>Title: AdminOrderServlet</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年4月5日下午3:55:34
 * @version 1.0
 */
 
public class AdminOrderServlet extends BaseServlet {
	/**
	 * 后台订单查询
	 * 优化：一个方法解决不同状态订单的查询
	 * <p>Title: findAllOrdes</p>
	 * <p>Description: </p>
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String findAllOrdes(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//获取所有的订单
		OrderService orderService = new OrderServiceImpl();
		String st = req.getParameter("state");
		List<Order> list= null;
		//判断订单状态
		if (null ==st||"".equals(st)) {
			list = orderService.findAllOrdes();
		} else {
			list = orderService.findAllOrdes(st);
		}
		//将订单信息存入request
		req.setAttribute("allOrders", list);
		//转发到/admin/order/list.jsp
		return "/admin/order/list.jsp";
	}
	//
	public String findOrderByOidWithAjax(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//服务端获取到订单ID,
				String oid=req.getParameter("id");
				//查询这个订单下所有的订单项以及订单项对应的商品信息,返回集合
				OrderService OrderService=new OrderServiceImpl();
				Order order=OrderService.findOrderByOid(oid);
				//将返回的集合转换为JSON格式字符串,响应到客户端
				String jsonStr=JSONArray.fromObject(order.getList()).toString();
				//响应到客户端
				resp.setContentType("application/json;charset=utf-8");
				resp.getWriter().println(jsonStr);
				return null;
	
	}
	/**
	 * 点击发货更改订单状态
	 * <p>Title: updateOrderByOid</p>
	 * <p>Description: </p>
	 * @param req
	 * @param resp
	 * @return
	 * @throws Exception
	 */
	public String updateOrderByOid(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		/*//获取订单id
		String oid = req.getParameter("oid");
		//根据订单id查询订单
		OrderService orderService = new OrderServiceImpl();
		Order order = orderService.findOrderByOid(oid);
		//设置订单状态
		order.setState(3);
		//修改订单信息
		orderService.updateOrder(order);
		//重定向到查询已发货订单
		resp.sendRedirect("/store_v5/AdminOrderServlet?method=findAllOrdes&state=3");
		return null;*/
		//获取订单ID
				String oid=req.getParameter("oid");
				//根据订单ID查询订单
				OrderService OrderService=new OrderServiceImpl();
				Order order=OrderService.findOrderByOid(oid);
				//设置订单状态
				order.setState(3);
				//修改订单信息
				OrderService.updateOrder(order);
				//重新定向到查询已发货订单
				resp.sendRedirect("/store_v5/AdminOrderServlet?method=findAllOrdes&state=3");
				return null;
	
	}
}
