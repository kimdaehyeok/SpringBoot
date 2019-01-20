CREATE TABLE pay (
  id         BIGINT NOT NULL AUTO_INCREMENT,
  amount     BIGINT,
  tx_name     VARCHAR(255),
  tx_date_time DATETIME,
  PRIMARY KEY (id)
) ENGINE = INNODB;

CREATE TABLE pay2 (
  id         BIGINT NOT NULL AUTO_INCREMENT,
  amount     BIGINT,
  tx_name     VARCHAR(255),
  tx_date_time DATETIME,
  PRIMARY KEY (id)
) ENGINE = INNODB;

INSERT INTO pay (amount, tx_name, tx_date_time) VALUES (1000, 'trade1', '2018-09-10 00:00:00');
INSERT INTO pay (amount, tx_name, tx_date_time) VALUES (2000, 'trade2', '2018-09-10 00:00:00');
INSERT INTO pay (amount, tx_name, tx_date_time) VALUES (3000, 'trade3', '2018-09-10 00:00:00');
INSERT INTO pay (amount, tx_name, tx_date_time) VALUES (4000, 'trade4', '2018-09-10 00:00:00');


COMMIT;