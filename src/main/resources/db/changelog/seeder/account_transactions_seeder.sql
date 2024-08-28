INSERT INTO account_transactions (
    transaction_id,
    account_number,
    customer_id,
    transaction_dt,
    transaction_summary,
    transaction_type,
    transaction_amt,
    closing_balance,
    create_dt
)
VALUES
    (gen_random_uuid(), 1865764534, 1, CURRENT_DATE - INTERVAL '7 days', 'Coffee Shop', 'Withdrawal', 30, 34500, CURRENT_DATE - INTERVAL '7 days'),

    (gen_random_uuid(), 1865764534, 1, CURRENT_DATE - INTERVAL '6 days', 'Uber', 'Withdrawal', 100, 34400, CURRENT_DATE - INTERVAL '6 days'),

    (gen_random_uuid(), 1865764534, 1, CURRENT_DATE - INTERVAL '5 days', 'Self Deposit', 'Deposit', 500, 34900, CURRENT_DATE - INTERVAL '5 days'),

    (gen_random_uuid(), 1865764534, 1, CURRENT_DATE - INTERVAL '4 days', 'Ebay', 'Withdrawal', 600, 34300, CURRENT_DATE - INTERVAL '4 days'),

    (gen_random_uuid(), 1865764534, 1, CURRENT_DATE - INTERVAL '2 days', 'OnlineTransfer', 'Deposit', 700, 35000, CURRENT_DATE - INTERVAL '2 days'),

    (gen_random_uuid(), 1865764534, 1, CURRENT_DATE - INTERVAL '1 day', 'Amazon.com', 'Withdrawal', 100, 34900, CURRENT_DATE - INTERVAL '1 day');
