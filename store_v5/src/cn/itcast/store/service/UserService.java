package cn.itcast.store.service;

import java.sql.SQLException;

import cn.itcast.store.domain.User;
/**
 * 用户服务接口
 * <p>Title: UserService</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年3月1日下午5:15:21
 * @version 1.0
 */
public interface UserService {

	

//	User findUserByUsreName(String um)throws SQLException;

	void userRegist(User user)throws SQLException;

	boolean userActive(String code)throws SQLException;

	User userLogin(User user)throws SQLException;
	
//	void updateUser(User user)throws SQLException;


}
