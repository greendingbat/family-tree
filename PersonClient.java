// Group Project 1
// CS211A Winter 2018
// Group 5
// Client code to use the Person class to read a 
// family tree file and create a family tree

import java.io.*;
import java.util.*;

public class PersonClient {


   public static void main(String[] args) throws FileNotFoundException {      
     
      File f = new File("tudor.dat");
      Scanner scanner = new Scanner(f);
      ArrayList<Person> people = readFile(scanner);
      userInterface(people);
            
   }
   
   public static void userInterface(ArrayList<Person> people) {
      Scanner console = new Scanner(System.in);
      System.out.print("Name (case sensitive): ");
      String queryName = console.nextLine();
      int index = find(people, queryName);
      if (index < 0) {
         System.out.println("THERE IS NO SUCH PERSON");
         userInterface(people);
      } else {
         Person queryPerson = people.get(index);
         System.out.println("Maternal Line:");
         System.out.println("\t" + queryPerson.getName());
         int i = 1;
         while (queryPerson.hasMother()) {
            for (int j=0; j<=i; j++) {
               System.out.print("\t");
            }
            System.out.println(queryPerson.getMother());
            queryPerson = queryPerson.getMother();
            i++;
         }
         
         // Reset our queryPerson (and our indentation loop counter)
         queryPerson = people.get(index);
         System.out.println("Paternal Line:");
         System.out.println("\t" + queryPerson.getName());
         i = 1;
         while (queryPerson.hasFather()) {
            for (int j=0; j<=i; j++) {
               System.out.print("\t");
            }
            System.out.println(queryPerson.getFather());
            queryPerson = queryPerson.getFather();
            i++;
         }
      }
   }
   
   // Reads a formatted family tree file and creates and returns
   // a data structure representing the family relationships
   public static ArrayList<Person> readFile(Scanner s) {
      // Create list of Person objects
      ArrayList<Person> people = new ArrayList<Person>();
      String name;
      // Until the first "END," read in names and create Person objects with those names
      // (The loop is set up this way so that even if the input file is empty, things work)
      // Assumes the list of names contains no duplicates 
      while (s.hasNextLine()) {
         name = s.nextLine();
         if (name.toUpperCase().equals("END")) {
            break;
         } else if (name.length() < 1) {
            continue; // skip empty lines
         }
         Person p = new Person(name);
         // All Person objects are added to our list
         people.add(p);
      }

      String childName;      
      // Once all names are in our list, begin adding family relations      
      while (s.hasNextLine()) {
         // First line is child name
         childName = s.nextLine();
         if (childName.toUpperCase().equals("END")) {
            break;
         } else if (childName.length() < 1) {
            continue; // Skip empty lines
         }
         // index of the Person object with current name
         int childIndex = find(people, childName);
         // Second line is mother name
         String motherName = s.nextLine();
         // Third line is father name
         String fatherName = s.nextLine();
         
         // Find the index in the list of the father and mother Person
         int motherIndex = find(people, motherName);
         int fatherIndex = find(people, fatherName);
         
         // If the mother was not found in the list, create her, add
         // her to the list, and add her as the mother of the current child
         if (motherIndex < 0) {
           Person m = new Person(motherName);
           people.add(m);
           people.get(childIndex).addMother(m);
         } else {
           people.get(childIndex).addMother(people.get(motherIndex));
         }
         
         // Same as above for father
         if (fatherIndex < 0) {
           Person f = new Person(fatherName);
           people.add(f);
           people.get(childIndex).addFather(f);
         } else {
           people.get(childIndex).addFather(people.get(fatherIndex));
         }
      }
      return people;
      
   }
   
   // Helper function to return the index of a Person object with the 
   // given name in the given list. Returns -1 if not found
   public static int find(ArrayList<Person> people, String name) {
      for (int i=0; i<people.size(); i++) {
         if (people.get(i).getName().equals(name)) {
            return i;
         }
      }
      return -1;
   }

}