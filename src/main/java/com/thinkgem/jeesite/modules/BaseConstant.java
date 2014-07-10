package com.thinkgem.jeesite.modules;

import java.util.HashMap;
import java.util.Map;

public class BaseConstant {
	public static Map<String, String> DBCONFIG = new HashMap<String, String>();
	public static String JDBC_DB_USERNAME = "jdbc.db.username"; // 数据库帐号
	public static String JDBC_DB_PASSWORD = "jdbc.db.password"; // 登陆密码
	public static String JDBC_DB_NAME = "jdbc.db.name"; // 需要备份的数据库名
	public static String JDBC_DB_FILEPATH = "jdbc.db.filepath"; // 备份的路径地址
	public static String JDBC_DB_HOST = "jdbc.db.host";                 //host地址
	public static String JDBC_DB_MYSQL_BIN_PATH = "jdbc.db.mysql.bin.path";   //mysql路劲
	public static String JDBC_DB_CHARCTER = "jdbc.db.charcter";  //数据库字符集
	
	
	public static Map<String, String[]> language = new HashMap<String, String[]>();
	static{
		language.put("en_US", new String[]{"en","US"});
		language.put("zh_CN", new String[]{"zh","CN"});
	}
	
}
