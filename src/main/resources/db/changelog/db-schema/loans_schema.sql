CREATE TABLE loans (
                       loan_number SERIAL PRIMARY KEY,
                       customer_id INT NOT NULL,
                       start_dt DATE NOT NULL,
                       loan_type VARCHAR(100) NOT NULL,
                       total_loan INT NOT NULL,
                       amount_paid INT NOT NULL,
                       outstanding_amount INT NOT NULL,
                       create_dt DATE DEFAULT NULL,
                       FOREIGN KEY (customer_id) REFERENCES customer (id) ON DELETE CASCADE
);
