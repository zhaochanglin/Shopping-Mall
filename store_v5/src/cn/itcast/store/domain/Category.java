package cn.itcast.store.domain;
/**
 * 分类信息
 * <p>Title: Category</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午12:49:18
 * @version 1.0
 */
public class Category {
	private String cid;
	private String cname;
	public String getCid() {
		return cid;
	}
	public void setCid(String cid) {
		this.cid = cid;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	
	//有参数无参数构造
	public Category(){
		
	}
	public Category(String cid, String cname) {
		super();
		this.cid = cid;
		this.cname = cname;
	}
	@Override
	public String toString() {
		return "Category [类id=：" + cid + ", 类名=：" + cname + "]";
	}
	
}
