package com.test.netty.rpc.api.util;

import java.io.Serializable;
/**
 * 消息包装类
 * @author karl.xu
 *
 */
public class InvokeMsg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 调用的类
	 */
	private String className;
	/**
	 * 方法
	 */
	private String methodName;
	
	private Class<?>[] paramTypes;
	private Object[] values;
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	public Class<?>[] getParamTypes() {
		return paramTypes;
	}
	public void setParamTypes(Class<?>[] paramTypes) {
		this.paramTypes = paramTypes;
	}
	public Object[] getValues() {
		return values;
	}
	public void setValues(Object[] values) {
		this.values = values;
	}

}
