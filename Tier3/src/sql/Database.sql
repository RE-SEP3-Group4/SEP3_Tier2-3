-- This table stores all data regarding the users --
-- id is the id of the user.
-- username is the name the user will use to identify itself.
-- password is what the user will need to login alongside with the username.
-- securityLevel will be used by the tier 1 to give access to specific parts of the website.

CREATE TABLE IF NOT EXISTS users
(
    id            SERIAL,
    username      VARCHAR(15),
    password      VARCHAR(15),
    securityLevel INTEGER,
    PRIMARY KEY (id)
);

-- This table stores all the payments done by the user. --
-- userID is the id of the user that performed the payment.
-- date is when the payment was performed.
-- period is the period of time the payment is valid for.

CREATE TABLE IF NOT EXISTS payments
(
    userID    INTEGER,
    startDate DATE,
    endDate   DATE,
    FOREIGN KEY (userID) REFERENCES users (id),
    PRIMARY KEY (userID, startDate, endDate)
);

-- This table stores the reservation to the gym --
-- userID is the id of the user.
-- date is the date when the user is coming.

CREATE TABLE IF NOT EXISTS reservations
(
    userID INTEGER,
    date   timestamp,
    FOREIGN KEY (userID) REFERENCES users (id),
    PRIMARY KEY (userID, date)
);

INSERT INTO users(username, password, securityLevel) VALUES ('admin', 'admin', 2);
INSERT INTO users(username, password, securityLevel) VALUES ('John', '1234', 1);

INSERT INTO payments(userID, startDate, endDate) VALUES (2, make_date(2021, 7, 29), make_date(2021, 8, 29));

INSERT INTO reservations(userid, date) VALUES (2, TO_TIMESTAMP('28 7 2021 21', 'MM-DD-YYYY HH24'));