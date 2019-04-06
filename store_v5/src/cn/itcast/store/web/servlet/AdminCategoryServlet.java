package cn.itcast.store.web.servlet;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.serviceImpl.CategoryServiceImpl;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;
/**
 * 后台得到所有分类
 * <p>Title: AdminCategoryServlet</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年4月1日下午7:53:52
 * @version 1.0
 */
public class AdminCategoryServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;

	//findAllCats
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取全部分类信息
		CategoryService cs =new CategoryServiceImpl();
		List<Category> list = cs.getAllCats();
		//存入request中
		request.setAttribute("allCats", list);
		//转发到/admin/category/list.jsp
		
		return "/admin/category/list.jsp";
		
	}
/**
 * 增加分类信息按钮转发
 * <p>Title: addCategoryUI</p>
 * <p>Description: </p>
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public String addCategoryUI(HttpServletRequest request, HttpServletResponse response) throws Exception {
		return "/admin/category/add.jsp";
	}
/**
 * 增加分类信息并保存到数据库
 * <p>Title: addCategory</p>
 * <p>Description: </p>
 * @param request
 * @param response
 * @return
 * @throws Exception
 */
	public String addCategory(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//获取分类名称
		String cname = request.getParameter("cname");
		//创建分类id
		String id = UUIDUtils.getId();
		Category c=new Category();
		c.setCid(id);
		c.setCname(cname);
		
		//调用业务层添加分类功能
		CategoryService categoryService = new CategoryServiceImpl();
		categoryService.addCategory(c);
		//重定向到查询全部分类信息
		response.sendRedirect("/store_v5/AdminCategoryServlet?method=findAllCats");
		
		return null;
		
	}
}
