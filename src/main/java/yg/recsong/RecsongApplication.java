package yg.recsong;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing // JPA Auditing을 사용하기 위한 어노테이션
public class RecsongApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecsongApplication.class, args);
	}

}
