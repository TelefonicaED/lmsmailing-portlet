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
 * This class is a wrapper for {@link MailTemplateLocalService}.
 * </p>
 *
 * @author    je03042
 * @see       MailTemplateLocalService
 * @generated
 */
public class MailTemplateLocalServiceWrapper implements MailTemplateLocalService,
	ServiceWrapper<MailTemplateLocalService> {
	public MailTemplateLocalServiceWrapper(
		MailTemplateLocalService mailTemplateLocalService) {
		_mailTemplateLocalService = mailTemplateLocalService;
	}

	/**
	* Adds the mail template to the database. Also notifies the appropriate model listeners.
	*
	* @param mailTemplate the mail template
	* @return the mail template that was added
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailTemplate addMailTemplate(
		com.tls.liferaylms.mail.model.MailTemplate mailTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.addMailTemplate(mailTemplate);
	}

	/**
	* Creates a new mail template with the primary key. Does not add the mail template to the database.
	*
	* @param idTemplate the primary key for the new mail template
	* @return the new mail template
	*/
	public com.tls.liferaylms.mail.model.MailTemplate createMailTemplate(
		long idTemplate) {
		return _mailTemplateLocalService.createMailTemplate(idTemplate);
	}

	/**
	* Deletes the mail template with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param idTemplate the primary key of the mail template
	* @return the mail template that was removed
	* @throws PortalException if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailTemplate deleteMailTemplate(
		long idTemplate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.deleteMailTemplate(idTemplate);
	}

	/**
	* Deletes the mail template from the database. Also notifies the appropriate model listeners.
	*
	* @param mailTemplate the mail template
	* @return the mail template that was removed
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailTemplate deleteMailTemplate(
		com.tls.liferaylms.mail.model.MailTemplate mailTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.deleteMailTemplate(mailTemplate);
	}

	public com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery() {
		return _mailTemplateLocalService.dynamicQuery();
	}

	/**
	* Performs a dynamic query on the database and returns the matching rows.
	*
	* @param dynamicQuery the dynamic query
	* @return the matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.dynamicQuery(dynamicQuery);
	}

	/**
	* Performs a dynamic query on the database and returns a range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @return the range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end) throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.dynamicQuery(dynamicQuery, start, end);
	}

	/**
	* Performs a dynamic query on the database and returns an ordered range of the matching rows.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param dynamicQuery the dynamic query
	* @param start the lower bound of the range of model instances
	* @param end the upper bound of the range of model instances (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching rows
	* @throws SystemException if a system exception occurred
	*/
	@SuppressWarnings("rawtypes")
	public java.util.List dynamicQuery(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.dynamicQuery(dynamicQuery, start, end,
			orderByComparator);
	}

	/**
	* Returns the number of rows that match the dynamic query.
	*
	* @param dynamicQuery the dynamic query
	* @return the number of rows that match the dynamic query
	* @throws SystemException if a system exception occurred
	*/
	public long dynamicQueryCount(
		com.liferay.portal.kernel.dao.orm.DynamicQuery dynamicQuery)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.dynamicQueryCount(dynamicQuery);
	}

	public com.tls.liferaylms.mail.model.MailTemplate fetchMailTemplate(
		long idTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.fetchMailTemplate(idTemplate);
	}

	/**
	* Returns the mail template with the primary key.
	*
	* @param idTemplate the primary key of the mail template
	* @return the mail template
	* @throws PortalException if a mail template with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailTemplate getMailTemplate(
		long idTemplate)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.getMailTemplate(idTemplate);
	}

	public com.liferay.portal.model.PersistedModel getPersistedModel(
		java.io.Serializable primaryKeyObj)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.getPersistedModel(primaryKeyObj);
	}

	/**
	* Returns the mail template with the UUID in the group.
	*
	* @param uuid the UUID of mail template
	* @param groupId the group id of the mail template
	* @return the mail template
	* @throws PortalException if a mail template with the UUID in the group could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailTemplate getMailTemplateByUuidAndGroupId(
		java.lang.String uuid, long groupId)
		throws com.liferay.portal.kernel.exception.PortalException,
			com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.getMailTemplateByUuidAndGroupId(uuid,
			groupId);
	}

	/**
	* Returns a range of all the mail templates.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of mail templates
	* @param end the upper bound of the range of mail templates (not inclusive)
	* @return the range of mail templates
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.tls.liferaylms.mail.model.MailTemplate> getMailTemplates(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.getMailTemplates(start, end);
	}

	/**
	* Returns the number of mail templates.
	*
	* @return the number of mail templates
	* @throws SystemException if a system exception occurred
	*/
	public int getMailTemplatesCount()
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.getMailTemplatesCount();
	}

	/**
	* Updates the mail template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mailTemplate the mail template
	* @return the mail template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailTemplate updateMailTemplate(
		com.tls.liferaylms.mail.model.MailTemplate mailTemplate)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.updateMailTemplate(mailTemplate);
	}

	/**
	* Updates the mail template in the database or adds it if it does not yet exist. Also notifies the appropriate model listeners.
	*
	* @param mailTemplate the mail template
	* @param merge whether to merge the mail template with the current session. See {@link com.liferay.portal.service.persistence.BatchSession#update(com.liferay.portal.kernel.dao.orm.Session, com.liferay.portal.model.BaseModel, boolean)} for an explanation.
	* @return the mail template that was updated
	* @throws SystemException if a system exception occurred
	*/
	public com.tls.liferaylms.mail.model.MailTemplate updateMailTemplate(
		com.tls.liferaylms.mail.model.MailTemplate mailTemplate, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.updateMailTemplate(mailTemplate, merge);
	}

	/**
	* Returns the Spring bean ID for this bean.
	*
	* @return the Spring bean ID for this bean
	*/
	public java.lang.String getBeanIdentifier() {
		return _mailTemplateLocalService.getBeanIdentifier();
	}

	/**
	* Sets the Spring bean ID for this bean.
	*
	* @param beanIdentifier the Spring bean ID for this bean
	*/
	public void setBeanIdentifier(java.lang.String beanIdentifier) {
		_mailTemplateLocalService.setBeanIdentifier(beanIdentifier);
	}

	public java.lang.Object invokeMethod(java.lang.String name,
		java.lang.String[] parameterTypes, java.lang.Object[] arguments)
		throws java.lang.Throwable {
		return _mailTemplateLocalService.invokeMethod(name, parameterTypes,
			arguments);
	}

	public java.util.List<com.tls.liferaylms.mail.model.MailTemplate> getMailTemplateByGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.getMailTemplateByGroupId(groupId);
	}

	public java.util.List<com.tls.liferaylms.mail.model.MailTemplate> getMailTemplateByGroupIdAndGlobalGroupId(
		long groupId)
		throws com.liferay.portal.kernel.exception.SystemException {
		return _mailTemplateLocalService.getMailTemplateByGroupIdAndGlobalGroupId(groupId);
	}

	/**
	 * @deprecated Renamed to {@link #getWrappedService}
	 */
	public MailTemplateLocalService getWrappedMailTemplateLocalService() {
		return _mailTemplateLocalService;
	}

	/**
	 * @deprecated Renamed to {@link #setWrappedService}
	 */
	public void setWrappedMailTemplateLocalService(
		MailTemplateLocalService mailTemplateLocalService) {
		_mailTemplateLocalService = mailTemplateLocalService;
	}

	public MailTemplateLocalService getWrappedService() {
		return _mailTemplateLocalService;
	}

	public void setWrappedService(
		MailTemplateLocalService mailTemplateLocalService) {
		_mailTemplateLocalService = mailTemplateLocalService;
	}

	private MailTemplateLocalService _mailTemplateLocalService;
}