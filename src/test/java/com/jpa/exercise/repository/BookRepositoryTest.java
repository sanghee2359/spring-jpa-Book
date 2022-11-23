package com.jpa.exercise.repository;

import com.jpa.exercise.domain.Author;
import com.jpa.exercise.domain.Book;
import com.jpa.exercise.domain.Publisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class BookRepositoryTest {
    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Test
    @DisplayName("author,book,publisher 테이블 간 단방향 매핑 테스트 " +
            "- book에서 author의 id를 외래키로 참조해 authorName을 가져올 수 있는지" +
            "- book에서 publisher의 id를 외래키로 참조해 publisherName을 가져올 수 있는지")
    void relationshipTest() {
        // 테스트 데이터 생성
        Author author = new Author(2L, "김경록");

        Publisher publisher = new Publisher(2L, "에이콘","서울시 종로구");

        Book book = new Book(1L, "토비의 스프링3",author,publisher);

        authorRepository.save(author);
        bookRepository.save(book);

        System.out.println(
                "Author name: "+bookRepository.findById(1L).orElseThrow(RuntimeException::new).getAuthor().getName()+"\n"+
                "Publisher name: "+bookRepository.findById(1L).orElseThrow(RuntimeException::new).getPublisher().getName()
        );
    }
}