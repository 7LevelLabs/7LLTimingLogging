package ua.ll7.slot7.timing.model.response;

import java.util.List;

/**
 * @author Alex Velichko
 *         27.03.14 : 11:24
 */
public class GetIDListResponse {
	private List<Long> data;

	public GetIDListResponse(List<Long> data) {
		this.data = data;
	}

	public List<Long> getData() {
		return data;
	}
}
