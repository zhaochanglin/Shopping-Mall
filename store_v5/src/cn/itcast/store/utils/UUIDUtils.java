package cn.itcast.store.utils;

import java.util.UUID;

public class UUIDUtils {
	/**
	 * 随机生成id
	 * @return
	 */
	public static String getId(){
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}
	
	
	public static String getUUID64(){
		return getId()+getId();
	}
	
	/**
	 * 生成随机码
	 * @return
	 */
	public static String getCode(){
		return getId();
	}
	
	public static void main(String[] args) {
		System.out.println(getId());
//		System.out.println(System.currentTimeMillis()
//				);
//	for (int i = 0; i < 50; i++) {
//		
//		String str = UUID.randomUUID().toString();
//		System.out.println(str);
//	}
	}
}
