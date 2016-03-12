package com.kiwi.stripes.actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kiwi.stripes.UserSession;
import com.kiwi.stripes.bo.User;
import com.kiwi.stripes.dao.DB;
import com.kiwi.stripes.dao.UserDao;

import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.validation.Validate;
import net.sourceforge.stripes.validation.ValidationError;
import net.sourceforge.stripes.validation.ValidationErrorHandler;
import net.sourceforge.stripes.validation.ValidationErrors;

@UrlBinding("/act/signUp/{$event}")
public class SignUpAction extends BasicAction implements ValidationErrorHandler
{
//	private static Logger logger = LoggerFactory.getLogger(SignUpAction.class);
	
//	@Validate(required=true, label="手机号码", on={"chefSignUp","storeSignUp"}, mask="1.*", minlength=11, maxlength=11)
	private String userName;
	
	@Validate(required=true, label="密码", on={"signIn/submit"}, minlength=3, maxlength=12)
	private String passWord;
	
//	@Validate(required=true, label="验证码", on={"chefSignUp","storeSignUp"})
//	private String vcode;
	
	private List<String> errMsgList;
	
	public Resolution handleValidationErrors(ValidationErrors errors) throws Exception
    {
		this.errMsgList = new ArrayList<String>();
			
		for(String key : errors.keySet())
		{
			List<ValidationError> errList = errors.get(key);
			for(ValidationError err : errList)
			{
				this.errMsgList.add(err.getMessage(Locale.getDefault()));
			}
		}
		return this.reSignUp();
    }
	
	@Override
	protected boolean needUserSignIn(String eventName)
	{
		return false;
	}
	
	@Override
	protected boolean needUser(String eventName)
	{
		return false;
	}
	
    @DefaultHandler
    public Resolution signUp()
    {
        return new ForwardResolution("/WEB-INF/page/signUp.jsp");
    }
    
    @HandlesEvent("submit") 
    public Resolution signUpSubmit() throws Exception
    {
    	System.out.println("userName = "+userName);
//    	logger.debug("+signIn:SignInSubmitAction");
    	
    	errMsgList = new ArrayList<String>();
    	
//    	// 检查冲突
//		User existUser = UserDao.queryUserByName(userName);
//		if(existUser != null) {
//			return this.forwardError("对不起，输入的手机号码已经被注册使用。");
//		}
		
		// 注册用户
		User user = new User();
		user.setUserName(userName);
		user.setPassword(passWord);
		
		SqlSession session= DB.get();
    	UserDao userDao = session.getMapper(UserDao.class);
    	try{
    		User auser = userDao.getUserLogin(user);
    		if (auser != null){
//    			return this.forwardError("User Name Exists.");
    			this.errMsgList.add("用户名已经存在！");
    			return this.reSignUp();
    		}
    		
    		int cnt = userDao.addUser(user); 
    		session.commit();
    		System.out.println(cnt+"/"+user.getUserId());
    	} catch (Exception ex){
    		System.out.println(ex.getMessage());
    	} finally {
			if (session != null) {session.close();}
		}
    	
		System.out.println("user = "+user);
		// 设置成功登录后的Session和Cookie
		
		UserSession userSession = new UserSession();
		userSession.setUser(user);
		userSession.setSignedIn(true);
		this.setUserSession(userSession);
		
		this.setCookie("userName", this.userName, 3600*24*7);
		
		System.out.println("Go to Index");
       	return new RedirectResolution(IndexAction.class);
    }
    
    private Resolution reSignUp()
    {
		return new ForwardResolution("/WEB-INF/page/signUp.jsp");
    }
    
	public String getUserName()
	{
		return userName;
	}

	public void setUserName(String userName)
	{
		this.userName = userName;
	}

	public String getPassWord()
	{
		return passWord;
	}
	public void setPassWord(String passWord)
	{
		this.passWord = passWord;
	}

	public List<String> getErrMsgList()
	{
		return errMsgList;
	}

	public void setErrMsgList(List<String> errMsgList)
	{
		this.errMsgList = errMsgList;
	}

}
