INSERT INTO users (id, email, created_at) VALUES (1, 'test001@test.com', now());
INSERT INTO users (id, email, created_at) VALUES (2, 'test002@test.com', now());

INSERT INTO account (id, user_id, balance, currency, account_number, created_at) VALUES (1, 1, 1000000, 'KRW', '2201-2222-221', now());
INSERT INTO account (id, user_id, balance, currency, account_number, created_at) VALUES (2, 2, 2.555, 'USD', '2201-2222-222', now());