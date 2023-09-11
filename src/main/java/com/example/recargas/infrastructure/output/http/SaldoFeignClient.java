package com.example.recargas.infrastructure.output.http;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.recargas.application.recarga.dto.RecargaSaldoDto;

@FeignClient(name = "saldoFeignClient", url = "${client.saldo.baseUrl}")
public interface SaldoFeignClient {
    
    
    @GetMapping("api/listar")
    List<RecargaSaldoDto> getSaldo();

    // @GetMapping("/posts/{postId}")
    // Post getPostById(@PathVariable Long postId);

    // @GetMapping("/posts")
    // List<Post> getPostByUserId(@RequestParam Long userId);

    // @PostMapping("/posts")
    // Post createPost(Post post);

    // @PutMapping("/posts")
    // Post updatePost(Post post);

    // @DeleteMapping("/posts/{postId}")
    // Post deletePost(@PathVariable Long postId);
}