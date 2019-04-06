package cn.itcast.store.dao;

import java.util.List;

import cn.itcast.store.domain.Category;

/**
 * 分类Dao层接口
 * <p>Title: CategoryDao</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午12:46:08
 * @version 1.0
 */
public interface CategoryDao {

	List<Category> getAllCats() throws Exception;

	void addCategory(Category c) throws Exception;

}
