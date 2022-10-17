SELECT * FROM accounts;

SELECT id FROM accounts;

SELECT balance FROM accounts;

SELECT * FROM accounts
WHERE balance = 10000 AND id = 1;

SELECT * FROM accounts
WHERE balance = 10000 OR id = 1;

SELECT * FROM accounts
ORDER BY balance;

SELECT * FROM accounts
LIMIT 1;

SELECT * FROM accounts
INNER JOIN bank_accounts
ON bank_accounts.id = accounts.id
ORDER BY balance;



SELECT * FROM bank_accounts ;

SELECT id FROM bank_accounts;

SELECT personal_code FROM bank_accounts;

SELECT * FROM bank_accounts
WHERE name LIKE "Vladislav" AND id = 1;

SELECT * FROM bank_accounts
WHERE name LIKE "Vladislav" OR id = 1;

SELECT * FROM bank_accounts
ORDER BY personal_code;

SELECT * FROM bank_accounts
LIMIT 1;

SELECT * FROM bank_accounts
INNER JOIN users
ON users.user_id = bank_accounts.id
ORDER BY id;

SELECT * FROM bank_accounts
INNER JOIN users
ON users.user_id = bank_accounts.id
INNER JOIN accounts
ON accounts.id = bank_accounts.id;



SELECT * FROM users;

SELECT user_id FROM users;

SELECT personal_code FROM users;

SELECT * FROM users
WHERE personal_code LIKE "000000-00002" AND password LIKE "user";

SELECT * FROM users
WHERE personal_code LIKE "000000-00002" OR password LIKE "user";

SELECT * FROM users
ORDER BY personal_code;

SELECT * FROM users
LIMIT 1;

SELECT * FROM users
INNER JOIN bank_accounts
ON bank_accounts.id = users.user_id
ORDER BY id;

SELECT * FROM users
INNER JOIN bank_accounts
ON bank_accounts.id = users.user_id
INNER JOIN accounts
ON accounts.id = bank_accounts.id;