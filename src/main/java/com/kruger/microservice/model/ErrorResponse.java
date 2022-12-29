package com.kruger.microservice.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse {


    @Schema(description = "HTTP status error code", example = "400")
    private String errorCode;

    @Schema(description = "Error description and details", example = "Player not exist")
    private String errorDescription;


    
}
