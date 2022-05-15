package br.com.tokunaga.controller;

import br.com.tokunaga.entity.Employee;
import br.com.tokunaga.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeRepository repository;

    @PostMapping
    public ResponseEntity put(@RequestBody Employee employee) {
        repository.put(employee);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> get(@PathVariable("id") String id) {
        return ResponseEntity.ok(repository.get(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id) {
        repository.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity update(@PathVariable("id") String id, @RequestBody Employee employee) {
        repository.update(id, employee);
        return ResponseEntity.ok().build();
    }
}
