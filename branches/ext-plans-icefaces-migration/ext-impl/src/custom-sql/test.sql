select {plan.*}
from Plan LEFT Join {
	select Plan.planId AS planId,
	count(*) as positions