package ru.netology.repository;

import org.springframework.stereotype.Repository;
import ru.netology.exception.NotFoundException;
import ru.netology.model.Post;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class PostRepositoryStubImpl implements PostRepository {
  private final ConcurrentHashMap<Long, Post> posts = new ConcurrentHashMap<>();
  private final AtomicLong atomicLong = new AtomicLong(0);

  public List<Post> all() {
    return new ArrayList<>(posts.values());
  }

  public Optional<Post> getById(long id) {
    return Optional.ofNullable(posts.get(id));
  }

  public Post save(Post post) throws NotFoundException {
    if (post.getId() == 0) {
      post.setId(atomicLong.incrementAndGet());
      posts.put(post.getId(), post);
    } else {
      if (posts.containsKey(post.getId())) {
        posts.replace(post.getId(), post);
      } else {
        throw new NotFoundException("not found");
      }
    }
    return post;
  }

  public void removeById(long id) {
    posts.remove(id);
  }
}