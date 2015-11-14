# Introduction #

Testing of services developed in Liferay's Ext environment takes lots of time as to test them user has to:
  1. deploy the code to application server
  1. lanuch application server
  1. perform tests, often not related to jUnit in any way

Performing all mentioned steps takes 2 - 3 minutes or even more. Because of that, in order to make testing faster, separate "framework" has been developed.


# Details #

The biggest problem with testing of Ext services is very long initialization of Liferay core. To make this process faster and to allow integration with jUnit framework special abstract TestCase has been developed - BaseCollabTest:
  * http://code.google.com/p/climatecollaboratorium/source/browse/trunk/ext/ext-impl/test/org/climatecollaboratorium/test/BaseCollabTest.java

Mentioned class contains logic required to initialize minimal set of Liferay classes involved in handling persistence logic etc. All developed test cases should extend it. For examples on how to write unit tests please refer to:
  * http://code.google.com/p/climatecollaboratorium/source/browse/trunk/ext/ext-impl/test/com/ext/portlet/debaterevision/model/impl/DebateImplTest.java
  * http://code.google.com/p/climatecollaboratorium/source/browse/trunk/ext/ext-impl/test/com/ext/portlet/debaterevision/model/impl/DebateItemImplTest.java
  * http://code.google.com/p/climatecollaboratorium/source/browse/trunk/ext/ext-impl/test/com/ext/portlet/debaterevision/AllTests.java - this class is a test suite that gathers multiple tests into one execution unit

To launch a test run one of mentioned classes as a Junit application.

Currently (19.05.2010) full launch of testing framework takes around 10 - 15 sec on Intel i920.