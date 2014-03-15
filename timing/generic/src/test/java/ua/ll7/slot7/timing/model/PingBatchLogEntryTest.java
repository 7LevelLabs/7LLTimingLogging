package ua.ll7.slot7.timing.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

/**
 * @author Oleksandr Velychko
 *         28.12.13 : 12:19
 */
public class PingBatchLogEntryTest extends Assert {

	private PingBatchLogEntry pingBatch;
	private PingLogEntry ping1, ping2, ping3;

	private final long time0 = 1388227620663L;
	private final long time1 = 1388227621663L;
	private final long time2 = 1388227622164L;
	private final long time3 = 1388227622414L;

	private final String batchCustomID = "Batch Custom ID Content";
	private final String batchComments = "Batch Comments Content";
	private final String batchUUID = "9d12589b-037c-47ce-9fb9-6dfcfbd49ea0";

	@Before
	public void setUp() throws Exception {
		pingBatch = new PingBatchLogEntry();

		pingBatch.setDate(new Date(time0));
		pingBatch.setPingBatchUUID(batchUUID);
		pingBatch.setPingBatchCustomID(batchCustomID);
		pingBatch.setPingBatchComments(batchComments);

		ping1 = new PingLogEntry(pingBatch, time1, "Ping 1");
		ping2 = new PingLogEntry(pingBatch, time2, "Ping 2");
		ping3 = new PingLogEntry(pingBatch, time3, "Ping 3");

		pingBatch.addPing(ping1);
		pingBatch.addPing(ping2);
		pingBatch.addPing(ping3);
	}

	@Test
	public void testAddPing() throws Exception {
		PingLogEntry ping4 = new PingLogEntry(pingBatch, System.currentTimeMillis(), "Ping 4");
		pingBatch.addPing(ping4);

		org.assertj.core.api.Assertions
			.assertThat(pingBatch.getPingLogEntries())
			.isNotEmpty();
		org.assertj.core.api.Assertions
			.assertThat(pingBatch.getPingLogEntries())
			.hasSize(4);
	}

	@Test
	public void testClearBatch() throws Exception {
		pingBatch.clearBatch();
		org.assertj.core.api.Assertions
			.assertThat(pingBatch.getPingLogEntries())
			.isEmpty();
	}

	@Test
	public void testClone() throws Exception {
		String pingBatchUUID = pingBatch.getPingBatchUUID();
		String pingBatchCustomID = pingBatch.getPingBatchCustomID();
		Date pingBatchDate = pingBatch.getDate();
		String pingBatchComments = pingBatch.getPingBatchComments();

		PingBatchLogEntry pingBatchCloned = pingBatch.clone();

		assertEquals(pingBatchUUID, pingBatchCloned.getPingBatchUUID());
		assertEquals(pingBatchCustomID, pingBatchCloned.getPingBatchCustomID());
		assertEquals(pingBatchDate, pingBatchCloned.getDate());
		assertEquals(pingBatchComments, pingBatchCloned.getPingBatchComments());

		assertTrue(
			pingBatchCloned.getPingLogEntries() != pingBatch.getPingLogEntries());
	}

	@Test
	public void testToString() throws Exception {
		String tmpToString = "PingBatchLogEntry{id=0, pingBatchCustomID='Batch Custom ID Content', pingBatchUUID='9d12589b-037c-47ce-9fb9-6dfcfbd49ea0', date=Sat Dec 28 12:47:00 EET 2013, pingLogEntries=[PingLogEntry{id=0, pingCustomID='Ping 1', pingMillis=1388227621663, pingComments='null'}, PingLogEntry{id=0, pingCustomID='Ping 2', pingMillis=1388227622164, pingComments='null'}, PingLogEntry{id=0, pingCustomID='Ping 3', pingMillis=1388227622414, pingComments='null'}], pingBatchComments='Batch Comments Content'}";
		assertEquals(tmpToString, pingBatch.toString());
	}
}
