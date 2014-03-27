package ua.ll7.slot7.timing.model.response;

import java.util.List;

/**
 * @author Alex Velichko
 *         26.03.14 : 11:17
 */
public class GetCustomIDsResponse {
	private List<String> data;

	public GetCustomIDsResponse(List<String> data) {
		this.data = data;
	}

	public List<String> getData() {
		return data;
	}
}
