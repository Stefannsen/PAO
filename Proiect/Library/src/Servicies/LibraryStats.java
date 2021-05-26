package Servicies;

import Models.*;
import Repositories.AuthorRepository;
import Repositories.BookRepository;
import Repositories.BorrowInfoRepository;
import Repositories.SectionRepository;

import java.util.*;

public class LibraryStats {

    public void sortBooksByCurrentNr(){
        ArrayList<Book> books = BookRepository.getInstance().getAllBooks();
        books.stream()
                .sorted(Book::compareTo)
                .forEach(System.out::println);
    }

    public void numberOfBorrowsPerBook(){
        ArrayList<BorrowInfo> bf = BorrowInfoRepository.getInstance().getAll();
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        System.out.println("Numarul total de imprumuturi: " + bf.size());
        for(var b : bf){
            if(hm.containsKey(b.getBookId()))
                hm.put(b.getBookId(), hm.get(b.getBookId()) + 1);
            else
                hm.put(b.getBookId(), 1);
        }
        for(Map.Entry<Integer, Integer> aux : hm.entrySet())
            System.out.println(aux.getKey() + " -> " + aux.getValue());
    }

    public void numberOfBorrowsPerCustomer(){
        ArrayList<BorrowInfo> bf = BorrowInfoRepository.getInstance().getAll();
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        System.out.println("Numarul total de imprumuturi: " + bf.size());
        for(var b : bf){
            if(hm.containsKey(b.getCustomerId()))
                hm.put(b.getCustomerId(), hm.get(b.getBookId()) + 1);
            else
                hm.put(b.getCustomerId(), 1);
        }
        for(Map.Entry<Integer, Integer> aux : hm.entrySet())
            System.out.println(aux.getKey() + " -> " + aux.getValue());
    }

    public void mostRedAuthor(){
        ArrayList<BorrowInfo> bf = BorrowInfoRepository.getInstance().getAll();
        ArrayList<Author> la = AuthorRepository.getInstance().getByAll();
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        System.out.println("Numarul total de imprumuturi: " + bf.size());
        for(var a : la){
            hm.put(a.getId(), 0);
        }
        for(var b : bf){
            int authorId = b.getBook().getAuthorId();
                hm.put(authorId, hm.get(authorId) + 1);
        }
        System.out.println(la.stream()
                .max(Comparator.comparingInt(a -> hm.get(a.getId()))));
    }

    public void mostPopularBookLastMonth(){
        ArrayList<BorrowInfo> bf = BorrowInfoRepository.getInstance().getAll();
        ArrayList<Book> books = BookRepository.getInstance().getAllBooks();
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        Date thisDate = new Date();
        for(var book : books){
            hm.put(book.getId(), 0);
        }
        for(var b : bf){
            if(b.getBorrowDate().getTime() >= thisDate.getTime() - 2.592e+9){
                hm.put(b.getBookId(), hm.get(b.getBookId()) + 1);
            }
        }
        System.out.println(books.stream()
                .max(Comparator.comparingInt(b -> hm.get(b.getId()))));
    }

    public void calculatePassMoney(List<NormalReader> lnr, List<VIPReader> lvip){
        int money = 0;
        for(var x : lnr)
            money += x.calculatePassPrice();
        for(var x : lvip)
            money += x.calculatePassPrice();
        System.out.println("Total: " + money);
    }

    public void top3Sections(){
        ArrayList<BorrowInfo> bf = BorrowInfoRepository.getInstance().getAll();
        ArrayList<Section> sections = SectionRepository.getInstance().getAll();
        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
        Date thisDate = new Date();
        for(var section : sections){
            hm.put(section.getId(), 0);
        }
        for(var b : bf){
            if(b.getBorrowDate().getTime() >= thisDate.getTime() - 2.592e+9){
                hm.put(b.getBook().getSectionId(), hm.get(b.getBook().getSectionId()) + 1);
            }
        }
        sections.stream()
                .sorted((b1,b2)->hm.get(b2.getId())-hm.get(b1.getId()))
                .limit(5)
                .forEach(System.out::println);
    }

    public void unavailableBooks(){
        ArrayList<Book> books = BookRepository.getInstance().getAllBooks();
        boolean ok = false;
        for(Book b : books){
            if(b.checkAvailability() == Availability.Unavailable)
                ok = true;
                System.out.println(b);
        }
        if(!ok){
            System.out.println("Nu exista carti nevalabile in acest moment");
        }
    }

    public void getBorrowInfoByCustomerId(int id){
        ArrayList<BorrowInfo> bf = BorrowInfoRepository.getInstance().getByCustomerId(id);
        for(var x : bf){
            System.out.println(x);
        }
    }


}
