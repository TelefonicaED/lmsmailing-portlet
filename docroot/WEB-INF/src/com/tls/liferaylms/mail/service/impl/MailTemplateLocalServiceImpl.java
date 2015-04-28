/**
 * Copyright (c) 2000-2011 Liferay, Inc. All rights reserved.
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

import java.util.List;

import com.liferay.portal.kernel.dao.orm.Criterion;
import com.liferay.portal.kernel.dao.orm.Disjunction;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;
import com.liferay.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.liferay.portal.kernel.dao.orm.PropertyFactoryUtil;
import com.liferay.portal.kernel.dao.orm.RestrictionsFactoryUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.model.Group;
import com.liferay.portal.service.CompanyLocalServiceUtil;
import com.liferay.portal.service.GroupLocalServiceUtil;
import com.tls.liferaylms.mail.model.MailTemplate;
import com.tls.liferaylms.mail.service.base.MailTemplateLocalServiceBaseImpl;

/**
 * The implementation of the mail template local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.tls.liferaylms.mail.service.MailTemplateLocalService} interface.
 * </p>
 *
 * <p>
 * Never reference this interface directly. Always use {@link com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil} to access the mail template local service.
 * </p>
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author je03042
 * @see com.tls.liferaylms.mail.service.base.MailTemplateLocalServiceBaseImpl
 * @see com.tls.liferaylms.mail.service.MailTemplateLocalServiceUtil
 */
public class MailTemplateLocalServiceImpl
	extends MailTemplateLocalServiceBaseImpl {
	
	@SuppressWarnings("unchecked")
	public List<MailTemplate> getMailTemplateByGroupId(long groupId) throws SystemException
	{ 			
		DynamicQuery consulta = DynamicQueryFactoryUtil.forClass(MailTemplate.class)
					.add(PropertyFactoryUtil.forName("groupId").eq(new Long(groupId)));
			
//System.out.println("groupId: "+groupId);
		return mailTemplatePersistence.findWithDynamicQuery(consulta);		
	}
	
	@SuppressWarnings("unchecked")
	public List<MailTemplate> getMailTemplateByGroupIdAndGlobalGroupId(long groupId) throws SystemException
	{ 
		Group group;
		long globalGroupId = 0;
		try {
			group = GroupLocalServiceUtil.getGroup(groupId);
			globalGroupId = CompanyLocalServiceUtil.getCompany(group.getCompanyId()).getGroup().getGroupId();
		} catch (PortalException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
		DynamicQuery consulta = DynamicQueryFactoryUtil.forClass(MailTemplate.class);
		
		Criterion criterio1 = PropertyFactoryUtil.forName("groupId").eq(new Long(groupId));
		Criterion criterio2 = PropertyFactoryUtil.forName("groupId").eq(new Long(globalGroupId));
		
		if(globalGroupId != 0){
			Disjunction or = RestrictionsFactoryUtil.disjunction();
			or.add(criterio1);
			or.add(criterio2);
			consulta.add(or);
		}
		else{	
			consulta.add(criterio1);
		}
//System.out.println("groupId: "+groupId+", companyId: "+globalGroupId);
		return mailTemplatePersistence.findWithDynamicQuery(consulta);		
	}
}