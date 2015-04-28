package com.tls.liferaylms.job.condition;

import java.util.Locale;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.kernel.util.LocaleUtil;
import com.tls.liferaylms.mail.model.MailJob;
import com.tls.liferaylms.util.MailStringPool;

public abstract class MainCondition implements Condition{
	private String className;
	private MailJob mailJob;
	
	public MainCondition(String className) {
		super();
		this.className = className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	@Override
	public String getName() {
		return LanguageUtil.get(LocaleUtil.getDefault(),MailStringPool.GROUPMAILING+className);
	}

	@Override
	public String getName(Locale locale) {
		return LanguageUtil.get(LocaleUtil.getDefault(),MailStringPool.GROUPMAILING+className);
	}

	@Override
	public String getClassName() {
		return className;
	}

	@Override
	public MailJob getMailJob() {
		return mailJob;
	}

	@Override
	public void setMailJob(MailJob mailJob) {
		this.mailJob = mailJob;
	}

}
