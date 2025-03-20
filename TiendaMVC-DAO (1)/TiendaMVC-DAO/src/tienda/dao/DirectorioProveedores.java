package tienda.dao;

import java.util.ArrayList;
import tienda.modelos.Proveedor;
import okhttp3.*;
import java.io.IOException;
import com.google.gson.*;
import com.google.gson.reflect.*;
import java.lang.reflect.*;



public class DirectorioProveedores implements DirectorioProveedoresInterface {
    
    private final String API_URL = "https://parseapi.back4app.com/classes/Proveedor";
    private final String APPLICATION_ID ="DD76WGMDy4sKzIwqdcsvoxX7bjSFUzAjujocUOUH";
    private final String REST_API_KEY ="pqBIT29XGqM8zWQSQJOKRmDcyNSsqdpqt5T5Dc1e";
    

    @Override
    public ArrayList<Proveedor> getAll(){
        ArrayList<Proveedor> employees = new ArrayList<Proveedor>();
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
              Type listType = new TypeToken<ArrayList<Proveedor>>() {
                        }.getType();
              employees = gson.fromJson(jsonArray, listType);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return employees;

      
    }
  @Override
    public String insert(Proveedor proveedor){
        String objectId="";
        Gson gson = new Gson();
        String jsonProveedor = gson.toJson(proveedor);
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
                Proveedor newProveedor = gson.fromJson(response.body().string(), Proveedor.class);
                objectId = newProveedor.objectId;
                System.out.println("insert" + objectId);
            }
        }
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return objectId;
    }
}


