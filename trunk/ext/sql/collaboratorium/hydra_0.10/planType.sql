ALTER TABLE PlanType ADD COLUMN defaultModelId bigint;
update PlanType set defaultModelId = 841 where planTypeId = 3;
