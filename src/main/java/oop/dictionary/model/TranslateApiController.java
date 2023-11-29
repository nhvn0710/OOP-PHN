package oop.dictionary.model;



import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import org.json.JSONArray;
import org.json.JSONObject;


/**
 *
 * API translate.
 */
public class TranslateApiController {
    private String translation;
    public TranslateApiController(String word, String sourceLanguage, String targetLanguage) {
        try{
            HttpResponse<String> response = Unirest.post("https://text-translator2.p.rapidapi.com/translate")
                    .header("content-type", "application/x-www-form-urlencoded")
                    .header("X-RapidAPI-Key", "5418ea3f37msh3b1cf2b76c4f733p1ff049jsnea29f37a92af")
                    .header("X-RapidAPI-Host", "text-translator2.p.rapidapi.com")
                    .body("source_language=" + sourceLanguage + "&target_language=" + targetLanguage + "&text=" + word)
                    .asString();

            JSONObject myResponse = new JSONObject(response.getBody().toString());
            JSONObject translateResponse = myResponse.getJSONObject("data");

            translation = translateResponse.getString("translatedText");

        }
        catch(Exception e) {

        }
    }
    public String getTranslatation() {
        return this.translation;
    }
}
