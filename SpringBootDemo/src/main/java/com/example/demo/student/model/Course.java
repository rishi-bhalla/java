package com.example.demo.student.model;

import java.util.List;

public class Course {

    private String id;
    private String name;
    private String description;
    private List<String> steps;

    public Course(String id, String name, String description, List<String> steps) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.steps = steps;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getSteps() {
        return steps;
    }

    public void setSteps(List<String> steps) {
        this.steps = steps;
    }

    @Override
    public String toString() {
        return String.format("Course [id=%s, name=%s, description=%s, steps=%s]", id, name, description, steps);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        return result * prime + ((id == null) ? 0 : id.hashCode());
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || getClass() != o.getClass())
            return false;
        if(this == o)
            return true;

        Course other = (Course) o;
        if(id == null || other.getId() == null)
            return false;

        return id.equals(other.getId());
    }
}
