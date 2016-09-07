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

import com.liferay.portal.kernel.exception.SystemException;
import com.tls.liferaylms.mail.model.AuditReceiverMail;
import com.tls.liferaylms.mail.service.base.AuditReceiverMailLocalServiceBaseImpl;
import com.tls.liferaylms.mail.service.persistence.AuditReceiverMailUtil;

/**
 * The implementation of the audit receiver mail local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.tls.liferaylms.mail.service.AuditReceiverMailLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author je03042
 * @see com.tls.liferaylms.mail.service.base.AuditReceiverMailLocalServiceBaseImpl
 * @see com.tls.liferaylms.mail.service.AuditReceiverMailLocalServiceUtil
 */
public class AuditReceiverMailLocalServiceImpl
	extends AuditReceiverMailLocalServiceBaseImpl {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this interface directly. Always use {@link com.tls.liferaylms.mail.service.AuditReceiverMailLocalServiceUtil} to access the audit receiver mail local service.
	 */
	
	public List<AuditReceiverMail> getRecieverMailsBySendMail (long auditSendMailsId, int start, int end) throws SystemException{
		List<AuditReceiverMail> auditReceiverMails = new ArrayList<AuditReceiverMail>();
		try{
			auditReceiverMails = AuditReceiverMailUtil.findByauditSendMail(auditSendMailsId, start, end);  
		}catch(Exception e){
			e.printStackTrace();
		}
		return auditReceiverMails;
	}
	
	public int countRecieverMailsBySendMail (long auditSendMailsId) throws SystemException{
		return  AuditReceiverMailUtil.countByauditSendMail(auditSendMailsId);  		
	}

}