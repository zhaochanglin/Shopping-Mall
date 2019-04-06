package cn.itcast.store.web.servlet;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.naming.java.javaURLContextFactory;

import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.service.serviceImpl.UserServiceImpl;
import cn.itcast.store.utils.MailUtils;
import cn.itcast.store.utils.MyBeanUtils;
import cn.itcast.store.utils.UUIDUtils;
import cn.itcast.store.web.base.BaseServlet;
import jdk.nashorn.internal.objects.annotations.Where;

/**
 * Servlet类
 * <p>Title: UserServlet</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月1日下午5:16:38
 * @version 1.0
 */
 
public class UserServlet extends BaseServlet {
	
	/** @Fields serialVersionUID : TODO*/
	private static final long serialVersionUID = 1L;
	/* 点击登录跳转到登录页面 */
	public String loginUI(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		return "/jsp/login.jsp";
		
	}
	/* 点击注册跳转到注册页面 */
	public String regisUI(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		return "/jsp/register.jsp";
		
	}
	/**
	 * 用户注册方法
	 * <p>Title: userRegist</p>
	 * <p>Description: </p>
	 * @param request
	 * @param response
	 * @return
	 * @throws ServletException
	 * @throws IOException
	 * @throws Exception 
	 * @throws IllegalAccessException 
	 */
	public String userRegist(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//接收表单参数
		Map<String, String[]> map = request.getParameterMap();
		User user = new User();
		MyBeanUtils.populate(user, map);
		//为用户的其他属性赋值
		user.setUid(UUIDUtils.getId());//用户ID
		user.setState(0);			   	//用户状态
		user.setCode(UUIDUtils.getCode());	//激活码
		System.out.println("user:"+ user);
		// 调用业务层注册功能
		UserService userService = new UserServiceImpl();
		try {
			userService.userRegist(user);
			//注册成功，向用户邮箱发送信息，跳转到提示页面
			//发送邮件
			MailUtils.sendMail(user.getEmail(), user.getCode());
			request.setAttribute("msg", "注册成功，请登录邮箱激活！");
		} catch (Exception e) {
			//注册失败，跳转到提示页面
			request.setAttribute("msg", "注册失败，请重新注册！");
		}
		return "/jsp/info.jsp";
	}
/**
 * 用户激活方法
 * <p>Title: active</p>
 * <p>Description: </p>
 * @param request
 * @param response
 * @return
 * @throws SQLException
 */
	public String active(HttpServletRequest request, HttpServletResponse response) throws SQLException {
		//获取激活码
		String code = request.getParameter("code");
		//调用激活功能
		UserService userService = new UserServiceImpl();
		boolean flag = userService.userActive(code);
		//进行激活提示
		if (flag=true) {
			//用户激活成功，向request放入提示信息，转到提示界面
			request.setAttribute("msg", "恭喜您激活成功，请登录商城！");
			return "/jsp/login.jsp";
		} else {
			//用户激活失败，向request放入提示信息，转到提示界面
			request.setAttribute("msg", "抱歉，您的账户激活失败，请重新激活！");
			return "/jsp/info.jsp";
		}
	}
/**
 * 用户登录的方法
 * <p>Title: userLogin</p>
 * <p>Description: </p>
 * @param request
 * @param response
 * @return
 * @throws SQLException
 */
public String userLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException {
	//获取用户等录数据（账号、密码）
	User user = new User();
	MyBeanUtils.populate(user, request.getParameterMap());
	//调用业务层的登录功能
	UserService userService = new UserServiceImpl();
	User user02 = null;
	try {
		user02 = userService.userLogin(user);
		//用户登录成功,将用户信息存放放到session中
		request.getSession().setAttribute("loginUser", user02);
		response.sendRedirect("/store_v5/index.jsp");
		return null;
	} catch (Exception e){
		//用户登录失败
		String msg = e.getMessage();
		System.out.println("登陆失败信息："+msg);
		//向request放入失败信息
		request.setAttribute("msg", msg);
		return "/jsp/login.jsp";
	  }
	}
/**
 * 实现退出登录操作
 * 重定向后不再转发
 * <p>Title: logOut</p>
 * <p>Description: </p>
 * @param request
 * @param response
 * @return
 * @throws Exception 
 */
public String logOut(HttpServletRequest request, HttpServletResponse response) throws Exception {
	request.getSession().invalidate();
	response.sendRedirect("/store_v5/index.jsp");//重定向到首页
	return null;//不再转发
	
}
}
