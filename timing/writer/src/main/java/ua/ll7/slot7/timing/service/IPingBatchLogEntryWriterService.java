package ua.ll7.slot7.timing.service;

import ua.ll7.slot7.timing.model.PingBatchLogEntry;

import java.util.Collection;

/**
 * @author Alex Velichko
 *         13.03.14 : 0:32
 */
public interface IPingBatchLogEntryWriterService {

	//CRUD
	public void createPingBatchLogEntry(final PingBatchLogEntry batchLogEntry);

	// BL
	public void collectBatchList(final Collection<PingBatchLogEntry> batchLogEntryList);
}
