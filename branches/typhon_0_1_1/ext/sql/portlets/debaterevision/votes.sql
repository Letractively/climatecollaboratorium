Drop table if exists DebateItemVote;
Create table DebateItemVote(
    debateItemVoteId BIGINT primary key,
    debateItemId BIGINT not null,
    userId BIGINT not null
);

DROP table if exists DebateItemVoteStats;
Create table DebateItemVoteStats(
    debateItemVotesStats BIGINT primary key,
    debateItemId BIGINT not null,
    votesCount BIGINT not null default 0
);