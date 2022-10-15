INSERT INTO recipes (id, dishName)
VALUES (001, 'Easy pancakes');

INSERT INTO ingredients (ingredient)
VALUES ('plain flour');

INSERT INTO ingredients (ingredient)
VALUES ('large eggs');

INSERT INTO ingredients (ingredient)
VALUES ('milk');

INSERT INTO ingredients (ingredient)
VALUES ('sunflower or vegetable oil');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (001, 1, 'Put 100g plain flour, 2 large eggs, 300ml milk, 1 tbsp sunflower or vegetable oil and a pinch of salt into a bowl or large jug, then whisk to a smooth batter.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (001, 2, 'Set aside for 30 minutes to rest if you have time, or start cooking straight away.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (001, 3, 'Set a medium frying pan or crepe pan over a medium heat and carefully wipe it with some oiled kitchen paper.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (001, 4, 'When hot, cook your pancakes for 1 min on each side until golden, keeping them warm in a low oven as you go.');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (001, 001, 100.0, 'g');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount)
VALUES (001, 002, 2.0);

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (001, 003, 300.0, 'ml');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (001, 004, 1.0, 'tbsp');


INSERT INTO recipes (id, dishName)
VALUES (002, 'Easy beef hotpot');

INSERT INTO ingredients (ingredient)
VALUES ('onion');

INSERT INTO ingredients (ingredient)
VALUES ('carrots');

INSERT INTO ingredients (ingredient)
VALUES ('potatoes');

INSERT INTO ingredients (ingredient)
VALUES ('lean minced beef');

INSERT INTO ingredients (ingredient)
VALUES ('beef stock cubes');

INSERT INTO ingredients (ingredient)
VALUES ('can baked beans');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (002, 1, 'Cut each onion into eight wedges. Roughly chop the carrots and cut the potatoes into large chunks. Put the kettle on.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (002, 2, 'Heat a large non-stick pan, add the mince and fry quickly, stirring all the time, until evenly browned. Crumble in the stock cubes and mix well. Add the prepared vegetables, stir them around, then pour in 900ml/1½ pints of hot water from the kettle. Bring to the boil.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (002, 3, 'Reduce the heat, cover and simmer for 25-30 minutes, until the veg are tender. Stir in the baked beans and a generous splash of Worcestershire sauce and heat through. Taste and add salt and pepper if necessary.');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (002, 005, 2.0, '');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (002, 006, 300.0, 'g');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (002, 007, 1.0, 'kg');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (002, 008, 450.0, 'g');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (002, 009, 2.0, '');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (002, 010, 400.0, 'g');


INSERT INTO recipes (id, dishName)
VALUES (003, 'Easy chocolate molten cakes');

INSERT INTO ingredients (ingredient)
VALUES ('butter');

INSERT INTO ingredients (ingredient)
VALUES ('dark chocolate, chopped');

INSERT INTO ingredients (ingredient)
VALUES ('light brown soft sugar');

INSERT INTO ingredients (ingredient)
VALUES ('vanilla extract');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (003, 1, 'Heat oven to 200C/180C fan/gas 6. Butter 6 dariole moulds or basins well and place on a baking tray.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (003, 2, 'Put 100g butter and 100g chopped dark chocolate in a heatproof bowl and set over a pan of hot water (or alternatively put in the microwave and melt in 30 second bursts on a low setting) and stir until smooth. Set aside to cool slightly for 15 mins.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (003, 3, 'Using an electric hand whisk, mix in 150g light brown soft sugar, then 3 large eggs, one at a time, followed by ½ tsp vanilla extract and finally 50g plain flour. Divide the mixture among the darioles or basins.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (003, 4, 'You can now either put the mixture in the fridge, or freezer until you are ready to bake them. Can be cooked straight from frozen for 16 mins, or bake now for 10-12 mins until the tops are firm to the touch but the middles still feel squidgy.');

INSERT INTO cooking_steps (recipe_id, step_order, instruction)
VALUES (003, 5, 'Carefully run a knife around the edge of each pudding, then turn out onto serving plates');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (003, 011, 100.0, 'g');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (003, 012, 100.0, 'g');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (003, 013, 150.0, 'g');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (003, 014, 0.5, 'tbsp');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (003, 002, 3.0, '');

INSERT INTO recipes_to_ingredients (recipe_id, ingredient_id, amount, measurement)
VALUES (003, 001, 50.0, 'g');


