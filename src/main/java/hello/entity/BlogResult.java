package hello.entity;

import java.util.List;

public class BlogResult extends Result<List<Blog>> {
    int total;
    int page;
    int totalPage;

    public BlogResult(String msg, String status, List<Blog> data, int page,int total,  int totalPage) {
        super(msg, status, data);
        this.page = page;
        this.total = total;
        this.totalPage = totalPage;
    }

    public static BlogResult newBlogResult(List<Blog> data, int page, int total, int totalPage ){
        return new BlogResult("ok", "获取成功", data, page, total, totalPage);
    }

    public static BlogResult failure(String msg) {
        return new BlogResult(msg, "fail", null, 0, 0, 0);
    }

    public int getTotal() {
        return total;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPage() {
        return totalPage;
    }
}
