package org.example.firstproject.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
public class Response {

    private HttpStatus status;
    private String message;
    private Object payload;
    private boolean success;

    public Response(HttpStatus status, String message, boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

    public ResponseEntity<Response> getResponse(boolean success , String message, Object payload){
        return new ResponseEntity<>(new Response(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST ,message,payload,success),success?HttpStatus.OK:HttpStatus.BAD_REQUEST);

    }

    public static ResponseEntity<Response> getResponseEntity(boolean success, String message) {
        return new ResponseEntity<>(new Response(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST,  message, null ,success), success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<Response> getResponseEntity(HttpStatus httpStatus, String message, Object payload) {
        return new ResponseEntity<>(new Response(httpStatus,message,payload, httpStatus.equals(HttpStatus.OK) ), httpStatus);
    }

    public static ResponseEntity<Response> getResponseEntity(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new Response(httpStatus, message,null, httpStatus.equals(HttpStatus.OK) ), httpStatus);


    }

}
