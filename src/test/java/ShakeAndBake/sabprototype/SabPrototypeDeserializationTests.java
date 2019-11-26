package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.edamam.EdamamRecipeResponse;
import ShakeAndBake.sabprototype.models.recipepuppy.RecipePuppyResponse;
import ShakeAndBake.sabprototype.models.spoonacular.SpoonacularIngredientRecipe;
import ShakeAndBake.sabprototype.models.spoonacular.SpoonacularRecipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.Unirest;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
	Shake And Bake Prototype Deserialization Tests
 */
@SpringBootTest
class SabPrototypeDeserializationTests {
	// Recipe Puppy API URI
	private static final String _recipePuppyApiUrl = "http://www.recipepuppy.com/api/";

	// Edamam API URI
	private static final String _edamamApiUrl = "https://api.edamam.com/search";

	// Spoonacular Ingredients API URI
	private static final String _spoonacularIngredientsApiUrl = "https://api.spoonacular.com/recipes/findByIngredients";

	// Spoonacular Recipe API URI
	private static final String _spoonacularRecipeApiUrl = "https://api.spoonacular.com/recipes/informationBulk";

	// Edamam app_id
	private static final String _edamamAppId = "dd836d03";

	// Edamam app_key
	private static final String _edamamAppKey = "27b615d78831494dfc9430226bce69e6";

	// Spoonacular API Key
	private static final String _spoonacularApiKey = "ce624a94673c45cfac384c84b0abbfbb";

	@Test
	void whenDeserializingRecipePuppyRecipeJson_thenReturnRecipePuppyResponse() {
		// given
		String recipeJson = Unirest.get(_recipePuppyApiUrl)
				.queryString("i", "peanuts")
				.queryString("p", "3")
				.asString()
				.getBody();

		// when
		RecipePuppyResponse response = new Gson().fromJson(recipeJson, RecipePuppyResponse.class);

		// then
		Assert.notNull(response.results, "Failed to deserialize Recipe Puppy JSON");
		Assert.isTrue(response.results.length > 0, "Expected greater than zero recipes from our RecipyPuppy API call, got zero.");
	}

	@Test
	void whenDeserializingEdamamRecipeJson_thenReturnEdamamResponse() {
		// given
		String recipeJson = Unirest.get(_edamamApiUrl)
				.queryString("q", "peanuts")
				.queryString("app_id", _edamamAppId)
				.queryString("app_key", _edamamAppKey)
				.asString()
				.getBody();

		// when
		EdamamRecipeResponse response = new Gson().fromJson(recipeJson, EdamamRecipeResponse.class);

		// then
		Assert.notNull(response.hits, "Failed to deserialize Edamam JSON");
		Assert.notNull(response.hits.get(0).recipe.url, "Failed to deserialize Edamam JSON");
		Assert.isTrue(response.hits.size() > 0, "Expected greater then zero recipes from our Edamama API call, got zero.");
	}

	@Test
	void whenDeserializingSpoonacularRecipeJson_thenReturnSpoonacularResponse(){
		// given
		String recipeJson = Unirest.get(_spoonacularIngredientsApiUrl)
				.queryString("ingredients", "peanuts")
				.queryString("apiKey", _spoonacularApiKey)
				.asString()
				.getBody();

		// when
		Type ingredientCollectionType = new TypeToken<Collection<SpoonacularIngredientRecipe>>(){}.getType();
		Collection<SpoonacularIngredientRecipe> ingredientRecipes = new Gson().fromJson(recipeJson, ingredientCollectionType);

		// then
		Assert.notNull(ingredientRecipes, "Failed to deserialize Spoonacular Ingredient Recipes JSON");

		// Spoonacular is weird, we get the recipe id's from the ingredients and then we need another API call
		// to get the recipe url and title, so we loop through all of our recipes, extract the IDs and do a bulkInformation call

		// given
		List<Integer> recipeIds = new ArrayList<Integer>();
		for (SpoonacularIngredientRecipe recipe : ingredientRecipes) {
			recipeIds.add(recipe.id);
		}
		String bulkRecipeJson = Unirest.get(_spoonacularRecipeApiUrl)
				.queryString("apiKey", _spoonacularApiKey)
				// Spoonacular needs a comma separated string of recipe ids, so we convert our id's to it
				.queryString("ids", recipeIds.stream().map(String::valueOf).collect(Collectors.joining(",")))
				.asString()
				.getBody();

		// when
		Type recipeCollectionType = new TypeToken<Collection<SpoonacularRecipe>>(){}.getType();
		Collection<SpoonacularRecipe> recipes = new Gson().fromJson(bulkRecipeJson, recipeCollectionType);

		// then
		Assert.notNull(recipes, "Failed to deserialize Spoonacular Recipes JSON");
		Assert.isTrue(recipes.size() > 0, "Expected greater than 0 Recipes from our Spoonacular Recipe API Call, got zero.");
	}

}
