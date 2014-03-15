package ua.ll7.slot7.timing.apps;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ua.ll7.slot7.timing.collector.PingsCollector;
import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.service.IPingBatchLogEntryWriterService;

import java.util.ArrayList;

/**
 * @author Oleksandr Velychko
 *         24.12.13 : 16:56
 */
@Component
@Scope("prototype")
public class TimingTestBean implements Runnable {
	private static Logger LOGGER = Logger.getLogger(TimingTestBean.class);

	public static int REPEATS = 50;
	public static int PINGS = 17;

	private String id;

	private ArrayList<PingBatchLogEntry> pingBatchLogEntryList;

	@Qualifier("pingBatchLogEntryWriterService")
	@Autowired
	private IPingBatchLogEntryWriterService iPingBatchLogEntryWriterService;

	@Override
	public void run() {
		init();
	}

	public void init() {
		pingBatchLogEntryList = new ArrayList<PingBatchLogEntry>(TimingTestBean.REPEATS);
		LOGGER.info("Start " + this.getId());

		try {
			process();
		} catch (InterruptedException e) {
			LOGGER.error("Error in the " + this.getId() + " bean. " + e.getMessage());
			e.printStackTrace();
		}

		LOGGER.info("Stop " + this.getId());
	}

	public void process() throws InterruptedException {
		PingsCollector pingsCollector = new PingsCollector("Pings Collector " + Thread.currentThread().getName() + " ");

		for (int i = 0; i < REPEATS; i++) {
			for (int y = 0; y < PINGS; y++) {
				pingsCollector.ping("Ping " + y);
				Thread.sleep(500);
			}

			pingBatchLogEntryList.add(pingsCollector.uploadBatch());
			Thread.sleep(1000);
		}

		LOGGER.info(this.getId() + " : Writing ping batch collection - BEGIN");
		iPingBatchLogEntryWriterService.collectBatchList(pingBatchLogEntryList);
		LOGGER.info(this.getId() + " : Writing ping batch collection - END. Current size: " + pingBatchLogEntryList.size());
		pingBatchLogEntryList.clear();
		LOGGER.info(this.getId() + " : Ping batches collection - cleared. Current size: " + pingBatchLogEntryList.size());

	}

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
