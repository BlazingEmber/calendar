package calendar.implementations;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import calendar.Event;

public class CalendarEvent implements Event {

	private Integer id;
	private String title;
	private Timestamp date;
	private String notes;
	private Integer priority;
	private HashSet<String> tags;

	public CalendarEvent() {
		id = -1;
		tags = new HashSet<String>();
	}
	
	@Override
	public boolean editEventNotes(String notes) {
		this.notes = notes;
		return true;
	}

	@Override
	public boolean deleteEventNotes() {
		this.notes = "";
		return true;
	}

	@Override
	public boolean editEvent(Timestamp newDate, String newTitle,
			Integer newPriority, String newDescription) {
		this.date = newDate;
		this.title = newTitle;
		this.priority = newPriority;
		this.notes = newDescription;

		return true;
	}

	@Override
	public boolean addEventNotes(String notes) {
		this.notes = notes;
		return true;
	}

	@Override
	public boolean addPriority(Integer priority) {
		this.priority = priority;
		return true;
	}

	@Override
	public boolean deleteTag(String tag) {
		tags.remove(tag);
		return true;
	}

	@Override
	public boolean editTag(List<String> adds, List<String> deletes) {
		if (adds != null)
			tags.addAll(adds);
		if (deletes != null)
			tags.removeAll(deletes);
		return true;
	}

	@Override
	public boolean createTag(String tag) {
		tags.add(tag);
		return true;
	}

	@Override
	public Integer getId() {
		return id;
	}
	
	@Override
	public String getTitle() {
		return title;
	}
	
	@Override
	public Timestamp getDate() {
		return date;
	}
	
	@Override
	public String getNotes() {
		return notes;
	}
	
	@Override
	public Integer getPriority() {
		return priority;
	}
	
	@Override
	public Collection<String> getTags() {
		return tags;
	}
	
	@Override
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public void setDate(Timestamp date) {
		this.date = date;
	}

	@Override
	public void setNotes(String notes) {
		this.notes = notes;
	}

	@Override
	public void setPriority(Integer priority) {
		this.priority = priority;
	}
}
