package cn.itcast.store.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.PageModel;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImpl.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;
/**
 * 商品模块
 * 最热最新商品
 * <p>Title: ProductServlet</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月4日下午4:57:40
 * @version 1.0
 */
public class ProductServlet extends BaseServlet {
	private static final long serialVersionUID = 1L;
	public String findProductByPid(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取商品pid
		String pid = request.getParameter("pid");
		//根据商品pid查询商品信息
		ProductService ps = new ProductServiceImpl();
		Product product = ps.findProductByPid(pid);
		//将查询到的信息存到request中
		request.setAttribute("product", product);
		//转发到/jsp/product_info.jsp
		return "/jsp/product_info.jsp";
	}
	/**
	 * 根据cid查询分类商品信息，并分页显示
	 * <p>Title: findProductsByCidWithPage</p>
	 * <p>Description: </p>
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	public String findProductsByCidWithPage(HttpServletRequest request, HttpServletResponse response) throws Exception {
//		获cid,num;
		String cid = request.getParameter("cid");
		int curNum = Integer.parseInt(request.getParameter("num"));
//		调用业务层功能以分页形式查询当前类别下商品信息
//		返回PageModel对象（1.当前页商品信息2.分页，3.url）
		ProductService ps = new ProductServiceImpl();
		PageModel pm = ps.findProductsByCidWithPage(cid,curNum);
//		将PageModel对象放入request
		request.setAttribute("page", pm);
//		转发到/jsp/product_list.jsp
		return "/jsp/product_list.jsp";
		
	}
}
