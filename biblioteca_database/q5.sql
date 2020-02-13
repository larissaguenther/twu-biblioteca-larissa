SELECT member.name, COUNT(checkout_item.member_id) FROM checkout_item
LEFT JOIN member ON checkout_item.member_id = member.id
GROUP BY name HAVING COUNT (checkout_item.member_id) > 1;