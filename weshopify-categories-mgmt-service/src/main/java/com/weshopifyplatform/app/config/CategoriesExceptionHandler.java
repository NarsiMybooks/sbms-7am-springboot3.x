package com.weshopifyplatform.app.config;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.weshopifyplatform.app.beans.ApiErrorBean;
import com.weshopifyplatform.app.exceptions.CategoriesException;

import lombok.extern.slf4j.Slf4j;

//@RestControllerAdvice(basePackages = {"com.weshopifyplatform.app.config"})
@ControllerAdvice
@Slf4j
public class CategoriesExceptionHandler {

	@ExceptionHandler(CategoriesException.class)
	public ResponseEntity<ApiErrorBean> handleCategoriesException(CategoriesException ce) {
		log.info("Exception message is:\t"+ce.getErrorMessage());
		ApiErrorBean apiError = new ApiErrorBean(ce.getErrorMessage(), HttpStatus.NOT_FOUND.value(), new Date());
		return new ResponseEntity<ApiErrorBean>(apiError, HttpStatus.NOT_FOUND);
	}
}
