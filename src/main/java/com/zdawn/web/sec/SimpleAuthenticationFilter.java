package com.zdawn.web.sec;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 从Header中获取用户名、密码校验是否合法
 */
public class SimpleAuthenticationFilter implements Filter {
	private ServiceAuthentication auth;
    
	public SimpleAuthenticationFilter() {
    }
	
	public void init(FilterConfig fConfig) throws ServletException {
		if(SpringContextHolder.getContext()!=null){
			auth = (ServiceAuthentication)SpringContextHolder.getBean("serviceAuthentication");
		}
		if(auth==null) throw new ServletException("ServiceAuthentication init failture");
	}
	
	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest)request;
		boolean success = false;
		String desc = "";
		String ip = req.getRemoteAddr();
		success = auth.trustIp(ip);
		if(!success){
			String userName = req.getHeader("userName");
			String password = req.getHeader("password");
			success = auth.authenticate(userName, password);
		}
		if(!success) desc = "认证失败";
		if(!success){
			HttpServletResponse resp = (HttpServletResponse)response;
			resp.setContentType("text/html;charset=UTF-8");
		    PrintWriter out  = resp.getWriter();
		    StringBuilder sb = new StringBuilder();
		    sb.append('{');
		    sb.append("\"version\":\"1.0.0\"").append(',');
		    sb.append("\"state\":\"0\"").append(',');
		    sb.append("\"errorCode\":\"500\"").append(',');
		    sb.append("\"errorInfo\":\"").append(desc).append('"');
		    sb.append('}');
		    out.write(sb.toString());
		    out.flush();
		    out.close();
			return ;
		}
		chain.doFilter(request, response);
	}
}
