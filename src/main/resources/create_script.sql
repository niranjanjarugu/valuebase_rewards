CREATE TABLE customer(id long, name VARCHAR2(50) , created_date_time DATE, update_date_time DATE);
CREATE TABLE transaction (id long,customer_id long ,transaction_date DATE, amount numeric, created_date_time DATE, update_date_time DATE);
CREATE TABLE reward (id long,customer_id long ,transaction_id long, transaction_date DATE, rewards_points numeric, created_date_time DATE, update_date_time DATE);