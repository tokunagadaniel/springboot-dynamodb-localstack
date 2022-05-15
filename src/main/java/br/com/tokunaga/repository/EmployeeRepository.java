package br.com.tokunaga.repository;

import br.com.tokunaga.entity.Employee;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EmployeeRepository {

    private final DynamoDBMapper dynamoDBMapper;

    public void put(Employee employee) {
        dynamoDBMapper.save(employee);
    }

    public Employee get(String id) {
        return dynamoDBMapper.load(Employee.class, id);
    }

    public void delete(String id) {
        Employee employee = dynamoDBMapper.load(Employee.class, id);
        dynamoDBMapper.delete(employee);
    }

    public void update(String id, Employee employee) {
        dynamoDBMapper.save(employee,
                new DynamoDBSaveExpression().withExpectedEntry("id",
                        new ExpectedAttributeValue(new AttributeValue().withS(id))));
    }
}
