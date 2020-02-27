package com.task.ms.users.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Habilitación de los repositorios JPA.
 * @author mmusatad
 *
 */

@Configuration
@EnableJpaRepositories(basePackages = "com.task.ms.users.repository")
public class JpaConfiguration {
   
}
