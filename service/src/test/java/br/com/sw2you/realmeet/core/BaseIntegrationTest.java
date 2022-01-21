package br.com.sw2you.realmeet.core;

import java.net.MalformedURLException;
import java.net.URL;
import br.com.sw2you.realmeet.Application;
import br.com.sw2you.realmeet.api.ApiClient;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(profiles = "integration-test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = Application.class)
public class BaseIntegrationTest {

    @Autowired
    private Flyway flyway;

    @LocalServerPort
    private int serverPort;

    @BeforeEach
    void setup()throws Exception{
        setupFlyway();
        setupEach();
    }

    protected void setupEach() throws Exception{}

    protected void setLocalHostBasePath(ApiClient apiClient, String path)throws MalformedURLException {
        apiClient.setBasePath(new URL("http", "localhost", serverPort, path).toString());
    }

    private void setupFlyway() {
        //limpa e inicia nova migração
        flyway.clean();
        flyway.migrate();
    }
}

/*
@ActiveProfiles(profiles = "integration-test"): Esta anotação é para que o spring saiba que tem que ler as informações do
application-integration-test.yml durante o teste de integração
@SpringBootTest: Usando t0do o conceito de spring
    webEnvironment: Define o servidor numa porta randomica a fim de evitar conflitos na execução
    classes: Onde o teste se inicia, no caso na classe principal Application.java
 */