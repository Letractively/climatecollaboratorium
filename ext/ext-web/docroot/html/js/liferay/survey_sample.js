function renderSurvey(survey) {
	if (survey.type == 'SURVEYMONKEY_EMBEDDED') {
		
		var iframe = document.createElement('iframe');
		iframe.width="800";
		iframe.height="600";
		iframe.style="border:0px;padding-bottom:4px;";
		iframe.frameborder="0";
		iframe.src = survey.url;
		
		jQuery("#surveyMonkeyInfo div").get(0).appendChild(iframe);
		
		Liferay.Service.Surveys.UserSurvey.setUserViewedSurvey({userId: Liferay.ThemeDisplay.getUserId(), eventName: "sampleEvent"}, function () {});
		
	}
}

jQuery(document).ready(function() {
	//alert('ready');
	Liferay.Service.Surveys.UserSurvey.hasUserViewedSurveyForEvent({userId: Liferay.ThemeDisplay.getUserId(), eventName: "sampleEvent"}, function(result) {
		if (result.returnValue == 'true') {
			jQuery("#surveyMonkeyInfo div").html("<h1>User has already seen the survey</h1>");
		}
		else {
			Liferay.Service.Surveys.Survey.getSurvey(
					{eventName: 'sampleEvent'}, 
					function(survey) {
						renderSurvey(survey);
					});
			
		}
	});
});

function resetSurveyStatus() {
	Liferay.Service.Surveys.UserSurvey.removeUserViewedSurvey({userId: Liferay.ThemeDisplay.getUserId(), eventName: "sampleEvent"}, function () {});
}

//alert(Liferay.ThemeDisplay.getUserId());