package com.codegym.casetemplate1.service;

import com.codegym.casetemplate1.model.User;

public interface IUserService {
    User checkUsernamePassword(String username, String password);
}
