package ShakeAndBake.sabprototype.models;

/*
    UserRecipe information
 */
public class UserRecipe {
    // Properties
    private String _title;
    private String _url;

    // Constructor
    public UserRecipe(String title, String url){
        _title = title;
        _url = url;
    }

    // Getters and Setters
    public String getTitle() {
        return _title;
    }

    public void setTitle(String title) {
        _title = title;
    }

    public String getUrl() {
        return _url;
    }

    public void setUrl(String url) {
        _url = url;
    }
}
