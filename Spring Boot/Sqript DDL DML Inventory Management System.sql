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
    name VARCHAR(40) NOT NULL UNIQUE,
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_history_type (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(5) NOT NULL UNIQUE,
    name VARCHAR(10) NOT NULL UNIQUE,
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);


CREATE TABLE t_product (
    id VARCHAR(36) PRIMARY KEY,
    name VARCHAR(40) NOT NULL,
    quantity INT NOT NULL,
    category_id VARCHAR(36) NOT NULL REFERENCES t_m_product_category(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);


CREATE TABLE t_move_in (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    supplier_id VARCHAR(36) NOT NULL REFERENCES t_m_supplier(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_move_out (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
    date TIMESTAMP NOT NULL,
    agent_id VARCHAR(36) NOT NULL REFERENCES t_m_agent(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_move_in_detail (
    id VARCHAR(36) PRIMARY KEY,
    quantity INT NOT NULL,
    product_id VARCHAR(36) NOT NULL REFERENCES t_product(id),
    movein_id VARCHAR(36) NOT NULL REFERENCES t_move_in(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_move_out_detail (
    id VARCHAR(36) PRIMARY KEY,
    quantity INT NOT NULL,
    product_id VARCHAR(36) NOT NULL REFERENCES t_product(id),
    moveout_id VARCHAR(36) NOT NULL REFERENCES t_move_out(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_move_history (
    id VARCHAR(36) PRIMARY KEY,
    date TIMESTAMP NOT NULL,
    quantity INT NOT NULL,
    new_quantity INT NOT NULL,
    product_id VARCHAR(36) NOT NULL REFERENCES t_product(id),
    status_id VARCHAR(36) NOT NULL REFERENCES t_history_type(id),
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

INSERT INTO t_m_product_category (id, name, version, created_at) VALUES 
(uuid_generate_v4(), 'Fruits', 0, now()),
(uuid_generate_v4(), 'Veggies', 0, now()),
(uuid_generate_v4(), 'Instant Noodle', 0, now()),
(uuid_generate_v4(), 'Household', 0, now()),
(uuid_generate_v4(), 'Beverages', 0, now()),
(uuid_generate_v4(), 'Frozen Food', 0, now()),
(uuid_generate_v4(), 'Snacks', 0, now()),
(uuid_generate_v4(), 'Personal Care', 0, now()),
(uuid_generate_v4(), 'Groceries', 0, now()),
(uuid_generate_v4(), 'Bakery', 0, now());

INSERT INTO t_m_supplier (id, name, version, created_at) VALUES 
(uuid_generate_v4(), 'PT. Indo Citra', 0, now()),
(uuid_generate_v4(), 'Magma Agenta', 0, now()),
(uuid_generate_v4(), 'PT. Indo Makmur', 0, now()),
(uuid_generate_v4(), 'CV. Sumber Rejeki', 0, now()),
(uuid_generate_v4(), 'Global Distribution Ltd', 0, now()),
(uuid_generate_v4(), 'PT. Segar Alam', 0, now()),
(uuid_generate_v4(), 'Jaya Abadi Sentosa', 0, now()),
(uuid_generate_v4(), 'CV. Sansurya', 0, now());

INSERT INTO t_m_agent (id, name, version, created_at) VALUES 
(uuid_generate_v4(), 'Super Mart A', 0, now()),
(uuid_generate_v4(), 'Mini Market B', 0, now()),
(uuid_generate_v4(), 'Agent C', 0, now()),
(uuid_generate_v4(), 'Toko Berkah', 0, now()),
(uuid_generate_v4(), 'Warung Sejahtera', 0, now()),
(uuid_generate_v4(), 'Online Store Z', 0, now()),
(uuid_generate_v4(), 'Hyper Store X', 0, now()),
(uuid_generate_v4(), 'Corner Shop 99', 0, now());

INSERT INTO t_history_type (id, code, name, version, created_at) VALUES 
(uuid_generate_v4(), 'IN', 'Supply', 0, now()),
(uuid_generate_v4(), 'OUT', 'Checkout', 0, now());

INSERT INTO t_product (id, name, quantity, category_id, version, created_at) VALUES 
(uuid_generate_v4(), 'Chitato Box 1 lusin', 110, (SELECT id FROM t_m_product_category WHERE name = 'Instant Noodle' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Indomie Goreng 4 lusin', 200, (SELECT id FROM t_m_product_category WHERE name = 'Instant Noodle' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Daia Clean Shirt 1 lusin', 50, (SELECT id FROM t_m_product_category WHERE name = 'Household' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Beras Pandan Wangi 5kg', 0, (SELECT id FROM t_m_product_category WHERE name = 'Groceries' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Minyak Goreng Sawit 2L', 0, (SELECT id FROM t_m_product_category WHERE name = 'Groceries' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Gula Pasir Putih 1kg', 0, (SELECT id FROM t_m_product_category WHERE name = 'Groceries' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Telur Ayam Negeri (Tray)', 0, (SELECT id FROM t_m_product_category WHERE name = 'Groceries' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Teh Botol Sosro 450ml', 0, (SELECT id FROM t_m_product_category WHERE name = 'Beverages' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Kopi Susu Kaleng', 0, (SELECT id FROM t_m_product_category WHERE name = 'Beverages' LIMIT 1), 0, now()),
(uuid_generate_v4(), 'Air Mineral Galon 19L', 0, (SELECT id FROM t_m_product_category WHERE name = 'Beverages' LIMIT 1), 0, now());

INSERT INTO t_move_in (id, code, date, supplier_id, version, created_at) VALUES 
(uuid_generate_v4(), 'SUP-001', now(), (SELECT id FROM t_m_supplier WHERE name = 'PT. Indo Citra' LIMIT 1), 0, now());

INSERT INTO t_move_in_detail (id, quantity, product_id, movein_id, version, created_at) VALUES 
(uuid_generate_v4(), 120, (SELECT id FROM t_product WHERE name = 'Chitato Box 1 lusin' LIMIT 1), (SELECT id FROM t_move_in WHERE code = 'SUP-001' LIMIT 1), 0, now()),
(uuid_generate_v4(), 200, (SELECT id FROM t_product WHERE name = 'Indomie Goreng 4 lusin' LIMIT 1), (SELECT id FROM t_move_in WHERE code = 'SUP-001' LIMIT 1), 0, now());

INSERT INTO t_move_out (id, code, date, agent_id, version, created_at) VALUES 
(uuid_generate_v4(), 'TRX-001', now(), (SELECT id FROM t_m_agent WHERE name = 'Super Mart A' LIMIT 1), 0, now());

INSERT INTO t_move_out_detail (id, quantity, product_id, moveout_id, version, created_at) VALUES 
(uuid_generate_v4(), 10, (SELECT id FROM t_product WHERE name = 'Chitato Box 1 lusin' LIMIT 1), (SELECT id FROM t_move_out WHERE code = 'TRX-001' LIMIT 1), 0, now());

INSERT INTO t_move_history (id, date, quantity, new_quantity, product_id, status_id, version, created_at) VALUES 
(uuid_generate_v4(), now(), 0, 120, (SELECT id FROM t_product WHERE name = 'Chitato Box 1 lusin' LIMIT 1), (SELECT id FROM t_history_type WHERE code = 'IN' LIMIT 1), 0, now()),
(uuid_generate_v4(), now(), 0, 200, (SELECT id FROM t_product WHERE name = 'Indomie Goreng 4 lusin' LIMIT 1), (SELECT id FROM t_history_type WHERE code = 'IN' LIMIT 1), 0, now()),
(uuid_generate_v4(), now(), 10, 110, (SELECT id FROM t_product WHERE name = 'Chitato Box 1 lusin' LIMIT 1), (SELECT id FROM t_history_type WHERE code = 'OUT' LIMIT 1), 0, now());