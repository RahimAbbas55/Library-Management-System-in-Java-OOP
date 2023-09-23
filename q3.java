//Modules for file reading and error handling.
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
//Module for user input.
import java.util.Scanner;
//To store books in library
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

interface configuration
{
    void display();
    int calculateCost();
}
//Super Class(Parent Class)
class item implements configuration
{
    public String name;
    public boolean available;
    public int popCount;
    public int cost;
    public item()
    {
        name = "\0";
        popCount = 0;
        available = true;
        cost = 0;
    }
    public item( String n , int count , int c)
    {
        name = n;
        popCount = count;
        available = true;
        cost = c;
    }
    public void display()
    {
        System.out.println("Item Name: " + name);
    }
    public int calculateCost()
    {
        System.out.println("Method for calculating price of items");
        return cost;
    }
    public int getPopularityCount()
    {
        return popCount;
    }
}
//Child Classes
class book extends item
{
    public int publication;
    public String author;
    int i;
    public book ()
    {
        super();
        publication = 0;
        author = "\0";
        i = 0;
    }
    public book ( String n , String a , int p , int id , int c , int cost)
    {
        super(n , c , cost);
        author = a;
        publication = p;
        i = id;
    }
    @Override
    public void display()
    {
        super.display();
        System.out.println("Author: " + author);
        System.out.println("Publication Year: " + publication);
    }
    @Override
    public int calculateCost() 
    {
        int totalPrice = 0;
        int price = super.calculateCost();
        int gst = 200;
        totalPrice = price + (int)(0.20f * price) + gst;

        return totalPrice;
    }
}
class newspaper extends item
{
    public String Pub_com;
    public String date;
    int i;
    public newspaper()
    {
        super();
        Pub_com = "\0";
        date = "\0";
        i = 0;
    }
    public newspaper(String n ,String publisher , String D , int id , int c , int cost)
    {
        super(n , c , cost);
        Pub_com = publisher;
        date = D;
        i = id;
    }
    @Override
    public void display()
    {
        super.display();
        System.out.println("Publishing Company: " + Pub_com);
        System.out.println("Date " + date);
    }
    @Override
    public int calculateCost()
    {
        int totalPrice = 10 + 5;
        return totalPrice;
    }
}
class magazine extends item
{
    public String auth;
    public String company;
    int i;
    public magazine()
    {
        super();
        auth = "\0";
        company = "\0";
        i = 0;
    }
    public magazine( String name , String a , String com , int id , int c , int cost)
    {
        super(name , c , cost);
        auth = a;
        company = com;
        i = id;
    }
    @Override
    public void display()
    {
        super.display();
        System.out.println("Author: " + auth);
        System.out.println("Company: " + company);
    }
    @Override
    public int calculateCost()
    {
        int totalPrice = 0;
        int price = super.calculateCost();
        int popC = super.popCount;
        totalPrice = price * popC;
        return totalPrice;
    }
}
class borrower
{
    private String name;
    public borrower ()
    {
        name = "";
    }
    public borrower (String n)
    {
        name = n;
    }
}
//Main library class
class Flibrary
{
    public static int bookId = 1;
    public static int MagId = 1;
    public static int NewsId = 1;

    private ArrayList<book> books;
    private ArrayList<magazine> magazines;
    private ArrayList<newspaper> newspaper;
    private ArrayList<String> users;
    private ArrayList<String> bookBorrowed;

