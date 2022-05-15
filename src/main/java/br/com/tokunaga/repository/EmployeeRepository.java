package br.com.tokunaga.repository;

import br.com.tokunaga.entity.Employee;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@AllArgsConstructor
public class EmployeeRepository {

    private DynamoDBMapper dynamoDBMapper;

    public Employee save(Employee funcionario) {
        dynamoDBMapper.save(funcionario);
        return funcionario;
    }

    public Employee getFuncionarioById(String funcionarioId) {
        return dynamoDBMapper.load(Employee.class, funcionarioId);
    }

    public String delete(String funcionarioId) {
        Employee funcionario = dynamoDBMapper.load(Employee.class, funcionarioId);
        dynamoDBMapper.delete(funcionario);
        return "Funcionário Deletado!!";
    }

    public String update(String funcionarioId, Employee funcionario) {
        dynamoDBMapper.save(funcionario,
                new DynamoDBSaveExpression().withExpectedEntry("funcionarioId",
                        new ExpectedAttributeValue(new AttributeValue().withS(funcionarioId))));
        return funcionarioId;
    }
}
