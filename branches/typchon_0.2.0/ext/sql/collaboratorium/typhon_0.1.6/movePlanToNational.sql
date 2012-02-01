Update PlanMeta set ContestPhase = 9, planTypeId = 4 where planId = 15056;

Update DiscussionCategoryGroup set url = '/web/guest/plans/-/plans/contestId/5/planId/15056#plans=tab:comments' where id_ = 15056;

delete from PlanAttribute where planId = 15056 and attributeName = 'REGION';
insert into PlanAttribute VALUES (124670, 15056, 'REGION', 'Africa');


update Counter set currentId = (SELECT MAX(pk) FROM ActivitySubscription) + 1000 where name LIKE '%ActivitySubscription%';

DELETE FROM PlanAttribute WHERE planId in (15101, 15102, 15103, 15104, 15105, 15106, 15107, 15108, 15109, 15110, 15112, 15111) AND attributeName LIKE '%ribbon%';
INSERT into PlanAttribute VALUES (11000, 15101, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11001, 15101, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the global contest');

INSERT into PlanAttribute VALUES (11002, 15102, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11003, 15102, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the global contest');

INSERT into PlanAttribute VALUES (11004, 15103, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11005, 15103, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the global contest');

INSERT into PlanAttribute VALUES (11006, 15104, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11007, 15104, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the global contest');

INSERT into PlanAttribute VALUES (11008, 15105, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11009, 15105, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the global contest');

INSERT into PlanAttribute VALUES (11010, 15106, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11011, 15106, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the global contest');

INSERT into PlanAttribute VALUES (11012, 15107, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11013, 15107, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the national contest');

INSERT into PlanAttribute VALUES (11014, 15108, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11015, 15108, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the national contest');

INSERT into PlanAttribute VALUES (11016, 15109, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11017, 15109, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the national contest');

INSERT into PlanAttribute VALUES (11018, 15110, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11019, 15110, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the national contest');

INSERT into PlanAttribute VALUES (11020, 15111, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11021, 15111, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the national contest');

INSERT into PlanAttribute VALUES (11022, 15112, 'PLAN_RIBBON', '1');
INSERT into PlanAttribute VALUES (11023, 15112, 'PLAN_RIBBON_TEXT', 'Finalist from the first phase of the national contest');
