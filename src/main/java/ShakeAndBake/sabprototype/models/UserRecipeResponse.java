package ShakeAndBake.sabprototype.models;

import java.util.List;

/*
    A User Recipe Response
 */
public class UserRecipeResponse {
    // Properties
    private String _userName;
    private List<UserRecipe> _userRecipes;

    // Constructors
    public UserRecipeResponse(String userName, List<UserRecipe> userRecipes) {
        _userName = userName;
        _userRecipes = userRecipes;
    }

    // Getters and Setters
    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public List<UserRecipe> getUserRecipes() {
        return _userRecipes;
    }

    public void setUserRecipes(List<UserRecipe> userRecipes) {
        _userRecipes = userRecipes;
    }
}
