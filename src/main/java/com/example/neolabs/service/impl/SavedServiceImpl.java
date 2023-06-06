package com.example.neolabs.service.impl;

import com.example.neolabs.entity.Saved;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.repository.BookRepository;
import com.example.neolabs.repository.SavedRepository;
import com.example.neolabs.service.SavedService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SavedServiceImpl implements SavedService {

    private final BookRepository bookRepository;

    private final SavedRepository savedRepository;

    @Override
    public void save(User user, Long bookId) {
        savedRepository.save(Saved.builder()
                .book(bookRepository.findById(bookId).orElseThrow(()->
                        new BaseException("Not found", HttpStatus.NOT_FOUND)))
                .user(user)
                .status(Status.ACTIVE)
                .build());
    }

    @Override
    public void deleteSavedById(Long savedId) {
        Saved saved = savedRepository.findById(savedId).orElseThrow(
                ()-> new BaseException("not found", HttpStatus.NOT_FOUND)
        );
        saved.setStatus(Status.DELETED);
        savedRepository.save(saved);
    }
}
