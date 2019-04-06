package cn.itcast.store.web.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import cn.itcast.store.domain.User;

/**
 * 权限过滤器
 * <p>Title: PriviledgeFilter</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年4月1日下午5:20:48
 * @version 1.0
 */
public class PriviledgeFilter implements Filter {
    public PriviledgeFilter() {
      
    }
	
	public void destroy() {
		
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest myReq = (HttpServletRequest) request;
		//判断当前的session中是否存在已经登录成功的用户
		User user = (User)myReq.getSession().getAttribute("loginUser");
		//存在则放行
		if (null != user) {
			chain.doFilter(request, response);
		} else {
			//否则转到提示页面
			myReq.setAttribute("msg", "请您先登录后再进行操作！");
			//转发到提示页面
			myReq.getRequestDispatcher("/jsp/info.jsp").forward(request, response);
		}
		
		
		
		
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
