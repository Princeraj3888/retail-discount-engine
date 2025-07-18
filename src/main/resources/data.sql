INSERT INTO CUSTOMER (id, name, role, loyalty_start_date, is_blacklisted) VALUES
(1, 'John Employee', 'EMPLOYEE', '2019-01-01', false),
(2, 'Anna Affiliate', 'AFFILIATE', '2020-01-01', false),
(3, 'Bob Loyal', 'CUSTOMER', '2020-05-01', false),
(4, 'Jenny Customer', 'CUSTOMER', '2025-01-01', false);

INSERT INTO ITEM (id, name, price, category) VALUES
(101, 'Milk', 10.00, 'GROCERY'),
(102, 'TV', 500.00, 'NON_GROCERY'),
(103, 'Laptop', 1000.00, 'NON_GROCERY');