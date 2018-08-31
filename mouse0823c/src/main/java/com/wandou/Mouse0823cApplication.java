package com.wandou;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.TimeZone;

@SpringBootApplication
public class Mouse0823cApplication {
    private static final Logger logger = LoggerFactory.getLogger(Mouse0823cApplication.class);

    public static void main(String[] args) {
        logger.debug("debug ***");
        logger.info("info log ***");
        logger.error("error log ***");
        logger.warn("warn log *** {}", "逗号后面的占位符里吗");
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
        SpringApplication.run(Mouse0823cApplication.class, args);
    }

//    @Bean
//    public EmbeddedServletContainerFactory servletContainer() {
//        TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
//            @Override
//            protected void postProcessContext(Context context) {
//                SecurityConstraint constraint = new SecurityConstraint();
//                constraint.setUserConstraint("CONFIDENTIAL");
//                SecurityCollection collection = new SecurityCollection();
//                collection.addPattern("/*");
//                constraint.addCollection(collection);
//                context.addConstraint(constraint);
//            }
//        };
//        tomcat.addAdditionalTomcatConnectors(httpConnector());
//        return tomcat;
//    }
//
//    @Bean
//    public Connector httpConnector() {
//        Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
//        connector.setScheme("http");
//        //Connector监听的http的端口号
//        connector.setPort(8888);
//        connector.setSecure(false);
//        //监听到http的端口号后转向到的https的端口号
//        connector.setRedirectPort(8888);
//        return connector;
//    }
}
