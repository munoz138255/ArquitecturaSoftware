package com.example.deportes.DAO;

import com.example.deportes.modelo.Partida;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ApiPartidaRepository {
    private static final String API_URL = "https://parseapi.back4app.com/classes/Partida";
    private static final String APPLICATION_ID = "OAg7xFcN25aU0NiXBRpgNenDVmiNqYg4uuRGAxT5";
    private static final String REST_API_KEY = "L5fwPqr2lY5XpYvvo7SMQiL8EyVpJUwpsBXmyhy8";
    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");

    private final OkHttpClient client = new OkHttpClient();
    private final Gson gson = new Gson();

    private Request.Builder getRequestBuilder() {
        return new Request.Builder()
                .addHeader("X-Parse-Application-Id", APPLICATION_ID)
                .addHeader("X-Parse-REST-API-Key", REST_API_KEY)
                .addHeader("Content-Type", "application/json");
    }

    // Crear una nueva partida
    public String crearPartida(Partida partida) {
        String json = gson.toJson(partida);
        Request request = getRequestBuilder()
                .url(API_URL)
                .post(RequestBody.create(json, JSON))
                .build();

        String responseJson = ejecutarPeticion(request);
        if (responseJson != null) {
            JsonObject jsonObject = gson.fromJson(responseJson, JsonObject.class);
            return jsonObject.get("objectId").getAsString();
        }
        return null;
    }

    public List<Partida> obtenerPartidas() {
        List<Partida> partidas = new ArrayList<>();
        Request request = getRequestBuilder()
                .url(API_URL)
                .get()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                String responseJson = response.body().string();
                Type listType = new TypeToken<List<Partida>>(){}.getType();
                partidas = gson.fromJson(responseJson, listType);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return partidas;
    }

    // Agregar un jugador a una partida
    public boolean agregarJugadorAPartida(String partidaId, String jugadorId) {
        RequestBody body = RequestBody.create("{\"jugadores\": {\"__op\": \"AddUnique\", \"objects\": [\"" + jugadorId + "\"]}}", JSON);
        Request request = getRequestBuilder()
                .url(API_URL + "/" + partidaId)
                .put(body)
                .build();

        return ejecutarPeticion(request) != null;
    }

    // Agregar una ronda a una partida
    public boolean agregarRondaAPartida(String partidaId, String rondaId) {
        RequestBody body = RequestBody.create("{\"rondas\": {\"__op\": \"AddUnique\", \"objects\": [\"" + rondaId + "\"]}}", JSON);
        Request request = getRequestBuilder()
                .url(API_URL + "/" + partidaId)
                .put(body)
                .build();

        return ejecutarPeticion(request) != null;
    }

    public boolean eliminarPartida(String partidaId) {
        Request request = getRequestBuilder()
                .url(API_URL + "/" + partidaId)
                .delete()
                .build();

        return ejecutarPeticion(request) != null;
    }

    private String ejecutarPeticion(Request request) {
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
