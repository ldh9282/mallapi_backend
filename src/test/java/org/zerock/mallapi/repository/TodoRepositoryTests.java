package org.zerock.mallapi.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.zerock.mallapi.common.paging.PageRequsetDTO;
import org.zerock.mallapi.common.paging.PageResponseDTO;
import org.zerock.mallapi.domain.Todo;
import org.zerock.mallapi.dto.TodoDTO;
import org.zerock.mallapi.service.TodoService;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
public class TodoRepositoryTests {
    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private TodoService todoService;
//    @Test
    public void testInsert() {

    	for (int i = 0; i <= 100; i++) {
    		
    		Todo todo = Todo.builder()
    						.title("Title..." + i)
    						.dueDate(LocalDate.of(2023, 12, 31))
    						.writer("user00")
    						.build();
//    		todoRepository.save(todo);
			
		}
    }
    
    @Test
    public void testSelect() {
    	System.out.println(todoRepository.findById(122L).orElse(null));
    }
    
//    @Test
    public void testModify() {
    	Todo todo = todoRepository.findById(122L).orElse(null);
    	todo.changeTitle("Modified 122...");
    	todo.changeComplete(true);
    	todo.changeDueDate(LocalDate.of(2023, 10, 10));
    	
    	todoRepository.save(todo);
    	
    }
    
//    @Test
    public void testDeleteAll() {
    	todoRepository.deleteAll();
    }
    
//    @Test
    public void testPaging() {
    	
    	Pageable pageable = PageRequest.of(0, 10, Sort.by("tno").descending()); // pageNum, pageSize, order
    	
    	Page<Todo> result = todoRepository.findAll(pageable);
    	
    	result.getContent().forEach(todo -> System.out.println(todo));
    }
    
    @Test
    public void testList() {
    	PageRequsetDTO pageRequsetDTO = PageRequsetDTO.builder()
    												.page(2)
    												.size(10)
    												.build();
    	PageResponseDTO<TodoDTO> responseDTO = todoService.list(pageRequsetDTO);
    	
    	System.out.println("responseDTO ::: " + responseDTO);
    }
}
