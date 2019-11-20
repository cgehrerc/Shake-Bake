package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.RecipePuppyResponse;
import com.google.gson.Gson;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;

import java.util.logging.Logger;

public class RecipeProxy implements IRecipeProxy {

    //region Properties

    // Logger
    private static final Logger _logger = Logger.getLogger(String.valueOf(RecipeProxy.class));

    // Recipe Puppy API URI
    private static final String _recipePuppyApiUrl = "http://www.recipepuppy.com/api/";

    // Edamam API URI
    private static final String _edamamApiUrl = "https://api.edamam.com/search/";

    // Edamam app_id
    private static final String _edamamAppId = "dd836d03";

    // Edamam app_key
    private static final String __edamamAppKey = "27b615d78831494dfc9430226bce69e6";

    // Spoonacular API URI
    private static final String _spoonacularApiUrl = "https://api.spoonacular.com/recipes/FindByIngredients";

    // Spoonacular API Key
    private static final String _spoonacularApiKey = "ce624a94673c45cfac384c84b0abbfbb";

    //endregion

    //region Constructor
    public RecipeProxy() {
        _logger.info("Constructing Recipe Proxy!");
        //_logger.info(String.format("API Response: %s", GetRecipesFromRecipePuppy()));
    }
    //endregion

    //TODO: this is the method that the recipe manager will invoke
    // and pass in a User Recipe Request, make the three API calls using information
    // from the request, and return a User Recipe Response which will get persisted to the database
    // public UserRecipeResponse GetNewRecipesForUser(UserRecipeRequest request)

    // TODO: return Java Object of List<Recipes> from the JSON using Gson
    @Override
    public RecipePuppyResponse GetRecipesFromRecipePuppy() {

        //todo: update query string for ingredients to be multiple ingredients
        // ex) Unirest.get("http://httpbin.org")
        //        .queryString("fruit", Arrays.asList("apple", "orange"))
        //        .queryString(ImmutableMap.of("droid", "R2D2", "beatle", "Ringo"))
        String recipeJson = Unirest.get(_recipePuppyApiUrl)
                .queryString("i", "peanuts")
                .queryString("p", "3")
                .asString()
                .getBody();

        RecipePuppyResponse response = new Gson().fromJson(recipeJson, RecipePuppyResponse.class);
        return response;
    }

    // TODO: implement
    // TODO: return Java Object of List<Recipes> from the JSON using Gson
    // public String GetRecipesFromEdamam()

    // TODO: implement
    // TODO: return Java Object of List<Recipes> from the JSON using Gson
    // public String  GetRecipesFromSpoonacular()
}
