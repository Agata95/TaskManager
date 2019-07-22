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

@Data
@ToString
public class Tasks {
    Gson g = new Gson();
    ScannerContent scannerContent = new ScannerContent();
    private final HttpClient client;
    // klasa, która potrafi wykonać zapytania HTTP -> te same komendy jakie są w PostMan (get, put, delete, itp.)
    // musi być Client aby stworzyć Request

    // obiekt, który reprezenctuje np. PostMan, czy przeglądarkę, coś co potrafi wykonać zapytanie
    // client wywołuje request -> można na nim wykonać metodę SEND, aby w wnyniku otrzymać odpowiedź - zawartość strony


    public Tasks() {
        client = HttpClient
                .newBuilder()
                .build();
    }

    // po wywołaniu metody 'build' obiekt kończy budowanie

    public List<Task> methodGet() {
        List<Task> objects;

        // tym należy manipulować
        // dla komendy GET chcemy otrzymywać listę
        HttpRequest request = HttpRequest
                .newBuilder(URI.create("http://192.168.110.34:8080/task"))
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
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // zmiana tekst -> obiekty
            // unmarshaller
            objects = g.fromJson(response.body(), List.class);
//            Boolean objects = g.fromJson(response.body(), Boolean.class);

//            System.out.println(objects);
            objects.forEach(System.out::println);

            return objects;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    public Boolean methodDelete() {
        // Zapytanie o usunięcie, na końcu zapytania mamy identyfikator elementu usuwanego
        // dla delete chcemy otrzymywać boolean -> true, gdy się uda usunąć, false, gdy nie uda
        Boolean object = false;
        int numberToDelete = scannerContent.writeNumberToDelete();
        HttpRequest request = HttpRequest
                .newBuilder(URI.create("http://192.168.110.34:8080/task/" + numberToDelete))
                .DELETE()
                .build();

        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            object = g.fromJson(response.body(), Boolean.class);
            System.out.println(object);

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return object;
    }

}
