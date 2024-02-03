package org.example;

import com.mongodb.client.MongoCollection;
import org.example.db.MongoDBConnector;
import org.example.documents.Comentario;
import org.example.repository.ComentarioRepository;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        MongoDBConnector.connect();
        List<String> collections = MongoDBConnector.database.listCollectionNames().into(new ArrayList<>());

        //trabajamos con comentario
        MongoCollection comentarioCollection= MongoDBConnector.database.getCollection("comentarios");

        ComentarioRepository comentarioRepository= new ComentarioRepository(comentarioCollection);

        Comentario comentario = new Comentario();
        comentario.setContenido("Horrible de mrd");
        comentarioRepository.agregarComentario(comentario);

        for(String collection: collections)
        {
            System.out.println(collection);
            System.out.println();
        }
    }
}