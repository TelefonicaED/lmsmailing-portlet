package com.tls.liferaylms.mail.upgrade;


import java.io.IOException;
import java.sql.SQLException;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class UpgradeVersion_3_3_0 extends UpgradeProcess {
	private static Log log = LogFactoryUtil.getLog(UpgradeVersion_3_3_0.class);
	
	public int getThreshold() {
		return 330;
	}

	protected void doUpgrade() throws Exception {
		log.info("Actualizando version a 3.3");
		log.info("Upgrading LMSMAILING Portlet ");
		 
		 String layoutUpdate =  "UPDATE layout la, "+
				 				"(SELECT plid, "+
				 				"REPLACE(typeSettings,'deregisteracademymail_WAR_liferaylmsportlet','deregisteracademymail_WAR_lmsmailingportlet') "
				 				+ "as settings "
				 				+ "FROM layout "
				 				+ "WHERE typeSettings LIKE '%deregisteracademymail_WAR_liferaylmsportle') AS t1 "
				 				+ "set la.typeSettings = t1.settings "
				 				+ "WHERE la.plid=t1.plid; ";
				 				
		 log.info("Insert DeregisterMail layout "); 
			 DB db = DBFactoryUtil.getDB();
			 try {
				db.runSQL(layoutUpdate);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	

	}
	
	
}