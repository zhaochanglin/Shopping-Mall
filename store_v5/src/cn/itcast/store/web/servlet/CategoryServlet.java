package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.itcast.store.domain.Category;
import cn.itcast.store.service.CategoryService;
import cn.itcast.store.service.serviceImpl.CategoryServiceImpl;
import cn.itcast.store.utils.JedisUtils;
import cn.itcast.store.web.base.BaseServlet;
import net.sf.json.JSONArray;
import redis.clients.jedis.Jedis;


public class CategoryServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;

	//findAllCats
	public String findAllCats(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//在redis中获取全部的分类信息
		Jedis jedis = JedisUtils.getJedis();
		String jsonStr = jedis.get("allCats");
		if (null == jsonStr||"".equals(jsonStr)) {
			//调用业务层获取全部分类
			CategoryService cs = new CategoryServiceImpl();
			List< Category> list = cs.getAllCats();
			//将全部分类转换为JSON格式的数据(将集合数据转换成json格式)
			jsonStr = JSONArray.fromObject(list).toString();
			System.out.println("转换后的字符串："+jsonStr);
			//将获取到的json数据存到redis中
			jedis.set("allCats", jsonStr);
			System.out.println("redis缓存中没有数据！");
			//将全部分类信息相应到客户端
			//告诉浏览器本次响应的数据是JSON格式的字符集
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().println(jsonStr);
			
		}else{
			System.out.println("redis缓存中有数据！");
			//将全部分类信息相应到客户端
			//告诉浏览器本次响应的数据是JSON格式的字符集
			response.setContentType("application/json;charset=utf-8");
			response.getWriter().println(jsonStr);
		}
		JedisUtils.closeJedis(jedis);   //释放缓存
		return null;
	}
}
