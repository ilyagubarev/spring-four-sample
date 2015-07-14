INSERT INTO bags(title) VALUES ('BAG 1');

INSERT INTO bags(title) VALUES ('BAG 2');

INSERT INTO items(bag_id, title) VALUES ((SELECT id FROM bags WHERE title = 'BAG 1'), 'ITEM 1');
