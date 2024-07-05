package yg.recsong.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
public class WebConfig {

    /**
     *      -  웹 브라우저에서는 기본적으로 GET과 POST 요청만 지원하기 때문에, <br>
     *      -  @DeleteMapping과 같은 HTTP 메서드를 사용하려면 추가적인 설정이 필요하다. <p>
     *
     *      -  이를 위해 HiddenHttpMethodFilter 빈을 등록하거나 <br>
     *      -  application.properties에 등록하여 form 태그에서 다른 HTTP 메서드를 지원받을 수 있다. <p>
     *
     *      -  HiddenHttpMethodFilter를 사용하면 기존 FORM 방식으로 전송하고 <br>
     *      -  서버에서는 원하는 타입(DELETE, PUT, PATCH 등)으로 받을 수 있다.
     */

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }
}
