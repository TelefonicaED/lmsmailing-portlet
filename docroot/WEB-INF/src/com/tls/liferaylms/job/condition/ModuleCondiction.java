package com.tls.liferaylms.job.condition;

import java.util.Date;
import java.util.Locale;
import java.util.Set;

import com.liferay.portal.model.User;

public class ModuleCondiction extends MainCondition{

	public ModuleCondiction(String className) {
		super(className);
	}

	@Override
	public Set<User> getUsersToSend() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean shouldBeProcessed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getConditionName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getConditionName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferenceName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getReferenceName(Locale locale) {
		// TODO Auto-generated method stub
		return null;
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
