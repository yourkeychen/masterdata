package com.thunisoft.masterdata.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;

@Component
public class PropertiesUtil {
	private static Logger log =LoggerFactory.getLogger(PropertiesUtil.class);
	
	public final static String DEFAULT_PATH = "props" + File.separator + "";
	private static HashMap<String, String> propertiesMap = new HashMap<String, String>();
	private static Properties temp;

	@PostConstruct
	public static void init() {
		//ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		URL classLoaderResource = PropertiesUtil.class.getResource("/");
		String classPath = classLoaderResource.getPath();
		String pathname = classPath + DEFAULT_PATH;
		File file = new File(pathname);
		dealFileOrFloder(file);
		System.out.println("读取配置文件完成");
	}

	private static void dealFileOrFloder(File file) {
		if (file != null&&file.exists()) {
			if (file.isDirectory()) {
				File[] listFiles = file.listFiles();
				for (File file2 : listFiles) {
					dealFileOrFloder(file2);
				}
			} else {
				temp = new Properties();
				try {
					temp.load(new FileInputStream(file));
				} catch (FileNotFoundException e) {
					log.error("配置文件{}不存在,请检查配置",file.getPath());
					e.printStackTrace();
				} catch (IOException e) {
					log.error("读取配置文件IO异常");
					e.printStackTrace();
				}
				loadingProperties(temp);
			}
			System.out.println("end");
		}else{
			log.warn("配置文件根目录{}不存在,请检查配置",file.getPath());
			System.out.println("end");
		}
	}

	private static void loadingProperties(Properties p) {
		if (p != null) {
			for (Entry<Object, Object> e : p.entrySet()) {
				propertiesMap.put((String) e.getKey(), (String) e.getValue());
			}
		}
	}

	/**获取属性，转化为Integer 类型数据 ,属性值不存在返回null
	 * @param key
	 * @return
	 */
	public static String getProperty(String key) {
		return propertiesMap.get(key);
	}

	/***获取属性，转化为Integer 类型数据 ,属性值不存在返回默认值
	 * @param key
	 * @param defultValue
	 * @return
	 */
	public static String getProperty(String key, String defultValue) {
		return getProperty(key) == null ? defultValue : getProperty(key);
	}

	/**获取属性，转化为Integer 类型数据 ,异常返回默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Integer getIntergetProperty(String key, Integer defaultValue) {
		Integer result = defaultValue;
		try {
			result = Integer.valueOf(Integer.parseInt(getProperty(key)));
		} catch (NumberFormatException e) {
		}
		return result;
	}

	/**获取属性，转化为Integer 类型数据 ,异常返回null
	 * @param key
	 * @return
	 */
	public static Integer getIntergetProperty(String key) {
		return getIntergetProperty(key, null);
	}
	/**获取属性，转化为Boolean 类型数据 ,异常返回默认值
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static Boolean getBooleanProperty(String key, Boolean defaultValue) {
		Boolean result = defaultValue;
		try {
			result = Boolean.valueOf(Boolean.parseBoolean(getProperty(key)));
		} catch (Exception e) {
		}
		return result;
	}

	/**获取属性，转化为Boolean 类型数据 ,异常返回false
	 * @param key
	 * @return
	 */
	public static Boolean getBooleanProperty(String key) {
		return getBooleanProperty(key, false);
	}
	public static Boolean put(String key,String value) {
		propertiesMap.put(key, value);
		return true;
	}
	
}
