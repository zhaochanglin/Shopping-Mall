package cn.itcast.store.dao.MySQLdaoImpl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.domain.Category;
import cn.itcast.store.utils.JDBCUtils;
/**
 * 分类Dao层接口实现
 * <p>Title: CategoryDaoImpl</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午12:47:32
 * @version 1.0
 */
public class MySQLCategoryDaoImpl implements CategoryDao {

	@Override
	public List<Category> getAllCats() throws Exception {
		String sql = "select * from category";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanListHandler<Category>(Category.class));
	}

	@Override
	public void addCategory(Category c) throws Exception {
		// TODO Auto-generated method stub
		String sql="insert into category values(?,?)";
		QueryRunner qr = new QueryRunner(JDBCUtils.getDataSource());
		qr.update(sql,c.getCid(),c.getCname());
	}

}
