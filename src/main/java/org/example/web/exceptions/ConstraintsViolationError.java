package org.example.web.exceptions;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ConstraintsViolationError {

    private final String property;
    private final String message;

}