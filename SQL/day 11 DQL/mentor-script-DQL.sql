SELECT * FROM driver;

SELECT * FROM food_order;

-- NOTES:
-- INNER JOIN: Shows intersect data (beririsan)
SELECT
	*
FROM
	food_order fo
INNER JOIN
	driver d
ON
	fo.driver_id = d.id;