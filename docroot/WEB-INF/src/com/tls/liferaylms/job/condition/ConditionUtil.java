package com.tls.liferaylms.job.condition;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.tls.liferaylms.job.ProcessMailJob;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.mail.service.MailJobLocalServiceUtil;

public class ConditionUtil {
	private static Log log = LogFactoryUtil.getLog(ConditionUtil.class);
	
	private final static String packageName = "com/tls/liferaylms/job/condition";
	private final static String packageClass = "com.tls.liferaylms.job.condition.";

	public static Set<Condition> getAllConditions(){ 
        URL pack = Thread.currentThread().getContextClassLoader().getResource(packageName);
        Set<Condition> classes = new HashSet<Condition>();
        if (pack == null) {
            return classes; 
        }
        
        File scannedDir = new File(pack.getFile());
        for (File file : scannedDir.listFiles()) {
        	
        	String name = file.getName().replaceAll("\\.class", StringPool.BLANK);
        	if(!(name.length()>8)||!name.substring(name.length()-8, name.length()).equals("ondition")){
        		continue;
        	}
            try {
				Class c = Class.forName(packageClass+name);
				Constructor[] cons = c.getConstructors();
				
				if(cons!=null&&cons.length>0){
					classes.add((Condition)cons[0].newInstance(new Object[] { name }));
				}
			} catch (ClassNotFoundException e) {
			} catch (IllegalAccessException e){
			} catch (InstantiationException e){
			} catch (InvocationTargetException e){
			}
        }
        
        return classes;
	}

	public static Condition instance(String name, MailJob mailJob) throws ClassNotFoundException {
		Class c = Class.forName(packageClass+name);
		Constructor[] cons = c.getConstructors();

		if(cons!=null&&cons.length>0){
			Condition condition;
			try {
				condition = (Condition)cons[0].newInstance(new Object[] { name });
				condition.setMailJob(mailJob);
				return condition;
			} catch (IllegalArgumentException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			} catch (InstantiationException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			} catch (IllegalAccessException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			} catch (InvocationTargetException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			}
		}
		return null;
	}
	
	public static Condition instance(String name, Long id) throws ClassNotFoundException {
		Class c = Class.forName(packageClass+name);
		Constructor[] cons = c.getConstructors();
		
		if(cons!=null&&cons.length>0){
			try {
				Condition condition = (Condition)cons[0].newInstance(new Object[] { name });
				MailJob mailJob = null;
				try {
					mailJob = MailJobLocalServiceUtil.getMailJob(id);
				} catch (PortalException e) {
					if(log.isDebugEnabled())e.printStackTrace();
					if(log.isErrorEnabled())log.error(e.getMessage());
				} catch (SystemException e) {
					if(log.isDebugEnabled())e.printStackTrace();
					if(log.isErrorEnabled())log.error(e.getMessage());
				}
				condition.setMailJob(mailJob);
				return condition;
			} catch (IllegalArgumentException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			} catch (InstantiationException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			} catch (IllegalAccessException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			} catch (InvocationTargetException e) {
				if(log.isDebugEnabled())e.printStackTrace();
				if(log.isErrorEnabled())log.error(e.getMessage());
			}
			
		}
		
		return null;
	}
}
