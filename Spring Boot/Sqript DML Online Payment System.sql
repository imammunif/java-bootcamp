CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

-- 1.1 Create Super Admin ROLE first (Temporarily using a random UUID for created_by)
INSERT INTO t_m_user_role (id, code, name, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'SA', 'Super Admin', now(), uuid_generate_v4(), 0);

-- 1.2 Create Super Admin USER (Assign to SA Role)
INSERT INTO t_user (id, name, email, password, is_active, role_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'John Admin', 'admin@mail.com', '$2a$10$XPTr0L0xjhHx3.Q5Z6J1Ru5tYBZ0Y8k0yP3fE9pQ4uM8zJ3uL0eWi', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'SA' LIMIT 1), 
 now(), uuid_generate_v4(), 0);

-- 1.3 UPDATE the SA User to be created by ITSELF
UPDATE t_user 
SET created_by = id 
WHERE email = 'admin@mail.com';

-- 1.4 UPDATE the SA Role to be created by the SA USER
UPDATE t_m_user_role 
SET created_by = (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1) 
WHERE code = 'SA';

INSERT INTO t_m_user_role (id, code, name, created_at, created_by, version) VALUES
(uuid_generate_v4(), 'SYS', 'System', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'CUST', 'Customer', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'GA', 'Gateway Admin', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0);

INSERT INTO t_m_gateway (id, code, name, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'DNA', 'Dana', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'GPY', 'Gopay', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'SHP', 'Shopee', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'TKP', 'Tokopedia', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'LNK', 'LinkAja', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0);

INSERT INTO t_m_product (id, code, name, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'PULSA', 'Pulsa Pra Bayar', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'DATA', 'Paket Data', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'LSTRK', 'Token Listrik', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'AIRPM', 'Air PDAM', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0);

INSERT INTO t_m_transaction_status (id, code, name, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'PROCESS', 'In Process', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'PAY', 'Paid', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'REJECT', 'Rejected', now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0);

-- Gateway Admins (Created by Super Admin)
INSERT INTO t_user (id, name, email, password, is_active, role_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'Gateway Dana Admin', 'admin.dana@payment.com', '$2a$12$8KzNOvMDxck0WPdvdpdMs.bNo1AUHsdhh0jPiFx2zt1Qt1pVc9HM.', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'GA' LIMIT 1), now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'Gateway Gopay Admin', 'admin.gopay@payment.com', '$2a$12$8KzNOvMDxck0WPdvdpdMs.bNo1AUHsdhh0jPiFx2zt1Qt1pVc9HM.', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'GA' LIMIT 1), now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'Gateway Shopee Admin', 'admin.shopee@payment.com', '$2a$12$8KzNOvMDxck0WPdvdpdMs.bNo1AUHsdhh0jPiFx2zt1Qt1pVc9HM.', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'GA' LIMIT 1), now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'Gateway Tokopedia Admin', 'admin.tokopedia@payment.com', '$2a$12$8KzNOvMDxck0WPdvdpdMs.bNo1AUHsdhh0jPiFx2zt1Qt1pVc9HM.', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'GA' LIMIT 1), now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'Gateway LinkAja Admin', 'admin.linkaja@payment.com', '$2a$12$8KzNOvMDxck0WPdvdpdMs.bNo1AUHsdhh0jPiFx2zt1Qt1pVc9HM.', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'GA' LIMIT 1), now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0);

-- Insert Customers (temporarily created by SA)
INSERT INTO t_user (id, name, email, password, is_active, role_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'Wahid Customer', 'wahid@email.com', '$2a$10$XPTr0L0xjhHx3.Q5Z6J1Ru5tYBZ0Y8k0yP3fE9pQ4uM8zJ3uL0eWi', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'CUST' LIMIT 1), now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'Siti Nurhaliza', 'siti@email.com', '$2a$10$XPTr0L0xjhHx3.Q5Z6J1Ru5tYBZ0Y8k0yP3fE9pQ4uM8zJ3uL0eWi', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'CUST' LIMIT 1), now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 'Budi Santoso', 'budi@email.com', '$2a$10$XPTr0L0xjhHx3.Q5Z6J1Ru5tYBZ0Y8k0yP3fE9pQ4uM8zJ3uL0eWi', true, 
 (SELECT id FROM t_m_user_role WHERE code = 'CUST' LIMIT 1), now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0);

-- Update Customers to be created by themselves
UPDATE t_user SET created_by = id WHERE email IN ('wahid@email.com', 'siti@email.com', 'budi@email.com');

INSERT INTO t_gateway_user (id, gateway_id, user_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 
 (SELECT id FROM t_m_gateway WHERE code = 'DNA' LIMIT 1),
 (SELECT id FROM t_user WHERE email = 'admin.dana@payment.com' LIMIT 1),
 now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 
 (SELECT id FROM t_m_gateway WHERE code = 'GPY' LIMIT 1),
 (SELECT id FROM t_user WHERE email = 'admin.gopay@payment.com' LIMIT 1),
 now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 
 (SELECT id FROM t_m_gateway WHERE code = 'SHP' LIMIT 1),
 (SELECT id FROM t_user WHERE email = 'admin.shopee@payment.com' LIMIT 1),
 now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 
 (SELECT id FROM t_m_gateway WHERE code = 'TKP' LIMIT 1),
 (SELECT id FROM t_user WHERE email = 'admin.tokopedia@payment.com' LIMIT 1),
 now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0),
(uuid_generate_v4(), 
 (SELECT id FROM t_m_gateway WHERE code = 'LNK' LIMIT 1),
 (SELECT id FROM t_user WHERE email = 'admin.linkaja@payment.com' LIMIT 1),
 now(), (SELECT id FROM t_user WHERE email = 'admin@mail.com' LIMIT 1), 0);

