package com.revature.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * This class represents the JsonResponse that is sent to our console after a controller
 * method is invoked.
 * The JsonResponse will consist of the Boolean success variable, indicating whether the
 * action was successful or unsuccessful, the String message, containing a message specific
 * to the action taken (EX. "User successfully created."), and the Object data, displaying the
 * Java object data.
 *
 * Creates a JsonResponse object.
 *
 * The Lombok dependency eliminates boilerplate getter and setter and constructor code with the @Data, @NoArgsConstructor,
 * and @AllArgsConstructor.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonResponse {
    Boolean success;
    String message;
    Object data;
}
