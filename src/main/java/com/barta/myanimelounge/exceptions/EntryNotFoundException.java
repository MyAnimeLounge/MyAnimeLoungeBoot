package com.barta.myanimelounge.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such Entity")
public class EntryNotFoundException extends RuntimeException {
}
