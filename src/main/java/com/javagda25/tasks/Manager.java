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
public class Manager {
    private final Gson g = new Gson();
    private final HttpClient client = HttpClient.newBuilder().build();
    private ScannerContent scannerContent = new ScannerContent();
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
        request = HttpRequest
                .newBuilder(URI.create(uri))
                .GET()
                .build();
        HttpResponse<String> response = null;

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
        return g.fromJson(response.body(), List.class);
    }

    public Boolean methodDelete(String uri) {
        // Zapytanie o usunięcie, na końcu zapytania mamy identyfikator elementu usuwanego
        // dla delete chcemy otrzymywać boolean -> true, gdy się uda usunąć, false, gdy nie uda
        HttpResponse<String> response = null;
        int numberToDelete = scannerContent.writeNumberToDelete();
        request = HttpRequest
                .newBuilder(URI.create(uri + "/" + numberToDelete))
                .DELETE()
                .build();

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return g.fromJson(response.body(), Boolean.class);
    }

}
