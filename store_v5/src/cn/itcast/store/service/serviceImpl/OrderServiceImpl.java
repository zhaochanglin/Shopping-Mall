package cn.itcast.store.service.serviceImpl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import cn.itcast.store.dao.OrderDao;
import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.MySQLdaoImpl.MySQLOrderDaoImpl;
import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.OrderItem;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.OrderService;
import cn.itcast.store.utils.BeanFactory;
import cn.itcast.store.utils.JDBCUtils;

public class OrderServiceImpl implements OrderService {
	OrderDao orderDao = (OrderDao)BeanFactory.createObject("OrderDao");
	/**
	 * 保存订单
	 * <p>Title: saveOrder</p>
	 * <p>Description: </p>
	 * @param order
	 * @throws SQLException
	 * @see cn.itcast.store.service.OrderService#saveOrder(cn.itcast.store.domain.Order)
	 */
	@Override
	public void saveOrder(Order order) throws SQLException {
		// 保存订单和订单下所有的订单项（同时成功或失败）
		Connection conn = null;
		try {
			//获取连接
conn=  JDBCUtils.getConnection();
			
			//开启事务
			conn.setAutoCommit(false);
			//保存订单
			
			orderDao.saveOrder(conn,order);
			//保存订单项
			for (OrderItem item : order.getList()) {
				orderDao.saveOrderItem(conn,item);
			}
			//提交
			conn.commit();
			//
		} catch (Exception e) {
			// 回滚事务
			conn.rollback();
		}
		//关闭连接
//		finally {
//			if (null!=conn) {
//				conn.close();
//				conn= null;
//			}
//		}
	}

	@Override
	public PageModel findMyOrdersWithPage(User user, int curNum) throws Exception {
		// 创建PageModel对象，计算并携带分页参数
		//select count(*) from orders where uid=?
		int totalRecords = orderDao.getTotalRecords(user);
		PageModel pm = new PageModel(curNum, totalRecords, 3);
		//关联集合
		List list = orderDao.findMyOrdersWithPage(user,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//关联url
		pm.setUrl("OrderServlet?method=findMyOrdersWithPage");
		return pm;
	}

	@Override
	public Order findOrderByOid(String oid) throws Exception {
		
		return orderDao.findOrderByOid(oid);
	}
/**
 * 更新收货人信息
 * <p>Title: updateOrder</p>
 * <p>Description: </p>
 * @param order
 * @throws Exception
 * @see cn.itcast.store.service.OrderService#updateOrder(cn.itcast.store.domain.Order)
 */
	@Override
	public void updateOrder(Order order) throws Exception {
		orderDao.updateOrder(order);
		
	}

@Override
public List<Order> findAllOrdes() throws Exception {
	
	return orderDao.findAllOrdes();
}

@Override
public List<Order> findAllOrdes(String st) throws Exception {
	
	return orderDao.findAllOrdes(st);
}

	

}
