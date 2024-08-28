CREATE TABLE account_transactions (
                                      transaction_id VARCHAR(200) NOT NULL,
                                      account_number INT NOT NULL,
                                      customer_id INT NOT NULL,
                                      transaction_dt DATE NOT NULL,
                                      transaction_summary VARCHAR(200) NOT NULL,
                                      transaction_type VARCHAR(100) NOT NULL,
                                      transaction_amt INT NOT NULL,
                                      closing_balance INT NOT NULL,
                                      create_dt DATE DEFAULT NULL,
                                      PRIMARY KEY (transaction_id),
                                      FOREIGN KEY (account_number) REFERENCES accounts (account_number) ON DELETE CASCADE,
                                      FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);
