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

package com.tls.liferaylms.mail;

import com.liferay.portal.NoSuchModelException;

/**
 * @author je03042
 */
public class NoSuchMailTemplateException extends NoSuchModelException {

	public NoSuchMailTemplateException() {
		super();
	}

	public NoSuchMailTemplateException(String msg) {
		super(msg);
	}

	public NoSuchMailTemplateException(String msg, Throwable cause) {
		super(msg, cause);
	}

	public NoSuchMailTemplateException(Throwable cause) {
		super(cause);
	}

}