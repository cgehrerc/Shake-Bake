package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.UserRecipe;
import ShakeAndBake.sabprototype.models.UserRecipeRequest;
import ShakeAndBake.sabprototype.models.UserRecipeResponse;
import ShakeAndBake.sabprototype.models.edamam.EdamamRecipe;
import ShakeAndBake.sabprototype.models.edamam.EdamamRecipeHit;
import ShakeAndBake.sabprototype.models.edamam.EdamamRecipeResponse;
import ShakeAndBake.sabprototype.models.recipepuppy.RecipePuppyRecipe;
import ShakeAndBake.sabprototype.models.recipepuppy.RecipePuppyResponse;
import ShakeAndBake.sabprototype.models.spoonacular.SpoonacularIngredientRecipe;
import ShakeAndBake.sabprototype.models.spoonacular.SpoonacularRecipe;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import kong.unirest.Unirest;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RecipeProxy implements IRecipeProxy {

    //region Properties

    // Logger
    private static final Logger _logger = Logger.getLogger(String.valueOf(RecipeProxy.class));

    // Recipe Puppy API URI
    private static final String _recipePuppyApiUrl = "http://www.recipepuppy.com/api/";

    // Edamam API URI
    private static final String _edamamApiUrl = "https://api.edamam.com/search";

    // Edamam app_id
    private static final String _edamamAppId = "dd836d03";

    // Edamam app_key
    private static final String _edamamAppKey = "27b615d78831494dfc9430226bce69e6";

    // Spoonacular Ingredients API URI
    private static final String _spoonacularIngredientsApiUrl = "https://api.spoonacular.com/recipes/findByIngredients";

    // Spoonacular Recipe API URI
    private static final String _spoonacularRecipeApiUrl = "https://api.spoonacular.com/recipes/informationBulk";

    // Spoonacular API Key
    private static final String _spoonacularApiKey = "ce624a94673c45cfac384c84b0abbfbb";

    //endregion

    //region Constructor
    public RecipeProxy() {
        _logger.info("Constructing Recipe Proxy!");
    }
    //endregion

    // This is the method that the recipe manager will invoke
    // and pass in a User Recipe Request, make three recipe calls using information
    // from the request, and return a User Recipe Response which will get persisted to the database
    @Override
    public UserRecipeResponse GetNewRecipesForUser(UserRecipeRequest request) {
        List<UserRecipe> userRecipes = new ArrayList<UserRecipe>();

        // Get Recipes from Recipe Puppy's API
        RecipePuppyResponse recipePuppyResponse = GetRecipesFromRecipePuppy(request.getListOfIngredients());
        for(RecipePuppyRecipe recipePuppyRecipe : recipePuppyResponse.results) {
            UserRecipe userRecipe = new UserRecipe(recipePuppyRecipe.title, recipePuppyRecipe.href);
            userRecipes.add(userRecipe);
        }

        // Get Recipes from Edamam API
        EdamamRecipeResponse edamamRecipeResponse = GetRecipesFromEdamam(request.getListOfIngredients());
        for(EdamamRecipeHit edamamRecipe : edamamRecipeResponse.hits) {
            UserRecipe userRecipe = new UserRecipe(edamamRecipe.recipe.label, edamamRecipe.recipe.url);
            userRecipes.add(userRecipe);
        }

        // Get Recipes from Spoonacular
        List<SpoonacularRecipe> spoonacularRecipes = GetRecipesFromSpoonacular(request.getListOfIngredients());
        for(SpoonacularRecipe spoonacularRecipe : spoonacularRecipes) {
            UserRecipe userRecipe = new UserRecipe(spoonacularRecipe.title, spoonacularRecipe.sourceUrl);
            userRecipes.add(userRecipe);
        }

        return new UserRecipeResponse(request.getUserName(), userRecipes);
    }

    /*
        Gets a list of recipes from Recipe Puppy's Recipe API from a provided list of ingredients
     */
    private RecipePuppyResponse GetRecipesFromRecipePuppy(List<String> ingredients) {
        String recipeJson = "";
        if (ingredients != null) {
            if (ingredients.size() == 1) {
                recipeJson = Unirest.get(_recipePuppyApiUrl)
                        .queryString("i", ingredients.get(0))
                        .asString()
                        .getBody();
            }
            else {
                recipeJson = Unirest.get(_recipePuppyApiUrl)
                        .queryString("i", String.join(",", ingredients))
                        .asString()
                        .getBody();
            }
        }

        return new Gson().fromJson(recipeJson, RecipePuppyResponse.class);
    }

    /*
        Gets a list of recipes from Edamam's Recipe API from a provided list of ingredients
     */
    private EdamamRecipeResponse GetRecipesFromEdamam(List<String> ingredients) {
        String recipeJson = "";
        if (ingredients != null) {
            if (ingredients.size() == 1) {
                recipeJson = Unirest.get(_edamamApiUrl)
                        .queryString("q", ingredients.get(0))
                        .queryString("app_id", _edamamAppId)
                        .queryString("app_key", _edamamAppKey)
                        .asString()
                        .getBody();
            }
            else {
                recipeJson = Unirest.get(_edamamApiUrl)
                        .queryString("q", String.join(",", ingredients))
                        .queryString("app_id", _edamamAppId)
                        .queryString("app_key", _edamamAppKey)
                        .asString()
                        .getBody();
            }
        }

        return new Gson().fromJson(recipeJson, EdamamRecipeResponse.class);
    }

    /*
        Gets a list of recipes from Spoonacular's Recipe API from a provided list of ingredients
     */
    private List<SpoonacularRecipe> GetRecipesFromSpoonacular(List<String> ingredients) {
        String recipeJson = "";
        if (ingredients != null) {
            if (ingredients.size() == 1) {
                recipeJson = Unirest.get(_spoonacularIngredientsApiUrl)
                        .queryString("ingredients", ingredients.get(0))
                        .queryString("apiKey", _spoonacularApiKey)
                        .asString()
                        .getBody();
            }
            else {
                recipeJson = Unirest.get(_spoonacularIngredientsApiUrl)
                        .queryString("ingredients", String.join(",", ingredients))
                        .queryString("apiKey", _spoonacularApiKey)
                        .asString()
                        .getBody();
            }
        }

        Type ingredientCollectionType = new TypeToken<Collection<SpoonacularIngredientRecipe>>(){}.getType();
        Collection<SpoonacularIngredientRecipe> ingredientRecipes = new Gson().fromJson(recipeJson, ingredientCollectionType);

        // Spoonacular is weird, we get the recipe id's from the ingredients and then we need another API call
        // to get the recipe url and title, so we loop through all of our recipes, extract the IDs and do a bulkInformation call
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

        Type recipeCollectionType = new TypeToken<Collection<SpoonacularRecipe>>(){}.getType();
        return new Gson().fromJson(bulkRecipeJson, recipeCollectionType);
    }
}
