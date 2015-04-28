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

package com.tls.liferaylms.mail.service;

import com.liferay.portal.service.ServiceWrapper;

/**
 * <p>
 * This class is a wrapper for {@link AuditSendMailsService}.
 * </p>
 *
 * @author    je03042
 * @see       AuditSendMailsService
 * @generated
 */
public class AuditSendMailsServiceWrapper implements AuditSendMailsService,
	ServiceWrapper<AuditSendMailsService> {
	public AuditSendMailsServiceWrapper(
		AuditSendMailsService auditSendMailsService) {
		_auditSendMailsService = auditSendMailsService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _auditSendMailsService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_auditSendMailsService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _auditSendMailsService.invokeMethod(name, parameterTypes,
			arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public AuditSendMailsService getWrappedAuditSendMailsService() {
		return _auditSendMailsService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedAuditSendMailsService(
		AuditSendMailsService auditSendMailsService) {
		_auditSendMailsService = auditSendMailsService;
	}

	public AuditSendMailsService getWrappedService() {
		return _auditSendMailsService;
	}

	public void setWrappedService(AuditSendMailsService auditSendMailsService) {
		_auditSendMailsService = auditSendMailsService;
	}

	private AuditSendMailsService _auditSendMailsService;
}