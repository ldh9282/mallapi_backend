package org.zerock.mallapi.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.mallapi.common.paging.PageRequsetDTO;
import org.zerock.mallapi.common.paging.PageResponseDTO;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.service.TodoService;

import lombok.extern.log4j.Log4j2;

@RestController @Log4j2
public class TodoController {

	@Autowired
	private TodoService todoService;
	
	@GetMapping("/api/todo/{tno}")
	public TodoDTO get(@PathVariable(name = "tno") Long tno) {
		return todoService.get(tno);
	}
	
	@GetMapping("/api/todo/list")
	public PageResponseDTO<TodoDTO> list(PageRequsetDTO pageRequsetDTO) {
		return todoService.list(pageRequsetDTO);
	}
	
	@PostMapping("/api/todo")
	public Map<String, Long> register(@RequestBody TodoDTO todoDTO) {
		
		Long tno = todoService.register(todoDTO);
		
		return Map.of("TNO", tno);
	}
	
	@PutMapping("/api/todo/{tno}")
	public Map<String, String> modify(@PathVariable(name = "tno") Long tno, @RequestBody TodoDTO todoDTO) {
		todoDTO.setTno(tno);
		todoService.modify(todoDTO);
		
		return Map.of("RESULT", "SUCCESS");
	}
	
	@DeleteMapping("/api/todo/{tno}")
	public Map<String, String> remove(@PathVariable(name = "tno") Long tno) {
		todoService.remove(tno);
		
		return Map.of("RESULT", "SUCCESS");
	}
	
	
}
