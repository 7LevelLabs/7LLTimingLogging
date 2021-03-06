package ua.ll7.slot7.timing.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;

/**
 * Timing system : ping log entry
 *
 * @author Alex Velichko
 *         20.12.13 : 16:47
 * @see PingBatchLogEntry
 * @see System#currentTimeMillis()
 */
@Entity
public class PingLogEntry {

	/**
	 * Tech-style id
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	/**
	 * Ping batch log entry
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(nullable = false)
	public PingBatchLogEntry pingBatchLogEntry;

	/**
	 * Custom ID of the ping
	 */
	@Column
	@Index(name = "pingCustomID")
	@NotBlank(message = "The Ping Custom ID must not be blank")
	private String pingCustomID;

	/**
	 * Time of the ping
	 *
	 * @see System#currentTimeMillis()
	 */
	@Column
	private long pingMillis;

	/**
	 * Comments for the ping
	 */
	@Column
	@Type(type = "text")
	private String pingComments;

	// ==========================

	/**
	 * @param pingMillis   Usually - {@link System#currentTimeMillis()} of the appropriate event ("ping")
	 * @param pingCustomID Ping short string id
	 */
	public PingLogEntry(final PingBatchLogEntry pingBatchLogEntry,
			      long pingMillis,
			      final String pingCustomID) {
		this.pingBatchLogEntry = pingBatchLogEntry;
		this.pingMillis = pingMillis;
		this.pingCustomID = pingCustomID;
	}

	/**
	 * Following JPA specs
	 */
	public PingLogEntry() {
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PingLogEntry{");
		sb.append("id=").append(id);
		sb.append(", pingCustomID='").append(pingCustomID).append('\'');
		sb.append(", pingMillis=").append(pingMillis);
		sb.append(", pingComments='").append(pingComments).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPingCustomID() {
		return pingCustomID;
	}

	public void setPingCustomID(String pingCustomID) {
		this.pingCustomID = pingCustomID;
	}

	public long getPingMillis() {
		return pingMillis;
	}

	public void setPingMillis(long pingMillis) {
		this.pingMillis = pingMillis;
	}

	public String getPingComments() {
		return pingComments;
	}

	public void setPingComments(String pingComments) {
		this.pingComments = pingComments;
	}

	public PingBatchLogEntry getPingBatchLogEntry() {
		return pingBatchLogEntry;
	}

	public void setPingBatchLogEntry(PingBatchLogEntry pingBatchLogEntry) {
		this.pingBatchLogEntry = pingBatchLogEntry;
	}
}
