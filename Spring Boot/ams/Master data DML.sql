CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

INSERT INTO t_m_role (version, created_by, created_at, id, role_name, role_code) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'super admin', 'RSA01'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'asset manager', 'RAM01');

INSERT INTO t_m_asset_status (version, created_by, created_at, id, name, code) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Deployable', 'DPLOY'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Undeployable', 'UNDPL'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Archived', 'ARCHV');

INSERT INTO t_m_asset_category (version, created_by, created_at, id, name, code) VALUES
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'General', 'GENER'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Licenses', 'LICEN'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Components', 'COMPN'),
(0, uuid_generate_v4(), NOW(), uuid_generate_v4(), 'Consumables', 'CONSM');