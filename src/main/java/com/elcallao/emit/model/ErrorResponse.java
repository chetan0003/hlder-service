package com.elcallao.emit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String status;
    private String status_code;
    private String message;
    private String duplicated;
}
