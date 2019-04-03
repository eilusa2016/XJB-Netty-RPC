package com.test.netty.rpc.api.util;

import java.io.Serializable;
/**
 * ��Ϣ��װ��
 * @author karl.xu
 *
 */
public class InvokeMsg implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * ���õ���
	 */
	private String className;
	/**
	 * ����
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
