package comp.comp152;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Main {

    public static void main(String[] args) {
	    var dataGrabber = HttpClient.newHttpClient();
        var requestBuilder = HttpRequest.newBuilder();
        var x =URI.create("http://universities.hipolabs.com/search?name=Young");
        var dataRequest = requestBuilder.uri(x).build();

        HttpResponse<String> response = null;
        try {
            response = dataGrabber.send(dataRequest,HttpResponse.BodyHandlers.ofString());
        }
        catch(IOException exceptionObject){
            System.out.println("Error connecting to network or site");
        }
        catch(InterruptedException e){
            System.out.println("Connection to site broken");
        }
        if (response == null){
            System.out.println("Something went wrong.");
            System.exit(-1);
        }
        var usefulData = response.body();
        var dataParser = new Gson();
        UniversityDataType[] uniList = dataParser.fromJson(usefulData,UniversityDataType[].class);
        System.out.println(usefulData);

    }



}
