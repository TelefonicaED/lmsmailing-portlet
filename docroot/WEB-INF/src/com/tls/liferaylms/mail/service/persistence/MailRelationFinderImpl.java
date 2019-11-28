package com.tls.liferaylms.mail.service.persistence;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.liferay.portal.kernel.bean.PortalBeanLocatorUtil;
import com.liferay.portal.kernel.dao.orm.ORMException;
import com.liferay.portal.kernel.dao.orm.QueryPos;
import com.liferay.portal.kernel.dao.orm.SQLQuery;
import com.liferay.portal.kernel.dao.orm.Session;
import com.liferay.portal.kernel.dao.orm.SessionFactory;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.model.User;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.social.model.SocialRelation;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class MailRelationFinderImpl extends BasePersistenceImpl<SocialRelation> implements MailRelationFinder {

	private static Log log = LogFactoryUtil.getLog(MailRelationFinderImpl.class);
	
	public static final String FIND_RELATION_TYPES_BY_COMPANYID =
			MailRelationFinder.class.getName() + ".findRelationTypesByCompanyId";
	
	public static final String FIND_USERS_BY_COMPANYID_SOCIALRELATIONTYPE_TO_USERID =
			MailRelationFinder.class.getName() + ".findUsersByCompanyIdSocialRelationTypeIdToUserId";
	
	public static final String WHERE_COMPANYID =
			MailRelationFinder.class.getName() + ".whereCompanyId";
	
	public static final String WHERE_SOCIALRELATIONTYPEID =
			MailRelationFinder.class.getName() + ".whereSocialRelationTypeId";
	
	public static final String WHERE_USERID =
			MailRelationFinder.class.getName() + ".whereUserId";
	
	public List<Integer> findRelationTypesByCompanyId(long companyId){
		
		log.debug("::findRelationTypesByCompanyId::: companyId :: " + companyId);
		
		List<Integer> relationTypes = new ArrayList<Integer>();
		
		Session session = null;
		
		try{
			session = openSessionLiferay();
			
			String sql = CustomSQLUtil.get(FIND_RELATION_TYPES_BY_COMPANYID);
			
			String whereCompanyId = StringPool.BLANK;
			if(Validator.isNotNull(companyId) && companyId > -1)
				whereCompanyId += CustomSQLUtil.get(WHERE_COMPANYID);
			sql = StringUtil.replace(sql, "[$WHERECOMPANYID$]", whereCompanyId);
			
			log.debug("::findRelationTypesByCompanyId::: sql :: " + sql);
			
			SQLQuery q = session.createSQLQuery(sql);
			QueryPos qPos = QueryPos.getInstance(q);
			
			if(Validator.isNotNull(companyId) && companyId > -1 )
				qPos.add(companyId);
			
			Iterator<Integer> itr =  q.iterate();
			log.debug(":::findRelationTypesByCompanyId:::: itr ok :: " + Validator.isNotNull(itr));
			
			Integer relationTypeId = null;
			
			while(itr.hasNext()){
				relationTypeId = itr.next();
				log.debug(":::findRelationTypesByCompanyId:::: relationTypeId :: " + relationTypeId);
				relationTypes.add(relationTypeId);
			}
			
		} catch (Exception e){
			e.printStackTrace();
		} finally {
			closeSessionLiferay(session);
		}
		
		return relationTypes;
	}
	
	public List<User> findUsersByCompanyIdSocialRelationTypeIdToUserId(long userId, int socialRelationTypeId, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug("::findUsersByCompanyIdSocialRelationTypeIdToUserId::: userId :: " + userId);
			log.debug("::findUsersByCompanyIdSocialRelationTypeIdToUserId::: socialRelationTypeId :: " + socialRelationTypeId);
			log.debug("::findUsersByCompanyIdSocialRelationTypeIdToUserId::: companyId :: " + companyId);
		}
		
		List<User> listUsers = new ArrayList<User>();
		
		if(Validator.isNotNull(userId) && userId>0){
		
			Session session = null;
			
			try{
				session = openSessionLiferay();
				
				String sql = CustomSQLUtil.get(FIND_USERS_BY_COMPANYID_SOCIALRELATIONTYPE_TO_USERID);
				
				String whereCompanyId = StringPool.BLANK;
				if(Validator.isNotNull(companyId) && companyId > -1)
					whereCompanyId += CustomSQLUtil.get(WHERE_COMPANYID);
				sql = StringUtil.replace(sql, "[$WHERECOMPANYID$]", whereCompanyId);
				
				String whereUserId = CustomSQLUtil.get(WHERE_USERID);
				if(Validator.isNull(companyId) || companyId < 0)
					whereUserId = StringUtil.replace(whereUserId, "AND", "WHERE");
				sql = StringUtil.replace(sql, "[$WHEREUSERID$]", whereUserId);
				
				String whereRelationTypeId = StringPool.BLANK;
				if(Validator.isNotNull(socialRelationTypeId) && socialRelationTypeId>-1)
					whereRelationTypeId += CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID);
				sql = StringUtil.replace(sql, "[$WHERESOCIALRELATIONTYPEID$]", whereRelationTypeId);
				
				log.debug("::findUsersByCompanyIdSocialRelationTypeIdToUserId::: sql :: " + sql);
				
				SQLQuery q = session.createSQLQuery(sql);
				q.addEntity("User_",PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
				QueryPos qPos = QueryPos.getInstance(q);
				
				if(Validator.isNotNull(companyId) && companyId > -1 )
					qPos.add(companyId);
				
				qPos.add(userId);
				
				if(Validator.isNotNull(socialRelationTypeId) && socialRelationTypeId>-1)
					qPos.add(socialRelationTypeId);
				
				listUsers = (List<User>) q.list();
				return listUsers;
				
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				closeSessionLiferay(session);
			}
		}
		
		return listUsers;
	}
	
	private SessionFactory getPortalSessionFactory() {
		String sessionFactory = "liferaySessionFactory";

		SessionFactory sf = (SessionFactory) PortalBeanLocatorUtil
				.getBeanLocator().locate(sessionFactory);

		return sf;
	}

	private void closeSessionLiferay(Session session) {
		getPortalSessionFactory().closeSession(session);
	}

	private Session openSessionLiferay() throws ORMException {
		return getPortalSessionFactory().openSession();
	}
}
