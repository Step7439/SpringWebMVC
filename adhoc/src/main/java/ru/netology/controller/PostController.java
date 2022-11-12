package ru.netology.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.netology.model.Post;
import ru.netology.service.PostService;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/api/posts")
public class PostController {
  private final PostService service;

  public PostController(PostService service) {
    this.service = service;
  }

  @GetMapping
  public List<Post> all(){
    return service.all();
  }

  @GetMapping("/{id}")
  public Post getById(@PathVariable long id) {
    return service.getById(id);
  }

  @PostMapping
  public Post save(@RequestBody Post post) throws IOException {
    return service.save(post);
  }

  @DeleteMapping("/{id}")
  public void removeById(long id) {
    service.removeById(id);
  }
}
