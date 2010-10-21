/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

Liferay.Service.register("Liferay.Service.ClimatePlan", "com.ext.portlet.plans.service");

Liferay.Service.register("Liferay.Service.Reports", "com.ext.portlet.reports.service");

Liferay.Service.register("Liferay.Service.Plan", "com.ext.portlet.plans.service");

Liferay.Service.register("Liferay.Service.Plans", "com.ext.portlet.plans.service");

Liferay.Service.register("Liferay.Service.Models", "com.ext.portlet.models.service");

Liferay.Service.register("Liferay.Service.Activity", "com.ext.portlet.Activity.service");

Liferay.Service.register("Liferay.Service.Migration", "com.ext.portlet.migration.service");

Liferay.Service.register("Liferay.Service.Debates", "com.ext.portlet.debates.service");

Liferay.Service.register("Liferay.Service.messaging", "com.ext.portlet.messaging.service");

Liferay.Service.register("Liferay.Service.mass_messaging", "com.ext.portlet.mass_messaging.service");

Liferay.Service.register("Liferay.Service.Messaging", "com.ext.portlet.messaging.service");

Liferay.Service.registerClass(
	Liferay.Service.Messaging, "Message",
	{
		addMessage: true
	}
);

Liferay.Service.register("Liferay.Service.Debaterevision", "com.ext.portlet.debaterevision.service");

Liferay.Service.register("Liferay.Service.Debatemigration", "com.ext.portlet.debatemigration.service");

Liferay.Service.register("Liferay.Service.inlinehelp", "com.ext.inlinehelp.service");

Liferay.Service.register("Liferay.Service.Discussions", "com.ext.portlet.discussions.service");

Liferay.Service.register("Liferay.Service.Contests", "com.ext.portlet.contests.service");

Liferay.Service.register("Liferay.Service.facebook", "com.ext.portlet.facebook.service");