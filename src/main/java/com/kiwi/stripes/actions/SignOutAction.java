package com.kiwi.stripes.actions;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

@UrlBinding("/act/signOut")
public class SignOutAction extends BasicAction
{
//	private static Logger logger = LoggerFactory.getLogger(SignOutAction.class);
	
	@Override
	protected boolean needUser(String eventName)
	{
		return false;
	}
	
	@Override
	protected boolean needUserSignIn(String eventName)
	{
		return false;
	}
	
    @DefaultHandler
    public Resolution signOut()
    {
//    	logger.debug("+signOut:SignOutAction");
    	this.setCookie("userName",null,0);
		this.setUserSession(null);
        return new RedirectResolution(IndexAction.class);
    }
}
