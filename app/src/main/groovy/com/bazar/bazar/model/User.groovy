package com.bazar.bazar.model

import groovy.transform.CompileStatic

@CompileStatic
class User {
    String _id
    String name
    String hometown
    String picture
    String location
    List<String> skills
    List<String> interests
}