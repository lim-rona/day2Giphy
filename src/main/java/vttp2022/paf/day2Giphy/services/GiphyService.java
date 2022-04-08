package vttp2022.paf.day2Giphy.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import vttp2022.paf.day2Giphy.model.Gif;

@Service
public class GiphyService {

    // //GIPHY_API_KEY
    // @Value("${giphy.api.key}")
    // private String giphyKey; 

    private static final String BASE_URL = "https://api.giphy.com/v1/gifs/search";

    public List<Gif> retrieveGif(String phrase, Integer limit, String rating){
        String url = UriComponentsBuilder.fromUriString(BASE_URL)
            .queryParam("api_key", "tED6TfJwH3MC1xpGdCeFGwetoMb2KaYC")
            .queryParam("q", phrase)
            .queryParam("limit", limit)
            .queryParam("offset", "0")
            .queryParam("rating", rating)
            .queryParam("lang", "en")
            .toUriString();
        
        RequestEntity<Void> req = RequestEntity.get(url)
            .accept(MediaType.APPLICATION_JSON)
            .build();
        
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);


        return Gif.create(resp.getBody());

    }
}
