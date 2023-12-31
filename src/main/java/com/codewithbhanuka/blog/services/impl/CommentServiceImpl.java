package com.codewithbhanuka.blog.services.impl;

import com.codewithbhanuka.blog.entities.Comment;
import com.codewithbhanuka.blog.payloads.CommentDto;
import com.codewithbhanuka.blog.entities.Post;
import com.codewithbhanuka.blog.exception.ResourceNotFoundException;
import com.codewithbhanuka.blog.repositories.CommentRepo;
import com.codewithbhanuka.blog.repositories.PostRepo;
import com.codewithbhanuka.blog.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "post id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        //set post details to comment class
        comment.setPost(post);

        Comment savedComment = this.commentRepo.save(comment);
        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment com = this.commentRepo.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "comment id", commentId));
        this.commentRepo.delete(com);

    }
}
