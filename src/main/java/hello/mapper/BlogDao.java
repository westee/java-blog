package hello.mapper;

import hello.entity.Blog;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.List;

@Service
public class BlogDao {
    private final SqlSession sqlSession;

    @Inject
    public BlogDao(SqlSession sqlSession) {
        this.sqlSession = sqlSession;
    }

    public List<Blog> getBlogs(int page, int pageNum, Integer userId){
         HashMap<String, Integer> params = new HashMap<>();
         params.put("pageLimit", pageNum);
         params.put("userId", userId);
         params.put("offset", (page - 1) * pageNum);

         return sqlSession.selectList("selectBlog", params);
     };

    public Integer getBlogsCount(Integer userId) {
        return sqlSession.selectOne("countBlog", userId);
    }
}
