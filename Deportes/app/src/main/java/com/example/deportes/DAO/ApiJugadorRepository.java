package com.example.deportes.DAO;

import com.example.deportes.modelo.Jugador;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiJugadorRepository {
    private final String API_URL="https://parseapi.back4app.com/classes/Jugador";
    private final String APPLICATION_ID = "WDIBVm5JUEmzu9sXMpnG63qqTBZSQA2zETPQsP34"; // ToDo. Pon aqui tú ID
    private final String REST_API_KEY = "LgQ9JpqM40HOrpYvKWov9vOEONZ2VwFrdwtf3Kux"; //ToDo. Pon aqui tú clave
    public ArrayList<Jugador> getAll(){
        ArrayList<Jugador> jugador = new ArrayList<Jugador>();
        try{
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
                Type listType = new TypeToken<ArrayList<Jugador>>() {
                }.getType();
                jugador = gson.fromJson(jsonArray, listType);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return jugador;
    }
    public String insert(Jugador employee){
        String objectId ="";
        Gson gson = new Gson();
        String json = gson.toJson(employee);
        try {
            Request request = new Request.Builder()
                    .url(API_URL)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .post(RequestBody.create(json, MediaType.get("application/json")))
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            if(response.isSuccessful() && response.body() != null) {
                JsonObject jsonObject = gson.fromJson(response.body().string(), JsonObject.class);
                objectId = jsonObject.get("objectId").getAsString();
            }
        }
        catch (IOException e) {
            System.out.println("Error al insertar el jugador: "+e.getMessage());
        }
        return objectId;
    }
    public Boolean update(Jugador jugador){


        Gson gson = new Gson();
        String json = gson.toJson(jugador);

        try {
            Request request = new Request.Builder()
                    .url(API_URL+"/"+jugador.objectId)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .addHeader("Content-Type", "application/json")
                    .put(RequestBody.create(json, MediaType.get("application/json")))
                    .build();
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            return response.isSuccessful();
        }
        catch (IOException e) {
            System.out.println("Error al modificar el empleado: " + e.getMessage());
            return false;
        }

    }
    public Boolean delete(String objectId) {
        try {
            Request request = new Request.Builder()
                    .url(API_URL + "/" + objectId)
                    .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                    .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                    .delete()
                    .build();

            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();

            return response.isSuccessful();
        } catch (IOException e) {
            System.out.println("Error al eliminar el empleado: " + e.getMessage());
            return false;
        }
    }
}
