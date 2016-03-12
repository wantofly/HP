package com.kiwi.stripes;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

//import com.easedone.jbiandangent.bo.User;

@Intercepts({LifecycleStage.HandlerResolution})
public class SecurityInterceptor implements Interceptor 
{
	/** Intercepts execution and checks that the user has appropriate permissions. */
	public Resolution intercept(ExecutionContext ctx) throws Exception 
	{
		Resolution resolution = ctx.proceed();
		
		if (isPermitted(ctx.getActionBean(), ctx.getActionBeanContext())) {
			return resolution;
		}else if (isLoggedIn(ctx.getActionBeanContext())) {
			return new RedirectResolution("/views/secure/Unauthorized.jsp");
		}else {
			return new RedirectResolution("/act/user/login");
		}
	}
	
	/** Returns true if the user is logged in. */
	protected boolean isLoggedIn(ActionBeanContext ctx) {
//		return ((MyActionBeanContext) ctx).getUser() != null;
		return true;
	}
	/** Returns true if the user is permitted to invoke the event requested. */
	protected boolean isPermitted(ActionBean abean, ActionBeanContext ctx) 
	{
		String uri1 = ctx.getRequest().getRequestURI(); 
		String path2 = ctx.getRequest().getContextPath();
		String contextPath = ctx.getRequest().getContextPath();
		System.out.println("event:"+ctx.getEventName());
		System.out.println("uri:"+uri1);
		System.out.println("url:"+path2);
		System.out.println("contextpath:"+ctx.getRequest().getContextPath());
		System.out.println("----------");
		String needuser = (String)ctx.getRequest().getAttribute("needuser");
		String needroot = (String)ctx.getRequest().getAttribute("needroot");
		String needloginagain = (String)ctx.getRequest().getAttribute("needloginagain");
		//TODO: action
		return true;
//		if (!uri1.endsWith("/"))
//			uri1 = uri1+ "/";
//		if (!(ctx.getEventName().equals("adduser")) 
//				&&(
//				uri1.equals(path2+"/act/user/login/")
//				|| uri1.equals(path2+"/act/user/")
//				|| uri1.startsWith(path2+"/m/")
//				)){
//			return true;
//		}else{
//			User auser = ((MyActionBeanContext)ctx).getUser();
//			if (auser == null){
//				return false;
//			}else{
//				uri1 = uri1.substring(contextPath.length());
//				if (uri1.charAt(uri1.length()-1) != '/'){
//					uri1 = uri1 + "/";
//				}
//				uri1 = uri1+ctx.getEventName()+"/";
//				if (uri1.endsWith("/index/")){
//					uri1 = uri1.substring(0,uri1.length()-6);
//				}
//				if (uri1.endsWith("/index/")){
//					uri1 = uri1.substring(0,uri1.length()-6);
//				}
//				System.out.println("uri1:"+uri1);
//				if (auser.hasRights(uri1)){
//					return true;
//				}
//				return false;
//			}
//		}
	}
	

}
