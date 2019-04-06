package cn.itcast.store.service;

import java.util.List;

import cn.itcast.store.domain.Order;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.User;

/**
 * 商品服务类
 * <p>Title: OrderService</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月18日下午4:52:47
 * @version 1.0
 */
public interface OrderService {

	void saveOrder(Order order) throws Exception;

	PageModel findMyOrdersWithPage(User user, int curNum) throws Exception;

	Order findOrderByOid(String oid) throws Exception;

	void updateOrder(Order order) throws Exception;

	List<Order> findAllOrdes() throws Exception;

	List<Order> findAllOrdes(String st) throws Exception;
	

}
