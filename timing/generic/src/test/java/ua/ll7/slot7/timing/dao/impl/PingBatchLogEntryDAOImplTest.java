package ua.ll7.slot7.timing.dao.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import ua.ll7.slot7.timing.dao.IPingBatchLogEntryDAO;
import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.model.PingLogEntry;

import java.util.Date;

/**
 * @author Oleksandr Velychko
 *         28.01.14 : 13:31
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:genericAContext.xml")
public class PingBatchLogEntryDAOImplTest extends Assert {

	private PingLogEntry ping11 = null;
	private PingLogEntry ping12 = null;
	private PingLogEntry ping13 = null;

	private PingLogEntry ping21 = null;
	private PingLogEntry ping22 = null;
	private PingLogEntry ping23 = null;

	private PingBatchLogEntry batchLogEntry1 = null;
	private PingBatchLogEntry batchLogEntry2 = null;

	private final long moment1 = 1L;
	private final long moment2 = 2L;

	private final long period11 = 2L;
	private final long period12 = 2L;
	private final long period13 = 2L;

	private final long period21 = 2L;
	private final long period22 = 2L;
	private final long period23 = 2L;

	@Autowired
	private IPingBatchLogEntryDAO iPingBatchLogEntryDAO;

	@Before
	public void setUp() throws Exception {

		//batch 1

		batchLogEntry1 = new PingBatchLogEntry();
		batchLogEntry1.setPingBatchComments("batchLogEntry1 Comments");
		batchLogEntry1.setPingBatchCustomID("batchLogEntry1 Custom ID");
		batchLogEntry1.setDate(new Date(moment1));

		ping11 = new PingLogEntry(batchLogEntry1, period11, "ping11 Custom ID");
		ping12 = new PingLogEntry(batchLogEntry1, period12, "ping12 Custom ID");
		ping13 = new PingLogEntry(batchLogEntry1, period13, "ping13 Custom ID");

		batchLogEntry1.addPing(ping11);
		batchLogEntry1.addPing(ping12);
		batchLogEntry1.addPing(ping13);

		//batch 2

		batchLogEntry2 = new PingBatchLogEntry();
		batchLogEntry2.setPingBatchComments("batchLogEntry2 Comments");
		batchLogEntry2.setPingBatchCustomID("batchLogEntry2 Custom ID");
		batchLogEntry2.setDate(new Date(moment2));

		ping21 = new PingLogEntry(batchLogEntry2, period21, "ping21 Custom ID");
		ping22 = new PingLogEntry(batchLogEntry2, period22, "ping22 Custom ID");
		ping23 = new PingLogEntry(batchLogEntry2, period23, "ping23 Custom ID");

		batchLogEntry2.addPing(ping21);
		batchLogEntry2.addPing(ping22);
		batchLogEntry2.addPing(ping23);

		//persist data
		iPingBatchLogEntryDAO.createPingBatchLogEntry(batchLogEntry1);
		iPingBatchLogEntryDAO.createPingBatchLogEntry(batchLogEntry2);

	}

	@Test
	public void testCreatePingBatchLogEntry() throws Exception {
		String uuid1 = batchLogEntry1.getPingBatchUUID();
		PingBatchLogEntry batchRead1 = iPingBatchLogEntryDAO.getByUUID(uuid1);

		assertEquals(uuid1, batchRead1.getPingBatchUUID());

		org.assertj.core.api.Assertions
			.assertThat(batchRead1.getPingLogEntries())
			.hasSize(3);
	}

}
