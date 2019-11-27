package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.UserRecipeRequest;
import ShakeAndBake.sabprototype.models.UserRecipeResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Arrays;

/*
    Shake And Bake Prototype Integration Tests
 */
@SpringBootTest
public class SabPrototypeIntegrationTests {

    // Integration test for retrieving recipes for a user named bob who likes peanuts
    @Test
    public void givenAUserRecipeRequestForBobWhoLikesPeanuts_whenGetNewRecipesForUser_thenUserRecipeResponse() {
        // given
        UserRecipeRequest userRecipeRequest = createUserRecipeRequest("Bob", new ArrayList<String>(Arrays.asList("peanuts")));
        RecipeProxy recipeProxy = new RecipeProxy();

        // when
        UserRecipeResponse userRecipeResponse = recipeProxy.GetNewRecipesForUser(userRecipeRequest);

        // then
        Assert.notNull(userRecipeResponse.getUserRecipes(), "User recipes are null!");
        Assert.isTrue(userRecipeResponse.getUserRecipes().size() > 0, "Expected greater than zero recipes for Bob, got zero.");
    }

    // Integration test for retrieving recipes for a user named Alice who likes chicken and sugar
    @Test
    public void givenAUserRecipeRequestForAliceWhoLikesChickenAndSugar_whenGetNewRecipesForUser_thenUserRecipeResponse() {
        // given
        UserRecipeRequest userRecipeRequest = createUserRecipeRequest("Alice", new ArrayList<String>(Arrays.asList("chicken", "sugar")));
        RecipeProxy recipeProxy = new RecipeProxy();

        // when
        UserRecipeResponse userRecipeResponse = recipeProxy.GetNewRecipesForUser(userRecipeRequest);

        // then
        Assert.notNull(userRecipeResponse.getUserRecipes(), "User recipes are null!");
        Assert.isTrue(userRecipeResponse.getUserRecipes().size() > 0, "Expected greater than zero recipes for Alice, got zero.");
    }

    // Integration test for retrieving recipes for a user named Roger who likes beans, peppers, onions, olives, and garlic
    @Test
    public void givenAUserRecipeRequestForRogerWhoLikesBeansPeppersOnionsOlivesAndGarlic_whenGetNewRecipesForUser_thenUserRecipeResponse() {
        // given
        UserRecipeRequest userRecipeRequest = createUserRecipeRequest("Roger", new ArrayList<String>(Arrays.asList("beans", "peppers", "onions", "olives", "garlic")));
        RecipeProxy recipeProxy = new RecipeProxy();

        // when
        UserRecipeResponse userRecipeResponse = recipeProxy.GetNewRecipesForUser(userRecipeRequest);

        // then
        Assert.notNull(userRecipeResponse.getUserRecipes(), "User recipes are null!");
        Assert.isTrue(userRecipeResponse.getUserRecipes().size() > 0, "Expected greater than zero recipes for Roger, got zero");
    }

    private UserRecipeRequest createUserRecipeRequest(String userName, ArrayList<String> ingredients) {
        return new UserRecipeRequest(userName, ingredients);
    }
}
