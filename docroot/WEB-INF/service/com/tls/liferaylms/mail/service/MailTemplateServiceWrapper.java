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
 * This class is a wrapper for {@link MailTemplateService}.
 * </p>
 *
 * @author    je03042
 * @see       MailTemplateService
 * @generated
 */
public class MailTemplateServiceWrapper implements MailTemplateService,
	ServiceWrapper<MailTemplateService> {
	public MailTemplateServiceWrapper(MailTemplateService mailTemplateService) {
		_mailTemplateService = mailTemplateService;
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _mailTemplateService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mailTemplateService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mailTemplateService.invokeMethod(name, parameterTypes, arguments);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MailTemplateService getWrappedMailTemplateService() {
		return _mailTemplateService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMailTemplateService(
		MailTemplateService mailTemplateService) {
		_mailTemplateService = mailTemplateService;
	}

	public MailTemplateService getWrappedService() {
		return _mailTemplateService;
	}

	public void setWrappedService(MailTemplateService mailTemplateService) {
		_mailTemplateService = mailTemplateService;
	}

	private MailTemplateService _mailTemplateService;
}