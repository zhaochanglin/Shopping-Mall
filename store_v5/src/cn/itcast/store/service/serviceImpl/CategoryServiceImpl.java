package cn.itcast.store.service.serviceImpl;

import java.util.List;

import cn.itcast.store.dao.CategoryDao;
import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.MySQLdaoImpl.MySQLCategoryDaoImpl;
import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.utils.BeanFactory;
import cn.itcast.store.utils.JedisUtils;
import redis.clients.jedis.Jedis;
/**
 * 分类接口实现
 * <p>Title: CategoryServiceImpl</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午12:45:24
 * @version 1.0
 */
public class CategoryServiceImpl implements CategoryService {

	CategoryDao cd = (CategoryDao)BeanFactory.createObject("CategoryDao");
	@Override
	public List<Category> getAllCats() throws Exception {
		return cd.getAllCats();
		
	}
/**
 * 增加分类实现
 * 更新Redis缓存（删除原有数据重新缓存）
 * <p>Title: addCategory</p>
 * <p>Description: </p>
 * @param c
 * @throws Exception
 * @see cn.itcast.store.service.CategoryService#addCategory(cn.itcast.store.domain.Category)
 */
	@Override
	public void addCategory(Category c) throws Exception {
		// 向数据库（MYSQL）插入了数据
		cd.addCategory(c);
		//更新Redis缓存
		Jedis jedis = JedisUtils.getJedis();
		jedis.del("allCats");
		JedisUtils.closeJedis(jedis);
	}

}
