package cn.itcast.store.utils;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import cn.itcast.store.dao.UserDao;
import cn.itcast.store.domain.User;

/**
 * 工厂解耦工具类   解析XML文件
 * <p>Title: BeanFactory</p>
 * <p>Description: </p> 
 * @author	ZCL
 * @date	2019年4月4日下午8:52:44
 * @version 1.0
 */
public class BeanFactory {
	//解析XML文件
	public static Object createObject(String name){
//		通过传递过来的name获取application.xml中对应的class值
   try {
		
//		获取到Document对象
		SAXReader reader = new SAXReader();
//		  application.xml文件必须位于src下
		InputStream is = BeanFactory.class.getClassLoader().getResourceAsStream("application.xml");
		Document doc = reader.read(is);
		//通过Document对象获取根节点
		Element rootElement = doc.getRootElement(); 
		//获取子节点 bean
		List<Element> list= rootElement.elements();
		//遍历集合，判断元素的id值是否与当前的name一致
		for (Element ele : list) {
			//ele相当于每一个bean
			String id = ele.attributeValue("id");
			if (id.equals(name)) {
				String str =ele.attributeValue("class");
				//通过反射创建对象并返回
				Class clazz = Class.forName(str);
				//利用class值通过反射创建返回
				return clazz.newInstance();
			}
		}
   } catch (Exception e) {
	e.printStackTrace();
  }
		return null;
	}
	public static void main(String[] args) throws Exception {
		UserDao ud = (UserDao)BeanFactory.createObject("UserDao");
		User user = new User();
		user.setUsername("aaa");
		user.setPassword("1");
		User uu = ud.userLogin(user);
		System.out.println(uu);
	}

}
