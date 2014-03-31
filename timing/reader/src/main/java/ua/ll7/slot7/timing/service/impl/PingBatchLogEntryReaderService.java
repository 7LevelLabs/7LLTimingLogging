package ua.ll7.slot7.timing.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ua.ll7.slot7.timing.dao.IPingBatchLogEntryDAO;
import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.model.vo.PBIdCustomIdDateVO;
import ua.ll7.slot7.timing.service.IPingBatchLogEntryReaderService;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Alex Velichko
 *         20.12.13 : 18:45
 */
@Service("pingBatchLogEntryService")
@Scope("singleton")
public class PingBatchLogEntryReaderService implements IPingBatchLogEntryReaderService {
	private static Logger LOGGER = Logger.getLogger(PingBatchLogEntryReaderService.class);

	@Autowired
	private IPingBatchLogEntryDAO iPingBatchLogEntryDAO;

	@Override
	public PingBatchLogEntry getByID(final long id) {
		return iPingBatchLogEntryDAO.getByID(id);
	}

	@Override
	public PingBatchLogEntry getByUUID(final String uuid) {
		return iPingBatchLogEntryDAO.getByUUID(uuid);
	}

	@Override
	public boolean isExist(long idToCheck) {
		return (iPingBatchLogEntryDAO.getByID(idToCheck) != null);
	}

	@Override
	public List<PBIdCustomIdDateVO> getVOList(final String customId) {
		List<PBIdCustomIdDateVO> resultList = new LinkedList<PBIdCustomIdDateVO>();
		List<Long> idList = iPingBatchLogEntryDAO.getIDList(customId);

		Iterator<Long> iterator = idList.iterator();

		while (iterator.hasNext()) {
			Long l = iterator.next();
			PingBatchLogEntry p = iPingBatchLogEntryDAO.getByID(l);
			resultList.add(new PBIdCustomIdDateVO(p.getId(), p.getPingBatchCustomID(), p.getDate()));
		}

		return resultList;
	}

	@Override
	public List<String> getUUIDs() {
		return iPingBatchLogEntryDAO.getUUIDs();
	}

	@Override
	public List<String> getCustomIDs() {
		return iPingBatchLogEntryDAO.getCustomIDs();
	}

	@Override
	public Long getCustomIDsCount(final String customIDToCount) {
		return iPingBatchLogEntryDAO.getCustomIDsCount(customIDToCount);
	}

	@Override
	public List<Long> getIDList(final String customIDToSearch) {
		return iPingBatchLogEntryDAO.getIDList(customIDToSearch);
	}
}
