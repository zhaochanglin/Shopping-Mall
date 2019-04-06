package cn.itcast.store.service;

import java.util.List;

import cn.itcast.store.domain.Category;

/**
 * 分类接口
 * <p>Title: CategoryService</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午12:44:59
 * @version 1.0
 */
public interface CategoryService {

	List<Category> getAllCats() throws Exception;

	void addCategory(Category c) throws Exception;

}
