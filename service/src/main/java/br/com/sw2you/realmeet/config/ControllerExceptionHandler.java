package br.com.sw2you.realmeet.config;

import br.com.sw2you.realmeet.api.model.ResponseError;
import br.com.sw2you.realmeet.exception.RoomNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(Exception exception){
        return buildResponseEntity(HttpStatus.NOT_FOUND, exception);
    }

    public ResponseEntity<Object> buildResponseEntity(HttpStatus httpStatus, Exception exception){
        return new ResponseEntity<>(
                new ResponseError()
                        .status(httpStatus.getReasonPhrase())
                        .code(httpStatus.value())
                        .message(exception.getMessage()),
                httpStatus
        );
    }
}

/*
@RestControllerAdvice: Responsavel por capturar exceçoes que ocorrem na aplicação e transformar/mapear em outra coisa (nesse caso um erro)

O ResponseError foi criado através as configurações da api.yml. Note que ela tem os parametros status, code e message que sao gerados quando
executamos o install do lifecycle

   ResponseError:
      type: object
      required:
        - field
        - errorCode
      properties:
        field:
          type: string
        errorCode:
          type: string
        status:
          type: string
        code:
          type: integer
          format: int32
        message:
          type: string
*/
