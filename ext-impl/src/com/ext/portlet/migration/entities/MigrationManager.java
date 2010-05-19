/*
 * Copyright (c) 2010. M.I.T. All Rights Reserved
 * Licensed under the MIT license. Please see http://www.opensource.org/licenses/mit-license.php
 * or the license.txt file included in this distribution for the full text of the license.
 */

package com.ext.portlet.migration.entities;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.ext.portlet.migration.MigrationHelper;
import com.ext.portlet.migration.model.MigrationData;
import com.ext.portlet.migration.service.MigrationDataLocalServiceUtil;
import com.liferay.portal.PortalException;
import com.liferay.portal.SystemException;

public class MigrationManager {
	public final static String PLAN_QUESTION_ID = "qid1";
	
	private final List<AlternativeEntity> alternatives = new ArrayList<AlternativeEntity>();
	private final List<ArgumentEntity> arguments = new ArrayList<ArgumentEntity>();
	private final List<QuestionEntity> questions = new ArrayList<QuestionEntity>();
	private final List<AlternativeEntity> plans = new ArrayList<AlternativeEntity>();
	private final List<UserEntity> users = new ArrayList<UserEntity>();
	private final List<VoteEntity> votes = new ArrayList<VoteEntity>();
	
	Map<String, QuestionEntity> questionById = new HashMap<String, QuestionEntity>();
	Map<String, AlternativeEntity> alternativeById = new HashMap<String, AlternativeEntity>();
	Map<String, ArgumentEntity> argumentById = new HashMap<String, ArgumentEntity>();
	
	Map<QuestionEntity, List<AlternativeEntity>> questionChildren = new HashMap<QuestionEntity, List<AlternativeEntity>>();
	Map<AlternativeEntity, List<ArgumentEntity>> alternativeChildren = new HashMap<AlternativeEntity, List<ArgumentEntity>>();
	Map<ArgumentEntity, List<ArgumentEntity>> argumentChildren = new HashMap<ArgumentEntity, List<ArgumentEntity>>();
	
	public MigrationManager(File file) throws SAXException, IOException, ParserConfigurationException, XPathExpressionException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(file);
		initialize(doc);
	}
	
	public MigrationManager(long migrationId) throws PortalException, SystemException, ParserConfigurationException, SAXException, IOException, XPathExpressionException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		MigrationData migrationData = MigrationDataLocalServiceUtil.getMigrationData(migrationId);
		
		ByteArrayInputStream xmlStream = new ByteArrayInputStream(migrationData.getXml().getBytes());
		
		DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		Document doc = builder.parse(xmlStream);
		initialize(doc);
	}
	
	private void initialize(Document doc) throws XPathExpressionException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, NoSuchMethodException, InvocationTargetException {
		users.addAll(MigrationHelper.deserializeEntities(doc, UserEntity.NAME, UserEntity.class));
		arguments.addAll(MigrationHelper.deserializeEntities(doc, ArgumentEntity.NAME, ArgumentEntity.class));
		questions.addAll(MigrationHelper.deserializeEntities(doc, QuestionEntity.NAME, QuestionEntity.class));
		alternatives.addAll(MigrationHelper.deserializeEntities(doc, AlternativeEntity.NAME, AlternativeEntity.class));
		votes.addAll(MigrationHelper.deserializeEntities(doc, VoteEntity.NAME, VoteEntity.class));
		
		// set index for questions
		for(QuestionEntity question: questions) {
			questionById.put(question.getId(), question);
		}
		
		// set index and children mapping for alternatives
		for(AlternativeEntity alternative: alternatives) {
			alternativeById.put(alternative.getId(), alternative);
			QuestionEntity question = questionById.get(alternative.getQuestion());
			if (question != null) {
				List<AlternativeEntity> children = questionChildren.get(question);
				if (children == null) {
					children = new ArrayList<AlternativeEntity>();
					questionChildren.put(question, children);
				}
				children.add(alternative);	
			}
		}
		
		// set index and children mapping for arguments
		for (ArgumentEntity argument: arguments) {
			argumentById.put(argument.getId(), argument);
		}
		
		for (ArgumentEntity argument: arguments) {
			if (argument.getArgument() != null && !argument.getArgument().trim().equals("")) {
				ArgumentEntity parentArgument = argumentById.get(argument.getArgument());
				List<ArgumentEntity> parentChilds = argumentChildren.get(parentArgument);
				if (parentChilds == null) {
					parentChilds = new ArrayList<ArgumentEntity>();
					argumentChildren.put(parentArgument, parentChilds);
				}
				parentChilds.add(argument);	
			} 
			else if (argument.getAlternative() != null && !argument.getAlternative().trim().equals("")) {
				AlternativeEntity parentAlternative = alternativeById.get(argument.getAlternative());
				List<ArgumentEntity> parentChilds = alternativeChildren.get(parentAlternative);
				if (parentChilds == null) {
					parentChilds = new ArrayList<ArgumentEntity>();
					alternativeChildren.put(parentAlternative, parentChilds);
				}
				parentChilds.add(argument);
			}
		}
		List<QuestionEntity> questionPlan = new ArrayList<QuestionEntity>();
		for (QuestionEntity question: questions) {
			if (question.getId().equals(PLAN_QUESTION_ID)) {
			    questionPlan.add(question);
				if (questionChildren.containsKey(question)) {
					for (AlternativeEntity alternative: questionChildren.get(question)) {
						plans.add(alternative);
					}
				}
			}
		}
		questions.removeAll(questionPlan);
	}

	public static String getPlanQuestionId() {
		return PLAN_QUESTION_ID;
	}

	public List<AlternativeEntity> getAlternatives() {
		return alternatives;
	}

	public List<ArgumentEntity> getArguments() {
		return arguments;
	}

	public List<QuestionEntity> getQuestions() {
		return questions;
	}

	public List<AlternativeEntity> getPlans() {
		return plans;
	}

	public List<UserEntity> getUsers() {
		return users;
	}

	public List<VoteEntity> getVotes() {
		return votes;
	}

	public Map<String, QuestionEntity> getQuestionById() {
		return questionById;
	}

	public Map<String, AlternativeEntity> getAlternativeById() {
		return alternativeById;
	}

	public Map<String, ArgumentEntity> getArgumentById() {
		return argumentById;
	}

	public Map<QuestionEntity, List<AlternativeEntity>> getQuestionChildren() {
		return questionChildren;
	}

	public Map<AlternativeEntity, List<ArgumentEntity>> getAlternativeChildren() {
		return alternativeChildren;
	}

	public Map<ArgumentEntity, List<ArgumentEntity>> getArgumentChildren() {
		return argumentChildren;
	}

}
