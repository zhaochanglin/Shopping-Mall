package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.domain.Product;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.ProductService;
import cn.itcast.store.service.serviceImpl.CategoryServiceImpl;
import cn.itcast.store.service.serviceImpl.ProductServiceImpl;
import cn.itcast.store.web.base.BaseServlet;



public class IndexServlet extends BaseServlet {
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//调用业务层功能，获取全部分类信息，返回集合
		/*
		 * 以下已使用ajax请求实现
		 * 
		 * CategoryService cs = new CategoryServiceImpl();
		List<Category> list = cs.getAllCats();
		//将返回的集合放入request
		request.setAttribute("allCats", list);
		 */
		//调用业务层查询最新商品、最热商品，返回两个集合
		ProductService ps = new ProductServiceImpl();
		List<Product> list01 = ps.findHots();
		List<Product> list02 = ps.findNews();
		//将两个集合放到request中
		request.setAttribute("hots", list01);
		request.setAttribute("news", list02);
		//转发到真实首页
		return "/jsp/index.jsp";
	}

}
