CREATE TABLE `ct_family_details` (
	`id` BIGINT(20) NOT NULL AUTO_INCREMENT,
	`client_id` BIGINT(20) NOT NULL,
	`firstname` VARCHAR(50) NULL DEFAULT NULL,
	`middlename` VARCHAR(50) NULL DEFAULT NULL,
	`lastname` VARCHAR(50) NULL DEFAULT NULL,
	`relationship_cv_id` INT(11) NULL DEFAULT NULL,
	`gender_cv_id` INT(11) NULL DEFAULT NULL,
	`date_of_birth` DATE NULL DEFAULT NULL,
	`age` INT(3) NULL DEFAULT NULL,
	`occupation_cv_id` INT(11) NULL DEFAULT NULL,
	`educational_status_cv_id` INT(11) NULL DEFAULT NULL,
	PRIMARY KEY (`id`),
	INDEX `FK1_n_family_details_client_id` (`client_id`),
	INDEX `FK2_n_family_details_relationship_cv_id` (`relationship_cv_id`),
	INDEX `FK3_n_family_details_sex_cv_id` (`gender_cv_id`),
	INDEX `FK4_n_family_details_occupation_cv_id` (`occupation_cv_id`),
	INDEX `FK5_n_family_details_educational_status_cv_id` (`educational_status_cv_id`),
	CONSTRAINT `FK1_n_family_details_client_id` FOREIGN KEY (`client_id`) REFERENCES `m_client` (`id`),
	CONSTRAINT `FK2_n_family_details_relationship_cv_id` FOREIGN KEY (`relationship_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK3_n_family_details_sex_cv_id` FOREIGN KEY (`gender_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK4_n_family_details_occupation_cv_id` FOREIGN KEY (`occupation_cv_id`) REFERENCES `m_code_value` (`id`),
	CONSTRAINT `FK5_n_family_details_educational_status_cv_id` FOREIGN KEY (`educational_status_cv_id`) REFERENCES `m_code_value` (`id`)
)
COLLATE='latin1_swedish_ci'
ENGINE=InnoDB
;
