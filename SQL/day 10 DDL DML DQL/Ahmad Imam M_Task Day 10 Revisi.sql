CREATE TABLE t_faculty (
	id SERIAL PRIMARY KEY,
	faculty_name VARCHAR(35)
);

CREATE TABLE t_study_program (
	id SERIAL PRIMARY KEY,
	faculty_id INT REFERENCES t_faculty (id),
	study_program_name VARCHAR(50)
);

CREATE TABLE t_gender (
	id SERIAL PRIMARY KEY,
	name VARCHAR(8)
);

CREATE TABLE t_religion (
	id SERIAL PRIMARY KEY,
	name VARCHAR(10)
);

CREATE TABLE t_semester (
	id SERIAL PRIMARY KEY,
	semester VARCHAR(8)
);

CREATE TABLE t_academic_year (
	id SERIAL PRIMARY KEY,
	year_start DATE,
	year_end DATE,
	semester_id INT REFERENCES t_semester (id)
);

CREATE TABLE t_user (
	id SERIAL PRIMARY KEY,
	fullname VARCHAR(45),
	birth_date DATE,
	gender_id INT REFERENCES t_gender (id),
	religion_id INT REFERENCES t_religion (id),
	address VARCHAR(100),
	phone VARCHAR(20)
);

CREATE TABLE t_student (
	id SERIAL PRIMARY KEY,
	user_id INT REFERENCES t_user (id),
	student_id VARCHAR(20),
	study_program_id INT REFERENCES t_study_program (id),
	registered_date DATE
);

CREATE TABLE t_lecture (
	id SERIAL PRIMARY KEY,
	user_id INT REFERENCES t_user (id),
	lecture_id VARCHAR(20)
);

CREATE TABLE t_study_course (
	id SERIAL PRIMARY KEY,
	course_code VARCHAR(15),
	course_name VARCHAR(50)
);

CREATE TABLE t_study_class (
	id SERIAL PRIMARY KEY,
	course_id INT REFERENCES t_study_course (id),
	class_name VARCHAR(50),
	credit INT,
	lecture_id INT REFERENCES t_lecture (id)
);

CREATE TABLE t_study_result (
	id SERIAL PRIMARY KEY,
	student_id INT REFERENCES t_student (id),
	academic_year_id INT REFERENCES t_academic_year (id)
);

CREATE TABLE t_study_result_detail (
	id SERIAL PRIMARY KEY,
	study_result_id INT REFERENCES t_study_result (id),
	study_class_id INT REFERENCES t_study_class (id),
	grade DECIMAL(3, 2)
);

INSERT INTO t_faculty(faculty_name)
VALUES
	('Adab dan Ilmu Budaya'),
	('Dakwah dan Komunikasi'),
	('Ekonomi Bisnis Islam'),
	('Ilmu Sosial dan Humaniora'),
    ('Sains dan Teknologi');

INSERT INTO t_study_program(study_program_name, faculty_id)
VALUES 
	('Bahasa dan Sastra Arab', 1),
	('Sejarah dan Kebudayaan Islam', 1),
	('Ilmu Perpustakaan', 1),
	('Sastra Inggris', 1),
	('Komunikasi dan penyiaran Islam', 2),
	('Bimbingan dan Konseling Islam', 2),
	('Pengembangan Masyarakat Islam', 2),
	('Manajemen Dakwah', 2),
	('Ilmu Kesejahteraan Sosial', 2),
	('Ekonomi Syariah', 3),
	('Perbankan Syariah', 3),
	('Manajemen Keuangan Syariah', 3),
	('Akuntansi Syariah', 3),
	('Psikologi', 4),
	('Sosiologi', 4),
	('Ilmu Komunikasi', 4);

INSERT INTO t_gender(name)
values
	('Pria'),
	('Wanita');
	
	
INSERT INTO t_religion(name)
VALUES
    ('Islam'),
    ('Kristen'),
    ('Katolik'),
    ('Hindu'),
    ('Budha'),
    ('Konghucu');

INSERT INTO t_semester(semester)
VALUES
   	('ganjil'),
    ('genap');

INSERT INTO t_academic_year(year_start, year_end, semester_id)
VALUES
	('2022-09-01', '2023-01-31', 1),
	('2023-02-01', '2023-08-31', 2),
	('2023-09-01', '2024-01-31', 1),
	('2024-02-01', '2024-08-31', 2),
	('2024-09-01', '2025-01-31', 1),
	('2025-02-01', '2025-08-31', 2),
	('2025-09-01', '2026-01-31', 1),
	('2026-02-01', '2026-08-31', 2);

