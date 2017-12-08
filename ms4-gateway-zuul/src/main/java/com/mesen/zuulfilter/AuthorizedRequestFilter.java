package com.mesen.zuulfilter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

import java.nio.charset.Charset;
import java.util.Base64;

/**
 * zuul的过滤器，和其他过滤器一样，起到对请求进行过滤的作用。
 *
 */
public class AuthorizedRequestFilter extends ZuulFilter {

	/**
	 * 表示具体的过滤执行操作。
	 * 在此处我们对请求进行授权操作。
	 *
	 * @return
	 */
	@Override
	public Object run() {
		/**
		 * 1.获取当前请求的上下文。
		 * 2.对密码进行加密处理。
		 * 3.授权给zuul的请求。
		 */
		//1.
		RequestContext currentContext = RequestContext.getCurrentContext() ;
		//2.
		String auth = "admin:admin123";
		byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
		// 在进行授权的头信息内容配置的时候加密的信息一定要与“Basic”之间有一个空格
		String authHeader = "Basic " + new String(encodedAuth);
		//3.
		currentContext.addZuulRequestHeader("Authorization", authHeader);

		return null;
	}

	/**
	 * 该Filter是否要执行。
	 *
	 * @return
	 */
	@Override
	public boolean shouldFilter() {
		return true ;
	}

	/**
	 * 设置优先级，数字越大优先级越低。
	 *
	 * @return
	 */
	@Override
	public int filterOrder() {
		return 0;
	}

	/**
	 * 在进行Zuul过滤的时候可以设置其过滤执行的位置，那么此时有如下几种类型（也是此过滤器的四个生命周期）：
	 *		pre：在请求发出之前执行过滤，如果要进行访问，肯定在请求前设置头信息
	 *		route：在进行路由请求的时候被调用；
	 *		post：在路由之后发送请求信息的时候被调用；
	 *		error：出现错误之后进行调用
	 *
	 * @return
	 */
	@Override
	public String filterType() {
		return "pre";
	}

}
