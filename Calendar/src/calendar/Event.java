package calendar;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

public interface Event {
	public boolean editEventNotes(String notes);
	public boolean deleteEventNotes();
	public boolean editEvent(Timestamp newDate, String newTitle, Integer newPriority, String newDescription);
	public boolean addEventNotes(String notes);
	public boolean addPriority(Integer priority);
	public boolean deleteTag(String tag);
	public boolean editTag(List<String> adds, List<String> deletes);
	public boolean createTag(String tag);
	public Integer getId();
	public String getTitle();
	public Timestamp getDate();
	public String getNotes();
	public Integer getPriority();
	public Collection<String> getTags();
	public void setId(Integer id);
	public void setTitle(String title);
	public void setDate(Timestamp date);
	public void setNotes(String notes);
	public void setPriority(Integer priority);
}