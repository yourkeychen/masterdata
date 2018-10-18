package com.thunisoft.masterdata.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class JurisdictionUtil {
	private static Properties properties =new Properties();
	
	/**
	 * @param path
	 * @return
	 */
	public static boolean init(String path) {
		FileInputStream fio = null;
		try {
			fio = new FileInputStream(new File(path));
			properties.load(fio);
		} catch (FileNotFoundException e) {
			System.out.println("配置文件不存在!");
			e.printStackTrace();
			return false;
		} catch (IOException e) {
			System.out.println("配置文件读取失败!");
			e.printStackTrace();
			return false;
		}finally{
			try {
				if (fio!=null) {
					fio.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return true;
	}
	
	
	/**
	 * @param paramName 
	 * @return
	 */
	public static String get(String paramName) {
		return PropertiesUtil.getProperty(paramName);
	}
	
	
	
	
	
}
