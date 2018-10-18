/*
 * @(#)ComonUtils.java 2017年12月4日下午4:43:06
 * fzba
 * Copyright 2017 Thuisoft, Inc. All rights reserved.
 * THUNISOFT PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.thunisoft.masterdata.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * ComonUtils
 * @author jiangyishan
 * @version 1.0
 *
 */
public class CommonUtils {
    
    /**
     * 日志对象
     */
    private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

    /**
     * 统一返回前台json格式
     * @param result 
     * @return
     */
    public static JSONObject getJsonRes(Object result){
        JSONObject rtn = new JSONObject();
        rtn.put("code", "success");
        rtn.put("result", result);
        rtn.put("message", "请求数据成功");
        rtn.put("success", true);
        return rtn;
    }
    
    /**
     * 默认url解码，做异常处理
     * @param nr 内容
     * @return
     */
    public static String getUrlDecode(String nr){
        try {
            return URLDecoder.decode(nr, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            logger.error("字符集转码失败，原字符串内容为{}",nr,e);
        }
        return null;
    }
    
    /**
     * 格式化日期，做判空处理
     * @param date 日期
     * @param pattern 格式
     * @return
     */
    public static String dateFormat(Date date,String pattern){
        if(date != null){
            return DateFormatUtils.format(date, pattern);
        }
        return null;
    }
    
    /**
     * 默认格式化日期
     * @param date 日期
     * @return
     */
    public static String dateFormat(Date date){
        return dateFormat(date, "yyyy-MM-dd");
    }
    
    /**
     * 字符串转字符串数组
     * @param arrStr 数组字符串
     * @return
     */
    public static String[] sqlStr2Arr(String arrStr){
        if(StringUtils.isBlank(arrStr)||arrStr.length()<=2){
            return new String[]{};
        }
        List<String> arrList = new ArrayList<String>();
        StringBuffer segString = new StringBuffer();
        char[] chars = arrStr.toCharArray();
        for(int i=1;i<chars.length-1;i++){
            if(chars[i]=='"'){
                for(int j=i+1;j<chars.length-1;j++){
                    if(chars[j]=='\\'&&(chars[j+1]=='"'||chars[j+1]=='}'||chars[j+1]=='{')){
                        continue;
                    }
                    if(chars[j-1]!='\\'&&chars[j]=='"'){
                        arrList.add(segString.toString());
                        segString = new StringBuffer();
                        i=j+1;
                        break;
                    }
                    segString.append(chars[j]);
                }
            }else{
                for(int j=i;j<chars.length;j++){
                    if(chars[j]==','||chars[j]=='}'){
                        arrList.add(segString.toString());
                        segString = new StringBuffer();
                        i=j;
                        break;
                    }
                    segString.append(chars[j]);
                }
            }
        }
        return arrList.toArray(new String[]{});
    }
    
    /**
     * 字符串转jsonarray
     * @param arrStr 数组字符串
     * @return
     */
    public static JSONArray sqlStr2JA(String arrStr){
        String[] arrs = sqlStr2Arr(arrStr);
        JSONArray ja = new JSONArray();
        for(String arr:arrs){
            if(!"NULL".equals(arr)){
                ja.add(arr);
            }
        }
        return ja;
    }
    
//    /**
//     * 字符串转jsonarray
//     * @param arrStr 数组字符串
//     * @param codeType 代码类型
//     * @return
//     */
//    public static JSONArray sqlStr2JA(String arrStr, String codeType){
//        String[] arrs = sqlStr2Arr(arrStr);
//        JSONArray ja = new JSONArray();
//        for(String arr:arrs){
//            arr = ArteryNormalCodeUtil.getSubCodeName(codeType, arr);
//            if(!"NULL".equals(arr)){
//                ja.add(arr);
//            }
//        }
//        return ja;
//    }
    
//    /**
//     * 反向查询单值代码值
//     * @param type
//     * @param name
//     * @return
//     */
//    public static String getNCodeByName(String type, String name){
//        List<INormalCode> subCodes = ArteryNormalCodeUtil.getSubCodeList(type);
//        for(INormalCode subCode:subCodes){
//            if(StringUtils.equals(subCode.getName(), name)){
//                return subCode.getCode();
//            }
//        }
//        return null;
//    }
    
//    /**
//     * 在map中转化单值代码
//     * @param result
//     * @param key
//     * @param type
//     */
//    public static void transNCode(Map<String,Object> result, String key, String type){
//        result.put(key, CommonUtils.getNCodeByName(type, (String)result.get(key)));
//    }
    
    
    /**
     * 转化成int
     * @param str 
     * @return
     */
    public static Integer parseInt(String str){
        Integer i = null;
        try {
            i = Integer.parseInt(str);
        } catch (Exception e) {
            logger.info("转化成数值异常，{}",str);
        }
        return i;
    }
    

    /**
     * 装配sql
     * @param sql 
     * @param objects 
     * @return
     */
    public static String appendIn(String sql, List<?> objects){
        StringBuffer sb = new StringBuffer(sql);
        for(int index=0; index<objects.size(); index++){
            if(index==0){
                sb.append("(");
            }
            sb.append("?");
            if(index != objects.size()-1){
                sb.append(",");
            }else{
                sb.append(")");
            }
        }
        return sb.toString();
    }
    
    
//    /**
//     * 字符串与单值代码中的模糊匹配
//     * @param str
//     * @param mainCode
//     * @return
//     */
//    public static String containsAny(String str, String mainCode){
//        List<INormalCode> subCodes = ArteryNormalCodeUtil.getSubCodeList(mainCode);
//        for(INormalCode subCode:subCodes){
//            if(StringUtils.contains(str, subCode.getName())){
//                return subCode.getCode();
//            }
//        }
//        return null;
//    }
    
    
    /**
     * 判断字符串列表里是否包含
     * @param strs 
     * @param search 
     * @return
     */
    public static boolean contains(List<String> strs, String search){
        if(strs!=null&&strs.size()!=0){
            for(String str:strs){
                if(StringUtils.equals(str, search)){
                    return true;
                }
            }
        }
        return false;
    }
    
//    /**
//     * 由id获取人名
//     * @param id
//     * @return
//     */
//    public static String getNameById(String id){
//        IUser user = ArteryOrganUtil.getUserById(id);
//        return user == null ? "" : user.getName();
//    }
    /**
     * 将数字转为汉字cgd
     * 
     * @param num
     * @return
     */
	public static String chinaNum(String num){
		String[] china = new String[]{"零","一","二","三","四","五","六","七","八","九"};
		String arr="";
		for(int i=0;i<num.length();i++){
			
		}
		char[] english = num.toCharArray();
		for(int i=0;i<english.length;i++){
			Integer nbr=Integer.parseInt(String.valueOf(english[i]));
			if((english.length==2&&nbr==1)||(nbr==0&&(i+1==english.length||String.valueOf(english[i+1]).equals("0")))){
			}else{
				arr += china[nbr];
			}
			if(nbr!=0){
				if(english.length>=2&&english.length-i==2){
					arr +="十";
				}else if(english.length>=3&&english.length-i==3){
					arr +="百";
				}else if(english.length>=4&&english.length-i==4){
					arr +="千";
				}else if(english.length>=5&&english.length-i==5){
					arr +="万";
				}
			}
		}
		return arr;
	}
}
