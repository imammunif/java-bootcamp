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