Update PlanMeta set ContestPhase = 9, planTypeId = 4 where planId = 15056;

Update DiscussionCategoryGroup set url = '/web/guest/plans/-/plans/contestId/5/planId/15056#plans=tab:comments' where id_ = 15056;

delete from PlanAttribute where planId = 15056 and attributeName = 'REGION';
insert into PlanAttribute VALUES (124670, 15056, 'REGION', 'Africa');