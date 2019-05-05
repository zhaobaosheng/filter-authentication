package com.zdawn.web.sec;
/**
 * 简单认证类
 * @author zhaobs
 *
 */
public interface ServiceAuthentication {
	public boolean trustIp(String ip);
	public boolean authenticate(String userName,String password);
}
