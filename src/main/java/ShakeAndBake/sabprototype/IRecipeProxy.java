package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.UserRecipeRequest;
import ShakeAndBake.sabprototype.models.UserRecipeResponse;
import ShakeAndBake.sabprototype.models.edamam.EdamamRecipeResponse;
import ShakeAndBake.sabprototype.models.recipepuppy.RecipePuppyResponse;

import java.util.List;

public interface IRecipeProxy {

    // Gets Recipes from a provided User Recipe Request
    UserRecipeResponse GetNewRecipesForUser(UserRecipeRequest request);
}
