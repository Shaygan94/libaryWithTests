package com.shaygan.libarywithtests.Location;

import com.shaygan.libarywithtests.Book.Book;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "location_seq")
    @SequenceGenerator(name = "location_seq", sequenceName = "location_seq", allocationSize = 1)
    private Long id;

    private String address;
    private String room;
    private String shelf;
    @OneToMany()
    @JoinColumn(name = "book_id")
    private List<Book> books;

    public Location() {
    }

    public Location(String address, String room, String shelf) {
        this.address = address;
        this.room = room;
        this.shelf = shelf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getShelf() {
        return shelf;
    }

    public void setShelf(String shelf) {
        this.shelf = shelf;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
