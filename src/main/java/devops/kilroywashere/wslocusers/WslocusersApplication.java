package devops.kilroywashere.wslocusers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class WslocusersApplication {

    public static void main(String[] args) {
        SpringApplication.run(WslocusersApplication.class, args);
    }

}
