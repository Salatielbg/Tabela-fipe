package br.com.alura.tabelfipe;

import br.com.alura.tabelfipe.principal.Principal;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TabelfipeApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TabelfipeApplication.class, args);
	}


	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal();
		principal.exibeMenu();
	}
}