    public Flibrary()
    {
        books = new ArrayList<>();
        magazines = new ArrayList<>();
        newspaper = new ArrayList<>();
        users = new ArrayList<>();
        bookBorrowed = new ArrayList<>();
    }
    //done
    void loadFromFile()
    {
        int itemType = 0;
        int c =0;
        int cost = 0;
        String name = "\0";
        String author = "\0";
        String date = "\0";
        try ( BufferedReader br= new BufferedReader(new FileReader("data.txt")))
        {
            String line;
            while ( (line = br.readLine()) != null )
            {
                String [] p = line.split(",");
                if ( p.length == 6)
                {
                    itemType = Integer.parseInt(p[0].trim());
                    //if item type is 1, take book input.
                    if ( itemType == 1)
                    {
                        int issue = 0;
                        name = p[1].trim();
                        author = p[2].trim();
                        issue = Integer.parseInt(p[3].trim());
                        c = Integer.parseInt(p[4].trim());
                        cost = Integer.parseInt(p[5].trim());
                        System.out.println("Book Name: " + name);
                        System.out.println("Book Author: " + author);
                        System.out.println("Book Publication: " + issue);
                        System.out.println("Book Cost: " + cost);
                        System.out.println("Book Popularity Count: " + c);
                        //books.add( new book(name, author, issue , bookId , c , cost));
                        bookId += 1;
                        System.out.println("-----------------------------------");
                    }
                    //if item type is 2, take magazine input.
                    else if ( itemType == 2)
                    {
                        String publisher = "\0";
                        name = p[1].trim();
                        author = p[2].trim();
                        publisher = p[3].trim();
                        c = Integer.parseInt(p[4].trim());
                        cost = Integer.parseInt(p[5].trim());
                        System.out.println("Magazine Name: " + name);
                        System.out.println("Magazine Author: " + author);
                        System.out.println("Magazine Publisher: " + publisher);
                        System.out.println("Magazine Cost: " + cost);
                        System.out.println("Magazine Popularity Count: " + c);
                        //magazines.add( new magazine(name, author, publisher , MagId , c , cost));
                        MagId += 1;
                        System.out.println("-----------------------------------");
                    }
                    //if item type is 3, take newspaper input.
                    else if ( itemType == 3)
                    {
                        String com = "\0";
                        name = p[1].trim();
                        com = p[2].trim();
                        date = p[3].trim();
                        c = Integer.parseInt(p[4].trim());
                        cost = Integer.parseInt(p[5].trim());
                        System.out.println("Newspaper Name: " + name);
                        System.out.println("Newspaper Company: " + com);
                        System.out.println("Newspaper Date: " + date);
                        System.out.println("Newspaper Cost: " + cost);
                        System.out.println("Newspaper Popularity Count: " + c);
                        //newspaper.add( new newspaper(name , com , date , NewsId , c , cost));
                        NewsId += 1;
                        System.out.println("-----------------------------------");
                    }
                    else
                    {
                        System.out.println("Invalid Item Type");
                    }
                }
                else
                {
                    System.out.println("Invalid file format!");
                }
            }
        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
    }
    //done
    void addItem() 
    {
        // COMMON VARIABLE
        String name = "\0";
        int pop = 0;
        int cost = 0;
        // for book
        int year = 0;
        String author = "\0";
        // for magazine
        String comp = "\0";
        // for newspaper
        String publisher = "\0";
        String date = "\0";

        Scanner in = new Scanner(System.in);
        String itemType = "\0";
        System.out.println("Enter what type of item you want to add?");
        itemType = in.nextLine().toLowerCase();

        if (itemType.equals("books")) 
        {
            System.out.print("Enter name of the book: ");
            name = in.nextLine();
            System.out.println();

            System.out.print("Enter Name of the author: ");
            author = in.nextLine();
            System.out.println();

            System.out.print("Enter release year: ");
            year = in.nextInt();
            in.nextLine(); 
            System.out.println();
            System.out.println("Enter Popularity: ");
            pop = in.nextInt();
            System.out.println();
            System.out.println("Enter cost of the book: ");
            cost = in.nextInt();
            System.out.println();
            books.add(new book(name, author, year , bookId , pop , cost));
            bookId += 1;
            System.out.println("Book Added Successfully");
        } 
        else if (itemType.equals("magazine")) 
        {
            System.out.print("Enter name of the magazine: ");
            name = in.nextLine();
            System.out.println();

            System.out.print("Enter name of the author: ");
            author = in.nextLine();
            System.out.println();

            System.out.print("Enter name of the company: ");
            comp = in.nextLine();
            System.out.println();

            System.out.println("Enter Popularity: ");
            pop = in.nextInt();
            System.out.println();

            System.out.println("Enter cost of the magazine: ");
            cost = in.nextInt();
            System.out.println();

            magazines.add(new magazine(name, author, comp , MagId , pop , cost));
            MagId += 1;
            System.out.println("Magazine Added Successfully");
        } 
        else if (itemType.equals("newspaper")) 
        {
            System.out.print("Enter name of the newspaper: ");
            name = in.nextLine();
            System.out.println();

            System.out.print("Enter name of the publisher: ");
            publisher = in.nextLine();
            System.out.println();

            System.out.print("Enter date at which magazine was released: ");
            date = in.nextLine();
            System.out.println();

            System.out.println("Enter Popularity: ");
            pop = in.nextInt();
            System.out.println();

            System.out.println("Enter cost of the newspaper: ");
            cost = in.nextInt();
            System.out.println();
            
            newspaper.add(new newspaper(name, publisher, date , NewsId , pop , cost));
            NewsId += 1;
            System.out.println("Newspaper Added Successfully");
        } 
        else 
        {
            System.out.println("Invalid choice!");
        }
    }
    //done
    void edit_items()
    {
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        String itemType = "\0";
        int id = 0 , popC = 0 , cost = 0;
        System.out.println("Enter what type of item you want to edit?");
        itemType = in.nextLine().toLowerCase();

        if (itemType.equals("books")) 
        {
            System.out.println("Enter ID of the book that you want edit:");
            id = in.nextInt();
            for ( int i = 0 ; i < books.size() ; i++)
            {
                book b = books.get(i);
                if (b.i == id) {
                    String eName = editTitle();
                    String eAuth = editAuth();
                    int eYear = editYear();
                    popC = editPopCount();
                    cost = editCost();
                    b.author = eAuth;
                    b.name = eName; 
                    b.publication = eYear;
                    b.popCount = popC;
                    b.cost = cost;
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Book with ID " + id + " has been edited.");
            }
            else
            {
                System.out.println("Book with ID " + id + " not found."); 
            }
        } 
        else if (itemType.equals("magazine")) 
        {
            System.out.println("Enter ID of the magazine that you want edit:");
            id = in.nextInt();
            for ( int i = 0 ; i < magazines.size() ; i++)
            {
                magazine m = magazines.get(i);
                if (m.i == id) {
                    String eName = editTitle();
                    String eAuth = editAuth();
                    String comp = editCompany();
                    popC = editPopCount();
                    cost = editCost();
                    m.auth = eAuth;
                    m.name = eName; 
                    m.company = comp;
                    m.popCount = popC;
                    m.cost = cost;
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Magazine with ID " + id + " has been edited.");
            }
            else
            {
                System.out.println("Magazine with ID " + id + " not found."); 
            }
        } 
        else if (itemType.equals("newspaper")) 
        {
            System.out.println("Enter ID of the magazine that you want edit:");
            id = in.nextInt();
            for ( int i = 0 ; i <   newspaper.size() ; i++)
            {
                newspaper n = newspaper.get(i);
                if (n.i == id) {
                    String eName = editTitle();
                    String date = editDate();
                    String comp = editCompany();
                    popC = editPopCount();
                    cost = editCost();
                    n.Pub_com = comp;
                    n.date = date ;
                    n.name = eName;
                    n.popCount = popC;
                    n.cost = cost;
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Magazine with ID " + id + " has been edited.");
            }
            else
            {
                System.out.println("Magazine with ID " + id + " not found."); 
            }
        } 
        else 
        {
            System.out.println("Invalid choice!");
        }
        
    }
    //done
    void displayAll()
    {
        System.out.println("------------------------------------");
        System.out.println("               BOOKS                ");
        System.out.println("------------------------------------");
        if ( books.isEmpty())
        {
            System.out.println("Shelf is empty!");
        }
        else
        {
            System.out.println("Books in Library Shelf.");
            for ( book obj : books)
            {
                System.out.println("-------------------------------");
                System.out.println("Book id: " + obj.i);
                System.out.println("Book Name: " + obj.name);
                System.out.println("Book Author: " + obj.author);
                System.out.println("Publication Year: " + obj.publication);
                System.out.println("Popularity: " + obj.popCount);
                System.out.println("Cost: " + obj.cost);
                System.out.println("-------------------------------");
                obj.calculateCost();
            }
        }
        System.out.println("------------------------------------");
        System.out.println("               MAGAZINE             ");
        System.out.println("------------------------------------");
        if ( magazines.isEmpty())
        {
            System.out.println("Shelf is empty!");
        }
        else
        {
            System.out.println("Magazines in Library Shelf.");
            for ( magazine obj : magazines)
            {
                System.out.println("-------------------------------");
                System.out.println("Magazine id: " + obj.i);
                System.out.println("Magazine Name: " + obj.name);
                System.out.println("Magazine Author: " + obj.auth);
                System.out.println("Company: " + obj.company);
                System.out.println("Popularity: " + obj.popCount);
                System.out.println("Cost: " + obj.cost);
                System.out.println("-------------------------------");
            }
        }

        System.out.println("------------------------------------");
        System.out.println("               NEWSPAPERSS          ");
        System.out.println("------------------------------------");
        if ( newspaper.isEmpty())
        {
            System.out.println("Shelf is empty!");
        }
        else
        {
            System.out.println("Newspaper in Library Shelf.");
            for ( newspaper obj : newspaper)
            {
                System.out.println("-------------------------------");
                System.out.println("Newspaper id " + obj.i);
                System.out.println("Newspaper Name: " + obj.name);
                System.out.println("Company: " + obj.Pub_com);
                System.out.println("Release Date: " + obj.date);
                System.out.println("Popularity: " + obj.popCount);
                System.out.println("Cost: " + obj.cost);
                System.out.println("-------------------------------");
            }
        }
    }
    //done 
    void deleteItems()
    {
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        String itemType = "\0";
        int id = 0;
        System.out.print("Enter what type of item you want to delete?");
        itemType = in.nextLine().toLowerCase();

        if (itemType.equals("books")) 
        {
            System.out.println("Enter ID of the book that you want to delete:");
            id = in.nextInt();
            for ( int i = 0 ; i < books.size() ; i++)
            {
                book b = books.get(i);
                if (b.i == id) {
                    books.remove(i);
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Book with ID " + id + " has been deleted.");
            }
            else
            {
                System.out.println("Book with ID " + id + " not found."); 
            }
        } 
        else if (itemType.equals("magazine")) 
        {
            System.out.println("Enter ID of the magazine that you want delete:");
            id = in.nextInt();
            for ( int i = 0 ; i < magazines.size() ; i++)
            {
                magazine m = magazines.get(i);
                if (m.i == id) {
                    magazines.remove(i);
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Magazine with ID " + id + " has been deleted.");
            }
            else
            {
                System.out.println("Magazine with ID " + id + " not found."); 
            }
        } 
        else if (itemType.equals("newspaper")) 
        {
            System.out.println("Enter ID of the magazine that you want edit:");
            id = in.nextInt();
            for ( int i = 0 ; i <   newspaper.size() ; i++)
            {
                newspaper n = newspaper.get(i);
                if (n.i == id) {
                    newspaper.remove(i);
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Magazine with ID " + id + " has been edited.");
            }
            else
            {
                System.out.println("Magazine with ID " + id + " not found."); 
            }
        } 
        else 
        {
            System.out.println("Invalid choice!");
        }
    }
    //done
    void displayById()
    {
        boolean flag = false;
        Scanner in = new Scanner(System.in);
        String itemType = "\0";
        int id = 0;
        System.out.println("Enter what type of item you want to display?");
        itemType = in.nextLine().toLowerCase();

        if (itemType.equals("books")) 
        {
            System.out.println("Enter ID of the book that you want to display:");
            id = in.nextInt();
            for ( int i = 0 ; i < books.size() ; i++)
            {
                book b = books.get(i);
                if (b.i == id) 
                {
                    System.out.println("-------------------------------");
                    System.out.println("Book id: " + b.i);
                    System.out.println("Book Name: " + b.name);
                    System.out.println("Book Author: " + b.author);
                    System.out.println("Publication Year: " + b.publication);
                    System.out.println("Cost: " + b.cost);
                    System.out.println("-------------------------------");
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Book with ID " + id + " has been displayed.");
            }
            else
            {
                System.out.println("Book with ID " + id + " not found."); 
            }
        } 
        else if (itemType.equals("magazine")) 
        {
            System.out.println("Enter ID of the magazine that you want display:");
            id = in.nextInt();
            for ( int i = 0 ; i < magazines.size() ; i++)
            {
                magazine m = magazines.get(i);
                if (m.i == id) 
                {
                    System.out.println("-------------------------------");
                    System.out.println("Magazine id: " + m.i);
                    System.out.println("Magazine Name: " + m.name);
                    System.out.println("Magazine Author: " + m.auth);
                    System.out.println("Company: " + m.company);
                    System.out.println("Cost: " + m.cost);
                    System.out.println("-------------------------------");
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Magazine with ID " + id + " has been displayed.");
            }
            else
            {
                System.out.println("Magazine with ID " + id + " not found."); 
            }
        } 
        else if (itemType.equals("newspaper")) 
        {
            System.out.println("Enter ID of the magazine that you want displayed:");
            id = in.nextInt();
            for ( int i = 0 ; i <   newspaper.size() ; i++)
            {
                newspaper n = newspaper.get(i);
                if (n.i == id) 
                {
                    System.out.println("-------------------------------");
                    System.out.println("Newspaper id " + n.i);
                    System.out.println("Newspaper Name: " + n.name);
                    System.out.println("Company: " + n.Pub_com);
                    System.out.println("Release Date: " + n.date);
                    System.out.println("Cost: " + n.cost);
                    System.out.println("-------------------------------");
                    flag = true;
                    break;
                }
            }
            if ( flag == true )
            {
                System.out.println("Magazine with ID " + id + " has been displayed.");
            }
            else
            {
                System.out.println("Magazine with ID " + id + " not found."); 
            }
        } 
        else 
        {
            System.out.println("Invalid choice!");
        }
    }
    //done
    void displayByType ( String ItemType )
    {
        if (ItemType.equals("books"))
        {
            display_Books();
        }
        else if (ItemType.equals("magazine"))
        {
            display_Magazines();
        }
        else if (ItemType.equals("newspaper"))
        {
            display_Newspaper();
        }
        else
        {
            System.out.println("Invalid choice");
        }
    }
    //done
    void BorrowItems()
    {
        int borrowCost = 0;
        String itemName = "\0";
        String UserName = "\0";
        Scanner in = new Scanner(System.in);
        String itemType = "\0";
        System.out.println("Enter your user name: ");
        UserName = in.nextLine();
        System.out.println("Enter what type of item you want to borrow?");
        itemType = in.nextLine().toLowerCase();

        if (itemType.equals("books"))
        {
            boolean checkB = false;
            System.out.println("Enter the book that you want to borrow: ");
            itemName = in.nextLine();
            for ( int i = 0 ; i < books.size() ; i++ )
            {
                book b = books.get(i);
                if (b.name.equals(itemName) )
                {
                    if ( b.available == true )
                    {
                        checkB = true;
                        b.available = false;
                        users.add(UserName);
                        bookBorrowed.add(itemName);
                        borrowCost = b.calculateCost();
                        break;
                    }
                    else
                    {
                        System.out.println("Book is not available.Sorry!");
                    }
                }
            }
            if ( checkB )
            {
                System.out.println("Book: " + itemName + " borrowed by " + UserName);
            }
            else
            {
                System.out.println("Book: " + itemName + " not found in library.");
            }
        }
        else if (itemType.equals("magazine"))
        {
            boolean checkM = false;
            System.out.println("Enter the magazine that you want to borrow: ");
            itemName = in.nextLine();
            for ( int i = 0 ; i < magazines.size() ; i++ )
            {
                magazine m = magazines.get(i);
                if (m.name.equals(itemName) )
                {
                    if ( m.available == true )
                    {
                        checkM = true;
                        m.available = false;
                        users.add(UserName);
                        bookBorrowed.add(itemName);
                        borrowCost = m.calculateCost();
                        break;
                    }
                    else
                    {
                        System.out.println("Magazine is not available.Sorry!");
                    }
                }
            }
            if ( checkM )
            {
                System.out.println("Magazine: " + itemName + " borrowed by " + UserName);
            }
            else
            {
                System.out.println("Magazine: " + itemName + " not found in library.");
            }
        }
        else if (itemType.equals("newspaper"))
        {
            boolean checkN = false;
            System.out.println("Enter the newspaper that you want to borrow: ");
            itemName = in.nextLine();
            for ( int i = 0 ; i < newspaper.size() ; i++ )
            {
                newspaper n = newspaper.get(i);
                if (n.name.equals(itemName) )
                {
                    if ( n.available == true )
                    {
                        checkN = true;
                        n.available = false;
                        users.add(UserName);
                        bookBorrowed.add(itemName);
                        borrowCost = n.calculateCost();
                        break;
                    }
                    else
                    {
                        System.out.println("Newspaper is not available.Sorry!");
                    }
                }
            }
            if ( checkN )
            {
                System.out.println("Newspaper: " + itemName + " borrowed by " + UserName);
            }
            else
            {
                System.out.println("Newspaper: " + itemName + " not found in library.");
            }
        }
        else
        {
            System.out.println("Invalid choice");
        }
        System.out.println("COST OF YOUR BORROW IS: " + borrowCost);
    }
    //FOR THIS FUNCTION, POPULARITY IS IN ASCENDING ORDER. (I.E 1 BEING THE MOST POPULAR, 2 BEING LESS POPULAR THAN 1 AND SOO ON.)
    //done
    void HotPicks()     
    {
        for ( int  i = 0 ; i < books.size() ; i++)
        {
            Collections.sort(books, Comparator.comparingInt(book::getPopularityCount));
        }

        for ( int  i = 0 ; i < magazines.size() ; i++)
        {
            Collections.sort(magazines, Comparator.comparingInt(magazine::getPopularityCount));
        }

        for ( int  i = 0 ; i < newspaper.size() ; i++)
        {
            // Collections.sort(newspaper, Comparator.comparingInt(newspaper::getPopularityCount));
        }

        System.out.println("Items based on their popularity.");
        displayAll();
    }
    //done
    void ShowBorrow()
    {
        System.out.println("USER - ITEM");
        for ( int i = 0 ; i < bookBorrowed.size() ; i++)
        {
            String u = users.get(i);
            String b = bookBorrowed.get(i);
            System.out.println(u + " - " + b);
        }
        
    }

    //HELPER FUNCTIONS
    //done
    private void display_Books()
    {
        if ( books.isEmpty())
        {
            System.out.println("Shelf is empty!");
        }
        else
        {
            System.out.println("Books in Library Shelf.");
            for ( book obj : books)
            {
                System.out.println("-------------------------------");
                System.out.println("Book id: " + obj.i);
                System.out.println("Book Name: " + obj.name);
                System.out.println("Book Author: " + obj.author);
                System.out.println("Publication Year: " + obj.publication);
                System.out.println("Popularity: " + obj.popCount);
                System.out.println("-------------------------------");
            }
        }
    }
    //done
    private void display_Magazines()
    {
        if ( magazines.isEmpty())
        {
            System.out.println("Shelf is empty!");
        }
        else
        {
            System.out.println("Magazines in Library Shelf.");
            for ( magazine obj : magazines)
            {
                System.out.println("-------------------------------");
                System.out.println("Magazine id: " + obj.i);
                System.out.println("Magazine Name: " + obj.name);
                System.out.println("Magazine Author: " + obj.auth);
                System.out.println("Company: " + obj.company);
                System.out.println("Popularity: " + obj.popCount);
                System.out.println("-------------------------------");
            }
        }
    }
    //done
    private void display_Newspaper()
    {
        if ( newspaper.isEmpty())
        {
            System.out.println("Shelf is empty!");
        }
        else
        {
            System.out.println("Newspaper in Library Shelf.");
            for ( newspaper obj : newspaper)
            {
                System.out.println("-------------------------------");
                System.out.println("Newspaper id " + obj.i);
                System.out.println("Newspaper Name: " + obj.name);
                System.out.println("Company: " + obj.Pub_com);
                System.out.println("Release Date: " + obj.date);
                System.out.println("Popularity: " + obj.popCount);
                System.out.println("-------------------------------");
            }
        }
    }
    //done
    private int editPopCount()
    {
        int count;
        Scanner o1 = new Scanner(System.in);
        System.out.print("\nEnter edited popularity count: ");
        count = o1.nextInt();
        return  count;
    }
    //FOR EDITING BOOKS
    //done
    private String editTitle()
    {
        String tname;
        Scanner o1 = new Scanner(System.in);
        System.out.print("\nEnter edited title: ");
        tname = o1.nextLine();
        return tname;
    }
    //done
    private String editAuth()
    {
        String Aname;
        Scanner o1 = new Scanner(System.in);
        System.out.print("\nEnter edited author: ");
        Aname = o1.nextLine();
        return Aname;
    }
    //done
    private int editYear()
    {
        int year;
        Scanner o1 = new Scanner(System.in);
        System.out.print("\nEnter edited year: ");
        year = o1.nextInt();
        return  year;
    }
    //FOR MAGAZINE
    //done
    private String editCompany()
    {
        String Cname;
        Scanner o1 = new Scanner(System.in);
        System.out.print("\nEnter edited company name: ");
        Cname = o1.nextLine();
        return Cname;
    }
    //FOR NEWSPAPER
    //done
    private String editDate()
    {
        String editDate;
        Scanner o1 = new Scanner(System.in);
        System.out.print("\nEnter edited date: ");
        editDate = o1.nextLine();
        return editDate;
    }
    private int editCost()
    {
        int cost;
        Scanner o1 = new Scanner(System.in);
        System.out.print("\nEnter edited cost: ");
        cost = o1.nextInt();
        return  cost;
    }
}

public class q3
{
    public static void main(String[] args)
    {
        Flibrary shelf = new Flibrary();
        int choice = 0;
        Scanner obj1 = new Scanner(System.in);
        do
        {
            System.out.println(" ------------------------------------");
            System.out.println("|   Library Managemen System Menu    |");
            System.out.println(" ------------------------------------");
            System.out.println("(----------Books Loaded from the file.----------)");
            System.out.println("1) Hot Pics.");
            System.out.println("2) Borrow Items.");
            System.out.println("3) Add items.");
            System.out.println("4) Edit items.");
            System.out.println("5) Delete Items.");
            System.out.println("6) View all items.");
            System.out.println("7) View items by ID.");
            System.out.println("8) View Borrow List.");
            System.out.println("9) Load From File.");
            System.out.println("10) Exit.");
            System.out.println("Enter your choice: ");
            choice = obj1.nextInt();
            if ( choice == 1 )
            {
                shelf.HotPicks();
                System.out.print("\n\n");
            }
            else if ( choice == 2 )
            {
                shelf.BorrowItems();
                System.out.print("\n\n");
            }
            else if ( choice == 3 )
            {
                shelf.addItem();
                System.out.print("\n\n");
            }
            else if ( choice == 4 )
            {
                shelf.edit_items();
                System.out.print("\n\n");
            }
            else if ( choice == 5)
            {
                shelf.deleteItems();
                System.out.print("\n\n");
            }
            else if ( choice == 6 )
            {
                shelf.displayAll();
                System.out.print("\n\n");
            }
            else if ( choice == 7 )
            {
                shelf.displayById();
                System.out.print("\n\n");
            }
            else if ( choice == 8 )
            {
                shelf.ShowBorrow();
                System.out.print("\n\n");
            }
            else if ( choice == 9 )
            {
                shelf.loadFromFile();
                System.out.print("\n\n");
            }
            else if ( choice == 10 )
            {
                System.out.println("Library Management System Exited.\nG.O.O.D B.Y.E.");
                System.out.print("\n\n");
            }
            else 
            {
                System.out.println("Wrong Input\nEnter Again!!!");
                System.out.print("\n\n");
            }
        }while ( choice != 10 );
        obj1.close();
    }
}