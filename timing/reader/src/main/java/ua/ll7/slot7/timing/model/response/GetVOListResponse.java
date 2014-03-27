package ua.ll7.slot7.timing.model.response;

import ua.ll7.slot7.timing.model.vo.PBIdCustomIdDateVO;

import java.util.List;

/**
 * @author Alex Velichko
 *         27.03.14 : 11:29
 */
public class GetVOListResponse {
	private List<PBIdCustomIdDateVO> data;

	public GetVOListResponse(List<PBIdCustomIdDateVO> data) {
		this.data = data;
	}

	public List<PBIdCustomIdDateVO> getData() {
		return data;
	}
}
