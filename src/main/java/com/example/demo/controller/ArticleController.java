package com.example.demo.controller;

import com.example.demo.dto.ArticleRequest;
import com.example.demo.dto.ArticleResponse;
import com.example.demo.service.ArticleService;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class ArticleController {
    private final MemberService memberService;
    private final ArticleService articleService;

    @PostMapping("/{id}/articles")
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleResponse postArticle(@PathVariable Long id, @RequestBody ArticleRequest articleRequest) {
        return articleService.create(id, articleRequest);
    }

    @GetMapping
    public List<ArticleResponse> getByMember(@RequestParam(name = "memberId", required = false) Long memberId){
        if(memberId == null){
            return articleService.findAll();
        }
        else {
            return articleService.findByMemberId(memberId);
        }
    }
}
