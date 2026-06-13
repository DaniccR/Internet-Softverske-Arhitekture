/**
 * @author Radomir Danic
 * @date 13. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.exception;

/**
 * 
 * 
 * @author Radomir
 */
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.ExceptionHandler; 
import org.springframework.web.bind.annotation.RestControllerAdvice; 
 
import jakarta.servlet.http.HttpServletRequest; 
 
@RestControllerAdvice 
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class) 
    public ResponseEntity<ErrorResponse> handleResourceNotFound( 
    		ResourceNotFoundException ex, 
    		HttpServletRequest request) { 
 
        ErrorResponse error = new ErrorResponse( 
            HttpStatus.NOT_FOUND.value(), 
            "Not Found", 
            ex.getMessage(), 
            request.getRequestURI() 
        ); 
 
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND); 
    } 
 
    @ExceptionHandler(BusinessException.class) 
    public ResponseEntity<ErrorResponse> handleBusinessException( 
            BusinessException ex, 
            HttpServletRequest request) { 
 
        ErrorResponse error = new ErrorResponse( 
            HttpStatus.BAD_REQUEST.value(), 
            "Bad Request", 
            ex.getMessage(), 
            request.getRequestURI() 
        ); 
 
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST); 
    } 
 
    @ExceptionHandler(Exception.class) 
    public ResponseEntity<ErrorResponse> handleGeneralException( 
            Exception ex, 
            HttpServletRequest request) { 
 
        ErrorResponse error = new ErrorResponse( 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Internal Server Error", 
            "Doslo je do neocekivane greske u sistemu.", 
            request.getRequestURI() 
        ); 
 
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); 
    } 
}
