# Shake && Bake

## Resources

spoonacular api key:
ce624a94673c45cfac384c84b0abbfbb

Once you have your API key, you have to put it in the request URL for every request you make like so ?apiKey=YOUR-API-KEY.
docs: https://spoonacular.com/food-api/docs

edemam api key:
app id = dd836d03
app key = 27b615d78831494dfc9430226bce69e6

example curl "https://api.edamam.com/search?q=chicken&app_id=${YOUR_APP_ID}&app_key=${YOUR_APP_KEY}&from=0&to=3&calories=591-722&health=alcohol-free"
docs: https://developer.edamam.com/edamam-docs-recipe-api

http://www.recipepuppy.com/about/api/

https://stackoverflow.com/questions/16512188/how-to-generate-java-client-proxy-for-restful-service-implemented-with-spring

http://kong.github.io/unirest-java/#configuration

sample output from jar (java -jar target/sab-prototype-1.0.0-SNAPSHOT.jar):

2019-11-12 23:08:58.730  INFO 888 --- [           main] c.sabprototype.RecipeProxy               : Constructing Recipe Proxy!
2019-11-12 23:08:59.828  INFO 888 --- [           main] c.sabprototype.RecipeProxy               : API Response: {"title":"Recipe Puppy","version":0.1,"href":"http:\/\/www.recipepuppy.co
m\/","results":[{"title":"Delicious Nutty Apple Snack \r\n\r\n","href":"http:\/\/www.kraftfoods.com\/kf\/recipes\/delicious-nutty-apple-snack-65923.aspx?cm_re=1-_-1-_-RecipeAlsoEnjoy","i
ngredients":"apple, cream cheese, peanuts","thumbnail":"http:\/\/img.recipepuppy.com\/676526.jpg"},{"title":"Nutty Nuggets \r\n\t\t\r\n\t\r\n\t\t\r\n\t\r\n\t\r\n\r\n","href":"http:\/\/ww
w.kraftfoods.com\/kf\/recipes\/nutty-nuggets-109920.aspx","ingredients":"chicken, honey mustard, peanuts","thumbnail":"http:\/\/img.recipepuppy.com\/629438.jpg"},{"title":"Peanuts & Crac
ker Jacks Rafts","href":"http:\/\/allrecipes.com\/Recipe\/Peanuts--Cracker-Jacks-Rafts\/Detail.aspx","ingredients":"peanuts, poppy seed, peanuts, lettuce","thumbnail":"http:\/\/img.recip
epuppy.com\/21168.jpg"},{"title":"Candy Corn Snack Mix","href":"http:\/\/www.recipezaar.com\/Candy-Corn-Snack-Mix-319764","ingredients":"candy corn, chocolate, peanuts","thumbnail":"http
:\/\/img.recipepuppy.com\/279051.jpg"},{"title":"Chocolate Covered Oranges","href":"http:\/\/www.recipezaar.com\/Chocolate-Covered-Oranges-85455","ingredients":"milk chocolate, orange, p
eanuts","thumbnail":"http:\/\/img.recipepuppy.com\/307211.jpg"},{"title":"Haystacks","href":"http:\/\/www.recipezaar.com\/Haystacks-342578","ingredients":"butterscotch chips, chow mein n
oodles, peanuts","thumbnail":"http:\/\/img.recipepuppy.com\/349472.jpg"},{"title":"Easy Ice Cream Sandwiches","href":"http:\/\/www.recipezaar.com\/Easy-Ice-Cream-Sandwiches-310677","ingr
edients":"cookies, peanuts, vanilla ice cream","thumbnail":"http:\/\/img.recipepuppy.com\/523160.jpg"},{"title":"Green Jumbo Boiled Peanuts","href":"http:\/\/www.recipezaar.com\/Green-Ju
mbo-Boiled-Peanuts-246711","ingredients":"peanuts, salt, water","thumbnail":"http:\/\/img.recipepuppy.com\/706011.jpg"},{"title":"EAGLE BRAND&#174; Peanut Butter Fudge","href":"http:\/\/
allrecipes.com\/Recipe\/EAGLE-BRAND-Peanut-Butter-Fudge\/Detail.aspx","ingredients":"peanut butter, peanuts, vanilla extract","thumbnail":"http:\/\/img.recipepuppy.com\/6087.jpg"},{"titl
e":"Hot Fudge Ice Cream Bar Dessert","href":"http:\/\/allrecipes.com\/Recipe\/Hot-Fudge-Ice-Cream-Bar-Dessert\/Detail.aspx","ingredients":"ice cream, peanut butter, peanuts","thumbnail":
"http:\/\/img.recipepuppy.com\/16966.jpg"}]}
