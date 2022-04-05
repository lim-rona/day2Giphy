package vttp2022.paf.day2Giphy.model;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;

public class Gif {
    private String id;
    private String url;

    //getters and setters
    public String getId() {
        return id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public void setId(String id) {
        this.id = id;
    }

    //create method
    public static List<Gif> create(String s, Integer limit){
        List<Gif> list = new LinkedList<Gif>();

        try (InputStream is = new ByteArrayInputStream(s.getBytes())) {
            JsonReader reader = Json.createReader(is);
            JsonObject o = reader.readObject();

            JsonArray r = o.getJsonArray("data");
            // for(JsonObject c: r){
            // Gif gif = new Gif();
            // gif.setId(c.getString("id"));
            // gif.setUrl(c.getString("url"));
            // System.out.println(c.getString("url"));
            // }

            //r.size();
            
            for(int i=0;i<limit;i++){
                JsonObject c = r.getJsonObject(i);
                JsonObject q = c.getJsonObject("images");
                JsonObject t = q.getJsonObject("original");
                Gif gif = new Gif();
                //gif.setId(q.getString("id"));
                gif.setUrl(t.getString("url"));
                list.add(gif);
                System.out.println(t.getString("url"));
            }
            
            

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return list;
    }

}
