package ua.ll7.slot7.timing.webservice.rest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.model.vo.PBIdCustomIdDateVO;
import ua.ll7.slot7.timing.service.impl.PingBatchLogEntryReaderService;
import ua.ll7.slot7.timing.webservice.IPingBatch;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

/**
 * @author Oleksandr Velychko
 *         29.01.14 : 17:31
 */
@Controller
@Path("/pingbatch")
public class RSPingBatch implements IPingBatch {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Qualifier("pingBatchLogEntryService")
	@Autowired
	private PingBatchLogEntryReaderService pingBatchLogEntryService;

	@GET
	@Path("/getcustomids")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<String> getCustomIDs() {
		return pingBatchLogEntryService.getCustomIDs();
	}

	@GET
	@Path("/getidlist/{customid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<Long> getIDList(@PathParam("customid") String customIDToSearch) {
		return pingBatchLogEntryService.getIDList(customIDToSearch);
	}

	@GET
	@Path("/getvolist/{customid}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public List<PBIdCustomIdDateVO> getVOList(@PathParam("customid") String CustomId) {
		return pingBatchLogEntryService.getVOList(CustomId);
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Override
	public PingBatchLogEntry getByID(@PathParam("id") long id) {
		return null;
	}

}
