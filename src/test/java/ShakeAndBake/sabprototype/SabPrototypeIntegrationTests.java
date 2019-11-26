package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.UserRecipe;
import ShakeAndBake.sabprototype.models.UserRecipeRequest;
import ShakeAndBake.sabprototype.models.UserRecipeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/*
    Shake And Bake Prototype Integration Tests
 */
@SpringBootTest
public class SabPrototypeIntegrationTests {
    // Recipe Puppy API URI
    private static final String _recipePuppyApiUrl = "http://www.recipepuppy.com/api/";

    // Integration test for retrieving recipes for a user named bob who likes peanuts
    @Test
    public void givenAUserRecipeRequestForBobWhoLikesPeanuts_whenGetNewRecipesForUser_thenUserRecipeResponse(){
        // given
        UserRecipeRequest userRecipeRequest = createUserRecipeRequest("bob", new ArrayList<String>(Arrays.asList("peanuts")));
        RecipeProxy recipeProxy = new RecipeProxy();

        // when
        UserRecipeResponse userRecipeResponse = recipeProxy.GetNewRecipesForUser(userRecipeRequest);

        // then
        Assert.notNull(userRecipeResponse.getUserRecipes(), "User recipes are null!");
        Assert.isTrue(userRecipeResponse.getUserRecipes().size() > 0, "Expected greater than zero recipes for a user, got zero.");
    }

    // Integration test for retrieving recipes for a user named bob who likes peanuts
    @Test
    public void givenAUserRecipeRequestForAliceWhoLikesChickenAndSugar_whenGetNewRecipesForUser_thenUserRecipeResponse(){
        // given
        UserRecipeRequest userRecipeRequest = createUserRecipeRequest("alice", new ArrayList<String>(Arrays.asList("chicken", "sugar")));
        RecipeProxy recipeProxy = new RecipeProxy();

        // when
        UserRecipeResponse userRecipeResponse = recipeProxy.GetNewRecipesForUser(userRecipeRequest);

        // then
        Assert.notNull(userRecipeResponse.getUserRecipes(), "User recipes are null!");
        Assert.isTrue(userRecipeResponse.getUserRecipes().size() > 0, "Expected greater than zero recipes for a user, got zero.");
    }

    private UserRecipeRequest createUserRecipeRequest(String userName, ArrayList<String> ingredients) {
        return new UserRecipeRequest(userName, ingredients);
    }
}
