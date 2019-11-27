package com.tls.liferaylms.mail.service.persistence;

import java.math.BigInteger;
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
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.persistence.impl.BasePersistenceImpl;
import com.liferay.portlet.social.model.SocialRelation;
import com.liferay.util.dao.orm.CustomSQLUtil;

public class MailRelationFinderImpl extends BasePersistenceImpl<SocialRelation> implements MailRelationFinder {

	private static Log log = LogFactoryUtil.getLog(MailRelationFinderImpl.class);
	
	public static final String FIND_RELATION_TYPES_BY_COMPANYID =
			MailRelationFinder.class.getName() + ".findRelationTypesByCompanyId";
	
	public static final String WHERE_COMPANYID =
			MailRelationFinder.class.getName() + ".whereCompanyId";
	
	
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
