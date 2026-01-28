CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE t_m_supplier (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE, 
    name VARCHAR(100) NOT NULL,
    address VARCHAR(250) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    version INT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP
);

CREATE TABLE t_m_agent (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(10) NOT NULL UNIQUE, 
    name VARCHAR(100) NOT NULL,
    address VARCHAR(250) NOT NULL,
    phone VARCHAR(20) NOT NULL,
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

INSERT INTO t_m_supplier (id, code, name, address, phone, version, created_at) VALUES 
(uuid_generate_v4(), 'SP01282601', 'PT. Indo Citra', 'Kawasan Industri Pulogadung Blok A, Jakarta', '0214601234', 0, now()),
(uuid_generate_v4(), 'SP01282602', 'Magma Agenta', 'Jl. Soekarno Hatta No. 202, Bandung', '0227509876', 0, now()),
(uuid_generate_v4(), 'SP01282603', 'PT. Indo Makmur', 'Jl. Margomulyo Indah Kav. 8, Surabaya', '0317491122', 0, now()),
(uuid_generate_v4(), 'SP01282604', 'CV. Sumber Rejeki', 'Jl. Gajah Mada No. 55, Semarang', '0248415566', 0, now()),
(uuid_generate_v4(), 'SP01282605', 'Global Distribution Ltd', 'Sudirman Business District Lot 9, Jakarta', '0215152233', 0, now()),
(uuid_generate_v4(), 'SP01282606', 'PT. Segar Alam', 'Jl. Raya Bogor KM 25, Depok', '0218774455', 0, now()),
(uuid_generate_v4(), 'SP01282607', 'Jaya Abadi Sentosa', 'Komplek Pergudangan Safe N Lock, Sidoarjo', '031-8913344', 0, now()),
(uuid_generate_v4(), 'SP01282608', 'CV. Sansurya', 'Jl. Teuku Umar No. 10, Denpasar', '0361-223344', 0, now());

INSERT INTO t_m_agent (id, code, name, address, phone, version, created_at) VALUES 
(uuid_generate_v4(), 'AG01282601', 'Super Mart A', 'Jl. Jend. Sudirman No. 10, Jakarta', '081234567890', 0, now()),
(uuid_generate_v4(), 'AG01282602', 'Mini Market B', 'Jl. Gatot Subroto Kav. 5, Jakarta', '081398765432', 0, now()),
(uuid_generate_v4(), 'AG01282603', 'Agent C', 'Jl. Asia Afrika No. 88, Bandung', '081812345678', 0, now()),
(uuid_generate_v4(), 'AG01282604', 'Toko Berkah', 'Jl. Malioboro No. 12, Yogyakarta', '085711223344', 0, now()),
(uuid_generate_v4(), 'AG01282605', 'Warung Sejahtera', 'Jl. Diponegoro No. 45, Surabaya', '081155667788', 0, now()),
(uuid_generate_v4(), 'AG01282606', 'Online Store Z', 'Ruko Grand Wisata Blok AA, Bekasi', '02188997766', 0, now()),
(uuid_generate_v4(), 'AG01282607', 'Hyper Store X', 'Jl. Pahlawan Seribu, Tangerang', '02155443322', 0, now()),
(uuid_generate_v4(), 'AG01282608', 'Corner Shop 99', 'Jl. Raya Bogor KM 30, Depok', '089644556677', 0, now());

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