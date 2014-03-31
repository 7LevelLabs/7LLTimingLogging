package ua.ll7.slot7.timing.service;

import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.model.vo.PBIdCustomIdDateVO;

import java.util.List;

/**
 * @author Alex Velichko
 *         20.12.13 : 18:41
 */
public interface IPingBatchLogEntryReaderService {

	public PingBatchLogEntry getByID(final long id);

	public PingBatchLogEntry getByUUID(final String uuid);

	public boolean isExist(final long id);

	/**
	 * Get {@link PBIdCustomIdDateVO} list with CustomID
	 *
	 * @param CustomId CustomID to search
	 */
	public List<PBIdCustomIdDateVO> getVOList(final String CustomId);

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
	 * Get list of {@link PingBatchLogEntry#id} with customIDToSearch
	 *
	 * @param customIDToSearch CustomID to search
	 */
	public List<Long> getIDList(final String customIDToSearch);
}
