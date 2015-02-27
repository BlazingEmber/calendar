package calendar;

public interface DatabaseSupport {
	public Event getEvent(Integer id);
	public boolean putEvent(Event e);
}