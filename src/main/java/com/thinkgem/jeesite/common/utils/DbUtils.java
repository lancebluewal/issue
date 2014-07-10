package com.thinkgem.jeesite.common.utils;

import com.thinkgem.jeesite.modules.BaseConstant;

public class DbUtils {
	
	public static boolean backup() {
		String user = BaseConstant.DBCONFIG.get(BaseConstant.JDBC_DB_USERNAME); // 数据库帐号
		String password = BaseConstant.DBCONFIG.get(BaseConstant.JDBC_DB_PASSWORD); // 登陆密码
		String database = BaseConstant.DBCONFIG.get(BaseConstant.JDBC_DB_NAME); // 需要备份的数据库名
		String filePath =  BaseConstant.DBCONFIG.get(BaseConstant.JDBC_DB_FILEPATH); // R备份的路径地址
		String host =  BaseConstant.DBCONFIG.get(BaseConstant.JDBC_DB_HOST);
		String mysqlBean =  BaseConstant.DBCONFIG.get(BaseConstant.JDBC_DB_MYSQL_BIN_PATH);
		try {
			String stmt1 = mysqlBean+" " + database + " -h  "+host + " -u"
					+ user + " -p" + password
					+ " --default-character-set=utf8 --result-file=" + filePath;
			Runtime.getRuntime().exec("cmd.exe /k start "+stmt1); 
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
}
