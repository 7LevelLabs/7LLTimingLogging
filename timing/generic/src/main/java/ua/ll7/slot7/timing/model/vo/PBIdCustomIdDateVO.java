package ua.ll7.slot7.timing.model.vo;

import java.util.Date;

/**
 * @author Oleksandr Velychko
 *         29.01.14 : 11:52
 */
public class PBIdCustomIdDateVO {
	private final long id;
	private final String pingBatchCustomID;
	private final Date date;

	public PBIdCustomIdDateVO(long id, String pingBatchCustomID, Date date) {
		this.id = id;
		this.pingBatchCustomID = pingBatchCustomID;
		this.date = date;
	}

	public long getId() {
		return id;
	}

	public String getPingBatchCustomID() {
		return pingBatchCustomID;
	}

	public Date getDate() {
		return date;
	}

	@Override
	public String toString() {
		final StringBuilder sb = new StringBuilder("PBIdCustomIdVO{");
		sb.append("id=").append(id);
		sb.append(", pingBatchCustomID='").append(pingBatchCustomID).append('\'');
		sb.append('}');
		return sb.toString();
	}
}
