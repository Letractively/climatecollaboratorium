SELECT COUNT(*) AS COUNT_VALUE FROM Plan
  LEFT JOIN
  (SELECT Plan.planId AS planId, COUNT(*) as positionsCount FROM Plan
  INNER JOIN PlanPosition ON Plan.planId = PlanPosition.planId WHERE true GROUP BY Plan.planId )
  AS PositionsVotes ON Plan.planId = PositionsVotes.planId
  INNER JOIN
  PlanAttribute as PA0 ON Plan.planId=PA0.planId AND PA0.attributeName='CO2' AND LEFT JOIN PlanAttribute as PA1 ON Plan.planId=PA1.planId AND PA1.attributeName='TEMP' AND INNER JOIN PlanAttribute as PA2 ON Plan.planId=PA2.planId AND PA2.attributeName='MIN_MITIGATION_COST' AND INNER JOIN PlanAttribute as PA3 ON Plan.planId=PA3.planId AND PA3.attributeName='MAX_MITIGATION_COST' AND LEFT JOIN PlanAttribute as PA4 ON Plan.planId=PA4.planId AND PA4.attributeName='EMISSIONS_DEVELOPED' AND LEFT JOIN PlanAttribute as PA5 ON Plan.planId=PA5.planId AND PA5.attributeName='EMISSIONS_DEVELOPING_A' AND LEFT JOIN PlanAttribute as PA6 ON Plan.planId=PA6.planId AND PA6.attributeName='EMISSIONS_DEVELOPING_B' AND LEFT JOIN PlanAttribute as PA7 ON Plan.planId=PA7.planId AND PA7.attributeName='SEA_LEVEL' AND INNER JOIN PlanAttribute as PA8 ON Plan.planId=PA8.planId AND PA8.attributeName='MAX_DAMAGE_COST' AND INNER JOIN PlanAttribute as PA9 ON Plan.planId=PA9.planId AND PA9.attributeName='MIN_DAMAGE_COST' AND   (SELECT COUNT(*) AS countAll FROM PlanVote) AS Votes WHERE Plan.published = ? AND Plan.name LIKE ? AND Plan.content LIKE ? AND Plan.userName LIKE ? AND IF (?, PositionsVotes.positionsCount >= ?, 1 ) AND ( IF (Votes.countAll > 0 AND Plan.votes IS NOT NULL, Plan.votes/Votes.countAll * 100, 0)  >= ? AND IF (Votes.countAll > 0 AND Plan.votes IS NOT NULL, Plan.votes/Votes.countAll * 100, 0)  <= ? ) AND IF (Plan.published > 0, Plan.publishDate >= ? AND Plan.publishDate <= ?, Plan.createDate >= ? AND Plan.createDate <= ? )