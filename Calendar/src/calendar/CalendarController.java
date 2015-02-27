package calendar;

import java.sql.Timestamp;
import java.util.List;

public interface CalendarController {
	public boolean deleteTag(String tag, List<Integer> id);
	public boolean editTag(Integer id, List<String> adds);
	public boolean createTag(String tag, List<Integer> id);
	public boolean addPriority(Integer id, Integer priority);
	public boolean addNotification(Integer id, Integer minutes);
	public boolean removeEvent(Integer id, Integer minutes);
	public boolean editEventNotes(Integer id, String notes);
	public boolean addEventNotes(Integer id, String notes);
	public boolean addEvent(Timestamp date, String title, Integer priority, String description);
	public boolean editEvent(Integer id, Timestamp newDate, String newTitle, Integer newPriority, String newDescription);
	public boolean deleteEvent(Integer id);
}

