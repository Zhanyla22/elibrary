package com.example.neolabs.service;

import com.example.neolabs.entity.User;

public interface SavedService {

    void save(User user,Long bookId);

    void deleteSavedById(Long savedId);
}
