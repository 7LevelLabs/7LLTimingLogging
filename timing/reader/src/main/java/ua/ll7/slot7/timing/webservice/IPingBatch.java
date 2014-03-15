package ua.ll7.slot7.timing.webservice;

import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.model.vo.PBIdCustomIdDateVO;

import java.util.List;

/**
 * @author Oleksandr Velychko
 *         29.01.14 : 17:19
 */
public interface IPingBatch {

	public List<String> getCustomIDs();

	public List<Long> getIDList(String customIDToSearch);

	public List<PBIdCustomIdDateVO> getVOList(String CustomId);

	public PingBatchLogEntry getByID(long id);

}
