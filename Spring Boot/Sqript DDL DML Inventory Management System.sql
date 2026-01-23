CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE t_m_supplier (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_m_agent (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_m_product_category (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(50) NOT NULL UNIQUE,
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_history_status (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE,
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);


CREATE TABLE t_product (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    quantity INT NOT NULL,
    category_id VARCHAR(36) NOT NULL REFERENCES t_m_product_category(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);


CREATE TABLE t_supply (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    supplier_id VARCHAR(36) NOT NULL REFERENCES t_m_supplier(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_checkout (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    agent_id VARCHAR(36) NOT NULL REFERENCES t_m_agent(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_supply_detail (
    id VARCHAR(36) PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    quantity INT NOT NULL,
    product_id VARCHAR(36) NOT NULL REFERENCES t_product(id),
    supply_id VARCHAR(36) NOT NULL REFERENCES t_supply(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_checkout_detail (
    id VARCHAR(36) PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    quantity INT NOT NULL,
    product_id VARCHAR(36) NOT NULL REFERENCES t_product(id),
    checkout_id VARCHAR(36) NOT NULL REFERENCES t_checkout(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_stock_history (
    id VARCHAR(36) PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    quantity INT NOT NULL,
    new_quantity INT NOT NULL,
    product_id VARCHAR(36) NOT NULL REFERENCES t_product(id),
    status_id VARCHAR(36) NOT NULL REFERENCES t_history_status(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

INSERT INTO t_m_product_category (id, name, version, created_at) VALUES 
(uuid_generate_v4(), 'Fruits', 0, now()),
(uuid_generate_v4(), 'Veggies', 0, now()),
(uuid_generate_v4(), 'Instant Noodle', 0, now()),
(uuid_generate_v4(), 'Household', 0, now());

INSERT INTO t_m_supplier (id, name, version, created_at) VALUES 
(uuid_generate_v4(), 'Indo Citra', 0, now()),
(uuid_generate_v4(), 'Magma Agenta', 0, now()),
(uuid_generate_v4(), 'PT. Indo Makmur', 0, now());

INSERT INTO t_m_agent (id, name, version, created_at) VALUES 
(uuid_generate_v4(), 'Super Mart A', 0, now()),
(uuid_generate_v4(), 'Mini Market B', 0, now()),
(uuid_generate_v4(), 'Agent C', 0, now());

INSERT INTO t_history_status (id, code, version, created_at) VALUES 
(uuid_generate_v4(), 'IN', 0, now()),
(uuid_generate_v4(), 'OUT', 0, now()),
(uuid_generate_v4(), 'ADJ', 0, now());

INSERT INTO t_product (id, name, quantity, category_id, version, created_at) VALUES 
(uuid_generate_v4(), 'Chitato Box', 100, (SELECT id FROM t_m_product_category WHERE name = 'Instant Noodle' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Indomie Goreng', 200, (SELECT id FROM t_m_product_category WHERE name = 'Instant Noodle' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Daia Clean Shirt', 50, (SELECT id FROM t_m_product_category WHERE name = 'Household' LIMIT 1), 0, now());

INSERT INTO t_supply (id, code, date, supplier_id, version, created_at) VALUES 
(uuid_generate_v4(), 'SUP-001', now(), (SELECT id FROM t_m_supplier WHERE name = 'Indo Citra' LIMIT 1), 0, now());

INSERT INTO t_supply_detail (id, date, quantity, product_id, supply_id, version, created_at) VALUES 
(uuid_generate_v4(), now(), 50, (SELECT id FROM t_product WHERE name = 'Chitato Box' LIMIT 1), (SELECT id FROM t_supply WHERE code = 'SUP-001' LIMIT 1), 0, now()),
(uuid_generate_v4(), now(), 100, (SELECT id FROM t_product WHERE name = 'Indomie Goreng' LIMIT 1), (SELECT id FROM t_supply WHERE code = 'SUP-001' LIMIT 1), 0, now());

INSERT INTO t_checkout (id, code, date, agent_id, version, created_at) VALUES 
(uuid_generate_v4(), 'TRX-001', now(), (SELECT id FROM t_m_agent WHERE name = 'Super Mart A' LIMIT 1), 0, now());

INSERT INTO t_checkout_detail (id, date, quantity, product_id, checkout_id, version, created_at) VALUES 
(uuid_generate_v4(), now(), 10, (SELECT id FROM t_product WHERE name = 'Chitato Box' LIMIT 1), (SELECT id FROM t_checkout WHERE code = 'TRX-001' LIMIT 1), 0, now());

INSERT INTO t_stock_history (id, date, quantity, new_quantity, product_id, status_id, version, created_at) VALUES 
(uuid_generate_v4(), now(), 50, 150, (SELECT id FROM t_product WHERE name = 'Chitato Box' LIMIT 1), (SELECT id FROM t_history_status WHERE code = 'IN' LIMIT 1), 0, now()),
(uuid_generate_v4(), now(), 100, 300, (SELECT id FROM t_product WHERE name = 'Indomie Goreng' LIMIT 1), (SELECT id FROM t_history_status WHERE code = 'IN' LIMIT 1), 0, now());

INSERT INTO t_stock_history (id, date, quantity, new_quantity, product_id, status_id, version, created_at) VALUES 
(uuid_generate_v4(), now(), 10, 140, (SELECT id FROM t_product WHERE name = 'Chitato Box' LIMIT 1), (SELECT id FROM t_history_status WHERE code = 'OUT' LIMIT 1), 0, now());