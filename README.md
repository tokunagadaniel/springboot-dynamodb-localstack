# springboot-dynamodb-localstack
Spring boot application integrated with dynamodb and localstack

1. First one we need run docker-compose to create local dynamodb and tables.
   1. docker-compose up -d

2. Run application.
   1. maven install
   2. java -jar springboot-dynamodb-localstack

3. Go to /postman and import collection to tests.

For more information on how tune dynamodb client configuration: 

https://aws.amazon.com/pt/blogs/database/tuning-aws-java-sdk-http-request-settings-for-latency-aware-amazon-dynamodb-applications