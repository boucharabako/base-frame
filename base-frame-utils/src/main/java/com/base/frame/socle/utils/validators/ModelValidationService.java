/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.base.frame.socle.utils.validators;


import com.base.frame.socle.utils.Constants;
import com.base.frame.socle.utils.exceptions.ApiError;
import com.base.frame.socle.utils.exceptions.ObjectValidationException;
import java.util.ArrayList;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.NoSuchMessageException;
import org.springframework.stereotype.Service;

/**
 * Validation des objets
 *
 * @author William kouwonou
 * @version 1.0.0
 * @since 19-08-2019
 */
@Service
public class ModelValidationService {

    private static Validator validator;
    private final static String HTTP_400 ="400";
    private final static String MSG_PREFIX ="attr.name.";
    @Autowired
    MessageSourceKV messageSource;

    @PostConstruct
    private void init() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    /**
     *
     * @param <T> La classe de l'obvjet à valider
     * @param t l'objet à valider
     * @return renvoie un boolean true i l'objet est valide et une exception
     * sinon.
     */
    public <T> boolean validate(T t) {

        Set<ConstraintViolation<T>> constraintViolations
                = validator.validate(t);
        ObjectValidationException exception = new ObjectValidationException(Constants.VAL_GLOBAL_ERROR_CODE, new ArrayList<>());
        if (!constraintViolations.isEmpty()) {

            constraintViolations.stream().forEach(x -> {

               
                String pr="";
                   try {
                       
                       
                    pr= messageSource.getMessage(MSG_PREFIX+x.getPropertyPath().toString(),  
                                new Object[]{}) +": ";
                } catch (NoSuchMessageException e) {
                }
                exception.getApiErrors().add(new ApiError(x.getPropertyPath().toString()
                        ,pr+messageSource.getMessage(x.getMessage(), x.getConstraintDescriptor().getAttributes())));

            });
            System.out.println(exception.getApiErrors());
            throw exception;
        }

        return true;
    }
}
