DROP TABLE IF EXISTS BOOKING;
  
CREATE TABLE BOOKING (
    id INT NOT NULL AUTO_INCREMENT,
    service_id INT NOT NULL,
    user_id INT NOT NULL,
    assignee_id INT DEFAULT NULL,
    assignee_name VARCHAR(50) DEFAULT NULL,
    booking_status VARCHAR(15) NOT NULL,
    comment VARCHAR(500) DEFAULT NULL,
    service_amount DOUBLE NOT NULL,
    payment_mode VARCHAR(15) NOT NULL,
    created_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    modified_on DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));