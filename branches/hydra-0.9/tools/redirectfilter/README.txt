Redirect filter that can should be used when portal is moved from one host 
name to another. It gives user 301 response whenever user accesses old hostname
accessed uri is passed to new hostname. To use this filter one should adjust
target host name setting in war/WEB-INF/web.xml . It should point to new 
domain name. On how to deal with virtual host settings in tomcat refer to
http://www.ex-parrot.com/pete/tomcat-vhost.html


