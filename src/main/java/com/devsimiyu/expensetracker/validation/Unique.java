package com.devsimiyu.expensetracker.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({
    ElementType.METHOD, 
    ElementType.FIELD, 
    ElementType.ANNOTATION_TYPE, 
    ElementType.CONSTRUCTOR, 
    ElementType.PARAMETER, 
    ElementType.TYPE_USE
})
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(List.class)
@Documented
@Constraint(validatedBy = {UniqueValidator.class})
public @interface Unique {
    
    String message() default "Must be unique";
    Class<?>[] groups() default { };
    Class<? extends Payload>[] payload() default { };
}

@Target({ 
    ElementType.METHOD, 
    ElementType.FIELD, 
    ElementType.ANNOTATION_TYPE, 
    ElementType.CONSTRUCTOR, 
    ElementType.PARAMETER, 
    ElementType.TYPE_USE 
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@interface List {
    Unique[] value();
}
