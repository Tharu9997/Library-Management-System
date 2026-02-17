package library.factory;

import library.model.*;

public class BookFactory {
    public static Book createBook(String type, String id, String title,
                                 String author, String isbn, 
                                 double price, Object extraParam) {
        switch(type.toLowerCase()) {
            case "textbook":
            case "1":
                if(extraParam instanceof String[]) {
                    String[] params = (String[]) extraParam;
                    String subject = params[0];
                    String level = params[1];
                    return new TextBook(id, title, author, isbn, 
                                      price, subject, level);
                }
                break;
                
            case "fiction":
            case "2":
                if(extraParam instanceof String) {
                    String genre = (String) extraParam;
                    return new FictionBook(id, title, author, isbn, 
                                         price, genre);
                }
                break;
                
            case "reference":
            case "3":
                if(extraParam instanceof String) {
                    String category = (String) extraParam;
                    return new ReferenceBook(id, title, author, isbn, 
                                           price, category);
                }
                break;
        }
        return null;
    }
}
