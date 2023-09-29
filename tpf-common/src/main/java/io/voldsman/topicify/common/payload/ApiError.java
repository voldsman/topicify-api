package io.voldsman.topicify.common.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiError {

    private int statusCode;
    private String message;
    private String description;
}