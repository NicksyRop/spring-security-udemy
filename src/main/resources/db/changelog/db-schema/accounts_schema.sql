CREATE TABLE accounts (
                          customer_id INT NOT NULL,
                          account_number INT NOT NULL,
                          account_type VARCHAR(100) NOT NULL,
                          branch_address VARCHAR(200) NOT NULL,
                          create_dt DATE DEFAULT NULL,
                          PRIMARY KEY (account_number),
                          FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);
