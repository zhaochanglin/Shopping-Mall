package cn.itcast.store.dao.MySQLdaoImpl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domain.User;
import cn.itcast.store.utils.JDBCUtils;
/**
 * 用户Dao实现类
 * <p>Title: UserDaoImpl</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年2月26日下午5:38:34
 * @version 1.0
 */
public class MySQLUserDaoImpl implements UserDao {
/**
 * 实现注册功能
 * <p>Title: userRegist</p>
 * <p>Description: </p>
 * @param user
 * @throws SQLException
 * @see cn.itcast.store.dao.UserDao#userRegist(cn.itcast.store.domain.User)
 */
	@Override
	public void userRegist(User user) throws SQLException {
		String sql = "INSERT INTO USER VALUES(?,?,?,?,?,?,?,?,?,?)";
		QueryRunner qr= new QueryRunner(JDBCUtils.getDataSource());
		Object[] params={user.getUid(),user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode()};
		qr.update(sql,params);
			
	}
/**
 * 实现激活功能
 * <p>Title: userActive</p>
 * <p>Description: </p>
 * @param code
 * @return
 * @throws SQLException 
 * @see cn.itcast.store.dao.UserDao#userActive(java.lang.String)
 */
@Override
public User userActive(String code) throws SQLException {
		String sql ="select * from user where code=?";
		QueryRunner qr= new QueryRunner(JDBCUtils.getDataSource());
		User user = qr.query(sql, new BeanHandler<User>(User.class),code);
		return user;
	}
/**全局更改用户数据方法
 * 实现用户状态更新;
 * 根据uid更改所有数据，覆盖一次
 * <p>Title: updateUser</p>
 * <p>Description: </p>
 * @param user
 * @see cn.itcast.store.dao.UserDao#updateUser(cn.itcast.store.domain.User)
 */
@Override
public void updateUser(User user) throws SQLException{
	String sql ="update user set username=?,password=?,name=?,email=?,telephone=?,birthday=?,sex=?,state=?,code=? where uid=?";
	QueryRunner qr= new QueryRunner(JDBCUtils.getDataSource());
	Object[] params={user.getUsername(),user.getPassword(),user.getName(),user.getEmail(),user.getTelephone(),user.getBirthday(),user.getSex(),user.getState(),user.getCode(),user.getUid()};
	qr.update(sql,params);
	
}

	@Override
	public User userLogin(User user) throws SQLException{
		String sql="select * from user where username=? and password=?";
		QueryRunner qr= new QueryRunner(JDBCUtils.getDataSource());
		return qr.query(sql, new BeanHandler<User>(User.class),user.getUsername(),user.getPassword());
		
	}

//	@Override
//	public User findUserByUsreName(String um) throws SQLException{
//		// TODO Auto-generated method stub
//		return null;
//	}

	


}
