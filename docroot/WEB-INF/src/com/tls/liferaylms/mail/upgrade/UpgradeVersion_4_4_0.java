package com.tls.liferaylms.mail.upgrade;


import java.io.IOException;
import java.sql.SQLException;

import com.liferay.portal.kernel.dao.db.DB;
import com.liferay.portal.kernel.dao.db.DBFactoryUtil;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.upgrade.UpgradeProcess;

public class UpgradeVersion_4_4_0 extends UpgradeProcess {
	private static Log log = LogFactoryUtil.getLog(UpgradeVersion_4_4_0.class);
	
	public int getThreshold() {
		return 440;
	}

	protected void doUpgrade() throws Exception {
		log.info("Actualizando version a 4.4");
		log.info("Upgrading LMSMAILING Portlet ");
		 
		 String layoutUpdate =  "ALTER TABLE `lmsmail_mailjob` ADD COLUMN `extraData` LONGTEXT NULL DEFAULT NULL AFTER `processed`;";
				 				
		 log.info("Insert extradata column "); 
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