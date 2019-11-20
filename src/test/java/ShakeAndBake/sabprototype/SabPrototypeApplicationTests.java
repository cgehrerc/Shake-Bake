package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.EdamamResponse;
import ShakeAndBake.sabprototype.models.RecipePuppyResponse;
import com.google.gson.Gson;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@SpringBootTest
class SabPrototypeApplicationTests {
	// Recipe Puppy API URI
	private static final String _recipePuppyApiUrl = "http://www.recipepuppy.com/api/";

	// Edamam API URI
	private static final String _edamamApiUrl = "https://api.edamam.com/search";

	// Edamam app_id
	private static final String _edamamAppId = "dd836d03";

	// Edamam app_key
	private static final String _edamamAppKey = "27b615d78831494dfc9430226bce69e6";

	@Test
	void testingDeserializeOfRecipePuppyJson() {
		String recipeJson = Unirest.get(_recipePuppyApiUrl)
				.queryString("i", "peanuts")
				.queryString("p", "3")
				.asString()
				.getBody();

		RecipePuppyResponse response = new Gson().fromJson(recipeJson, RecipePuppyResponse.class);
		Assert.notNull(response.results);
	}

	@Test
	void testingDeserializeOfEdamamJson() {
		String recipeJson = Unirest.get(_edamamApiUrl)
				.queryString("q", "peanuts")
				.queryString("app_id", _edamamAppId)
				.queryString("app_key", _edamamAppKey)
				.asString()
				.getBody();

		EdamamResponse response = new Gson().fromJson(recipeJson, EdamamResponse.class);
		Assert.notNull(response.hits);
		Assert.notNull(response.hits.get(0).recipe.url);
	}

}
