package devops.kilroywashere.wslocusers.controllers;

import devops.kilroywashere.wslocusers.models.Post;
import devops.kilroywashere.wslocusers.proxies.JSONPlaceHolderClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    private final JSONPlaceHolderClient jsonPlaceHolderClient;

    PostController(JSONPlaceHolderClient jsonPlaceHolderClient) {
        this.jsonPlaceHolderClient = jsonPlaceHolderClient;
    }

    @GetMapping("/posts")
    List<Post> getPosts() {
        return jsonPlaceHolderClient.getPosts();
    }
    @GetMapping("/posts/{id}")
    Post getPost(@PathVariable Long id) {
        Post post = jsonPlaceHolderClient.getPostById(id);
        String title = post.getTitle();
        return post;
    }

}
