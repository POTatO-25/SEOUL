package com.tech.seoul.edu.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;

import com.tech.seoul.edu.models.EduDao;
import com.tech.seoul.edu.models.KidsLocalCenterNameDto;
import com.tech.seoul.edu.models.PageInfoDto;
import com.tech.seoul.edu.util.EduSearchVO;

@Service
public class LocalCenterService {

	private EduDao eduDao;
	
	public LocalCenterService(EduDao eduDao) {
		this.eduDao = eduDao;
	}
	
	public PageInfoDto<KidsLocalCenterNameDto> localCenterInfo(
			HttpServletRequest request,
			EduSearchVO searchVO){
		String query = request.getParameter("query");
		String areas = request.getParameter("areas");
		String[] areas_array = areas.split(",");
		String filters = request.getParameter("filters");
		String[] filters_array = filters.split(",");
		
		if (searchVO == null) {
		    searchVO = new EduSearchVO();
		}
		
		int total = 0;
		total = eduDao.localCenterNameCnt(filters_array, query, areas_array);
		String strPage = request.getParameter("page");
		if(strPage == null || strPage.isEmpty()) {
 			strPage="1";
 		}
		int page = Integer.parseInt(strPage);
		searchVO.setPage(page);
		
		searchVO.pageCalculate(total);
		
		int rowStart = searchVO.getRowStart();
		int rowEnd = searchVO.getRowEnd();
		List<KidsLocalCenterNameDto> items =
				eduDao.localCenterName(filters_array, query, areas_array,(rowStart-1),8);
		return new PageInfoDto<>(items,total,searchVO);
	}
}
