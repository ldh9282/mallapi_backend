package org.zerock.mallapi.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.zerock.mallapi.common.paging.PageRequsetDTO;
import org.zerock.mallapi.common.paging.PageResponseDTO;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.repository.TodoRepository;

import lombok.extern.log4j.Log4j2;

@Service @Transactional @Log4j2
public class TodoService {

	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	/***
	 * @기능 Todo 단건 등록
	 * @param todoDTO
	 * @return tno
	 */
	public Long register(TodoDTO todoDTO) {
		if (log.isDebugEnabled()) {log.debug("Start TodoService.register");}
		if (log.isDebugEnabled()) {log.debug("todoDTO ::: " + todoDTO);}
		Todo todo = modelMapper.map(todoDTO, Todo.class);
		if (log.isDebugEnabled()) {log.debug("todo ::: " + todo);}
		if (log.isDebugEnabled()) {log.debug("do ::: todoRepository.save");}
		Todo savedTodo = todoRepository.save(todo);
		if (log.isDebugEnabled()) {log.debug("savedTodo ::: " + savedTodo);}
		if (log.isDebugEnabled()) {log.debug("End TodoService.register");}
		return savedTodo.getTno();
	}
	
	/***
	 * @기능 Todo 단건 조회
	 * @param tno
	 * @return TodoDTO
	 */
	public TodoDTO get(Long tno) {
		if (log.isDebugEnabled()) {log.debug("Start TodoService.get");}
		if (log.isDebugEnabled()) {log.debug("tno ::: " + tno);}
		if (log.isDebugEnabled()) {log.debug("do ::: todoRepository.findById");}
		Optional<Todo> result = todoRepository.findById(tno);
		Todo todo = result.orElseThrow();
		TodoDTO dto = modelMapper.map(todo, TodoDTO.class);
		if (log.isDebugEnabled()) {log.debug("dto ::: " + dto);}
		if (log.isDebugEnabled()) {log.debug("End TodoService.get");}
		
		return dto;
	}
	
	/***
	 * @기능 Todo 단건 수정
	 * @param todoDTO
	 * @return
	 */
	public void modify(TodoDTO todoDTO) {
		if (log.isDebugEnabled()) {log.debug("Start TodoService.modify");}
		if (log.isDebugEnabled()) {log.debug("todoDTO ::: " + todoDTO);}
		if (log.isDebugEnabled()) {log.debug("do ::: todoRepository.findById");}
		Optional<Todo> result = todoRepository.findById(todoDTO.getTno());
		
		Todo todo = result.orElseThrow();
		
		todo.changeTitle(todoDTO.getTitle());
		todo.changeDueDate(todoDTO.getDueDate());
		todo.changeComplete(todoDTO.isComplete());
		if (log.isDebugEnabled()) {log.debug("todo ::: " + todo);}
		if (log.isDebugEnabled()) {log.debug("do ::: todoRepository.save");}
		todoRepository.save(todo);
		if (log.isDebugEnabled()) {log.debug("End TodoService.modify");}
		
	}
	
	/***
	 * @기능 Todo 단건 삭제
	 * @param tno
	 * @return
	 */
	public void remove(Long tno) {
		if (log.isDebugEnabled()) {log.debug("Start TodoService.remove");}
		if (log.isDebugEnabled()) {log.debug("tno ::: " + tno);}
		if (log.isDebugEnabled()) {log.debug("do ::: todoRepository.deleteById");}
		todoRepository.deleteById(tno);
		if (log.isDebugEnabled()) {log.debug("End TodoService.remove");}
	}
	
	/***
	 * @기능 TodoDTO 목록 페이징 조회
	 * @param pageRequsetDTO
	 * @return PageResponseDTO<TodoDTO>
	 */
	public PageResponseDTO<TodoDTO> list(PageRequsetDTO pageRequsetDTO) {
		if (log.isDebugEnabled()) {log.debug("Start TodoService.list");}
		if (log.isDebugEnabled()) {log.debug("pageRequsetDTO ::: " + pageRequsetDTO);}
		Pageable pageable = PageRequest.of(pageRequsetDTO.getPage() - 1, pageRequsetDTO.getSize(), Sort.by("tno").descending()); // 1페이지가 0이므로 주의
		if (log.isDebugEnabled()) {log.debug("pageable ::: " + pageable);}
		if (log.isDebugEnabled()) {log.debug("do ::: todoRepository.findAll");}
		Page<Todo> result = todoRepository.findAll(pageable);
		if (log.isDebugEnabled()) {log.debug("result ::: " + result);}
		List<TodoDTO> dtoList = result.getContent().stream().map(todo -> modelMapper.map(todo, TodoDTO.class))
															.collect(Collectors.toList());
		long totalCount = result.getTotalElements();
		if (log.isDebugEnabled()) {log.debug("dtoList ::: " + dtoList);}
		if (log.isDebugEnabled()) {log.debug("pageRequsetDTO ::: " + pageRequsetDTO);}
		if (log.isDebugEnabled()) {log.debug("totalCount ::: " + totalCount);}
		PageResponseDTO<TodoDTO> responseDTO = PageResponseDTO.<TodoDTO>withAll()
																.dtoList(dtoList)
																.pageRequsetDTO(pageRequsetDTO)
																.totalCount(totalCount)
																.build();
		if (log.isDebugEnabled()) {log.debug("build PageResponseDTO ::: " + responseDTO);}
		
		if (log.isDebugEnabled()) {log.debug("End TodoService.list");}
		return responseDTO;
	}
}
