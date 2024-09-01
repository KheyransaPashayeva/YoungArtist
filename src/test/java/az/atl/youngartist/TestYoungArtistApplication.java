package az.atl.youngartist;

import org.springframework.boot.SpringApplication;

public class TestYoungArtistApplication {

    public static void main(String[] args) {
        SpringApplication.from(YoungArtistApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
