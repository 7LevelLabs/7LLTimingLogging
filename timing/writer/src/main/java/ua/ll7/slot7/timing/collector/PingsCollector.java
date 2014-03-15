package ua.ll7.slot7.timing.collector;

import org.apache.commons.lang3.StringUtils;
import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.model.PingLogEntry;

import java.util.Date;
import java.util.UUID;

/**
 *
 * Pings collector: only-one-way-ticket style<br/>
 * - Create batch ({@link #PingsCollector(String)} or {@link #PingsCollector(String, String)}),
 * - Collect pings ({@link #ping()}, {@link #ping(String)}, {@link #ping(String, String)})
 * - Upload batch ({@link #uploadBatch()})
 *
 * @author Oleksandr Velychko
 *         26.12.13 : 11:27
 */
public class PingsCollector {
	private PingBatchLogEntry pingBatch;

	/**
	 * Constructor
	 *
	 * @param customID Custom ID for inner {@link ua.ll7.slot7.timing.model.PingBatchLogEntry}
	 * @throws java.lang.IllegalArgumentException Paramentr must be not null and not empty
	 */
	public PingsCollector(final String customID) {
		if (StringUtils.isBlank(customID)) {
			throw new IllegalArgumentException("Arguments must be not null or empty");
		}
		this.pingBatch = new PingBatchLogEntry();
		this.pingBatch.setDate(new Date());
		this.pingBatch.setPingBatchCustomID(customID);
	}

	/**
	 * Constructor
	 *
	 * @param customID Custom ID for inner {@link ua.ll7.slot7.timing.model.PingBatchLogEntry}
	 * @param comments Comments for inner {@link ua.ll7.slot7.timing.model.PingBatchLogEntry}
	 * @throws java.lang.IllegalArgumentException Parameters (both) must be not null and not empty
	 */
	public PingsCollector(final String customID, final String comments) {
		this(customID);
		if (StringUtils.isBlank(comments)) {
			throw new IllegalArgumentException("Arguments must be not null or empty");
		}
		this.pingBatch.setPingBatchComments(comments);
	}

	/**
	 * Collect new ping
	 */
	public void ping() {
		long pingMillis = System.currentTimeMillis();
		String pingCustomID = UUID.randomUUID().toString();
		PingLogEntry ping = new PingLogEntry(this.pingBatch, pingMillis, pingCustomID);
		pingBatch.addPing(ping);
	}

	/**
	 * Collect new ping with custom string ID
	 *
	 * @param pingCustomID Custom ID, not null
	 * @throws java.lang.IllegalArgumentException Parameter must be not null and not empty
	 * @see ua.ll7.slot7.timing.model.PingLogEntry
	 */
	public void ping(final String pingCustomID) {
		if (StringUtils.isBlank(pingCustomID)) {
			throw new IllegalArgumentException("Arguments must be not null or empty.");
		}
		long pingMillis = System.currentTimeMillis();
		PingLogEntry ping = new PingLogEntry(this.pingBatch, pingMillis, pingCustomID);
		pingBatch.addPing(ping);
	}

	/**
	 * Collect new ping with custom string ID and ping comments
	 *
	 * @param pingCustomID Custom ID, not null
	 * @param pingComments Ping comments, not null
	 * @throws java.lang.IllegalArgumentException Parameters (both) must be not null and not empty
	 * @see ua.ll7.slot7.timing.model.PingLogEntry
	 */
	public void ping(final String pingCustomID, final String pingComments) {
		if (StringUtils.isBlank(pingCustomID)) {
			throw new IllegalArgumentException("Arguments must be not null or empty.");
		}
		if (StringUtils.isBlank(pingCustomID)) {
			throw new IllegalArgumentException("Arguments must be not null or empty.");
		}
		long pingMillis = System.currentTimeMillis();
		PingLogEntry ping = new PingLogEntry(this.pingBatch, pingMillis, pingCustomID);
		ping.setPingComments(pingComments);
		pingBatch.addPing(ping);
	}

	/**
	 * Upload pings batch
	 */
	public PingBatchLogEntry uploadBatch() {
		return pingBatch.clone();
	}

}
