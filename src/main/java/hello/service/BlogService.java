package hello.service;

import hello.entity.Blog;
import hello.entity.BlogResult;
import hello.mapper.BlogDao;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service
public class BlogService {
    BlogDao blogDao;

    @Inject
    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public BlogResult getBlogs(int page, int pageSize, Integer userId){
        try {
        Integer blogsCount = blogDao.getBlogsCount(userId);
        List<Blog> blogs = blogDao.getBlogs(page, pageSize, userId);
        int totalPage = blogsCount % pageSize == 0 ? blogsCount / pageSize : blogsCount / pageSize + 1;
        return  BlogResult.newBlogResult(blogs, page ,blogsCount, totalPage);
        } catch (Exception e){
            return BlogResult.failure("系统异常");
        }

    }
}
