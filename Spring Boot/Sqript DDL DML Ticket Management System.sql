CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE t_m_user_role (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(20) NOT NULL UNIQUE,
	code VARCHAR(5) NOT NULL UNIQUE,
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_m_company (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(35) NOT NULL,
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_m_user (
    id VARCHAR(36) PRIMARY KEY,
	full_name VARCHAR(50) NOT NULL,
	email VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(200) NOT NULL,
	role_id VARCHAR(36) NOT NULL REFERENCES t_m_user_role(id),
	company_id VARCHAR(36) NOT NULL REFERENCES t_m_company(id),
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_pic_customer (
    id VARCHAR(36) PRIMARY KEY,
	pic_id VARCHAR(36) NOT NULL REFERENCES t_m_user(id),
	customer_id VARCHAR(36) NOT NULL REFERENCES t_m_user(id),
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP,
    UNIQUE(pic_id, customer_id)
);

CREATE TABLE t_m_product (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(40) NOT NULL,
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_product_customer (
    id VARCHAR(36) PRIMARY KEY,
	customer_id VARCHAR(36) NOT NULL REFERENCES t_m_user(id),
	product_id VARCHAR(36) NOT NULL REFERENCES t_m_product(id),
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_m_ticket_status (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(35) NOT NULL,
	code VARCHAR(5) NOT NULL UNIQUE,
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_ticket (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(5) NOT NULL UNIQUE,
	title VARCHAR(50) NOT NULL,
	description TEXT NOT NULL,
	expired_date DATE NOT NULL,
	status_id VARCHAR(36) NOT NULL REFERENCES t_m_ticket_status(id),
	customer_id VARCHAR(36) NOT NULL REFERENCES t_m_user(id),
	product_id VARCHAR(36) NOT NULL REFERENCES t_m_product(id),
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_ticket_status_history (
    id VARCHAR(36) PRIMARY KEY,
	status_id VARCHAR(36) NOT NULL REFERENCES t_m_ticket_status(id),
	ticket_id VARCHAR(50) NOT NULL REFERENCES t_ticket(id),
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_ticket_message (
    id VARCHAR(36) PRIMARY KEY,
    message TEXT NOT NULL,
	user_id VARCHAR(36) NOT NULL REFERENCES t_m_user(id),
	ticket_id VARCHAR(36) NOT NULL REFERENCES t_ticket(id),
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

INSERT INTO t_m_user_role (id, name, code, version, created_by, created_at) VALUES 
(uuid_generate_v4(), 'Super Admin', 'SA', 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Person In Charge', 'PIC', 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Customer', 'CUST', 0, uuid_generate_v4(), now());

INSERT INTO t_m_company (id, name, version, created_by, created_at) VALUES 
(uuid_generate_v4(), 'Tech Solutions Inc', 0, uuid_generate_v4(), now());

INSERT INTO t_m_product (id, name, version, created_by, created_at) VALUES 
(uuid_generate_v4(), 'Learning Management System', 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Asset Management System', 0, uuid_generate_v4(), now());

INSERT INTO t_m_ticket_status (id, name, code, version, created_by, created_at) VALUES 
(uuid_generate_v4(), 'Open', 'OPEN', 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Resolved', 'RSLV', 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Closed', 'CLSD', 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Re-open', 'REOP', 0, uuid_generate_v4(), now());

INSERT INTO t_m_user (id, full_name, email, password, role_id, company_id, version, created_by, created_at) VALUES 
(uuid_generate_v4(), 'Super Admin User', 'admin@sys.com', 'pass123', 
    (SELECT id FROM t_m_user_role WHERE code = 'SA' LIMIT 1), 
    (SELECT id FROM t_m_company LIMIT 1), 0, uuid_generate_v4(), now()),

(uuid_generate_v4(), 'PIC One', 'pic1@sys.com', 'pass123', 
    (SELECT id FROM t_m_user_role WHERE code = 'PIC' LIMIT 1), 
    (SELECT id FROM t_m_company LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'PIC Two', 'pic2@sys.com', 'pass123', 
    (SELECT id FROM t_m_user_role WHERE code = 'PIC' LIMIT 1), 
    (SELECT id FROM t_m_company LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Customer One', 'cust1@client.com', 'pass123', 
    (SELECT id FROM t_m_user_role WHERE code = 'CUST' LIMIT 1), 
    (SELECT id FROM t_m_company LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Customer Two', 'cust2@client.com', 'pass123', 
    (SELECT id FROM t_m_user_role WHERE code = 'CUST' LIMIT 1), 
    (SELECT id FROM t_m_company LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Customer Three', 'cust3@client.com', 'pass123', 
    (SELECT id FROM t_m_user_role WHERE code = 'CUST' LIMIT 1), 
    (SELECT id FROM t_m_company LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'Customer Four', 'cust4@client.com', 'pass123', 
    (SELECT id FROM t_m_user_role WHERE code = 'CUST' LIMIT 1), 
    (SELECT id FROM t_m_company LIMIT 1), 0, uuid_generate_v4(), now());

INSERT INTO t_pic_customer (id, pic_id, customer_id, version, created_by, created_at) VALUES 
(uuid_generate_v4(), (SELECT id FROM t_m_user WHERE email = 'pic1@sys.com' LIMIT 1), (SELECT id FROM t_m_user WHERE email = 'cust1@client.com' LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), (SELECT id FROM t_m_user WHERE email = 'pic1@sys.com' LIMIT 1), (SELECT id FROM t_m_user WHERE email = 'cust2@client.com' LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), (SELECT id FROM t_m_user WHERE email = 'pic2@sys.com' LIMIT 1), (SELECT id FROM t_m_user WHERE email = 'cust3@client.com' LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), (SELECT id FROM t_m_user WHERE email = 'pic2@sys.com' LIMIT 1), (SELECT id FROM t_m_user WHERE email = 'cust4@client.com' LIMIT 1), 0, uuid_generate_v4(), now());

INSERT INTO t_product_customer (id, customer_id, product_id, version, created_by, created_at) VALUES 
(uuid_generate_v4(), (SELECT id FROM t_m_user WHERE email = 'cust1@client.com' LIMIT 1), (SELECT id FROM t_m_product WHERE name = 'Learning Management System' LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), (SELECT id FROM t_m_user WHERE email = 'cust2@client.com' LIMIT 1), (SELECT id FROM t_m_product WHERE name = 'Learning Management System' LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), (SELECT id FROM t_m_user WHERE email = 'cust3@client.com' LIMIT 1), (SELECT id FROM t_m_product WHERE name = 'Asset Management System' LIMIT 1), 0, uuid_generate_v4(), now()),
(uuid_generate_v4(), (SELECT id FROM t_m_user WHERE email = 'cust4@client.com' LIMIT 1), (SELECT id FROM t_m_product WHERE name = 'Asset Management System' LIMIT 1), 0, uuid_generate_v4(), now());

INSERT INTO t_ticket (id, code, title, description, expired_date, status_id, customer_id, product_id, version, created_by, created_at) VALUES 
(uuid_generate_v4(), 'T0001', 'LMS Login Failure', 'User cannot login.', (now() + INTERVAL '30 days')::DATE, 
    (SELECT id FROM t_m_ticket_status WHERE code = 'REOP' LIMIT 1), 
    (SELECT id FROM t_m_user WHERE email = 'cust1@client.com' LIMIT 1), 
    (SELECT id FROM t_m_product WHERE name = 'Learning Management System' LIMIT 1), 
    0, uuid_generate_v4(), now()),
(uuid_generate_v4(), 'T0002', 'Asset Not Found', 'Asset #404 missing.', (now() + INTERVAL '30 days')::DATE, 
    (SELECT id FROM t_m_ticket_status WHERE code = 'CLSD' LIMIT 1), 
    (SELECT id FROM t_m_user WHERE email = 'cust3@client.com' LIMIT 1), 
    (SELECT id FROM t_m_product WHERE name = 'Asset Management System' LIMIT 1), 
    0, uuid_generate_v4(), now());

INSERT INTO t_ticket_status_history (id, ticket_id, status_id, version, created_by, created_at) VALUES
(uuid_generate_v4(), (SELECT id FROM t_ticket WHERE code = 'T0001' LIMIT 1), (SELECT id FROM t_m_ticket_status WHERE code = 'OPEN' LIMIT 1), 0, uuid_generate_v4(), now() - INTERVAL '3 days'),
(uuid_generate_v4(), (SELECT id FROM t_ticket WHERE code = 'T0001' LIMIT 1), (SELECT id FROM t_m_ticket_status WHERE code = 'RSLV' LIMIT 1), 0, uuid_generate_v4(), now() - INTERVAL '2 days'),
(uuid_generate_v4(), (SELECT id FROM t_ticket WHERE code = 'T0001' LIMIT 1), (SELECT id FROM t_m_ticket_status WHERE code = 'REOP' LIMIT 1), 0, uuid_generate_v4(), now() - INTERVAL '1 hour'),
(uuid_generate_v4(), (SELECT id FROM t_ticket WHERE code = 'T0002' LIMIT 1), (SELECT id FROM t_m_ticket_status WHERE code = 'OPEN' LIMIT 1), 0, uuid_generate_v4(), now() - INTERVAL '5 days'),
(uuid_generate_v4(), (SELECT id FROM t_ticket WHERE code = 'T0002' LIMIT 1), (SELECT id FROM t_m_ticket_status WHERE code = 'RSLV' LIMIT 1), 0, uuid_generate_v4(), now() - INTERVAL '4 days'),
(uuid_generate_v4(), (SELECT id FROM t_ticket WHERE code = 'T0002' LIMIT 1), (SELECT id FROM t_m_ticket_status WHERE code = 'CLSD' LIMIT 1), 0, uuid_generate_v4(), now() - INTERVAL '1 day');

INSERT INTO t_ticket_message (id, message, user_id, ticket_id, version, created_by, created_at) VALUES 
(uuid_generate_v4(), 'I am unable to login to the dashboard. It keeps spinning.', 
    (SELECT id FROM t_m_user WHERE email = 'cust1@client.com' LIMIT 1), 
    (SELECT id FROM t_ticket WHERE code = 'T0001' LIMIT 1),
    0, uuid_generate_v4(), now() - INTERVAL '3 days'),
(uuid_generate_v4(), 'We have reset your session on the server. Please try clearing your cache.', 
    (SELECT id FROM t_m_user WHERE email = 'pic1@sys.com' LIMIT 1), 
    (SELECT id FROM t_ticket WHERE code = 'T0001' LIMIT 1), 
    0, uuid_generate_v4(), now() - INTERVAL '2 days'),
(uuid_generate_v4(), 'I cleared the cache but the issue persists. Please check again.', 
    (SELECT id FROM t_m_user WHERE email = 'cust1@client.com' LIMIT 1), 
    (SELECT id FROM t_ticket WHERE code = 'T0001' LIMIT 1), 
    0, uuid_generate_v4(), now() - INTERVAL '1 hour'),
(uuid_generate_v4(), 'Asset #404 is not appearing in the monthly report.', 
    (SELECT id FROM t_m_user WHERE email = 'cust3@client.com' LIMIT 1), 
    (SELECT id FROM t_ticket WHERE code = 'T0002' LIMIT 1), 
    0, uuid_generate_v4(), now() - INTERVAL '5 days'),
(uuid_generate_v4(), 'The asset was archived by mistake. I have restored it.', 
    (SELECT id FROM t_m_user WHERE email = 'pic2@sys.com' LIMIT 1), 
    (SELECT id FROM t_ticket WHERE code = 'T0002' LIMIT 1), 
    0, uuid_generate_v4(), now() - INTERVAL '4 days'),
(uuid_generate_v4(), 'Confirmed, I can see it now. Closing ticket.', 
    (SELECT id FROM t_m_user WHERE email = 'cust3@client.com' LIMIT 1), 
    (SELECT id FROM t_ticket WHERE code = 'T0002' LIMIT 1), 
    0, uuid_generate_v4(), now() - INTERVAL '1 day');
