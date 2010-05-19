<%--
  ~ Copyright (c) 2010. M.I.T. All Rights Reserved
  ~ Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
  ~ or the license.txt file included in this distribution for the full text of the license.
  --%>

<%
/**
 *
 *
 * User profile page.
 *
 * @author TCSDEVELOPER
 * @version 1.0
 */
%>
<%@ include file="/html/common/init.jsp" %>



<script type="text/javascript">
	jQuery(document).ready(function() {
		alert("This page is a mock up, out of scope.");
	});



</script>
				<div class="tabContent">
                    <div class="tabinner">
                        <div class="modelContent">
                        	<div class="profileh2">
                            	<h2><span class="hidden">Debates</span></h2>
                            </div>
                            <div class="profileContent">
                                <div class="floatLeft leftContent">
                                	<div class="corcen_top" id="profile">
                                    	<div class="corcen_bottom">
                                        	<div class="corcen">
                                            	<div class="topBtn">
                                                	<a href="javascript:;" class="editProfileBtn"><span class="hidden">Edit Profile</span></a>
                                                </div>
												<div class="userProfile">
                                                	<div class="floatLeft userFace">
                                                    	<img src="/collaboratorium-theme/images/userFace_pic.gif" alt="userFace" />
                                                    </div>
                                                    <div class="floatLeft userProfileInfo">
                                                    	<dl>
                                                        	<dt>Handler : </dt>
                                                            <dd><%=ParamUtil.get(request,"userId",-1) %></dd>
                                                    <div class="clear"></div>
                                                            <dt>Member since : </dt>
                                                            <dd>05/ 05/ 2008</dd>
                                                    <div class="clear"></div>
                                                            <dt>Name : </dt>
                                                            <dd>English Elbert </dd>
                                                    <div class="clear"></div>
                                                            <dt>Address : </dt>
                                                            <dd>12345 xxxxx xxxxxxx xxxxxxxxxxx<br />xxxxxx. 123, England</dd>
                                                    <div class="clear"></div>
                                                            <dt>Email : </dt>
                                                            <dd>engelbert@gmail.com</dd>
                                                        </dl>
                                                    </div>
                                                    <div class="clear"></div>
                                                </div>
                                                <div class="aboutMe">
                                                	<h4><span class="hidden">ABOUT ME</span></h4>
                                                    <p class="first">Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Morbi commodo, ipsum sed pharetra gravida, orci magna rhoncus neque, id pulvinar odio lorem non turpis. Nullam sit amet enim. Suspendisse id velit vitae ligula volutpat condimentum. Aliquam erat volutpat. Sed quis velit. Nulla facilisi. Nulla libero. Vivamus pharetra posuere sapien. Nam consectetuer. Sed aliquam, nunc eget euismod ullamcorper, lectus nunc ullamcorper orci, fermentum bibendum enim nibh eget ipsum. Donec porttitor ligula eu dolor. </p>
                                                    <p>Maecenas vitae nulla consequat libero cursus venenatis. Nam magna enim, accumsan eu, blandit sed, blandit a, eros.Quisque facilisis erat a dui. Nam malesuada ornare dolor. Cras gravida, diam sit amet rhoncus ornare, erat elit consectetuer erat, id egestas pede nibh eget odio. Proin tincidunt, velit vel porta elementum, magna diam molestie sapien, non aliquet massa pede eu diam. Aliquam iaculis. Fusce et ipsum et nulla tristique facilisis.</p>
                                                    <p>Maecenas vitae nulla consequat libero cursus venenatis. Nam magna enim, accumsan eu, blandit sed, blandit a, eros.Quisque facilisis erat a dui. Nam malesuada ornare dolor. Cras gravida, diam sit amet rhoncus ornare, erat elit consectetuer erat, id egestas pede nibh eget odio. Proin tincidunt, velit vel porta elementum, magna diam molestie sapien, non aliquet massa pede eu diam. Aliquam iaculis. Fusce et ipsum et nulla tristique facilisis.</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="corcen_top marginTop18 hidden" id="myWatch">
                                    	<div class="corcen_bottom">
                                        	<div class="corcen">
                                            	<div class="watches">
                                                    <div class="floatLeft titleTxt">
                                                        <h3><span class="hidden">My watches</span></h3>
                                                    </div>
                                                </div>
                                                <div class="table">
                                                    <table border="0" cellpadding="0" cellspacing="0">
                                                        <tr>
                                                            <td class="first"><strong>Plan</strong></td>
                                                            <td><a href="plan.html" class="black">WEO 450 ppm</a></td>
                                                            <td><a href="javascript:;"><!-- img src="i/delete_btn.png" alt="delete" /--></a></td>
                                                        </tr>
                                                        <tr class="even">
                                                            <td class="first"><strong>Plan</strong></td>
                                                            <td><a href="plan.html" class="black">BAU</a></td>
                                                            <td><a href="javascript:;"><!-- img src="i/delete_btn.png" alt="delete" /--></a></td>
                                                        </tr>
                                                        <tr>
                                                            <td class="first"><strong>Debate</strong></td>
                                                            <td><a href="debates.html" class="black">What kinds of technology solutions should be adopted to reduce emissions? </a></td>
                                                            <td><a href="javascript:;"><!-- img src="i/delete_btn.png" alt="delete" /--></a></td>
                                                        </tr>
                                                    </table>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- end .leftContent -->
                                <div class="floatRight rightContent">
                                	<div class="corcen_top" id="group">
                                    	<div class="corcen_bottom">
                                        	<div class="corcen">
                                            	<div class="groupTitle">
                                                    <div class="floatLeft titleTxt">
                                                        <h3><span class="hidden">engelberts Group</span></h3>
                                                    </div>
                                                </div>
                                                <ul>
                                                    <li>- Administrator of the "<a href="plan_detail.html" class="blue"><strong>WEO 450 ppm</strong></a>" plan community.</li>
                                                    <li>- Member of the "<a href="plan_detail.html" class="blue"><strong>350 or bust</strong></a>" plan community.</li>
                                                    <li>- Member of the "<strong>Argument moderator</strong>" community.</li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="corcen_top marginTop18" id="history">
                                    	<div class="corcen_bottom">
                                        	<div class="corcen">
                                            	<div class="hostoryTitle">
                                                    <div class="floatLeft titleTxt">
                                                        <h3><span class="hidden">engelberts Group</span></h3>
                                                    </div>
                                                </div>
                                            	<table border="0" cellpadding="0" cellspacing="0">
                                                	<thead>
                                                    	<tr>
                                                        	<th class="first">Date</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    	<tr>
                                                        	<td>11/19/09</td>
                                                            <td class="last">Added <strong>comment</strong> on Plan <a href="plan_detail.html" class="blue">BAU</a></td>
                                                        </tr>
                                                        <tr class="even">
                                                        	<td>11/19/09</td>
                                                            <td class="last">Voted for Plan <a href="plan_detail.html" class="blue">WEO 450 ppm</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td>11/19/09</td>
                                                            <td class="last">Finalized Plan <a href="plan_detail.html" class="blue">WEO 450 oom</a></td>
                                                        </tr>
                                                        <tr class="even">
                                                        	<td>11/19/09</td>
                                                            <td class="last">Added comment on Plan <a href="plan_detail.html" class="blue">WEO 450 ppm</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td>11/19/09</td>
                                                            <td class="last">Added <strong>argument</strong> in support of <br /><strong>Renewable</strong> to debate "<strong>What technology <br />portfolio should we choose</strong>?"</td>
                                                        </tr>
                                                        <tr class="even">
                                                        	<td>11/19/09</td>
                                                            <td class="last">Granted access to tmalone on <br />Plan <a href="plan_detail.html" class="blue">WEO 450 ppm</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td>11/19/09</td>
                                                            <td class="last">Granted access to qwerty12345 on<br /> Plan <a href="plan_detail.html" class="blue">WEO 450 ppm</a></td>
                                                        </tr>
                                                        <tr class="even">
                                                        	<td colspan="2">
                                                            	<a href="plan.html" class="viewAll floatLeft">View All</a>
                                                                <div class="pageNum floatRight">
                                                                	<span class="previous black">Previous</span>
                                    								<a href="javascript:;" class="next black">Next</a>
                                                                </div>
                                                            </td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="corcen_top marginTop18 hidden" id="monitor">
                                    	<div class="corcen_bottom">
                                        	<div class="corcen">
                                            	<div class="monitor">
                                                    <div class="floatLeft titleTxt">
                                                        <h3><span class="hidden">engelberts Group</span></h3>
                                                    </div>
                                                </div>
                                            	<table border="0" cellpadding="0" cellspacing="0">
                                                	<thead>
                                                    	<tr>
                                                        	<th class="first">Date</th>
                                                            <th>Action</th>
                                                        </tr>
                                                    </thead>
                                                    <tbody>
                                                    	<tr>
                                                        	<td>11/19/09</td>
                                                            <td class="last">Added <strong>comment</strong> on Plan <a href="plan_detail.html" class="blue">BAU</a></td>
                                                        </tr>
                                                        <tr class="even">
                                                        	<td>11/19/09</td>
                                                            <td class="last">Voted for Plan <a href="plan_detail.html" class="blue">WEO 450 ppm</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td>11/19/09</td>
                                                            <td class="last">Finalized Plan <a href="plan_detail.html" class="blue">WEO 450 oom</a></td>
                                                        </tr>
                                                        <tr class="even">
                                                        	<td>11/19/09</td>
                                                            <td class="last">Added comment on Plan <a href="plan_detail.html" class="blue">WEO 450 ppm</a></td>
                                                        </tr>
                                                        <tr>
                                                        	<td>11/19/09</td>
                                                            <td class="last">Finalized Plan <a href="plan_detail.html" class="blue">WEO 450 oom</a></td>
                                                        </tr>
                                                        <tr class="even">
                                                        	<td>11/19/09</td>
                                                            <td class="last">Granted access to tmalone on <br />Plan <a href="plan_detail.html" class="blue">WEO 450 ppm</a></td>
                                                        </tr>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <!-- end .rightContent -->
                                <div class="clear"></div>
                            </div>
                        </div>
                        <!-- end .comparsion -->
                    </div>
                </div>

                <div class="clear"></div>