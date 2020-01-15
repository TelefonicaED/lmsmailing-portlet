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
import com.liferay.portal.kernel.dao.orm.Type;
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
	
	public static final String FIND_DISTINCT_USERS_BY_COMPANYID_SOCIALRELATIONTYPE_TO_USERID =
			MailRelationFinder.class.getName() + ".findDistinctUsersByCompanyIdSocialRelationTypeIdToUserId";
	
	public static final String COUNT_SOCIALRELATIONS_BETWEENUSERS_BY_RELATIONTYPEIDS =
			MailRelationFinder.class.getName() + ".countSocialRelationsBetweenUsersBySocialRelationTypeIds";
	
	public static final String WHERE_COMPANYID =
			MailRelationFinder.class.getName() + ".whereCompanyId";
	
	public static final String WHERE_SOCIALRELATIONTYPEID =
			MailRelationFinder.class.getName() + ".whereSocialRelationTypeId";
	
	public static final String WHERE_USERID1 =
			MailRelationFinder.class.getName() + ".whereUserId1";
	
	public static final String WHERE_USERID2 =
			MailRelationFinder.class.getName() + ".whereUserId2";
	
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
		
		if(Validator.isNotNull(userId) && userId>-1){
		
			Session session = null;
			
			try{
				session = openSessionLiferay();
				
				String sql = CustomSQLUtil.get(FIND_USERS_BY_COMPANYID_SOCIALRELATIONTYPE_TO_USERID);
				
				String whereCompanyId = StringPool.BLANK;
				if(Validator.isNotNull(companyId) && companyId > -1)
					whereCompanyId += CustomSQLUtil.get(WHERE_COMPANYID);
				sql = StringUtil.replace(sql, "[$WHERECOMPANYID$]", whereCompanyId);
				
				String whereUserId = CustomSQLUtil.get(WHERE_USERID1);
				if(Validator.isNull(companyId) || companyId < 0)
					whereUserId = StringUtil.replace(whereUserId, "AND", "WHERE");
				sql = StringUtil.replace(sql, "[$WHEREUSERID1$]", whereUserId);
				
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
	
	public List<User> findUsersByCompanyIdSocialRelationTypeIdToUser(User user, int socialRelationTypeId, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug("::findUsersByCompanyIdSocialRelationTypeIdToUser::: user :: " + user);
			log.debug("::findUsersByCompanyIdSocialRelationTypeIdToUser::: socialRelationTypeId :: " + socialRelationTypeId);
			log.debug("::findUsersByCompanyIdSocialRelationTypeIdToUser::: companyId :: " + companyId);
		}
		
		List<User> listUsers = new ArrayList<User>();
		
		if(Validator.isNotNull(user))
			listUsers = findUsersByCompanyIdSocialRelationTypeIdToUserId(user.getUserId(), socialRelationTypeId, companyId);
		
		return listUsers;
	}
	
	public List<User> findDistinctUsersByCompanyIdSocialRelationTypeIdToUserIds(List<Long> userIds, int socialRelationTypeId, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdToUserIds::: userIds.size :: " + userIds.size());
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdToUserIds::: socialRelationTypeId :: " + socialRelationTypeId);
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdToUserIds::: companyId :: " + companyId);
		}
		
		List<User> listUsers = new ArrayList<User>();
		
		if(Validator.isNotNull(userIds) && userIds.size()>0){
		
			Session session = null;
			
			try{
				session = openSessionLiferay();
				
				String sql = CustomSQLUtil.get(FIND_DISTINCT_USERS_BY_COMPANYID_SOCIALRELATIONTYPE_TO_USERID);
				
				String whereCompanyId = StringPool.BLANK;
				if(Validator.isNotNull(companyId) && companyId > -1)
					whereCompanyId += CustomSQLUtil.get(WHERE_COMPANYID);
				sql = StringUtil.replace(sql, "[$WHERECOMPANYID$]", whereCompanyId);
				
				String whereUserId = CustomSQLUtil.get(WHERE_USERID1);
				if(Validator.isNull(companyId) || companyId < 0)
					whereUserId = StringUtil.replace(whereUserId, "AND", "WHERE");
				if(userIds.size()>1){
					if(Validator.isNull(companyId) || companyId < 0)
						whereUserId = StringUtil.replace(whereUserId, "WHERE", "WHERE (");
					else
						whereUserId = StringUtil.replace(whereUserId, "AND", "AND (");
					for(int i=1;i<userIds.size();i++)
						whereUserId += StringUtil.replace(CustomSQLUtil.get(WHERE_USERID1), "AND", "OR");
					whereUserId += ")";
				}
				sql = StringUtil.replace(sql, "[$WHEREUSERID1$]", whereUserId);
				
				
				String whereRelationTypeId = StringPool.BLANK;
				if(Validator.isNotNull(socialRelationTypeId) && socialRelationTypeId>-1)
					whereRelationTypeId += CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID);
				sql = StringUtil.replace(sql, "[$WHERESOCIALRELATIONTYPEID$]", whereRelationTypeId);
				
				log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdToUserIds::: sql :: " + sql);
				
				SQLQuery q = session.createSQLQuery(sql);
				q.addEntity("User_",PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
				QueryPos qPos = QueryPos.getInstance(q);
				
				if(Validator.isNotNull(companyId) && companyId > -1 )
					qPos.add(companyId);
				
				for(long userId:userIds)
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
	
	public List<User> findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId(long userId, List<Integer> socialRelationTypeIds, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId::: userId :: " + userId);
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId::: socialRelationTypeId.size :: " + socialRelationTypeIds.size());
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId::: companyId :: " + companyId);
		}
		
		List<User> listUsers = new ArrayList<User>();
		
		if(Validator.isNotNull(userId) && userId >-1 ){
		
			Session session = null;
			
			try{
				session = openSessionLiferay();
				
				String sql = CustomSQLUtil.get(FIND_DISTINCT_USERS_BY_COMPANYID_SOCIALRELATIONTYPE_TO_USERID);
				
				String whereCompanyId = StringPool.BLANK;
				if(Validator.isNotNull(companyId) && companyId > -1)
					whereCompanyId += CustomSQLUtil.get(WHERE_COMPANYID);
				sql = StringUtil.replace(sql, "[$WHERECOMPANYID$]", whereCompanyId);
				
				String whereUserId = CustomSQLUtil.get(WHERE_USERID1);
				if(Validator.isNull(companyId) || companyId < 0)
					whereUserId = StringUtil.replace(whereUserId, "AND", "WHERE");
				sql = StringUtil.replace(sql, "[$WHEREUSERID1$]", whereUserId);
				
				String whereRelationTypeId = StringPool.BLANK;
				if(Validator.isNotNull(socialRelationTypeIds) && socialRelationTypeIds.size()>0){
					whereRelationTypeId += CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID);
					if(socialRelationTypeIds.size()>1){
						whereRelationTypeId = StringUtil.replace(whereRelationTypeId, "AND", "AND (");
						for(int i=1;i<socialRelationTypeIds.size();i++)
							whereRelationTypeId += StringUtil.replace(CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID), "AND", "OR");
						whereRelationTypeId += ")";
					}
				}
				sql = StringUtil.replace(sql, "[$WHERESOCIALRELATIONTYPEID$]", whereRelationTypeId);
				
				log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId::: sql :: " + sql);
				
				SQLQuery q = session.createSQLQuery(sql);
				q.addEntity("User_",PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
				QueryPos qPos = QueryPos.getInstance(q);
				
				if(Validator.isNotNull(companyId) && companyId > -1 )
					qPos.add(companyId);
				
				qPos.add(userId);
				
				if(Validator.isNotNull(socialRelationTypeIds) && socialRelationTypeIds.size()>0){
					for(int socialRelationTypeId:socialRelationTypeIds)
						qPos.add(socialRelationTypeId);
				}
				
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
	
	public List<User> findDistinctUsersByCompanyIdSocialRelationTypeIdsToUser(User user, List<Integer> socialRelationTypeIds, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId::: user :: " + user);
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId::: socialRelationTypeId.size :: " + socialRelationTypeIds.size());
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId::: companyId :: " + companyId);
		}
		
		List<User> listUsers = new ArrayList<User>();
		
		if(Validator.isNotNull(user))
			listUsers = findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserId(user.getUserId(), socialRelationTypeIds, companyId);
		
		return listUsers;
	}
	
	public List<User> findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserIds(List<Long> userIds, List<Integer> socialRelationTypeIds, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserIds::: userIds.size :: " + userIds.size());
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserIds::: socialRelationTypeId.size :: " + socialRelationTypeIds.size());
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserIds::: companyId :: " + companyId);
		}
		
		List<User> listUsers = new ArrayList<User>();
		
		if(Validator.isNotNull(userIds) && userIds.size()>0){
		
			Session session = null;
			
			try{
				session = openSessionLiferay();
				
				String sql = CustomSQLUtil.get(FIND_DISTINCT_USERS_BY_COMPANYID_SOCIALRELATIONTYPE_TO_USERID);
				
				String whereCompanyId = StringPool.BLANK;
				if(Validator.isNotNull(companyId) && companyId > -1)
					whereCompanyId += CustomSQLUtil.get(WHERE_COMPANYID);
				sql = StringUtil.replace(sql, "[$WHERECOMPANYID$]", whereCompanyId);
				
				String whereUserId = CustomSQLUtil.get(WHERE_USERID1);
				if(Validator.isNull(companyId) || companyId < 0)
					whereUserId = StringUtil.replace(whereUserId, "AND", "WHERE");
				if(userIds.size()>1){
					whereUserId = StringUtil.replace(whereUserId, "WHERE", "WHERE (");
					for(int i=1;i<userIds.size();i++)
						whereUserId += CustomSQLUtil.get(WHERE_USERID1);
					whereUserId = StringUtil.replace(whereUserId, "AND", "OR");
					whereUserId += ")";
				}
				sql = StringUtil.replace(sql, "[$WHEREUSERID1$]", whereUserId);
				
				String whereRelationTypeId = StringPool.BLANK;
				if(Validator.isNotNull(socialRelationTypeIds) && socialRelationTypeIds.size()>0){
					whereRelationTypeId += CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID);
					if(socialRelationTypeIds.size()>1){
						whereRelationTypeId = StringUtil.replace(whereRelationTypeId, "WHERE", "WHERE (");
						for(int i=1;i<socialRelationTypeIds.size();i++)
							whereRelationTypeId += CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID);
						whereRelationTypeId = StringUtil.replace(whereRelationTypeId, "AND", "OR");
						whereRelationTypeId += ")";
					}
				}
				sql = StringUtil.replace(sql, "[$WHERESOCIALRELATIONTYPEID$]", whereRelationTypeId);
				
				log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUserIds::: sql :: " + sql);
				
				SQLQuery q = session.createSQLQuery(sql);
				q.addEntity("User_",PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
				QueryPos qPos = QueryPos.getInstance(q);
				
				if(Validator.isNotNull(companyId) && companyId > -1 )
					qPos.add(companyId);
				
				for(long userId:userIds)
					qPos.add(userId);
				
				if(Validator.isNotNull(socialRelationTypeIds) && socialRelationTypeIds.size()>0){
					for(int socialRelationTypeId:socialRelationTypeIds)
						qPos.add(socialRelationTypeId);
				}
				
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
	
	public List<User> findDistinctUsersByCompanyIdSocialRelationTypeIdsToUsers(List<User> users, List<Integer> socialRelationTypeIds, long companyId){
		
		if(log.isDebugEnabled()){
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUsers::: users.size :: " + users.size());
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUsers::: socialRelationTypeId.size :: " + socialRelationTypeIds.size());
			log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUsers::: companyId :: " + companyId);
		}
		
		List<User> listUsers = new ArrayList<User>();
		
		if(Validator.isNotNull(users) && users.size()>0){
		
			Session session = null;
			
			try{
				session = openSessionLiferay();
				
				String sql = CustomSQLUtil.get(FIND_DISTINCT_USERS_BY_COMPANYID_SOCIALRELATIONTYPE_TO_USERID);
				
				String whereCompanyId = StringPool.BLANK;
				if(Validator.isNotNull(companyId) && companyId > -1)
					whereCompanyId += CustomSQLUtil.get(WHERE_COMPANYID);
				sql = StringUtil.replace(sql, "[$WHERECOMPANYID$]", whereCompanyId);
				
				String whereUserId = CustomSQLUtil.get(WHERE_USERID1);
				if(Validator.isNull(companyId) || companyId < 0)
					whereUserId = StringUtil.replace(whereUserId, "AND", "WHERE");
				if(users.size()>1){
					if(Validator.isNull(companyId) || companyId < 0)
						whereUserId = StringUtil.replace(whereUserId, "WHERE", "WHERE (");
					else
						whereUserId = StringUtil.replace(whereUserId, "AND", "AND (");
					for(int i=1;i<users.size();i++)
						whereUserId += StringUtil.replace(CustomSQLUtil.get(WHERE_USERID1),"AND", "OR");
					whereUserId += ")";
				}
				sql = StringUtil.replace(sql, "[$WHEREUSERID1$]", whereUserId);
				
				String whereRelationTypeId = StringPool.BLANK;
				if(Validator.isNotNull(socialRelationTypeIds) && socialRelationTypeIds.size()>0){
					whereRelationTypeId += CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID);
					if(socialRelationTypeIds.size()>1){
						whereRelationTypeId = StringUtil.replace(whereRelationTypeId, "AND", "AND (");
						for(int i=1;i<socialRelationTypeIds.size();i++)
							whereRelationTypeId += StringUtil.replace(CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID), "AND", "OR");
						whereRelationTypeId += ")";
					}
				}
				sql = StringUtil.replace(sql, "[$WHERESOCIALRELATIONTYPEID$]", whereRelationTypeId);
				
				log.debug("::findDistinctUsersByCompanyIdSocialRelationTypeIdsToUsers::: sql :: " + sql);
				
				SQLQuery q = session.createSQLQuery(sql);
				q.addEntity("User_",PortalClassLoaderUtil.getClassLoader().loadClass("com.liferay.portal.model.impl.UserImpl"));
				QueryPos qPos = QueryPos.getInstance(q);
				
				if(Validator.isNotNull(companyId) && companyId > -1 )
					qPos.add(companyId);
				
				for(User user:users)
					qPos.add(user.getUserId());
				
				if(Validator.isNotNull(socialRelationTypeIds) && socialRelationTypeIds.size()>0){
					for(int socialRelationTypeId:socialRelationTypeIds)
						qPos.add(socialRelationTypeId);
				}
				
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
	
	public int countSocialRelationsBeetweenUsersBySocialRelationTypeIds(long userId1, long userId2, List<Integer> socialRelationTypeIds, long companyId){
		
		int numSocialRelations = 0;
		
		if(log.isDebugEnabled()){
			log.debug("::countSocialRelationsBeetweenUsersBySocialRelationTypeIds::: userId1 :: " + userId1);
			log.debug("::countSocialRelationsBeetweenUsersBySocialRelationTypeIds::: userId2 :: " + userId2);
			log.debug("::countSocialRelationsBeetweenUsersBySocialRelationTypeIds::: socialRelationTypeId :: " + Validator.isNotNull(socialRelationTypeIds));
			log.debug("::countSocialRelationsBeetweenUsersBySocialRelationTypeIds::: companyId :: " + companyId);
		}
		
		if(Validator.isNotNull(userId1) && Validator.isNotNull(userId2) && userId1>-1 && userId2>-1 && Validator.isNotNull(socialRelationTypeIds) && socialRelationTypeIds.size()>0){
		
			Session session = null;
			
			try{
				session = openSessionLiferay();
				
				String sql = CustomSQLUtil.get(COUNT_SOCIALRELATIONS_BETWEENUSERS_BY_RELATIONTYPEIDS);
				
				String whereCompanyId = StringPool.BLANK;
				if(Validator.isNotNull(companyId) && companyId > -1)
					whereCompanyId += CustomSQLUtil.get(WHERE_COMPANYID);
				sql = StringUtil.replace(sql, "[$WHERECOMPANYID$]", whereCompanyId);
				
				String whereUserId1 = CustomSQLUtil.get(WHERE_USERID1);
				if(Validator.isNull(companyId) || companyId < 0)
					whereUserId1 = StringUtil.replace(whereUserId1, "AND", "WHERE");
				sql = StringUtil.replace(sql, "[$WHEREUSERID1$]", whereUserId1);
				sql = StringUtil.replace(sql, "[$WHEREUSERID2$]", CustomSQLUtil.get(WHERE_USERID2));
				
				String whereRelationTypeId = CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID);
				if(socialRelationTypeIds.size()>1){
					whereRelationTypeId = StringUtil.replace(whereRelationTypeId, "AND", "AND (");
					for(int i = 1; i< socialRelationTypeIds.size() ; i++)
						whereRelationTypeId += StringUtil.replace(CustomSQLUtil.get(WHERE_SOCIALRELATIONTYPEID), "AND", "OR");
					whereRelationTypeId += ")";
				}
				sql = StringUtil.replace(sql, "[$WHERESOCIALRELATIONTYPEID$]", whereRelationTypeId);
				
				log.debug("::countSocialRelationsBeetweenUsersBySocialRelationTypeIds::: sql :: " + sql);
				
				SQLQuery q = session.createSQLQuery(sql);
				q.addScalar(COUNT_COLUMN_NAME, Type.LONG);
				QueryPos qPos = QueryPos.getInstance(q);
				
				if(Validator.isNotNull(companyId) && companyId > -1 )
					qPos.add(companyId);
				
				qPos.add(userId1);
				qPos.add(userId2);
				
				for(int socialRelationTypeId:socialRelationTypeIds)
					qPos.add(socialRelationTypeId);
				
				Object count = q.list().get(0);
				if (Validator.isNotNull(count)) {
					if(count instanceof Long){
						numSocialRelations = ((Long)count).intValue();
					}else if(count instanceof BigInteger){
						numSocialRelations = ((BigInteger)count).intValue();
					}else if(count instanceof Integer){
						numSocialRelations = (Integer)count;
					}
				}
				
			} catch (Exception e){
				e.printStackTrace();
			} finally {
				closeSessionLiferay(session);
			}
		}
		
		return numSocialRelations;
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
