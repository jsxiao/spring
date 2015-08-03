/**
 * 
 */
package org.xiaojs.validator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

/**
 * @author xiaojs
 */
public abstract class BasValidator {

	public static String ERROR = "error";

	/**
	 * errors
	 */
	private Map<String, String> errors = new HashMap<String, String>();

	/**
	 * 累加错误
	 * @param binder
	 */
	private void initError(Errors binder){
		binder.reject(ERROR) ;
	}
	
	/**
	 * 获取错误信息
	 * @return errors
	 */
	public Map<String, String> getErrors() {
		return this.errors;
	}
	
	/**
	 * 基于validator验证
	 * @param key
	 * @param message
	 * @param binder
	 */
	public void addError(String key,String message,Errors binder){
		this.initError(binder) ;
		this.errors.put(key, message) ;
	}
	
	/**
	 * 自行验证
	 * @param key
	 * @param message
	 */
	public void addError(String key,String message){
		this.errors.put(key, message) ;
	}
	
	
	public boolean hasErrors(){
		return this.errors.size() > 0 ;
	}
	
	/**
	 * 转换map显示错误信息
	 * @param objectErrors 系统错误集合
	 * @return errors map
	 */
	public static Map<String, String> convertErrors(List<ObjectError> objectErrors){
		Map<String, String> errors = new HashMap<String, String>() ;
		for (ObjectError error : objectErrors) {
			errors.put(error.getObjectName(), error.getDefaultMessage()) ;
		}
		return errors;
	}

}
