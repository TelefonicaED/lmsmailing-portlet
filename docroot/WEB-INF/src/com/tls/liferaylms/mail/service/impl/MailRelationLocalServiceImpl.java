/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.tls.liferaylms.mail.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.tls.liferaylms.mail.service.base.MailRelationLocalServiceBaseImpl;

/**
 * The implementation of the mail relation local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.tls.liferaylms.mail.service.MailRelationLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author je03042
 * @see com.tls.liferaylms.mail.service.base.MailRelationLocalServiceBaseImpl
 * @see com.tls.liferaylms.mail.service.MailRelationLocalServiceUtil
 */
public class MailRelationLocalServiceImpl
	extends MailRelationLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.tls.liferaylms.mail.service.MailRelationLocalServiceUtil} to access the mail relation local service.
	 */
	
	private static Log log = LogFactoryUtil.getLog(MailRelationLocalServiceImpl.class);
	
	public List<Integer> findRelationTypeIdsByCompanyId(long companyId){
		log.debug(":::findRelationTypeIdsByCompanyId::: companyId :: " + companyId);
		return mailRelationFinder.findRelationTypesByCompanyId(companyId);
	}
	
	public List<User> findUsersByCompanyIdSocialRelationTypeIdToUserId(long userId, int socialRelationTypeId, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdToUserId::: userId :: " + userId);
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdToUserId::: socialRelationTypeId :: " + socialRelationTypeId);
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdToUserId::: companyId :: " + companyId);
		}
		
		List<User> users = new ArrayList<User>();
		
		if(Validator.isNotNull(userId))
			users = mailRelationFinder.findUsersByCompanyIdSocialRelationTypeIdToUserId(userId, socialRelationTypeId, companyId);
		
		return users;
	}
	
	public List<User> findUsersByCompanyIdSocialRelationTypeIdToUserIds(List<Long> userIds, int socialRelationTypeId, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdToUserIds::: userIds.size :: " + userIds.size());
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdToUserIds::: socialRelationTypeId :: " + socialRelationTypeId);
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdToUserIds::: companyId :: " + companyId);
		}
		
		List<User> users = new ArrayList<User>();
		
		if(Validator.isNotNull(userIds)) {
			if(userIds.size()==1)
				users = findUsersByCompanyIdSocialRelationTypeIdToUserId(userIds.get(0), socialRelationTypeId, companyId);
			else if(userIds.size()>1)
				users = mailRelationFinder.findDistinctUsersByCompanyIdSocialRelationTypeIdToUserIds(userIds, socialRelationTypeId, companyId);
		}
		
		return users;
	}
	
	public List<User> findUsersByCompanyIdSocialRelationTypeIdsToUserId(long userId, List<Integer> socialRelationTypeIds, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUserId::: userId :: " + userId);
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUserId::: socialRelationTypeIds :: " + socialRelationTypeIds);
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUserId::: companyId :: " + companyId);
		}
		
		List<User> users = new ArrayList<User>();
		
		if(Validator.isNotNull(userId) && userId>-1) {
			if(Validator.isNull(socialRelationTypeIds) || socialRelationTypeIds.isEmpty()){
				users = findUsersByCompanyIdSocialRelationTypeIdToUserId(userId, -1, companyId);
			} else {
			if(socialRelationTypeIds.size()==1)
				users = findUsersByCompanyIdSocialRelationTypeIdToUserId(userId, socialRelationTypeIds.get(0), companyId);
			else if(socialRelationTypeIds.size()>1)
				users = mailRelationFinder.findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId(userId, socialRelationTypeIds, companyId);
			}
		}
		
		return users;
	}
	
	public List<User> findUsersByCompanyIdSocialRelationTypeIdsToUserIds(List<Long> userIds, List<Integer> socialRelationTypeIds, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUserIds::: userIds.size :: " + userIds.size());
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUserIds::: socialRelationTypeIds.size :: " + socialRelationTypeIds.size());
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUserIds::: companyId :: " + companyId);
		}
		
		List<User> users = new ArrayList<User>();
		
		if(Validator.isNotNull(userIds) && userIds.size()>0)
			users = mailRelationFinder.findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserIds(userIds, socialRelationTypeIds, companyId);
		
		return users;
	}
	
	public List<User> findUsersByCompanyIdSocialRelationTypeIdsToUsers(List<User> users, List<Integer> socialRelationTypeIds, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUsers::: users.size :: " + users.size());
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUsers::: socialRelationTypeIds.size :: " + socialRelationTypeIds.size());
			log.debug(":::findUsersByCompanyIdSocialRelationTypeIdsToUsers::: companyId :: " + companyId);
		}
		
		List<User> usersRelated = new ArrayList<User>();
		
		if(Validator.isNotNull(users) && users.size()>0)
			usersRelated = mailRelationFinder.findDistinctUsersByCompanyIdSocialRelationTypeIdsToUsers(users, socialRelationTypeIds, companyId);
		
		return usersRelated;
	}
}