package cn.itcast.store.domain;

import java.util.Date;

public class User {
	private String uid;			//用户编码
	private String username;	//用户账号
	private String password;	//用户密码
	private String name;		//昵称
	private String email;		//用户邮箱
	private String telephone;	//用户电话
	private Date birthday;		//用户生日
	private String sex;			//性别
	private int state;			//用户状态1：激活 0：未激活
	private String code;		//激活码
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public User(){
		
	}
	public User(String uid, String username, String password, String name, String email, String telephone,
			Date birthday, String sex, int state, String code) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.telephone = telephone;
		this.birthday = birthday;
		this.sex = sex;
		this.state = state;
		this.code = code;
	}
	@Override
	public String toString() {
		return "User: [用户ID=：" + uid + ", 用户名=：" + username + ", 密码=：" + password + ", 姓名=:" + name + ", E_mail=："
				+ email + ", 电话=:" + telephone + ", 生日=:" + birthday + ", 性别=：" + sex + ", 用户状态=:" + state
				+ ", 激活码=：" + code + "]";
	}
	
}
