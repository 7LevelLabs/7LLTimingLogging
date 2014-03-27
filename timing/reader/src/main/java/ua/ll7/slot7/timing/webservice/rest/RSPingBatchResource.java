package ua.ll7.slot7.timing.webservice.rest;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import ua.ll7.slot7.timing.model.PingBatchLogEntry;
import ua.ll7.slot7.timing.model.response.GetCustomIDsResponse;
import ua.ll7.slot7.timing.model.response.GetIDListResponse;
import ua.ll7.slot7.timing.model.response.GetVOListResponse;
import ua.ll7.slot7.timing.model.vo.PBIdCustomIdDateVO;
import ua.ll7.slot7.timing.service.impl.PingBatchLogEntryReaderService;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * @author Oleksandr Velychko
 *         29.01.14 : 17:31
 */
@Controller
@Path("/pingbatch")
@Api(value = "/pingbatch", description = "Operations about Ping Batches")
public class RSPingBatchResource implements IPingBatch {

	private final Logger LOGGER = Logger.getLogger(this.getClass());

	@Qualifier("pingBatchLogEntryService")
	@Autowired
	private PingBatchLogEntryReaderService pingBatchLogEntryService;

	@GET
	@Path("/getcustomids")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find all Ping Batch custom IDs", response = GetCustomIDsResponse.class)
	@Override
	public Response getCustomIDs() {
		List<String> resultList = null;
		resultList = pingBatchLogEntryService.getCustomIDs();

		if (resultList == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Data not found").build();
		}

		return Response.status(200).entity(new GetCustomIDsResponse(resultList)).build();
	}

	@GET
	@Path("/getidlist/{customid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find Ping Batch IDs by custom ID", response = GetIDListResponse.class)
	@Override
	public Response getIDList(@PathParam("customid") String customId) {
		if (StringUtils.isBlank(customId)) {
			return Response.serverError().entity("Ping Batch custom ID cannot be blank").build();
		}

		List<Long> resultList = null;
		resultList = pingBatchLogEntryService.getIDList(customId);

		if (resultList == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Data not found ; custom ID : " + customId).build();
		}

		return Response.status(200).entity(new GetIDListResponse(resultList)).build();
	}

	@GET
	@Path("/getvolist/{customid}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find Ping Batch data by custom ID", response = GetVOListResponse.class)
	@Override
	public Response getVOList(@PathParam("customid") String customId) {
		if (StringUtils.isBlank(customId)) {
			return Response.serverError().entity("Ping Batch custom ID cannot be blank").build();
		}

		List<PBIdCustomIdDateVO> resultList = null;
		resultList = pingBatchLogEntryService.getVOList(customId);

		if (resultList == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Data not found ; custom ID : " + customId).build();
		}

		return Response.status(200).entity(new GetVOListResponse(resultList)).build();
	}

	@GET
	@Path("/get/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(value = "Find Ping Batch by ID", response = PingBatchLogEntry.class)
	@Override
	public Response getByID(@PathParam("id") long id) {
		if (id < 1) {
			return Response.serverError().entity("Ping Batch ID cannot be 0 or minor").build();
		}

		PingBatchLogEntry pingBatchLogEntry = null;
		pingBatchLogEntry = pingBatchLogEntryService.getByID(id);

		if (pingBatchLogEntry == null) {
			return Response.status(Response.Status.NOT_FOUND).entity("Ping Batch not found ; ID : " + id).build();
		}

		return Response.status(200).entity(pingBatchLogEntry).build();
	}

}
