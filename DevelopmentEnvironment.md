The Climate Collaboratorium is based upon v.5.2.3 of the [Liferay web-portal](http://liferay.com).  It has been developed against Tomcat 6.1 and MySQL 5.1  We recommend the following steps for getting your environment up and running.

### Environment ###

We have not yet built any re-distributable wars for the main sources of the collaboratorium, so it will be necessary to deploy your own from the "ext" environment.  Instructions on setting up the ext environment may be found in the Liferay developer docs. _Note:  The Collaboratorium has been built and tested against Sun's JVM 1.6.  [Anecdotal reports](http://groups.google.com/group/climate-collaboratorium-developers/browse_thread/thread/b4b2592491798a3c) are that the Blackdown JDK (default on Debian) will not work._

  1. Install MySQL 5.1.
  1. Install the Liferay Tomcat bundle [download](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/liferay-portal-tomcat-6.0-5.2.3.zip)
  1. Install Liferay's ext environment [download](http://sourceforge.net/projects/lportal/files/Liferay%20Portal/5.2.3/liferay-portal-ext-5.2.3.zip/download)

### Configuring Liferay ###
  1. Remove the Liferay demo application by deleting the seven-cogs web application (more info [here](https://www.liferay.com/community/wiki/-/wiki/Main/7Cogs+sample+data/maximized))
  1. Configure Liferay ext environment according to instructions: [developer liferay wiki post](http://www.liferay.com/community/wiki/-/wiki/Main/Setting%20up%20the%20Extension%20Environment),  [developer docs](http://docs.liferay.com/portal/5.0/official/liferay-development-documentation-5.0.pdf)


### Installing the Collaboratorium Source ###
  1. **Install Ext**  (trunk/ext) Code from the repository should be added to ext directory structure - e.g. ext-impl, ext-lib, ext-web, etc.  The sources that come with the ext environments ("reports") may be removed, but you should **not** remove jars / wars provided by the ext distribution.
  1. **Install the plugins** (trunk/plugins)  The repository contains an entire copy of Liferay's plugins environment. You will need to follow the developer docs (above) to configure local settings for this environment (this should only involve creating a build.`<username>`.properties file to specify relevant local directories).
  1. Additional source directories (icefaces-custom, collab\_jsf\_components) may be installed wherever the developer sees fit.  It is not necessary to build these components, as the relevant jars are incorporated their dependent source trees.

### Configuring the database ###
  * Main Liferay DB
    1. Create a user/password liferay/liferay.
    1. Create a DB named 'lportal' and grant the liferay user create permissions
    1. Build the DB using the SQL script [collaboratorium.sample.sql](http://code.google.com/p/climatecollaboratorium/source/browse/trunk/ext/sql/collaboratorium/collaboratorium.sample.sql), which can be found in  ext/sql/collaboratorium/ in the source tree.
  * Simulation DB
    1. Create a user/password climatedb/cci08.
    1. Create a DB named 'simulation3' and grant the climatdb user create permissions.
    1. Build the DB using the SQL script [simulation3.sql](http://code.google.com/p/climatecollaboratorium/source/browse/trunk/web-services/modeling_v1/sql/simulation3.sql), which can be found in web-services/modeling\_v1/sql in the source tree.
  * Excel DB
    1. Create a user/password climatedb/cci08 (if you have not already).
    1. Create a DB named 'exceldb2' and grant the climatdb user create permissions.
    1. Build the DB using the SQL script [exceldb2.sql](http://code.google.com/p/climatecollaboratorium/source/browse/trunk/web-services/excel_v1/sql/exceldb2.sql), which can be found in web-services/excel\_v1/sql in the source tree.

### Installing additional web services ###
  1. Install the three web services from the downloads page ([simulation-servlet](http://climatecollaboratorium.googlecode.com/files/simulation-servlet.war), [pangaea-servlet](http://climatecollaboratorium.googlecode.com/files/pangaea-servlet.war), [excel\_wrapper-servlet](http://http://climatecollaboratorium.googlecode.com/files/excel_wrapper-servlet.war)) by copying them to tomcat's webapps directory.  Please note that the generated web services must be named simulation-servlet, pangaea-servlet, and excel\_wrapper-servlet, respectively.

### Deploy the Collaboratorium ###
  1. From the root ext directory, execute "ant deploy".
  1. Under plugins/portlets/debates-portlet "ant deploy" (**NOTE:** If you would like to enable rapid development for this portlet, you can run "ant deploy-exploded", but you will need to edit debates-portlet.xml to point to the absolute path of your local development directory).
  1. Under plugins/themes/collaboratorium-theme, execute "ant deploy"



Once you've deployed, you should be able to start up Tomcat in the standard fashion.  If all goes well, http://localhost:8080 will redirect you to the home page.  The Administrator account is the only account configured in the sample data.  The user name is "Administrator" and the password is "test".
