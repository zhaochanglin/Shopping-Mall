package cn.itcast.store.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * 购物车
 * 2个属性3个方法
 * <p>Title: Cart</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月13日下午3:12:31
 * @version 1.0
 */
public class Cart {
	//	总计、积分
	private double total=0;
	//	个数不确定的购物项
	Map<String,CartItem> map = new HashMap<String,CartItem>();
	//	添加购物项到购物车
	public void addCartItemToCart(CartItem cartItem){
		//获取正在想购物车添加商品的pid
		String pid = cartItem.getProduct().getPid();
		/*将当前购物项加入购物车之前判断之前是否添加过本类商品；没有则只需list.add(cartItem);如果添加过，
		 * 则获取原先的数量、本次数量，相加后设置到原先的购物项上 */
		if (map.containsKey(pid)) {
			CartItem oldItem = map.get(pid);
			oldItem.setNum(oldItem.getNum()+cartItem.getNum());//之前的数量+现在添加的数量
		} else {
			map.put(pid, cartItem);
		}
		
	}
	//返回Map中所有的值
	public Collection<CartItem> getCartItems(){
		return map.values();
		
	}
	//	移除购物项
	public void removeCartItem(String pid){
		map.remove(pid);
	}
	//	清空购物车内购物项
	public void clearCart(){
		map.clear();
	}
	//总计是可以通过计算获取到的
	public double getTotal() {
		//向上总计
		total=0;
		//获取到Map中所有的购物项
		Collection<CartItem> values = map.values();
		//遍历所有的购物项，将购物项上的小计相加
		for (CartItem cartItem : values) {
			
			total+=cartItem.getSubtotal();
		}
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
}
