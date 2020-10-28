package hello.service;

import hello.entity.Blog;
import hello.mapper.BlogDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Inject
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<Blog> getBlogs(int page, int pageSize, Integer id){
        return blogDao.getBlogs(page, pageSize, id);
    }
}
