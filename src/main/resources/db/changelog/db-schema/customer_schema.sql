CREATE TABLE customer (
                          id SERIAL PRIMARY KEY,
                          email VARCHAR(45) NOT NULL,
                          password VARCHAR(200) NOT NULL,
                          role VARCHAR(45) NOT NULL
);
