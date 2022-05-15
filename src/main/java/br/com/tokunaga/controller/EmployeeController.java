package br.com.tokunaga.controller;

import br.com.tokunaga.entity.Employee;
import br.com.tokunaga.repository.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeRepository funcionarioRepository;

    @PostMapping
    public Employee save(@RequestBody Employee employee) {
        return funcionarioRepository.save(employee);
    }

    @GetMapping("/{id}")
    public Employee getFuncionarioById(@PathVariable("id") String id) {
        return funcionarioRepository.getFuncionarioById(id);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") String id) {
        return funcionarioRepository.delete(id);
    }

    @PutMapping("/{id}")
    public String update(@PathVariable("id") String id, @RequestBody Employee employee) {
        return funcionarioRepository.update(id, employee);
    }
}
