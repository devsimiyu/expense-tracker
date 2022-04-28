package com.devsimiyu.expensetracker.validation;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, String> {

    @PersistenceContext
    EntityManager entityManager;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        Number count = (Number) entityManager
            .createNativeQuery("SELECT COUNT(*) FROM categories WHERE name = :name")
            .setParameter("name", value)
            .getSingleResult();

        return count.intValue() == 0;
    }
}
