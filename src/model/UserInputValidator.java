package model;

import java.util.regex.Pattern;
import java.util.StringTokenizer;
import java.util.regex.Matcher;

public class UserInputValidator {
	
    // simple name regex
    private static final String NAME_PATTERN = "^[a-z0-9\\._-]{5,20}$";

    // strict username and password regex
    private static final String USERNAME_PATTERN =
            "^[a-zA-Z0-9]([._-](?![._-])|[a-zA-Z0-9]){3,18}[a-zA-Z0-9]$";
    
    private static final Pattern patternSimple = Pattern.compile(NAME_PATTERN );

    private static final Pattern patternStrict = Pattern.compile(USERNAME_PATTERN);
    
    private int notesLengthAllowed = 50; 
    
    private int userNotesLength;

    public boolean strictInputIsValid(final String userInput) {
        Matcher matcher = patternStrict.matcher(userInput);
        return matcher.matches();
    }
    
    public boolean simpleInputIsValid(final String userInput) {
        Matcher matcher = patternSimple.matcher(userInput);
        return matcher.matches();
    }

    public boolean checkNotesLength(String userNotes) {
    	boolean notesLengthChecked = false;
		StringTokenizer tokens = new StringTokenizer(userNotes);
		userNotesLength = tokens.countTokens();
		if (userNotesLength <= notesLengthAllowed) {
			notesLengthChecked = true;
		}
		return notesLengthChecked;
    }
}
