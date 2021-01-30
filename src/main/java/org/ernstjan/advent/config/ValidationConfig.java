package org.ernstjan.advent.config;

import org.ernstjan.advent.day4.constraints.LengthValidatorForWrappedString;
import org.ernstjan.advent.day4.constraints.MaxValidatorForWrappedInteger;
import org.ernstjan.advent.day4.constraints.MinValidatorForWrappedInteger;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.hibernate.validator.cfg.ConstraintMapping;
import org.hibernate.validator.constraints.Length;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

// see https://stackoverflow.com/questions/38393822/adding-custom-constraintvalidator-for-future-and-localdate-to-a-spring-boot-pro
@Configuration
public class ValidationConfig {

    @Bean
    public LocalValidatorFactoryBean defaultValidator() {
        return new CustomValidatorFactoryBean();
    }

    private static class CustomValidatorFactoryBean extends LocalValidatorFactoryBean {
        @Override
        protected void postProcessConfiguration(javax.validation.Configuration<?> configuration) {
            HibernateValidatorConfiguration hibernateConfiguration = (HibernateValidatorConfiguration) configuration;
            ConstraintMapping constraintMapping = hibernateConfiguration.createConstraintMapping();

            constraintMapping
                    .constraintDefinition(Min.class)
                    .validatedBy(MinValidatorForWrappedInteger.class)
                    .includeExistingValidators(true);

            constraintMapping
                    .constraintDefinition(Max.class)
                    .validatedBy(MaxValidatorForWrappedInteger.class)
                    .includeExistingValidators(true);

            constraintMapping
                    .constraintDefinition(Length.class)
                    .validatedBy(LengthValidatorForWrappedString.class)
                    .includeExistingValidators(true);

            hibernateConfiguration.addMapping(constraintMapping);
            super.postProcessConfiguration(configuration);
        }
    }
}
