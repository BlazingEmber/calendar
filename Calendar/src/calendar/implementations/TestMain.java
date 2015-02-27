package calendar.implementations;

import java.sql.SQLException;

import calendar.DatabaseSupport;
import calendar.Event;

public class TestMain {

	private static DatabaseSupport dbs;
	
	public static void main(String[] args) {
		try {
			dbs = new DatabaseSupportImpl();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		Event e = new CalendarEvent();
		/*e.setDate(new Timestamp(System.currentTimeMillis()));
		e.setTitle("EVENT!!");
		e.setNotes("This is silly and pointless");
		List<String> tags = new ArrayList<String>();
		tags.add("a tag");
		tags.add("another tag");
		tags.add("tags are fun");
		e.editTag(tags, null);*/
		dbs.putEvent(e);
		
		
		Event e2 = dbs.getEvent(22);
		
		System.out.println("Title: " + e2.getTitle() + " Notes: " + e2.getNotes() + " Date: " + e2.getDate().toString());
		System.out.println("Tags:");
		for (String tag : e2.getTags()) {
			System.out.println(tag);
		}
	}

}
