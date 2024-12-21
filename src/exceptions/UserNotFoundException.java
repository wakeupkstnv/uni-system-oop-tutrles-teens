package exceptions;

import Core.CoreSystem;
import Core.Language;

public class UserNotFoundException extends Exception {
	  public UserNotFoundException(String username) {
	        super(getMessage(username));
	    }
	  
	  private static String getMessage(String username) {
	        Language language = CoreSystem.getLanguageMode();

	        if (language == Language.RUS) {
	            return "Пользователь \"" + username + "\" не найден.";
	        } else if (language == Language.ENG) {
	            return "User \"" + username + "\" not found.";
	        }
	        else {
	        	return "Қолданушы \"" + username + "\" табылмады.";

	        }
	    }
}

