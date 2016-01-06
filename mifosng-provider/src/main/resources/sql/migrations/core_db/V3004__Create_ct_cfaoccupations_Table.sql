CREATE TABLE `ct_cfaoccupations` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`occupation_type_cv_id` INT(11) NOT NULL,
	`annual_revenue` DECIMAL(10,2) NULL DEFAULT NULL,
	`annual_expense` DECIMAL(10,2) NULL DEFAULT NULL,
	`annual_surplus` DECIMAL(10,2) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_n_cfa_occupation_type_cv_id` (`occupation_type_cv_id`),
	INDEX `fk_n_cfa_occ_client_id_m_client` (`client_id`),
	CONSTRAINT `fk_n_cfa_occ_client_id_m_client` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `fk_n_cfa_occupation_type_cv_id` FOREIGN KEY (`occupation_type_cv_id`) REFERENCES `m_code_value` (`id`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
