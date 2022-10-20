UPDATE bank.accounts
SET balance = 0
WHERE id = 1;

UPDATE bank.bank_accounts
SET name = "Example",
surname = "Example" ,
personal_code = "000000-00003"
WHERE id = 1;

UPDATE bank.bank_accounts
SET personal_code = "000000-00003"
WHERE name LIKE "Vladislav";

UPDATE bank.bank_accounts
	SET personal_code = "000000-00003"
	WHERE name LIKE "Vladislav"
    OR id = 2;

UPDATE bank.users
SET personal_code = "000000-00005",
 password = "user"
 WHERE personal_code LIKE "000000-00002";

 UPDATE bank.users
SET personal_code = "000000-00005"
 WHERE personal_code LIKE "000000-00002"
 OR password LIKE "password";
