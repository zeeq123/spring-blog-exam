package shop.mtcoding.blog.board;

import lombok.Data;

public class BoardRequest {

    @Data
    public static class BoardDTO{
        private String title;
        private String content;
        private String author;
    }
}
