package com.tls.liferaylms.job.condition;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import com.liferay.portal.kernel.language.LanguageUtil;
import com.liferay.portal.model.User;

public class InscriptionCondition extends MainCondition{

	public InscriptionCondition(String className) {
		super(className);
	}

	@Override
	public Set<User> getUsersToSend() {
		return null;
	}

	@Override
	public boolean shouldBeProcessed() {
		return false;
	}

	@Override
	public String getConditionName() {
		return "Inscription";
	}

	@Override
	public String getConditionName(Locale locale) {
		return "Inscription";
	}

	@Override
	public String getReferenceName() {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public String getReferenceName(Locale locale) {
		// TODO Auto-generated method stub
		return "";
	}

	@Override
	public Long getActReferencePK() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getActConditionPK() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getModReferencePK() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getModConditionPK() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Date getDate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getFormatDate() {
		// TODO Auto-generated method stub
		return null;
	}

}
