package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.UserRecipeRequest;
import ShakeAndBake.sabprototype.models.UserRecipeResponse;

/*
    Interface for a Recipe Proxy
 */
public interface IRecipeProxy {

    // Gets Recipes from a provided User Recipe Request
    UserRecipeResponse GetNewRecipesForUser(UserRecipeRequest request);
}
