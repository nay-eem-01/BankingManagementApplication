package org.example.firstproject.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Data
@AllArgsConstructor
public class HttpResponse {

    private HttpStatus status;
    private String message;
    private Object payload;
    private boolean success;

    public HttpResponse(HttpStatus status, String message, boolean success) {
        this.status = status;
        this.message = message;
        this.success = success;
    }

    public ResponseEntity<HttpResponse> getResponse(boolean success , String message, Object payload){
        return new ResponseEntity<>(new HttpResponse(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST ,message,payload,success),success?HttpStatus.OK:HttpStatus.BAD_REQUEST);

    }

    public static ResponseEntity<HttpResponse> getResponseEntity(boolean success, String message) {
        return new ResponseEntity<>(new HttpResponse(success ? HttpStatus.OK : HttpStatus.BAD_REQUEST,  message, null ,success), success ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
    }

    public static ResponseEntity<HttpResponse> getResponseEntity(HttpStatus httpStatus, String message, Object payload) {
        return new ResponseEntity<>(new HttpResponse(httpStatus,message,payload, httpStatus.equals(HttpStatus.OK) ), httpStatus);
    }

    public static ResponseEntity<HttpResponse> getResponseEntity(HttpStatus httpStatus, String message) {
        return new ResponseEntity<>(new HttpResponse(httpStatus, message,null, httpStatus.equals(HttpStatus.OK) ), httpStatus);

    }

    public static ResponseEntity<HttpResponse> getResponseEntity(HttpStatus httpStatus, String message, List<Object> payload) {
        return new ResponseEntity<>(new HttpResponse(httpStatus, message,payload, httpStatus.equals(HttpStatus.OK) ), httpStatus);

    }


}
