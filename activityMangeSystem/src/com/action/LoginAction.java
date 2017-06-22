package com.action;

import hib.com.HibernateSessionFactory;


import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.bean.User;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LoginAction extends ActionSupport implements ModelDriven<User>{

private User user=new User();
public String login(){
	String id=user.getUserid();
	String password=user.getUserpassword();
	Session session = HibernateSessionFactory.getSession();  
	Transaction tx = session.beginTransaction(); 



	boolean has = false;
	try {
		    List catlist = null;
	    catlist =session.createQuery("from User").list();
	         if (catlist != null) {
	        Iterator it= catlist.iterator();
	       
	        while (it.hasNext()) {
	            User user =(User) it.next(); 
	            if(user.getUserid().equals(id) && user.getUserpassword().equals(password)) { 
	                has = true;
	                ServletActionContext.getRequest().getSession().setAttribute("sid", id);
	                break;
	            }
	            else 
	            has= false;
	            
	        }
	    }
	    tx.commit();
	    session.clear();
	}
	catch (Exception e) {   
		tx.rollback();  
	    e.printStackTrace();  
	}finally{  
	    session.close();  
	}  
	if (has)
	    return SUCCESS;
	else{
	
		return ERROR;
	    
	}
}


@Override
public User getModel() {
	// TODO Auto-generated method stub
	return user;
}


	
	
}
