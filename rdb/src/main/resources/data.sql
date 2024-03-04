INSERT INTO users (id, email, created_at) VALUES (1, 'test001@test.com', now());
INSERT INTO users (id, email, created_at) VALUES (2, 'test002@test.com', now());

INSERT INTO account (id, user_id, balance, currency, account_number, created_at) VALUES (1, 1, 1000, 'KRW', '2201-2222-221', now());
INSERT INTO account (id, user_id, balance, currency, account_number, created_at) VALUES (2, 2, 2.555, 'USD', '2201-2222-222', now());


INSERT INTO merchant (id, name, created_at) VALUES ('STARBUCKS_BS', '스타벅스 부평삼거리점', now());
INSERT INTO merchant (id, name, created_at) VALUES ('STARBUCKS_PANGYO', '스타벅스 판교', now());

INSERT INTO merchant_fee (id, merchant_id, currency, fees, created_at) VALUES (1, 'STARBUCKS_BS', 'KRW', 100, now());
INSERT INTO merchant_fee (id, merchant_id, currency, fees, created_at) VALUES (2, 'STARBUCKS_BS', 'USD', 200.5, now());
INSERT INTO merchant_fee (id, merchant_id, currency, fees, created_at) VALUES (3, 'STARBUCKS_PANGYO', 'KRW', 1000, now());
INSERT INTO merchant_fee (id, merchant_id, currency, fees, created_at) VALUES (4, 'STARBUCKS_PANGYO', 'USD', 200.1, now());
