package hello.service;

import hello.entity.Result;
import hello.mapper.BlogDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@MockitoSettings(strictness = Strictness.LENIENT)
@ExtendWith(MockitoExtension.class)
public class BlogServiceTest {

    @Mock
    BlogDao blogDao;

    @InjectMocks
    BlogService blogService;

    @Test
    void testGetBlogs(){
        blogService.getBlogs(1,1, null);

        Mockito.verify(blogDao).getBlogs(1,1,null);
    }

    @Test
    void throwExceptionWhenFail(){
        Mockito.when(blogDao.getBlogs(Mockito.anyInt(),Mockito.anyInt(),Mockito.anyInt())).thenThrow(new RuntimeException());

        Result result = blogService.getBlogs(1, 10, null);
        Assertions.assertEquals("系统异常", result.getMsg());
        Assertions.assertEquals("fail", result.getStatus());

    }
}
