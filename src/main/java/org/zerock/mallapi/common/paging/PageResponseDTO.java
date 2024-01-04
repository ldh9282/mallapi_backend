package org.zerock.mallapi.common.paging;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import lombok.Builder;
import lombok.Data;

@Data
public class PageResponseDTO<E> {

	private List<E> dtoList;
	
	private List<Integer> pageNumList;
	
	private PageRequsetDTO pageRequsetDTO;
	
	private boolean prev, next;
	
	private int totalCount, prevPage, nextPage, totalPage, current;
	
	@Builder(builderMethodName = "withAll")
	public PageResponseDTO(List<E> dtoList, PageRequsetDTO pageRequsetDTO, long totalCount) {
		this.dtoList = dtoList;
		this.pageRequsetDTO = pageRequsetDTO; // page 이동할 페이지, size 페이지당 rowNum
		this.totalCount = (int) totalCount;
		
		int end = (int)(Math.ceil(pageRequsetDTO.getPage() / 10.0)) * 10;
		int start = end - 9; // "1" ~ 10, "11" ~ 20
		int last = (int)(Math.ceil(totalCount/(double)pageRequsetDTO.getSize())); // 마지막 페이지
		end = end > last ? last : end; // 1 ~ "10", 11 ~ "20" 
		this.prev = start > 1; // prev 버튼
		this.next = totalCount > end * pageRequsetDTO.getSize(); // next 버튼
		
		this.pageNumList = IntStream.rangeClosed(start, end).boxed().collect(Collectors.toList()); // 1 ~ 10
		
		if (prev) {
			this.prevPage = start - 1; // prev 버튼클릭시 페이지
		}
		
		if (next) {
			this.nextPage = end + 1; // next 버튼클릭시 페이지
		}
		
		this.totalPage = this.pageNumList.size(); // 1 ~ 10 페이지 개수
		this.current = pageRequsetDTO.getPage(); // 현재 페이지
	}
}
