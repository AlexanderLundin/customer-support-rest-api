DROP DATABASE customer_api;
CREATE DATABASE customer_api;
Use customer_api
DROP TABLE IF EXISTS customer_requests;
CREATE TABLE customer_requests (
    requestNumber   INTEGER NOT NULL AUTO_INCREMENT,
    requestDateTime VARCHAR(20) NOT NULL,
    customerName    VARCHAR(50) NOT NULL,
    customerAddress VARCHAR(50) NOT NULL,
    phoneNumber     VARCHAR(20) NOT NULL,
    description     VARCHAR(50),
    technician      VARCHAR(50),
    appointmentDate VARCHAR(20),
    requestStatus   VARCHAR(20),
    PRIMARY KEY (requestNumber)
);

INSERT INTO customer_requests(requestDateTime, customerName, customerAddress, phoneNumber, description, technician, appointmentDate, requestStatus) VALUES('03/16/2020 6:07PM1', 'Some Customer1', '123 Any Street, SomeCity, ST, 999991', '111-222-33331', 'its broke and I need it fixed!1', 'tech1', 'appt1', 'ASSIGNED');
INSERT INTO customer_requests(requestDateTime, customerName, customerAddress, phoneNumber, description, technician, appointmentDate, requestStatus) VALUES('03/16/2020 6:07PM2', 'Some Customer2', '123 Any Street, SomeCity, ST, 999992', '111-222-33332', 'its broke and I need it fixed!2', 'tech2', 'appt2', 'RESOLVED');
select * from customer_requests;
