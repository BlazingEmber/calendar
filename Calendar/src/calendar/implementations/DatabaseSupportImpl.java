package calendar.implementations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;

import calendar.DatabaseSupport;
import calendar.Event;

public class DatabaseSupportImpl implements DatabaseSupport {

	private Connection c;
	//private Statement stmt;

	public DatabaseSupportImpl() throws SQLException {
		c = DriverManager.getConnection("jdbc:mysql://192.168.1.104/calendar?"
				+ "user=root&password=billybob");
	}

	@Override
	public Event getEvent(Integer id) {
		try {
			PreparedStatement ps = c.prepareStatement("SELECT * FROM events WHERE id=?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			Event e = new CalendarEvent();
			if (rs.next()) {
				e.setId(rs.getInt("id"));
				e.setTitle(rs.getString("title"));
				e.setDate(rs.getTimestamp("date"));
				e.setNotes(rs.getString("notes"));
				e.setPriority(rs.getInt("priority"));
			}
			
			ps = c.prepareStatement("SELECT * FROM tags WHERE event_id=?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while(rs.next()) {
				e.createTag(rs.getString("tag"));
			}
			return e;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean putEvent(Event e) {
		if (e.getId() < 0) {
			try {
				
				// Put event fields into events table
				PreparedStatement ps = c.prepareStatement("INSERT INTO events(title, date, notes, priority) VALUES(?, ?, ?, ?);", Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, e.getTitle());
				ps.setTimestamp(2, e.getDate());
				ps.setString(3, e.getNotes());
				if (e.getPriority() != null)
					ps.setInt(4, e.getPriority());
				else
					ps.setNull(4, Types.INTEGER);
				ps.execute();
				ResultSet rs = ps.getGeneratedKeys();
				if (rs.next())
					e.setId(rs.getInt(1));
				
				// Put tags into tags table
				for(String tag : e.getTags()) {
					ps = c.prepareStatement("INSERT INTO tags(event_id, tag) VALUES(?, ?);");
					ps.setInt(1, e.getId());
					ps.setString(2, tag);
					ps.execute();
				}
				
				
				return true;
			} catch (SQLException ex) {
				ex.printStackTrace();
				return false;
			}
		} else {
			try {
				
				// Update event fields for exiting event
				PreparedStatement ps = c.prepareStatement("UPDATE events SET title=?, date=?, notes=?, priority =? WHERE id=?;");
				ps.setString(1, e.getTitle());
				ps.setTimestamp(2, e.getDate());
				ps.setString(3, e.getNotes());
				if (e.getPriority() != null)
					ps.setInt(4, e.getPriority());
				else
					ps.setNull(4, Types.INTEGER);
				ps.setInt(5, e.getId());
				ps.execute();
				
				// Update tags for existing event
				ps = c.prepareStatement("DELETE FROM tags WHERE event_id=?;");
				ps.setInt(1, e.getId());
				ps.execute();
				for(String tag : e.getTags()) {
					ps = c.prepareStatement("INSERT INTO tags(event_id, tag) VALUES(?, ?);");
					ps.setInt(1, e.getId());
					ps.setString(2, tag);
					ps.execute();
				}
				return true;
			} catch (SQLException ex) {
				ex.printStackTrace();
				return false;
			}
		}
	}
}
