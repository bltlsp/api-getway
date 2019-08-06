package com.yhb.filter;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

@Component
public class UserLoginZuulFilter extends ZuulFilter {

	public boolean shouldFilter() {
		 return true; // 该过滤器需要执行
	}

	public Object run() throws ZuulException {
		RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        String token = request.getParameter("token");
        if(StringUtils.isEmpty(token)){
            requestContext.setSendZuulResponse(false); // 过滤该请求，不对其进行路由
            requestContext.setResponseStatusCode(401); // 设置响应状态码
            requestContext.setResponseBody(" token is empty!!"); // 设置响应状态码
            return null;
        }
        return null;
	}

	@Override
	public String filterType() {
		 return "pre"; // 设置过滤器类型为：pre
	}

	@Override
	public int filterOrder() {
		return 0;// 设置执行顺序为0
	}

}
