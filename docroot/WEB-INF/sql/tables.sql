create table lmsmail_AuditSendMails (
	uuid_ VARCHAR(75) null,
	auditSendMailsId LONG not null primary key,
	userId LONG,
	templateId LONG,
	groupId LONG,
	sendDate DATE null
);

create table lmsmail_MailJob (
	uuid_ VARCHAR(75) null,
	idJob LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	idTemplate LONG,
	conditionClassName VARCHAR(75) null,
	conditionClassPK LONG,
	conditionStatus VARCHAR(75) null,
	dateClassName VARCHAR(75) null,
	dateClassPK LONG,
	dateReferenceDate LONG,
	dateShift LONG,
	teamId LONG,
	processed BOOLEAN
);

create table lmsmail_MailTemplate (
	uuid_ VARCHAR(75) null,
	idTemplate LONG not null primary key,
	companyId LONG,
	groupId LONG,
	userId LONG,
	subject VARCHAR(120) null,
	body TEXT null
);