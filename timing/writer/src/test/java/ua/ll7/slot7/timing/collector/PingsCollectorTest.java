package ua.ll7.slot7.timing.collector;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Alex Velichko
 *         28.12.13 : 13:00
 * @see ua.ll7.slot7.timing.model.PingLogEntry
 */
public class PingsCollectorTest extends Assert {

	private PingsCollector pingsCollector;

	@Before
	public void setUp() throws Exception {
		pingsCollector = new PingsCollector("Pings Collector 1");
	}

	@Test
	public void testPing() throws Exception {
		pingsCollector.ping();
		int size1 = pingsCollector.uploadBatch().getPingLogEntries().size();
		assertEquals(size1, 1);

		org.assertj.core.api.Assertions
			.assertThat(pingsCollector.uploadBatch().getPingLogEntries())
			.hasSize(1);

		pingsCollector.ping();

		org.assertj.core.api.Assertions
			.assertThat(pingsCollector.uploadBatch().getPingLogEntries())
			.hasSize(2);
	}

	@Test
	public void testPing1() throws Exception {
		pingsCollector.ping("Custom ID");
		String ping1CustomID = pingsCollector.uploadBatch().getPingLogEntries().get(0).getPingCustomID();
		assertEquals(ping1CustomID, "Custom ID");
	}

	@Test
	public void testPing2() throws Exception {
		pingsCollector.ping("Custom ID", "Ping comments");
		String ping1Comments = pingsCollector.uploadBatch().getPingLogEntries().get(0).getPingComments();
		assertEquals(ping1Comments, "Ping comments");
	}

}
