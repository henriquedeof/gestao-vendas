package au.com.xpto.gvendas.gestaovendas.exceptions;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ControllerAdvice //Creating listening for exceptions. It will intercept all thrown Exceptions.
public class GestaoVendasExceptionHandler extends ResponseEntityExceptionHandler {
    //It is common that the ExceptionHandler class of a project starts with the name of the project.

    private static final String CONSTANT_VALIDATION_NOT_BLANK = "NotBlank";
    private static final String CONSTANT_VALIDATION_LENGTH = "Length";

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        //Overriding this method to customize the Inputs treatment that comes from the Controllers and these objects have the @Valid annotation.

        return handleExceptionInternal(ex, this.gerarListaDeErros(ex.getBindingResult()), headers, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public ResponseEntity<Object> handleEmptyResultDataAccessException(EmptyResultDataAccessException ex, WebRequest request){
        String msgUser = "Recurso nao encontrado";
        String msgDeveloper = ex.toString();
        List<ErrorArgumentNotValid> listaErros = Arrays.asList(new ErrorArgumentNotValid(msgUser, msgDeveloper));
        return handleExceptionInternal(ex, listaErros, new HttpHeaders(), HttpStatus.NOT_FOUND, request);//I also can use the way below (same result).
        //return new ResponseEntity<Object>(listaErros, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RegraNegocioException.class)
    public ResponseEntity<Object> handleRegraNegocioException(RegraNegocioException ex, WebRequest request){
        String msgUser = ex.getMessage();
        String msgDeveloper = ex.getMessage();
        List<ErrorArgumentNotValid> listaErros = Arrays.asList(new ErrorArgumentNotValid(msgUser, msgDeveloper));
        return handleExceptionInternal(ex, listaErros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException ex, WebRequest request){
        String msgUser = "Recurso nao encontrado";
        String msgDeveloper = ex.getMessage();
        List<ErrorArgumentNotValid> listaErros = Arrays.asList(new ErrorArgumentNotValid(msgUser, msgDeveloper));
        return handleExceptionInternal(ex, listaErros, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    private List<ErrorArgumentNotValid> gerarListaDeErros(BindingResult bindingResult){
        List<ErrorArgumentNotValid> listaErros = new ArrayList<>();

        bindingResult.getFieldErrors().forEach(fieldError -> {
            //String msgUser = this.tratarMensagemDeErroParaUsuario(fieldError); //String msgDeveloper = fieldError.toString();
            listaErros.add(new ErrorArgumentNotValid(fieldError.getDefaultMessage(), fieldError.toString()));
        });

        return listaErros;
    }

    //I believe I do not need this method to filter all types of validation. I can use the default message as long as this message is clear enough.
    private String tratarMensagemDeErroParaUsuario(FieldError fieldError){
        if(fieldError.getCode().equals(CONSTANT_VALIDATION_NOT_BLANK)){
            return fieldError.getDefaultMessage().concat(" eh obrigatorio"); //getDefaultMessage() returns the information in @NotBlank(message = "Minha mensagem criada")
        }

        if(fieldError.getCode().equals(CONSTANT_VALIDATION_LENGTH)){
            return fieldError.getDefaultMessage().concat(String.format(" deve ter entre %s e %s caracteres.", fieldError.getArguments()[2], fieldError.getArguments()[1]));
        }

        return fieldError.toString();
    }



//====================================================================================================================================================

//    @ExceptionHandler
//    public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException ex, WebRequest request){
//        /*
//        Catch the UsernameAlreadyExistsException somewhere and return to the client the UsernameAlreadyExistsResponse.
//        */
//
//        UsernameAlreadyExistsResponse exceptionResponse = new UsernameAlreadyExistsResponse(ex.getMessage());
//        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
//    }

//======================================================================================================================================================

//    @ExceptionHandler({ResourceNotFoundException.class}) //When the exception ResourceNotFoundException is thrown by any class/method this method is invoked.
//    public ResponseEntity<Object> handleNotFoundException(Exception exception, WebRequest request){
//        return new ResponseEntity<Object>("Resource Not Found", new HttpHeaders(), HttpStatus.NOT_FOUND);
//    }



}
