package ua.ll7.slot7.timing.model;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Index;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * Ping batch data
 *
 * @author Alex Velichko
 *         20.12.13 : 17:45
 * @see ua.ll7.slot7.timing.model.PingLogEntry
 */
@Entity
public class PingBatchLogEntry {

	/**
	 * Tech-style id
	 */
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private long id;

	/**
	 * Generated UUID of the batch
	 */
	@Column
	@Index(name = "pingBatchUUID")
	private String pingBatchUUID;

	/**
	 * Custom ID of the batch
	 */
	@Column
	@Index(name = "pingBatchCustomID")
	@NotBlank(message = "The Custom ID must not be blank")
	private String pingBatchCustomID;

	/**
	 * Host
	 */
	@Column
	@Index(name = "host")
	@NotBlank(message = "The Host must not be blank")
	private String host;

	/**
	 * Thread
	 */
	@Column
	@Index(name = "thread")
	@NotBlank(message = "The Thread must not be blank")
	private String thread;

	/**
	 * Date() for the batch
	 */
	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull(message = "The Date must not be null")
	private Date date;

	/**
	 * Content of the Batch
	 */
	@OneToMany(fetch = FetchType.EAGER,
		mappedBy = "pingBatchLogEntry",
		cascade = {CascadeType.ALL})
	public List<PingLogEntry> pingLogEntries;

	/**
	 * Comments for the batch
	 */
	@Column
	@Type(type = "text")
	private String pingBatchComments;

	// ====================================

	/**
	 * Constructor
	 *
	 * @param pingBatchCustomID Batch short string id
	 * @param date              Usually - moment of the batch creation
	 */
	public PingBatchLogEntry(final String pingBatchCustomID,
				    final Date date,
				    final String host,
				    final String thread) {
		this();
		this.setHost(host);
		this.setThread(thread);
		this.setPingBatchCustomID(pingBatchCustomID);
		this.setDate(date);
	}

	/**
	 * Constructor<br/>
	 * Following JPA specs
	 */
	public PingBatchLogEntry() {
		UUID uuid = UUID.randomUUID();
		this.setPingBatchUUID(uuid.toString());
		this.setPingLogEntries(new LinkedList<PingLogEntry>());
		this.setDate(new Date());
	}

	/**
	 * Adding ping into batch
	 *
	 * @param pingLogEntry Ping to add
	 */
	public void addPing(@NotNull final PingLogEntry pingLogEntry) {
		this.pingLogEntries.add(pingLogEntry);
	}

	/**
	 * Clear inner ping collection
	 */
	public void clearBatch() {
		this.pingLogEntries.clear();
	}

	/**
	 * Clone this ping batch: ID - is null (ORM-driven model, yes), pings collection - cloned
	 */
	@Override
	public PingBatchLogEntry clone() {
		PingBatchLogEntry clonedBatch = new PingBatchLogEntry();

		//fields
		clonedBatch.setPingBatchUUID(this.getPingBatchUUID());
		clonedBatch.setPingBatchCustomID(this.getPingBatchCustomID());
		clonedBatch.setDate(this.getDate());
		clonedBatch.setPingBatchComments(this.getPingBatchComments());
		clonedBatch.setHost(this.getHost());
		clonedBatch.setThread(this.getThread());

		//collection
		LinkedList<PingLogEntry> tmpList = new LinkedList<PingLogEntry>(this.pingLogEntries);
		clonedBatch.setPingLogEntries(tmpList);

		return clonedBatch;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PingBatchLogEntry{");
		sb.append("id=").append(id);
		sb.append(", pingBatchUUID='").append(pingBatchUUID).append('\'');
		sb.append(", pingBatchCustomID='").append(pingBatchCustomID).append('\'');
		sb.append(", host='").append(host).append('\'');
		sb.append(", thread='").append(thread).append('\'');
		sb.append(", date=").append(date);
		sb.append(", pingLogEntries=").append(pingLogEntries);
		sb.append(", pingBatchComments='").append(pingBatchComments).append('\'');
		sb.append('}');
		return sb.toString();
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getThread() {
		return thread;
	}

	public void setThread(String thread) {
		this.thread = thread;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPingBatchUUID() {
		return pingBatchUUID;
	}

	public void setPingBatchUUID(String pingBatchUUID) {
		this.pingBatchUUID = pingBatchUUID;
	}

	public String getPingBatchCustomID() {
		return pingBatchCustomID;
	}

	public void setPingBatchCustomID(String pingBatchCustomID) {
		this.pingBatchCustomID = pingBatchCustomID;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public List<PingLogEntry> getPingLogEntries() {
		return pingLogEntries;
	}

	public void setPingLogEntries(LinkedList<PingLogEntry> pingLogEntries) {
		this.pingLogEntries = pingLogEntries;
	}

	public String getPingBatchComments() {
		return pingBatchComments;
	}

	public void setPingBatchComments(String pingBatchComments) {
		this.pingBatchComments = pingBatchComments;
	}
}
