package cn.itcast.store.service.serviceImpl;

import java.sql.SQLException;

import javax.management.RuntimeErrorException;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.dao.MySQLdaoImpl.MySQLUserDaoImpl;
import cn.itcast.store.domain.User;
import cn.itcast.store.service.UserService;
import cn.itcast.store.utils.BeanFactory;
import cn.itcast.store.utils.MailUtils;
/**
 * 用户服务实现类
 * <p>Title: UserServiceImpl</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月1日下午5:15:51
 * @version 1.0
 */
public class UserServiceImpl implements UserService {
	
	UserDao userDao = (UserDao)BeanFactory.createObject("UserDao");
/**
 * 实现注册功能
 * <p>Title: userRegist</p>
 * <p>Description: </p>
 * @param user
 * @throws SQLException
 * @see cn.itcast.store.service.UserService#userRegist(cn.itcast.store.domain.User)
 */
	@Override
	public void userRegist(User user) throws SQLException {
		// TODO Auto-generated method stub
		//实现注册功能
		
		userDao.userRegist(user);
	}
	/**
	 * 实现激活功能
	 * <p>Title: userActive</p>
	 * <p>Description: </p>
	 * @param code
	 * @return
	 * @throws SQLException
	 * @see cn.itcast.store.service.UserService#userActive(java.lang.String)
	 */
		@Override
		public boolean userActive(String code) throws SQLException {
			//对DB发送select * from user where code=?
			User user = userDao.userActive(code);
			if (null!=user) {
				//可根据激活码查询到用户
				//更改用户状态，清除激活码
				user.setState(1);
				user.setCode(null);
				//对数据库执行一次更新操作
				//update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?
				userDao.updateUser(user);
				return true;
			} else {
				//不可根据激活码查询到用户
				return false;
			}
		}
/**
 * 实现用户登录
 *使用了自定义异常
 * <p>Title: userLogin</p>
 * <p>Description: </p>
 * @param user
 * @return
 * @throws SQLException
 * @see cn.itcast.store.service.UserService#userLogin(cn.itcast.store.domain.User)
 */
@Override
public User userLogin(User user) throws SQLException {
	/*---- 此处：可以利用异常在模块之间传递数据 ----*/
	//实际发送的是 select * from user where username=? and password=?
	User uu = userDao.userLogin(user);
	if (null == uu) {
		throw new RuntimeException("您输入的密码有误，请重新输入！");
	} else if(uu.getState()==0){
		throw new RuntimeException("抱歉，您的账号未激活！");
	}else{
		return uu;
	}
	}
/**
 * 
 * <p>Title: findUserByUsreName</p>
 * <p>Description: </p>
 * @param um
 * @return
 * @throws SQLException
 * @see cn.itcast.store.service.UserService#findUserByUsreName(java.lang.String)
 */
//	@Override
//	public User findUserByUsreName(String um) throws SQLException {
//		// TODO Auto-generated method stub
//		return null;
//	}

/**
 * 
 * <p>Title: updateUser</p>
 * <p>Description: </p>
 * @param user
 * @throws SQLException
 * @see cn.itcast.store.service.UserService#updateUser(cn.itcast.store.domain.User)
 */
//	@Override
//	public void updateUser(User user) throws SQLException {
//		// TODO Auto-generated method stub
//		
//	}

	
}
