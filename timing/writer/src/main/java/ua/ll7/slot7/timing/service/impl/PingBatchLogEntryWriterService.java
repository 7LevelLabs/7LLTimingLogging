package ua.ll7.slot7.timing.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import ua.ll7.slot7.timing.dao.IPingBatchLogEntryDAO;
import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.service.IPingBatchLogEntryWriterService;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author Alex Velichko
 *         13.03.14 : 0:33
 */
public class PingBatchLogEntryWriterService implements IPingBatchLogEntryWriterService {

	private static Logger LOGGER = Logger.getLogger(PingBatchLogEntryWriterService.class);

	private Collection<PingBatchLogEntry> pingBatches;

	@Autowired
	private IPingBatchLogEntryDAO iPingBatchLogEntryDAO;

	private Integer pingBatchesAmountThreshold;

	@PostConstruct
	private void init() {
		pingBatches = Collections.synchronizedCollection(new ArrayList<PingBatchLogEntry>());
	}

	/**
	 * Serving ping batches collection: check size and store, if size exceed {@link #pingBatchesAmountThreshold}
	 */
	public void pingBatchesCheckToStore() {
		LOGGER.info("!!!!!!!! Task - 3000 : " + this.pingBatches.size());
		if (this.pingBatches.size() > this.pingBatchesAmountThreshold) {
			try {
				//write
				this.pingBatchesStore();
				//clear
				this.pingBatchesClear();
			} catch (DataAccessException dae) {
				LOGGER.error(dae.getLocalizedMessage());
				LOGGER.error("Ping batches collection NOT STORED.");
			}
		}
	}

	/**
	 * Store pingBatches collection
	 */
	public void pingBatchesStore() {
		for (PingBatchLogEntry pingBatch : this.pingBatches) {
			iPingBatchLogEntryDAO.createPingBatchLogEntry(pingBatch);
		}
	}

	public void pingBatchesClear() {
		this.pingBatches.clear();
	}

	@Override
	public void createPingBatchLogEntry(final PingBatchLogEntry batchLogEntry) {
		iPingBatchLogEntryDAO.createPingBatchLogEntry(batchLogEntry);
	}

	@Override
	public void collectBatchList(Collection<PingBatchLogEntry> batchLogEntryList) {
		this.pingBatches.addAll(batchLogEntryList);
	}

	public void setPingBatchesAmountThreshold(Integer pingBatchesAmountThreshold) {
		this.pingBatchesAmountThreshold = pingBatchesAmountThreshold;
	}
}
