package ShakeAndBake.sabprototype;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@SpringBootApplication
public class SabPrototypeApplication {
    private static final Logger logger = Logger.getLogger(String.valueOf(SabPrototypeApplication.class));

	public static void main(String[] args) {
		SpringApplication.run(SabPrototypeApplication.class, args);
        logger.info("Starting Recipe Proxy Prototype!");
        RecipeProxy recipeProxy = new RecipeProxy();
	}

}
