package ShakeAndBake.sabprototype;

import ShakeAndBake.sabprototype.models.RecipePuppyResponse;

public interface IRecipeProxy {

    // Gets recipe information from recipepuppy.com
    RecipePuppyResponse GetRecipesFromRecipePuppy();
}
