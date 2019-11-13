package ShakeAndBake.sabprototype;

import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.logging.Logger;

public class RecipeProxy {

    private static final Logger logger = Logger.getLogger(String.valueOf(RecipeProxy.class));

    public RecipeProxy() {
        logger.info("Constructing Recipe Proxy!");
        logger.info(String.format("API Response: %s", CallRestApi("http://www.recipepuppy.com/api/")));
    }

    private String CallRestApi(String uri) {
        return Unirest.get(uri)
                .queryString("i", "peanuts")
                .queryString("p", "3")
                .asString()
                .getBody();
    }
}
