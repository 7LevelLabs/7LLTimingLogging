package ua.ll7.slot7.timing.dao;

import ua.ll7.slot7.timing.model.PingBatchLogEntry;

import java.util.List;

/**
 * @author Oleksandr Velychko
 *         20.12.13 : 18:24
 */
public interface IPingBatchLogEntryDAO {
	/**
	 * Persist new batch
	 *
	 * @param pingBatchLogEntry PingBatchLogEntry instance to persist
	 */
	public void createPingBatchLogEntry(final PingBatchLogEntry pingBatchLogEntry);

	/**
	 * Get batch by its tech-style ID
	 *
	 * @param batchId tech-style batch ID
	 */
	public PingBatchLogEntry getByID(final long batchId);

	/**
	 * Get batch by its UUID
	 *
	 * @param uuid UUID batch ID
	 * @see java.util.UUID
	 */
	public PingBatchLogEntry getByUUID(final String uuid);

	/**
	 * Get overall UUIDs list
	 */
	public List<String> getUUIDs();

	/**
	 * Get overall CustomIDs list
	 */
	public List<String> getCustomIDs();

	/**
	 * Get count of the CustomID
	 */
	public Long getCustomIDsCount(final String customIDToCount);

	/**
	 * Get list of the {@link PingBatchLogEntry#id} with customIDToSearch
	 *
	 * @param customIDToSearch CustomID to search
	 */
	public List<Long> getIDList(final String customIDToSearch);

}
