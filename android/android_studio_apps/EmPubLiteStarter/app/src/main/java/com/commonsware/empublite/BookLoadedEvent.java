package com.commonsware.empublite;

/**
 * Created by jose on 8/9/14.
 */
public class BookLoadedEvent {

    private BookContents contents = null;

    public BookLoadedEvent(BookContents contents) {
        this.contents = contents;
    }

    public BookContents getBook() {
        return (contents);
    }
}
