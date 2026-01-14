CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

begin;

rollback;

CREATE TABLE t_m_role (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(15) NOT NULL,
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

CREATE TABLE t_m_asset_category (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(35) NOT NULL,
	code VARCHAR(5) NOT NULL UNIQUE,
	
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_m_asset_status (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(35) NOT NULL,
	code VARCHAR(5) NOT NULL UNIQUE,
	
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);


CREATE TABLE t_m_location (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(40) NOT NULL,
	
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_employee (
    id VARCHAR(36) PRIMARY KEY,
	full_name VARCHAR(45) NOT NULL,
	company_id VARCHAR(36) NOT NULL REFERENCES t_m_company(id),
	date_of_birth DATE NOT NULL,
	address VARCHAR(100) NOT NULL,
	phone VARCHAR(20) NOT NULL UNIQUE,
	code VARCHAR(20) NOT NULL UNIQUE,
	
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_user (
    id VARCHAR(36) PRIMARY KEY,
	role_id VARCHAR(36) NOT NULL REFERENCES t_m_role(id),
	employee_id VARCHAR(36) NOT NULL REFERENCES t_employee(id),
	email VARCHAR(50) NOT NULL UNIQUE,
	password VARCHAR(200) NOT NULL,
	
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_asset (
    id VARCHAR(36) PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	company_id VARCHAR(36) NOT NULL REFERENCES t_m_company(id),
	category_id VARCHAR(36) NOT NULL REFERENCES t_m_asset_category(id),
	status_id VARCHAR(36) NOT NULL REFERENCES t_m_asset_status(id),
	expired_date DATE,
	code VARCHAR(5) NOT NULL UNIQUE,
	
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_assignment (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(20) NOT NULL UNIQUE,
	assign_date DATE NOT NULL,
	target_location_id VARCHAR(36) REFERENCES t_m_location(id),
	target_asset_id VARCHAR(36) REFERENCES t_asset(id),
	target_employee_id VARCHAR(36) REFERENCES t_employee(id),
	
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP
);

CREATE TABLE t_assignment_detail (
    id VARCHAR(36) PRIMARY KEY,
	assignment_id VARCHAR(36) NOT NULL REFERENCES t_assignment(id),
	asset_id VARCHAR(36) NOT NULL REFERENCES t_asset(id),
	return_date DATE,
	
	version INT NOT NULL,
    created_by VARCHAR(36) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_by VARCHAR(36),
    updated_at TIMESTAMP,
    
	UNIQUE(assignment_id, asset_id)
);

INSERT INTO t_m_role (version, created_by, created_at, id, name, code) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'super admin', 'RSA01'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'asset manager', 'RAM01');

INSERT INTO t_m_company (version, created_by, created_at, id, name) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Global Tech Corp'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Creative Solutions Ltd'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Data Dynamics'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Fintech Partners'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Eco Systems Inc');

INSERT INTO t_m_asset_category (version, created_by, created_at, id, name, code) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'General', 'GENER'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Licenses', 'LICEN'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Components', 'COMPN'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Consumables', 'CONSM');

INSERT INTO t_m_asset_status (version, created_by, created_at, id, name, code) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Deployable', 'DPLOY'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Undeployable', 'UNDPL'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Archived', 'ARCHV');

INSERT INTO t_m_location (version, created_by, created_at, id, name) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Main Office - Jakarta'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Branch Office - Bandung'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Warehouse A'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Data Center'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Remote Work Hub');

INSERT INTO t_employee (version, created_by, created_at, id, full_name, company_id, date_of_birth, address, phone, code) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'John Doe', (SELECT id FROM t_m_company LIMIT 1), '1990-05-15', 'Jl. Sudirman No. 1', '08123456789', 'EMP001'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Jane Smith', (SELECT id FROM t_m_company OFFSET 1 LIMIT 1), '1992-08-20', 'Jl. Thamrin No. 5', '08123456780', 'EMP002'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Alice Johnson', (SELECT id FROM t_m_company OFFSET 2 LIMIT 1), '1985-12-10', 'Jl. Gatot Subroto No. 10', '08123456781', 'EMP003'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Bob Williams', (SELECT id FROM t_m_company OFFSET 3 LIMIT 1), '1988-03-25', 'Jl. Rasuna Said No. 12', '08123456782', 'EMP004'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Charlie Brown', (SELECT id FROM t_m_company OFFSET 4 LIMIT 1), '1995-07-30', 'Jl. Kemang Raya No. 3', '08123456783', 'EMP005');

INSERT INTO t_user (version, created_by, created_at, id, role_id, employee_id, email, password) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_m_role WHERE code = 'RSA01'), (SELECT id FROM t_employee WHERE code = 'EMP001'), 'admin@company.com', 'hashed_pass_1'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_m_role WHERE code = 'RAM01'), (SELECT id FROM t_employee WHERE code = 'EMP002'), 'jane@company.com', 'hashed_pass_2'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_m_role WHERE code = 'RAM01'), (SELECT id FROM t_employee WHERE code = 'EMP003'), 'alice@company.com', 'hashed_pass_3'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_m_role WHERE code = 'RAM01'), (SELECT id FROM t_employee WHERE code = 'EMP004'), 'bob@company.com', 'hashed_pass_4'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_m_role WHERE code = 'RAM01'), (SELECT id FROM t_employee WHERE code = 'EMP005'), 'charlie@company.com', 'hashed_pass_5');

