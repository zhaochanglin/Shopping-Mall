package cn.itcast.store.dao;

import java.util.List;


import cn.itcast.store.domain.Product;

/**
 * 商品类DAO接口     22222
 * <p>Title: ProductDao</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午5:02:34
 * @version 1.0
 */
public interface ProductDao {

	List<Product> findHots() throws Exception;

	List<Product> findNews() throws Exception;

	Product findProductByPid(String pid) throws Exception;

	int findTotalRecords(String cid) throws Exception;

	List findProductsByCidWithPage(String cid, int startIndex, int pageSize) throws Exception;

	int findTotalRecords() throws Exception;

	List<Product> findAllProductsWithPage(int startIndex, int pageSize)throws Exception;

	void saveProduct(Product product) throws Exception;

	


}
