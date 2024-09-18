package az.atl.youngartist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class YoungArtistApplication {

    public static void main(String[] args) {
        SpringApplication.run(YoungArtistApplication.class, args);
    }

}
