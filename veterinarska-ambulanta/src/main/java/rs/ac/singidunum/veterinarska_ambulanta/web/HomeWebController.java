/**
 * @author Radomir Danic
 * @date 13. 6. 2026.
 */
package rs.ac.singidunum.veterinarska_ambulanta.web;

/**
 * TODO
 * 
 * @author Radomir
 */
import org.springframework.stereotype.Controller; 
import org.springframework.web.bind.annotation.GetMapping; 
 
@Controller 
public class HomeWebController {
	 @GetMapping({"/", "/web"}) 
	 public String index() { 
		 return "index"; 
	 } 
}
