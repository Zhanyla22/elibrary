package com.example.neolabs.service.impl;

import com.example.neolabs.dto.response.HardCoverBooksResponse;
import com.example.neolabs.repository.SpecBookRepository;
import com.example.neolabs.service.SpecBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpecBookImpl implements SpecBookService {

    private final SpecBookRepository specBookRepository;

    @Override
    public HardCoverBooksResponse quantityOfPfysBooks() {
        return HardCoverBooksResponse.builder()
                .count(specBookRepository.countAll())
                .build();
    }
}
