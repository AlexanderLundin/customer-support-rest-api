DROP DATABASE customer_api;
CREATE DATABASE customer_api;
Use customer_api;
DROP TABLE IF EXISTS customer_requests;
CREATE TABLE customer_requests
(
    request_number    INTEGER AUTO_INCREMENT,
    request_date_time VARCHAR(20) DEFAULT 'EMPTY',
    customer_name     VARCHAR(50) DEFAULT 'EMPTY',
    customer_address  VARCHAR(50) DEFAULT 'EMPTY',
    phone_number      VARCHAR(20) DEFAULT 'EMPTY',
    description       VARCHAR(50),
    technician        VARCHAR(50),
    appointment_date  VARCHAR(20),
    request_status    VARCHAR(20),
    PRIMARY KEY (request_number)
);
DROP TABLE IF EXISTS request_notes;
CREATE TABLE request_notes
(
    note_id        INTEGER AUTO_INCREMENT,
    date_time      VARCHAR(20),
    notes          VARCHAR(50),
    request_number INTEGER,
    FOREIGN KEY (request_number) REFERENCES customer_requests (request_number),
    PRIMARY KEY (note_id)
);

INSERT INTO customer_requests(request_date_time, customer_name, customer_address, phone_number, description, technician,
                              appointment_date, request_status)
VALUES ('03/16/2020 6:07PM', 'Some Customer1', '123 Any Street, SomeCity, ST, 999991', '111-222-33331',
        'its broke and I need it fixed!1', 'tech1', 'appt1', 'ASSIGNED');
INSERT INTO customer_requests(request_date_time, customer_name, customer_address, phone_number, description, technician,
                              appointment_date, request_status)
VALUES ('03/16/2020 6:08PM', 'Some Customer2', '123 Any Street, SomeCity, ST, 999992', '111-222-33332',
        'its broke and I need it fixed!2', 'tech2', 'appt2', 'RESOLVED');
INSERT INTO customer_requests(request_date_time, customer_name, customer_address, phone_number, description, technician,
                              appointment_date, request_status)
VALUES ('03/16/2020 6:09PM', 'Some Customer3', '123 Any Street, SomeCity, ST, 999991', '111-222-33331',
        'its broke and I need it fixed!3', 'tech3', 'appt3', 'ASSIGNED');
INSERT INTO customer_requests(request_date_time, customer_name, customer_address, phone_number, description, technician,
                              appointment_date, request_status)
VALUES ('03/16/2020 10:00PM', 'Some Customer4', '123 Any Street, SomeCity, ST, 999992', '111-222-33332',
        'its broke and I need it fixed!4', 'tech4', 'appt4', 'RESOLVED');
INSERT INTO customer_requests(request_date_time, customer_name, customer_address, phone_number, description, technician,
                              appointment_date, request_status)
VALUES ('03/16/2020 11:00PM', 'Some Customer5', '123 Any Street, SomeCity, ST, 999991', '111-222-33331',
        'its broke and I need it fixed!5', 'tech5', 'appt5', 'ASSIGNED');
INSERT INTO customer_requests(request_date_time, customer_name, customer_address, phone_number, description, technician,
                              appointment_date, request_status)
VALUES ('03/16/2020 12:00PM', 'Some Customer6', '123 Any Street, SomeCity, ST, 999992', '111-222-33332',
        'its broke and I need it fixed!6', 'tech6', 'appt6', 'RESOLVED');
INSERT INTO request_notes(date_time, notes, request_number)
VALUES ('03/16/2020 6:07PM', 'Some Notes', 1);
INSERT INTO request_notes(date_time, notes, request_number)
VALUES ('03/16/2020 6:08PM', 'Some Notes2', 1);
select *
from customer_requests;
select *
from request_notes;
