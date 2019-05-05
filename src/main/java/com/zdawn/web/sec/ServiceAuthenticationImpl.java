package com.zdawn.web.sec;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service(value="serviceAuthentication")
public class ServiceAuthenticationImpl implements ServiceAuthentication {
	@Value("${websec.authUser}")
	private String authUser ="";
	@Value("${websec.authPassword}")
	private String authPassword ="";
	@Value("${websec.trustClientIps}")
	//多个使用逗号分隔
	private String trustClientIps;
	
	private List<String> listIps;
	
	@PostConstruct
	public void init(){
		if(trustClientIps!=null && !trustClientIps.equals("")){
			listIps = new ArrayList<String>();
			String[] temp=trustClientIps.split(",");
			for (int i = 0; i < temp.length; i++) {
				if(temp[i]!=null && !temp[i].equals("")) listIps.add(temp[i]);
			}
		}
	}
	
	@Override
	public boolean trustIp(String ip) {
		if(ip==null || ip.equals("")) return false;
		if(listIps==null) return false;
		return listIps.contains(ip);
	}


	@Override
	public boolean authenticate(String userName, String password) {
		if(userName==null || password==null) return false;
		return authUser.equals(userName) && authPassword.equals(password);
	}

	public void setAuthUser(String authUser) {
		this.authUser = authUser;
	}

	public void setAuthPassword(String authPassword) {
		this.authPassword = authPassword;
	}

	public void setTrustClientIps(String trustClientIps) {
		this.trustClientIps = trustClientIps;
	}
	
}
