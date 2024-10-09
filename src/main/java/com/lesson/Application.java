package com.lesson;

import com.lesson.services.VersionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.Banner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class, SecurityAutoConfiguration.class})
public class Application  {

    private static final Logger logger = LoggerFactory.getLogger(Application.class);


    /**
     * The Spring Authorization Server app starts here
     *
     * @param args holds an array of passed-in arguments from the command-line
     */
    public static void main( String[] args ) {
        logger.debug("main() started.");

        // Start up Spring Boot but disable the banner
        SpringApplication app = new SpringApplication(Application.class);
        app.setBannerMode(Banner.Mode.OFF);
        ConfigurableApplicationContext context  = app.run(args);

        // Get a reference to the versionService
        VersionService versionService = (VersionService) context.getBean("VersionService");

        // Log a message so that Tier 3 Support knows which version and commit Id is actually running
        logger.info("Custom OIDC Provider is Up / {} / {}\n", versionService.getAppVersion(), versionService.getCommitId() );
    }

}