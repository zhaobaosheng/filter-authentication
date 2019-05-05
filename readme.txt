简单调用服务安全实现

spring boot服务器端配置
1依赖
<dependency>
       <groupId>com.zdawn</groupId>
       <artifactId>filter-authentication</artifactId>
       <version>0.1.0</version>
   </dependency>
2入口类配置
 @ComponentScan({"com.zdawn.web.sec"})
 @PropertySource(value={"websec.properties"})
 
 @Bean
	public FilterRegistrationBean getSimpleAuthenticationFilter() {  
		FilterRegistrationBean authFilter = new FilterRegistrationBean();
		authFilter.addUrlPatterns("*.do");//添加过滤url
		authFilter.setFilter(new SimpleAuthenticationFilter());
		return authFilter;
    }
3配置属性文件
 websec.authUser=用户名
 websec.authPassword=密码
 websec.trustClientIps=信任客户端ip地址 多个使用逗号分隔

客户端调用服务时设置如下http header
userName=xxx
password=xxx