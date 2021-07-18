// MyAIdl.aidl
package com.yuyi.binderservice;

// Declare any non-default types here with import statements
import com.yuyi.binderservice.Person;
interface MyAIdl {
    void addPerson(in Person person);
    List<Person> getPersonList();
}