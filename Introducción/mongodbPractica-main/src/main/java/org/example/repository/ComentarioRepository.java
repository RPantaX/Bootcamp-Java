package org.example.repository;

import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.example.documents.Comentario;

public class ComentarioRepository {
    MongoCollection collectionComentario;
    public ComentarioRepository(MongoCollection collectionComentario){
        this.collectionComentario=collectionComentario;
    }
    public void agregarComentario(Comentario comentario){
        Document documentComentario= new Document();
        documentComentario.append("contenido",comentario.getContenido());
        collectionComentario.insertOne(documentComentario);
    }
}
