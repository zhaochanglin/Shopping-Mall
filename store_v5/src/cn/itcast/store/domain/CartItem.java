package cn.itcast.store.domain;


/**
 * 购物项
 * <p>Title: CartItem</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月13日下午3:12:13
 * @version 1.0
 */
public class CartItem {
	private Product product;//携带购物项3种参数（图片路径，商品名，商品价格）
	private int num;//当前类别商品数量
	private double subtotal;//小计
	
	//小记可通过计算得出
		public double getSubtotal() {
			return product.getShop_price()*num;
		}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}
	
}