INSERT INTO t_asset (version, created_by, created_at, id, name, company_id, category_id, status_id, expired_date, code) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Thinkpad L310 Intel i5 Gen 11', (SELECT id FROM t_m_company LIMIT 1), (SELECT id FROM t_m_asset_category WHERE code = 'GENER'), (SELECT id FROM t_m_asset_status WHERE code = 'ARCHV'), NULL, 'AST01'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Adobe Creative Cloud', (SELECT id FROM t_m_company LIMIT 1), (SELECT id FROM t_m_asset_category WHERE code = 'LICEN'), (SELECT id FROM t_m_asset_status WHERE code = 'DPLOY'), '2025-06-30', 'AST02'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'DDR4 16GB RAM', (SELECT id FROM t_m_company LIMIT 1), (SELECT id FROM t_m_asset_category WHERE code = 'COMPN'), (SELECT id FROM t_m_asset_status WHERE code = 'DPLOY'), NULL, 'AST03'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Printer Toner XL', (SELECT id FROM t_m_company LIMIT 1), (SELECT id FROM t_m_asset_category WHERE code = 'CONSM'), (SELECT id FROM t_m_asset_status WHERE code = 'DPLOY'), NULL, 'AST04'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Dell Monitor 27"', (SELECT id FROM t_m_company LIMIT 1), (SELECT id FROM t_m_asset_category WHERE code = 'GENER'), (SELECT id FROM t_m_asset_status WHERE code = 'UNDPL'), NULL, 'AST05');

INSERT INTO t_assignment (version, created_by, created_at, id, code, assign_date, target_location_id, target_asset_id, target_employee_id) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'ASTGN001', NOW(), NULL, NULL, (SELECT id FROM t_employee OFFSET 4 LIMIT 1)), --
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'ASTGN002', NOW(), NULL, (SELECT id FROM t_asset LIMIT 1), NULL),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'ASTGN003', NOW(), NULL, (SELECT id FROM t_asset LIMIT 1), NULL),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'ASTGN004', NOW(), (SELECT id FROM t_m_location OFFSET 2 LIMIT 1), NULL, NULL),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'ASTGN005', NOW(), (SELECT id FROM t_m_location OFFSET 3 LIMIT 1), NULL, NULL);

INSERT INTO t_assignment_detail (version, created_by, created_at, id, assignment_id, asset_id, return_date) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_assignment LIMIT 1), (SELECT id FROM t_asset LIMIT 1), NULL), --thinkpad
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_assignment OFFSET 1 LIMIT 1), (SELECT id FROM t_asset OFFSET 1 LIMIT 1), NULL), --adobe
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_assignment OFFSET 2 LIMIT 1), (SELECT id FROM t_asset OFFSET 2 LIMIT 1), NULL), --DDR4 16GB RAM
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_assignment OFFSET 3 LIMIT 1), (SELECT id FROM t_asset OFFSET 3 LIMIT 1), NULL), --Printer Toner XL
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), (SELECT id FROM t_assignment OFFSET 4 LIMIT 1), (SELECT id FROM t_asset OFFSET 4 LIMIT 1), '2027-12-01'); --Dell Monitor 27"