INSERT INTO t_transaction (id, code, account_number, total_bill, customer_id, gateway_id, product_id, status_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'TRX-001', '08123456789', 50000,
 (SELECT id FROM t_user WHERE email = 'wahid@email.com' LIMIT 1),
 (SELECT id FROM t_m_gateway WHERE code = 'DNA' LIMIT 1),
 (SELECT id FROM t_m_product WHERE code = 'PULSA' LIMIT 1),
 (SELECT id FROM t_m_transaction_status WHERE code = 'PAY' LIMIT 1), now() - interval '2 hours',
 (SELECT id FROM t_user WHERE email = 'wahid@email.com' LIMIT 1), 1);

INSERT INTO t_transaction (id, code, account_number, total_bill, customer_id, gateway_id, product_id, status_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'TRX-002', '08129876543', 100000,
 (SELECT id FROM t_user WHERE email = 'siti@email.com' LIMIT 1),
 (SELECT id FROM t_m_gateway WHERE code = 'GPY' LIMIT 1),
 (SELECT id FROM t_m_product WHERE code = 'DATA' LIMIT 1),
 (SELECT id FROM t_m_transaction_status WHERE code = 'REJECT' LIMIT 1), now() - interval '90 minutes',
 (SELECT id FROM t_user WHERE email = 'siti@email.com' LIMIT 1), 1);

INSERT INTO t_transaction (id, code, account_number, total_bill, customer_id, gateway_id, product_id, status_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'TRX-003', '123456789012', 200000,
 (SELECT id FROM t_user WHERE email = 'budi@email.com' LIMIT 1),
 (SELECT id FROM t_m_gateway WHERE code = 'SHP' LIMIT 1),
 (SELECT id FROM t_m_product WHERE code = 'LSTRK' LIMIT 1),
 (SELECT id FROM t_m_transaction_status WHERE code = 'PROCESS' LIMIT 1),
 now() - interval '30 minutes', 
 (SELECT id FROM t_user WHERE email = 'budi@email.com' LIMIT 1), 0);

INSERT INTO t_transaction (id, code, account_number, total_bill, customer_id, gateway_id, product_id, status_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'TRX-004', 'PDAM-001', 150000,
 (SELECT id FROM t_user WHERE email = 'wahid@email.com' LIMIT 1),
 (SELECT id FROM t_m_gateway WHERE code = 'TKP' LIMIT 1),
 (SELECT id FROM t_m_product WHERE code = 'AIRPM' LIMIT 1),
 (SELECT id FROM t_m_transaction_status WHERE code = 'PAY' LIMIT 1),
 now() - interval '3 hours', 
 (SELECT id FROM t_user WHERE email = 'wahid@email.com' LIMIT 1), 1);

INSERT INTO t_transaction (id, code, account_number, total_bill, customer_id, gateway_id, product_id, status_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 'TRX-005', '081333444555', 75000,
 (SELECT id FROM t_user WHERE email = 'siti@email.com' LIMIT 1),
 (SELECT id FROM t_m_gateway WHERE code = 'LNK' LIMIT 1),
 (SELECT id FROM t_m_product WHERE code = 'PULSA' LIMIT 1),
 (SELECT id FROM t_m_transaction_status WHERE code = 'PAY' LIMIT 1),
 now() - interval '45 minutes', 
 (SELECT id FROM t_user WHERE email = 'siti@email.com' LIMIT 1), 1);

INSERT INTO t_transaction_status_history (id, status_id, transaction_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'PROCESS' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-001' LIMIT 1), 
 now() - interval '2 hours', 
 (SELECT id FROM t_user WHERE email = 'wahid@email.com' LIMIT 1), 0), (uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'PAY' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-001' LIMIT 1), 
 now() - interval '1 hour 50 minutes', 
 (SELECT id FROM t_user WHERE email = 'admin.dana@payment.com' LIMIT 1), 0);

INSERT INTO t_transaction_status_history (id, status_id, transaction_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'PROCESS' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-002' LIMIT 1), 
 now() - interval '90 minutes', 
 (SELECT id FROM t_user WHERE email = 'siti@email.com' LIMIT 1), 0),

(uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'REJECT' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-002' LIMIT 1), 
 now() - interval '75 minutes', 
 (SELECT id FROM t_user WHERE email = 'admin.gopay@payment.com' LIMIT 1), 0);

INSERT INTO t_transaction_status_history (id, status_id, transaction_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'PROCESS' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-003' LIMIT 1), 
 now() - interval '30 minutes', 
 (SELECT id FROM t_user WHERE email = 'budi@email.com' LIMIT 1), 0);

INSERT INTO t_transaction_status_history (id, status_id, transaction_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'PROCESS' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-004' LIMIT 1), 
 now() - interval '3 hours', 
 (SELECT id FROM t_user WHERE email = 'wahid@email.com' LIMIT 1),  0),
(uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'PAY' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-004' LIMIT 1), 
 now() - interval '2 hours 55 minutes', 
 (SELECT id FROM t_user WHERE email = 'admin.tokopedia@payment.com' LIMIT 1), 0);

INSERT INTO t_transaction_status_history (id, status_id, transaction_id, created_at, created_by, version) VALUES 
(uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'PROCESS' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-005' LIMIT 1), 
 now() - interval '45 minutes', 
 (SELECT id FROM t_user WHERE email = 'siti@email.com' LIMIT 1), 0),
(uuid_generate_v4(), 
 (SELECT id FROM t_m_transaction_status WHERE code = 'PAY' LIMIT 1),
 (SELECT id FROM t_transaction WHERE code = 'TRX-005' LIMIT 1), 
 now() - interval '40 minutes', 
 (SELECT id FROM t_user WHERE email = 'admin.linkaja@payment.com' LIMIT 1), 0);