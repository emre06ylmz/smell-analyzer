--file count of a project
select count(*) from projects
where scope = 'FIL' and language = 'java' and project_uuid = 'AXZin23VCl93wm5yKTEJ';

--issue count of a project
select count(*) from issues
where project_uuid = 'AXZin23VCl93wm5yKTEJ';

--rule variety of a project
select count(distinct rule_id) as count from issues
where project_uuid = 'AXZin23VCl93wm5yKTEJ';

--different rules of a project
select rule_id, count(*) as count from issues
where project_uuid = 'AXZin23VCl93wm5yKTEJ'
group by rule_id
order by count(*) desc;

--developer experiences of a project
SELECT SUM(CASE WHEN followers < 100 THEN 1 ELSE 0 END) AS "Junior",
       SUM(CASE WHEN followers BETWEEN 101 AND 500 THEN 1 ELSE 0 END) AS "Middle",
       SUM(CASE WHEN followers BETWEEN 501 AND 1000 THEN 1 ELSE 0 END) AS "Senior",
       SUM(CASE WHEN followers > 1000 THEN 1 ELSE 0 END) AS "Expert"
FROM github_users
where uuid = 'AXZin23VCl93wm5yKTEJ';