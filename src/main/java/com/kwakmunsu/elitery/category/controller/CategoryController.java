package com.kwakmunsu.elitery.category.controller;

import com.kwakmunsu.elitery.category.dto.CategoryRequest;
import com.kwakmunsu.elitery.category.entity.Category;
import com.kwakmunsu.elitery.category.service.CategoryService;
import com.kwakmunsu.elitery.global.response.ResponseData;
import com.kwakmunsu.elitery.global.response.success.SuccessCode;
import com.kwakmunsu.elitery.util.JwtUtil;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Category")
@RequestMapping(name = "/category")
@RestController
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    public ResponseEntity<ResponseData> create(@RequestBody CategoryRequest request) {
        categoryService.create(request.category());
        return ResponseData.success(SuccessCode.CREATED_CATEGORY);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseData<Category>> read(
        @PathVariable("categoryId") String categoryId
    ) {
        Category category = categoryService.read(Long.parseLong(categoryId));
        return ResponseData.success(SuccessCode.READ_CATEGORY, category);
    }

    @GetMapping
    public ResponseEntity<ResponseData<List<Category>>> readAll() {
        List<Category> categories = categoryService.readAll();
        return ResponseData.success(SuccessCode.READ_CATEGORY_LIST, categories);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<ResponseData> update(
        @PathVariable("categoryId") String categoryId,
        @RequestBody CategoryRequest request
    ) {
        categoryService.update(Long.parseLong(categoryId), request.category());
        return ResponseData.success(SuccessCode.UPDATE_CATEGORY);
    }

    @DeleteMapping
    public ResponseEntity<ResponseData> delete(@PathVariable("categoryId") String categoryId) {
        categoryService.delete(Long.parseLong(categoryId));
        return ResponseData.success(SuccessCode.DELETE_CATEGORY);
    }

}