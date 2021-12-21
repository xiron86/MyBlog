package com.test.testspringboot.controller;

import com.test.testspringboot.model.Post;
import com.test.testspringboot.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Optional;

@Controller
public class BlogController {

    @Autowired
    private PostRepo postRepo;

    @GetMapping("/blog")
    private String blog(Model model) {
        Iterable<Post> posts = postRepo.findAll();
        model.addAttribute("title", "Мои статьи");
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @GetMapping("/blog/tables")
    private String blogTables(Model model) {
        Iterable<Post> posts = postRepo.findAll();
        model.addAttribute("title", "Мои таблицы");
        model.addAttribute("posts", posts);
        return "blog-tables";
    }

    @GetMapping("/blog/add")
    private String blogAdd(Model model) {
        model.addAttribute("title", "Добавить статью");
        return "blog-add";
    }

    @PostMapping("/blog/add")
    private String blogAddPost(@RequestParam("title") String title,
                               @RequestParam("anons") String anons,
                               @RequestParam("fulltext") String fulltext, Model model) {
        Post post = new Post(title, anons, fulltext);
        postRepo.save(post);
        return "redirect:/blog";
    }

    @GetMapping("/blog/{id}")
    private String blogDetails(@PathVariable(value = "id") long id, Model model) {
        Optional<Post> post = postRepo.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("title", "Добавить статью");
        model.addAttribute("post", res);
        return "blog-details";
    }

    @GetMapping("/blog/{id}/edit")
    private String blogEdit(@PathVariable(value = "id") long id, Model model) {
        if (!postRepo.existsById(id)) {
            return "redirect:/blog";
        }

        Optional<Post> post = postRepo.findById(id);
        ArrayList<Post> res = new ArrayList<>();
        post.ifPresent(res::add);
        model.addAttribute("title", "Изменить статью");
        model.addAttribute("post", res);
        return "blog-edit";
    }

    @PostMapping("/blog/{id}/edit")
    private String blogEditPost(@PathVariable(value = "id") long id,
                                @RequestParam("title") String title,
                                @RequestParam("anons") String anons,
                                @RequestParam("fulltext") String fulltext, Model model) {
        Post post = postRepo.findById(id).orElseThrow();
        post.setTitle(title);
        post.setAnons(anons);
        post.setFulltext(fulltext);
        postRepo.save(post);
        return "redirect:/blog";
    }

    @PostMapping("/blog/{id}/delete")
    private String blogDelete(@PathVariable(value = "id") long id, Model model) {
        Post post = postRepo.findById(id).orElseThrow();
        postRepo.delete(post);
        return "redirect:/blog";
    }
}
