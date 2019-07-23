package com.javagda25.tasks;

import com.google.gson.Gson;
import lombok.Data;
import lombok.ToString;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Optional;

@Data
@ToString
public class Manager {
    private final Gson GSON = new Gson();
    private HttpClient client = HttpClient.newBuilder().build();
    private ScannerLoader scannerLoader = new ScannerLoader();
    private HttpRequest request;
    // klasa, która potrafi wykonać zapytania HTTP -> te same komendy jakie są w PostMan (get, put, delete, itp.)
    // musi być Client aby stworzyć Request

    // obiekt, który reprezenctuje np. PostMan, czy przeglądarkę, coś co potrafi wykonać zapytanie
    // client wywołuje request -> można na nim wykonać metodę SEND, aby w wnyniku otrzymać odpowiedź - zawartość strony


//    public Manager() {
//        client = HttpClient
//                .newBuilder()
//                .build();
//    }

    // po wywołaniu metody 'build' obiekt kończy budowanie

    public List methodGet(String uri) {
        // tym należy manipulować
        // dla komendy GET chcemy otrzymywać listę
        HttpResponse<String> response = null;
        request = HttpRequest
                .newBuilder(URI.create(uri))
                .GET()
                .build();

        try {
            // wysłanie przez klienta zapytania (request)
            // BodyHandler klasa która w swojej metodzie apply mówi co ma się wydarzyć z wynikiem.

            // client to obiekt który może wywołać SEND i przesłać request, a w wyniku otrzymuje odpowiedź (zawartość strony)

            // HttpResponse.BodyHandlers.ofString() - to gotowy obiekt który "radzi sobie" z odpowiedzią.
            //                                      został napisany w taki sposób by wyjście przepisać w postaci string'a
            //                                      i zwrócić go w body obiektu HttpResponse.
            // odpowiedzią ma być String
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // zmiana tekst -> obiekty
            // unmarshaller

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return GSON.fromJson(response.body(), List.class);
    }

    public Boolean methodDelete(String uri) {
        // Zapytanie o usunięcie, na końcu zapytania mamy identyfikator elementu usuwanego
        // dla delete chcemy otrzymywać boolean -> true, gdy się uda usunąć, false, gdy nie uda
        HttpResponse<String> response = null;
        Long numberToDelete = scannerLoader.askIDToDelete();
        request = HttpRequest
                .newBuilder(URI.create(uri + "/" + numberToDelete))
                .DELETE()
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
            return GSON.fromJson(response.body(), Boolean.class);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void methodPut(String uri, Task taskToSend) {
        HttpResponse<String> response;
        String jsonTaskToSend = GSON.toJson(taskToSend);
        request = HttpRequest
                .newBuilder(URI.create(uri))
                .PUT(HttpRequest.BodyPublishers.ofString(jsonTaskToSend))
                .header("Content-Type", "application/json")
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void methodPost(String uri, Task taskToUpdate) {
        HttpResponse<String> response;
        String jsonTaskToSend = GSON.toJson(taskToUpdate);
        request = HttpRequest
                .newBuilder(URI.create(uri))
                .POST(HttpRequest.BodyPublishers.ofString(jsonTaskToSend))
                .header("Content-Type", "application/json")
                .build();
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Optional<Task> getById(String uri, Long idToGet){
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(uri + "/" + idToGet))
                .GET()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return Optional.ofNullable(GSON.fromJson(response.body(), Task.class));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
