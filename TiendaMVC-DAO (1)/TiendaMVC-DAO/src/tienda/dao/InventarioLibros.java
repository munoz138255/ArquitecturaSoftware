package tienda.dao;

import java.util.ArrayList;
import tienda.modelos.Libro;
import okhttp3.*;
import java.io.IOException;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.lang.reflect.*;

public class InventarioLibros implements InventarioLibrosInterface {
    
    private final String API_URL = "https://parseapi.back4app.com/classes/Libro";
    private final String APPLICATION_ID ="DD76WGMDy4sKzIwqdcsvoxX7bjSFUzAjujocUOUH";
    private final String REST_API_KEY ="pqBIT29XGqM8zWQSQJOKRmDcyNSsqdpqt5T5Dc1e";
    

    @Override
    public ArrayList<Libro> getAll(){
        ArrayList<Libro> employees = new ArrayList<Libro>();
        try{
             //sincrono
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                  .url(API_URL)
                  .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                  .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                  .addHeader("Content-Type", "application/json")
                  .get()
                  .build();
            Response response = client.newCall(request).execute();
            if(response.code() == 200) {
              String responseJson = response.body().string();
              System.out.println(responseJson);
              JsonObject jsonObject = new Gson().fromJson(responseJson, JsonObject.class);
              JsonArray jsonArray = jsonObject.getAsJsonArray("results");
              Gson gson = new Gson();
              Type listType = new TypeToken<ArrayList<Libro>>() {
                        }.getType();
              employees = gson.fromJson(jsonArray, listType);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return employees;

      
    }
  @Override
    public String insert(Libro libro){
        String objectId="";
        Gson gson = new Gson();
        String jsonProveedor = gson.toJson(libro);
        try {
            Request request = new Request.Builder()
                .url(API_URL)
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .post(RequestBody.create(MediaType.get("application/json"),jsonProveedor))
                .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful()) {
                Libro newLibro = gson.fromJson(response.body().string(), Libro.class);
                objectId = newLibro.objectId;
                System.out.println("insert" + objectId);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return objectId;
    }
}