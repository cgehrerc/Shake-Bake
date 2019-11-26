package ShakeAndBake.sabprototype.models;

import java.util.List;

/*
    User Recipe Request
 */
public class UserRecipeRequest {
    // Properties
    private String _userName;
    private List<String> _listOfIngredients;

    // Constructor
    public UserRecipeRequest(String userName, List<String> listOfIngredients) {
        _userName = userName;
        _listOfIngredients = listOfIngredients;
    }

    // Getters and Setters
    public String getUserName() {
        return _userName;
    }

    public void setUserName(String userName) {
        _userName = userName;
    }

    public List<String> getListOfIngredients() {
        return _listOfIngredients;
    }

    public void setListOfIngredients(List<String> listOfIngredients) {
        _listOfIngredients = listOfIngredients;
    }
}
