Esse projeto contem exemplos uteis para o dia a dia.

Para subir a aplicação, executar o Método main em FleetApplication.java.

Para acessar a documentação da API, acesse: http://localhost:8080/swagger-ui/index.html.

Swagger
A configuração do swagger pode ser encontrada em: SwaggerConfig.java

Java Fluent Validator
Uma implementação simples pode ser encontrada em: CarValidator.java

Error Handler
O error handler, responsável por devolver mensagens de erros para o cliente, pode ser encontrada em: ErrorHandler.java

Rest controller
Um exemplo de controller com validação es tranformações pode ser encontrado em: CreateCarController.java

Map Struct (mappers)
Um exemplo de mapper pode ser encontrado em: CarMapper.java, CarEntityMapper.java

Teste unitário
Um teste unitário pode ser encontrado em: CreateCarUseCaseUnitTest.java, CarMapperUnitTest.java

Teste de integração
Exemplos de testes de integração podem ser encontrados em: SaveCarDataProviderIT.java, CreateCarControllerIT.java