INSERT INTO t_user (fullname, birth_date, gender_id, religion_id, address, phone)
VALUES
	('Ahmad Munir', '1998-04-12', 1, 1, 'Jl. Merdeka No. 10, Jakarta', '812345678'),
	('Siti Aisyah', '1999-09-25', 2, 1, 'Jl. Sudirman No. 22, Bandung', '813456789'),
	('Budi Santoso', '1997-01-30', 1, 2, 'Jl. Diponegoro No. 5, Surabaya', '814567890'),
	('Maria Angelina', '2000-06-18', 2, 3, 'Jl. Ahmad Yani No. 7, Semarang', '815678901'),
	('Andi Pratama', '1996-11-03', 1, 1, 'Jl. Gatot Subroto No. 15, Medan', '816789012'),
	('Rina Putri', '1998-07-21', 2, 1, 'Jl. Asia Afrika No. 8, Bandung', '817890123'),
	('Dedi Kurniawan', '1995-02-14', 1, 2, 'Jl. Veteran No. 12, Malang', '818901234'),
	('Clara Wijaya', '1999-12-05', 2, 3, 'Jl. Pemuda No. 20, Surabaya', '819012345'),
	('Fajar Nugroho', '1997-05-09', 1, 1, 'Jl. Kaliurang No. 3, Yogyakarta', '811234567'),
	('Nina Lestari', '2001-03-17', 2, 1, 'Jl. Pahlawan No. 11, Bogor', '812345679');

INSERT INTO t_student (user_id, student_id, study_program_id, registered_date)
VALUES
	(1, 2019001, 3, '2019-08-01'),
	(2, 2020002, 3, '2020-08-01'),
	(3, 2021003, 3, '2021-08-01'),
	(4, 2022004, 3, '2022-08-01'),
	(5, 2023005, 3, '2023-08-01');

INSERT INTO t_lecture (user_id, lecture_id)
VALUES
	(6, '9006'),
	(7, '9007'),
	(8, '9008'),
	(9, '9009'),
	(10, '9010');

INSERT INTO t_study_course (course_code, course_name)
VALUES
	('ESY514001', 'Ekonometrika'),
	('ESY514002', 'Ekonomi Mikro dan Makro'),
	('ESY514003', 'Manajemen Keuangan Islam'),
	('ESY514004', 'Pemikiran Ekonomi Islam'),
	('ESY524012', 'Analisis Laporan Keuangan'),
	('ESY514005', 'Fiqh Ekonomi dan Bisnis Islam (FEBI)'),
	('ESY514006', 'Manajemen Lembaga Keuangan Syariah'),
	('ESY514007', 'Metodologi Penelitian Ekonomi Islam'),
	('ESY514008', 'Produk dan Industri Halal'),
	('ESY514014', 'Ekonomi Pembangunan Islam'),
	('ESY524017', 'Manajemen Keuangan Internasional'),
	('ESY514018', 'Seminar Penelitian');

INSERT INTO t_study_class (course_id, class_name, credit, lecture_id)
VALUES
	(1, 'Ekonometrika', 3, 1),
	(2, 'Ekonomi Mikro dan Makro', 3, 1),
	(3, 'Manajemen Keuangan Islam', 3, 1),
	(4, 'Pemikiran Ekonomi Islam', 3, 1),
	(5, 'Analisis Laporan Keuangan', 3, 1),
	(6, 'Fiqh Ekonomi dan Bisnis Islam (FEBI)', 3, 1),
	(7, 'Manajemen Lembaga Keuangan Syariah', 3, 1),
	(8, 'Metodologi Penelitian Ekonomi Islam', 3, 1),
	(9, 'Produk dan Industri Halal', 3, 1),
	(10, 'Ekonomi Pembangunan Islam', 3, 1),
	(11, 'Manajemen Keuangan Internasional', 3, 1),
	(12, 'Seminar Penelitian', 3, 1);

INSERT INTO t_study_result (student_id, academic_year_id)
VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 1);

INSERT INTO t_study_result_detail (study_result_id, study_class_id, grade)
VALUES
	(1, 1, 3.75),
	(1, 2, 3.50),
	(1, 3, 3.80),
	(1, 4, 3.60),
	(1, 5, 3.40),
	(1, 6, 3.70),
	(1, 7, 3.65),
	(1, 8, 3.90),
	(1, 9, 3.55),
	(1, 10, 3.60),
	(1, 11, 3.45),
	(1, 12, 4.00),
	(2, 1, 3.75),
	(2, 2, 3.00),
	(2, 3, 3.80),
	(2, 4, 3.00),
	(2, 5, 3.40),
	(2, 6, 3.70),
	(2, 7, 3.65),
	(2, 8, 3.00),
	(2, 9, 3.50),
	(2, 10, 3.60),
	(2, 11, 3.00),
	(2, 12, 3.00),
    (3, 1, 3.65),
	(3, 2, 3.40),
	(3, 3, 3.85),
	(3, 4, 3.55),
	(3, 5, 3.30),
	(3, 6, 3.75),
	(3, 7, 3.60),
	(3, 8, 3.95),
	(3, 9, 3.50),
	(3, 10, 3.70),
	(3, 11, 3.45),
	(3, 12, 3.90),
	(4, 1, 3.55),
	(4, 2, 3.10),
	(4, 3, 3.75),
	(4, 4, 3.20),
	(4, 5, 3.35),
	(4, 6, 3.65),
	(4, 7, 3.50),
	(4, 8, 3.25),
	(4, 9, 3.45),
	(4, 10, 3.60),
	(4, 11, 3.15),
	(4, 12, 3.30),
	(5, 1, 3.75),
	(5, 2, 3.00),
	(5, 3, 3.80),
	(5, 4, 3.00),
	(5, 5, 3.40),
	(5, 6, 3.70),
	(5, 7, 3.65),
	(5, 8, 3.00),
	(5, 9, 3.50),
	(5, 10, 3.60),
	(5, 11, 3.00),
	(5, 12, 3.00);