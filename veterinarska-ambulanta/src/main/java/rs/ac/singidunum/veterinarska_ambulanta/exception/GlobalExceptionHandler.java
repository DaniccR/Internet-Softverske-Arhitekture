/**
 * @author Radomir Danic
 * @date 13. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.exception;

/**
 * TODO
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
	// Presreće grešku kada ne nađemo podatak u bazi
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
 
    // Presreće grešku kada je prekršeno poslovno pravilo
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
 
    // Presreće sve ostale (nepredviđene) greške sistema (status 500)
    @ExceptionHandler(Exception.class) 
    public ResponseEntity<ErrorResponse> handleGeneralException( 
            Exception ex, 
            HttpServletRequest request) { 
 
        ErrorResponse error = new ErrorResponse( 
            HttpStatus.INTERNAL_SERVER_ERROR.value(), 
            "Internal Server Error", 
            "Došlo je do neočekivane greške u sistemu.", 
            request.getRequestURI() 
        ); 
 
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR); 
    } 
}
