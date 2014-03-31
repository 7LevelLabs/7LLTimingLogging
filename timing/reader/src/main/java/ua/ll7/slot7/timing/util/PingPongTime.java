package ua.ll7.slot7.timing.util;

import ua.ll7.slot7.timing.model.PingLogEntry;

/**
 * Ping-pong time calculator
 *
 * @author Alex Velichko
 *         20.12.13 : 20:15
 */
public class PingPongTime {
	private PingLogEntry ping;
	private PingLogEntry pong;

	/**
	 * Return time between pong and ping events
	 *
	 * @param pingLogEntry Starting event
	 * @param pongLogEntry Stopping event
	 * @return Period between pong and ping events
	 * @throws java.lang.IllegalArgumentException if pingLogEntry or pongLogEntry are null
	 * @see ua.ll7.slot7.timing.model.PingLogEntry
	 */
	public static long pingPongTime(final PingLogEntry pingLogEntry, final PingLogEntry pongLogEntry) {
		if ((pingLogEntry == null) || (pongLogEntry == null)) {
			throw new IllegalArgumentException("Ping or pong are null.");
		}
		return pongLogEntry.getPingMillis() - pingLogEntry.getPingMillis();
	}

}
