package hello.controller;

import hello.entity.BlogResult;
import hello.service.BlogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
public class BlogController {
    BlogService blogService;

    @Inject
    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping("/blog")
    public BlogResult getBlogs(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize,
                               @RequestParam(value = "userId", required = false) Integer userId){
        return blogService.getBlogs(page,pageSize,userId);
    }
}
