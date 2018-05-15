// Group Project 1
// CS211A Winter 2018
// Group 5
// A class to store family tree data

// CLASS INVARIANT: children ArrayLists contain only unique elements

import java.util.ArrayList;

public class Person {
   private String name;
   private Person mother;
   private Person father;
   private ArrayList<Person> children;
   
   // Default constructor takes a name
   public Person(String name) {
      this.name = name;
      children = new ArrayList<Person>();
   }
   
   // Can also construct a Person with father and mother Person objects
   public Person(String name, Person father, Person mother) {
      this(name);
      addFather(father);
      addMother(mother);
   }
   
   // Store reference to father Person, 
   // add child to father if not already in children ArrayList
   public void addFather(Person father) {
      this.father = father;
      if (!father.getChildren().contains(this)) {
         father.addChild(this);
      }
   }
   
   // Store reference to mother Person, 
   // add child to mother if not already in children ArrayList
   public void addMother(Person mother) {
      this.mother = mother;
      if (!mother.getChildren().contains(this)) {
         mother.addChild(this);
      }
   }
   
   public void addChild(Person child) {
      this.children.add(child);
   }
   
   public String getName() {
      return this.name;
   }
   
   public Person getFather() {
      return this.father;
   }   
   
   public Person getMother() {
      return this.mother;
   }
   
   public boolean hasMother() {
      return (this.getMother() != null);
   }
   
   public boolean hasFather() {
      return (this.getFather() != null);
   }
   
   public ArrayList<Person> getChildren() {
      return this.children;
   }
   
   public String toString() {
      return this.getName();
   }

}