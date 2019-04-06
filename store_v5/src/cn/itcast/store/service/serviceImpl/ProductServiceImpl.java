package cn.itcast.store.service.serviceImpl;

import java.util.List;

import javax.print.attribute.standard.PDLOverrideSupported;

import cn.itcast.store.dao.ProductDao;
import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.MySQLdaoImpl.MySQLProductDaoImpl;
import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.utils.BeanFactory;
/**
 * 商品类接口实现类
 * <p>Title: ProductServiceImpl</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午5:00:50
 * @version 1.0
 */
public class ProductServiceImpl implements ProductService {
	ProductDao pd = (ProductDao)BeanFactory.createObject("ProductDao");
	//查找最热商品
	@Override
	public List<Product> findHots() throws Exception {
		
		return pd.findHots();
	}
	//查找最新商品
	@Override
	public List<Product> findNews() throws Exception {
		
		return pd.findNews();
	}
	//根据pid查找商品
	@Override
	public Product findProductByPid(String pid) throws Exception {
		
		return pd.findProductByPid(pid);
	}
	//根据cid查找分类商品信息并分页显示
	@Override
	public PageModel findProductsByCidWithPage(String cid, int curNum) throws Exception {
		//1_创建PageModel对象，目的是计算分页参数
		
		//统计当前分类下商品总数select count(*) from product where cid=?;
		int totalRecords = pd.findTotalRecords(cid);
		PageModel pm = new PageModel(curNum, totalRecords, 12);
		//2_关联集合select * from product where cid=? limit ?,?;
		List list = pd.findProductsByCidWithPage(cid,pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("ProductServlet?method=findProductsByCidWithPage&cid="+cid);
		return pm;
		
	}
	@Override
	public PageModel findAllProductsWithPage(int curNum) throws Exception {
		/*// 创建 对象
		int totalRecords = pd.findTotalRecords();
		PageModel pm = new PageModel(curNum, totalRecords, 5);
		// 关联集合 select * from product limit ?,?;
		List<Product> list = pd.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		
		// 关联url
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;*/
		
		//1_创建对象
		int totalRecords=pd.findTotalRecords();
		PageModel pm=new PageModel(curNum,totalRecords,8);
		//2_关联集合 select * from product limit ? , ?
		List<Product> list=pd.findAllProductsWithPage(pm.getStartIndex(),pm.getPageSize());
		pm.setList(list);
		//3_关联url
		pm.setUrl("AdminProductServlet?method=findAllProductsWithPage");
		return pm;
	}
	/**
	 * 保存添加的商品
	 * <p>Title: saveProduct</p>
	 * <p>Description: </p>
	 * @param product
	 * @throws Exception
	 * @see cn.itcast.store.service.ProductService#saveProduct(cn.itcast.store.domain.Product)
	 */
	@Override
	public void saveProduct(Product product) throws Exception {
		pd.saveProduct(product);
		
	}

	

}
