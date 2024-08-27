DROP TABLE IF EXISTS authorities;

CREATE TABLE authorities (
                             id SERIAL PRIMARY KEY,
                             customer_id INT NOT NULL,
                             name VARCHAR(50) NOT NULL,
                             CONSTRAINT fk_customer FOREIGN KEY (customer_id) REFERENCES customer (id)
);

