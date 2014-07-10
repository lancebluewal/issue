package com.thinkgem.jeesite;

import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.modules.BaseConstant;

/**
 * Servlet implementation class InitServlet
 */
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public InitServlet() {
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doPost(request, response);
	}
	
	@Override
	public void init() throws ServletException {
		Properties pt = new Properties();
		try {
			pt.load(InitServlet.class.getClassLoader().getResourceAsStream("jeesite.properties"));
			BaseConstant.DBCONFIG.put(BaseConstant.JDBC_DB_HOST, String.valueOf(pt.get(BaseConstant.JDBC_DB_HOST)));
			BaseConstant.DBCONFIG.put(BaseConstant.JDBC_DB_USERNAME, String.valueOf(pt.get(BaseConstant.JDBC_DB_USERNAME)));
			BaseConstant.DBCONFIG.put(BaseConstant.JDBC_DB_PASSWORD, String.valueOf(pt.get(BaseConstant.JDBC_DB_PASSWORD)));
			BaseConstant.DBCONFIG.put(BaseConstant.JDBC_DB_NAME, String.valueOf(pt.get(BaseConstant.JDBC_DB_NAME)));
			BaseConstant.DBCONFIG.put(BaseConstant.JDBC_DB_FILEPATH, String.valueOf(pt.get(BaseConstant.JDBC_DB_FILEPATH)));
			BaseConstant.DBCONFIG.put(BaseConstant.JDBC_DB_MYSQL_BIN_PATH, String.valueOf(pt.get(BaseConstant.JDBC_DB_MYSQL_BIN_PATH)));
			BaseConstant.DBCONFIG.put(BaseConstant.JDBC_DB_CHARCTER, String.valueOf(pt.get(BaseConstant.JDBC_DB_CHARCTER)));
		} catch (IOException e) {
			System.exit(0);
		}
		super.init();
	}

}
