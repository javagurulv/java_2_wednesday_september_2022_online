INSERT INTO recipes (id, dishName)
VALUES (001, 'Easy pancakes');

INSERT INTO ingredients (id, recipe_id, ingredient)
VALUES (001, 001,'plain flour');

INSERT INTO ingredients (id, recipe_id, ingredient)
VALUES (002, 001,'large eggs');

INSERT INTO ingredients (id, recipe_id, ingredient)
VALUES (003, 001,'milk');

INSERT INTO ingredients (id, recipe_id, ingredient)
VALUES (004, 001,'sunflower or vegetable oil');

INSERT INTO cooking_steps (recipe_id, step, instruction)
VALUES (001, 1, 'Put 100g plain flour, 2 large eggs, 300ml milk, 1 tbsp sunflower or vegetable oil and a pinch of salt into a bowl or large jug, then whisk to a smooth batter.');

INSERT INTO cooking_steps (recipe_id, step, instruction)
VALUES (001, 2, 'Set aside for 30 minutes to rest if you have time, or start cooking straight away.');

INSERT INTO cooking_steps (recipe_id, step, instruction)
VALUES (001, 3, 'Set a medium frying pan or crêpe pan over a medium heat and carefully wipe it with some oiled kitchen paper.');

INSERT INTO cooking_steps (recipe_id, step, instruction)
VALUES (001, 4, 'When hot, cook your pancakes for 1 min on each side until golden, keeping them warm in a low oven as you go.');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (001, 001, 100.0, 'g');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount)
VALUES (001, 002, 2.0);

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (001, 003, 300.0, 'ml');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (001, 004, 1.0, 'tbsp');