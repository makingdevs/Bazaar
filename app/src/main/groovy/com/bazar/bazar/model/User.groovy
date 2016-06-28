package com.bazar.bazar.model

import groovy.transform.CompileStatic

@CompileStatic
class User {
    String _id
    String email
    Profile profile
    String aboutMe
    List<String> skills
}