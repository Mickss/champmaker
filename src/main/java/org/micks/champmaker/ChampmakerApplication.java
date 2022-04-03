package org.micks.champmaker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class ChampmakerApplication {

	public static void main(String[] args) throws IOException {
		FirebaseInitializer.initialize();
		SpringApplication.run(ChampmakerApplication.class, args);
	}


}
