package model;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * MyHealthTests is the JUnit testing class for this MyHealth program. 
 * @author Rebecca Watson
 *
 */
public abstract class MyHealthTests implements DateAndTimeFormatter{
	
	/**
	 * Setting up private variables for the class.
	 */
	private ArrayList<Record> records;
	private ArrayList<User> users;
	private ArrayList<Profile> profiles;
	private LocalDate validRecordDate;
	private double validWeightKG;
	private double validTempCELC;
	private int validBloodPressureLow;
	private int validBloodPressureHigh;
	private String validNotes;
	private String validFirstName;
	private String validLastName;
	private LocalDate invalidRecordDate;
	private double invalidWeightKG;
	private double invalidTempCELC;
	private int invalidBloodPressureLow;
	private int invalidBloodPressureHigh;
	private String invalidNotes;
	private String invalidFirstName;
	private String invalidLastName;
	private String username;
	private String password;
	private LocalDate date = LocalDate.now();
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy MM dd");


    public boolean dateFormatter(String dateStr) throws DateTimeParseException{
        try {
            LocalDate.parse(dateStr, this.formatter);
        } catch (DateTimeParseException e) {
        	return false;
        }
        return true;
    }

	@Before
	public void setUp() throws Exception {
		this.records = new ArrayList<Record>();
		Record record = new Record (null, null, null, null, null, null, null);
		this.users = new ArrayList<User>();
		User user = new User (null, null, null, null);
		this.profiles = new ArrayList<Profile>();
		Profile profile = new Profile (null, null);
	}

	@After
	public void tearDown() throws Exception {
		this.records = null;
		this.users = null;

	}
	
	@Test
	public void testValidDate() {
		dateFormatter("20190228");
	}
	
	@Test
	public void testInvalidDate() {
		assertFalse(dateFormatter("20190230"));
	}
	
	@Test
	public void testValidWeightInput() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInvalidWeightInput() {
		fail("Not yet implemented");
	}

	@Test
	public void testWeightRange() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testHighAndLowBloodPressureRange() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testValidNotesInput() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInvalidNotesInput() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testValidRecordCreated() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testInvalidRecordCreated() {
		fail("Not yet implemented");
	}
}
