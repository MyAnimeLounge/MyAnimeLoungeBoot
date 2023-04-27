package com.barta.myanimelounge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED, reason = "You do not have access to this entity")
public class RestrictedAccessException extends RuntimeException {
}
