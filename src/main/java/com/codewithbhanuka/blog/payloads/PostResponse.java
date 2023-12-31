package com.codewithbhanuka.blog.payloads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class PostResponse {

    //content of post
    private List<PostDto> content;
    // page number in pagination
    private int pageNumber;

    //page size in one page
    private int pageSize;

    //total element in all the post
    private long totalElements;
    //how many pages in the post in application
    private int totalPages;

    //last page true or false
    private boolean lastPage;

}
