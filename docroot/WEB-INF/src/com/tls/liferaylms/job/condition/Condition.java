package com.tls.liferaylms.job.condition;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import com.liferay.portal.model.User;
import com.tls.liferaylms.mail.model.MailJob;

public interface Condition {
	public String getName();
	
	public String getName(Locale locale);
	
	public String getClassName();
	
	public void setClassName(String className);
	
	public MailJob getMailJob();
	
	public void setMailJob(MailJob id);
	
	public Set<User> getUsersToSend();
	
	public boolean shouldBeProcessed();
	
	public String getConditionName();
	
	public String getConditionName(Locale locale);
	
	public String getReferenceName();
	
	public String getReferenceName(Locale locale);
	
	public Long getActReferencePK();

	public Long getActConditionPK();

	public Long getModReferencePK();

	public Long getModConditionPK();

	public Date getDate();

	public String getFormatDate();
}
