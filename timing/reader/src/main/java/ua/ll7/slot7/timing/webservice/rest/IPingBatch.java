package ua.ll7.slot7.timing.webservice.rest;

/**
 * @author Oleksandr Velychko
 *         29.01.14 : 17:19
 */
public interface IPingBatch {

	public javax.ws.rs.core.Response getCustomIDs();

	public javax.ws.rs.core.Response getIDList(String customIDToSearch);

	public javax.ws.rs.core.Response getVOList(String CustomId);

	public javax.ws.rs.core.Response getByID(long id);

}
