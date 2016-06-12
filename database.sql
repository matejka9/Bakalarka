CREATE INDEX `idx_email` ON `user` (`email`);

CREATE INDEX `idx_log_in` ON `user` (`email`, `password`);

CREATE TABLE IF NOT EXISTS `user` (
  `id`                  BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name`                VARCHAR(150)    NOT NULL,
  `second_name`                VARCHAR(100)    NOT NULL,
  `email`            VARCHAR(50)     NOT NULL,
  `password`             VARCHAR(10)     NOT NULL,
  PRIMARY KEY (`id`)
);