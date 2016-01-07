CREATE TABLE `ct_address` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`address_type_cv_id` INT(11) NULL DEFAULT NULL,
	`house_no` VARCHAR(20) NULL DEFAULT NULL,
	`street_no` VARCHAR(20) NULL DEFAULT NULL,
	`area_locality` VARCHAR(100) NULL DEFAULT NULL,
	`landmark` VARCHAR(100) NULL DEFAULT NULL,
	`village_town` VARCHAR(100) NULL DEFAULT NULL,
	`taluka` VARCHAR(100) NULL DEFAULT NULL,
	`district_cv_id` INT(11) NOT NULL,
	`state_cv_id` INT(11) NOT NULL,
	`country_cv_id` INT(11) NOT NULL,
	`pin_code` INT(6) NOT NULL,
	`landline_no` BIGINT(20) NULL DEFAULT NULL,
	`mobile_no` BIGINT(11) NOT NULL,
	PRIMARY KEY (`id`),
	INDEX `fk_n_address_address_type_cv_id` (`address_type_cv_id`),
	INDEX `fk_n_address_district_cv_id` (`district_cv_id`),
	INDEX `fk_n_address_state_cv_id` (`state_cv_id`),
	INDEX `fk_n_adress_client_id` (`client_id`),
	INDEX `country_cv_id` (`country_cv_id`),
	CONSTRAINT `FK_ct_address_m_code_value_3` FOREIGN KEY (`country_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK_ct_address_m_client` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK_ct_address_m_code_value` FOREIGN KEY (`address_type_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK_ct_address_m_code_value_2` FOREIGN KEY (`district_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK_ct_address_m_code_value_4` FOREIGN KEY (`state_cv_id`) REFERENCES `m_code_value` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;