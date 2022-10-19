DELETE FROM bank.users;

DELETE FROM bank.bank_accounts;

DELETE FROM bank.accounts;

DELETE FROM bank.accounts
WHERE id = 1;

DELETE FROM bank.bank_accounts
WHERE id = 1;

DELETE FROM bank.users
WHERE user_id = 1;


DELETE FROM bank.accounts
WHERE balance = 10000;

DELETE FROM bank.bank_accounts
WHERE name LIKE "Vladislav";

DELETE FROM bank.users
WHERE password = "password";
