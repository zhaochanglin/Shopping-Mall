package cn.itcast.store.dao.MySQLdaoImpl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.domain.Product;
import cn.itcast.store.utils.JDBCUtils;
/**
 * 商品类dao实现
 * <p>Title: ProductDaoImpl</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午5:03:59
 * @version 1.0
 */
public class MySQLProductDaoImpl implements ProductDao {

	@Override
	public List<Product> findHots() throws Exception {
		String sql ="select * from product where pflag=0 and is_hot=1 order by pdate desc limit 0,9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	}

	@Override
	public List<Product> findNews() throws Exception {
		String sql ="select * from product where pflag=0 order by pdate desc limit 0,9";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class));
	
	}

	@Override
	public Product findProductByPid(String pid) throws Exception {
		String sql ="select * from product where pid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<Product>(Product.class),pid);
	}
	/**
	 * 统计类别下有多少种商品
	 * <p>Title: findTotalRecords</p>
	 * <p>Description: </p>
	 * @param cid
	 * @return
	 * @throws Exception 
	 * @throws Exception
	 * @see cn.itcast.store.dao.ProductDao#findTotalRecords(java.lang.String)
	 */

	@Override
	public int findTotalRecords(String cid) throws Exception {
		String sql="select count(*) from product where cid=?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		Long num = (Long)qr.query(sql, new ScalarHandler(),cid);
		return num.intValue();
	}
	/**
	 * 查询商品信息并分页显示
	 * <p>Title: findProductsByCidWithPage</p>
	 * <p>Description: </p>
	 * @param cid
	 * @param startIndex
	 * @param pageSize
	 * @return
	 * @throws Exception 
	 * @see cn.itcast.store.dao.ProductDao#findProductsByCidWithPage(java.lang.String, int, int)
	 */
	@Override
	public List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception {
		String sql="select * from product where cid=? limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),cid,startIndex,pageSize);
		
	}
/**
 * 后台分页计算
 * <p>Title: findTotalRecords</p>
 * <p>Description: </p>
 * @return
 * @throws Exception
 * @see cn.itcast.store.dao.ProductDao#findAllProductsWithPage()
 */
	@Override
/*	public int findTotalRecords() throws Exception {
		String sql="select count(*) from product";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}*/
	
	public int findTotalRecords() throws Exception {
		String sql="select count(*) from product";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		Long num=(Long)qr.query(sql, new ScalarHandler());
		return num.intValue();
	}
/**
 * 后台商品信息分页
 * <p>Title: findAllProductsWithPage</p>
 * <p>Description: </p>
 * @param startIndex
 * @param pageSize
 * @return
 * @throws Exception
 * @see cn.itcast.store.dao.ProductDao#findAllProductsWithPage(int, int)
 */
	@Override
	/*public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from product limit ?,?";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
		
	}*/
	public List<Product> findAllProductsWithPage(int startIndex, int pageSize) throws Exception {
		String sql="select * from product order by pdate desc limit  ? , ?";
		QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Product>(Product.class),startIndex,pageSize);
	}

@Override
public void saveProduct(Product product) throws Exception {
	String sql="INSERT INTO product VALUES(?,?,?,?,?,?,?,?,?,?)";
	QueryRunner qr=new QueryRunner(JDBCUtils.getDataSource());
	Object[] params={product.getPid(),product.getPname(),product.getMarket_price(),product.getShop_price(),product.getPimage(),product.getPdate(),product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
	qr.update(sql,params);
}

	

}
