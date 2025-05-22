DROP TABLE IF EXISTS work_type;
DROP TABLE IF EXISTS work_section;

CREATE TABLE work_section (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE work_type (
    id SERIAL PRIMARY KEY,
    code VARCHAR(50),
    name VARCHAR(255) NOT NULL,
    unit VARCHAR(50),
    price NUMERIC(10, 2),
    section_id INTEGER REFERENCES work_section(id)
);