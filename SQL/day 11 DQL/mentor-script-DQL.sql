SELECT * FROM driver;

SELECT * FROM food_order;

-- NOTES:
-- INNER JOIN: Shows intersect data (beririsan)
SELECT * FROM food_order fo
INNER JOIN driver d
ON fo.driver_id = d.id;

-- NOTES:
-- LEFT JOIN: Shows data based main table FROM (will show even if it's empty)
SELECT * FROM driver dr
LEFT JOIN food_order fo
ON fo.driver_id = dr.id;
-- depends on the selected table FROM
SELECT * FROM food_order fo
LEFT JOIN driver d
ON fo.driver_id = d.id;

-- NOTES:
-- AGGREGATION: get summary from table
--- COUNT: count data exist
--- recomended to count by id or all fiels *
SELECT COUNT(id) FROM driver;
--- SUM: calculate total
SELECT SUM(id) FROM driver;
--- AVG: calculate average
SELECT AVG(id) FROM driver;
--- MAX: get maximum value
SELECT MAX(id) FROM driver;
--- MIN: get maximum value
SELECT MIN(id) FROM driver;


-- NOTES:
-- GROUPING: combine same value
--- COUNT: count data exist
--- recomended to count by id or all fiels *
SELECT COUNT(id) FROM driver;
--- SUM: calculate total
SELECT SUM(id) FROM driver;
--- AVG: calculate average
SELECT AVG(id) FROM driver;
--- MAX: get maximum value
SELECT MAX(id) FROM driver;
--- MIN: get maximum value
SELECT MIN(id) FROM driver;
