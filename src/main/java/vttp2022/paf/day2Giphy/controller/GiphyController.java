package vttp2022.paf.day2Giphy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import vttp2022.paf.day2Giphy.model.Gif;
import vttp2022.paf.day2Giphy.services.GiphyService;

@Controller
@RequestMapping(path="/")
public class GiphyController {
    
    @Autowired
    GiphyService gifSvc;
    
    @PostMapping(path="/search")
    public String returnSearches(@RequestBody MultiValueMap<String, String> form, Model model){
        
        String phrase = form.getFirst("phrase");
        Integer limit = Integer.parseInt(form.getFirst("limit"));
        String rating = form.getFirst("rating");

        List<Gif> list = gifSvc.retrieveGif(phrase, limit, rating);
        
        //gif.create(); //a create method in model that takes in jsonobj
        //And then add the url to model, and then in the view return the url 
        model.addAttribute("list", list);
        
        
        return "searchResults";
    }


}
