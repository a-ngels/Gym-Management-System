import java.util.List;
import java.util.ArrayList;

public class Session {

   // variables
   private static int totalSessionsCreated = 0;
   private int id;
   private String name;
   private String type;
   private String trainer;
   private String location;
   private String date;
   private String time;
   private double price;
   private List<String> classList;

   public Session(String name, String type, String trainer, String location, String date, String time, double price) {
      this.id = totalSessionsCreated;
      this.name = name;
      this.type = type;
      this.trainer = trainer;
      this.location = location;
      this.date = date;
      this.time = time;
      this.price = price;
      this.classList = new ArrayList<String>();
      totalSessionsCreated++;
   }

   public void addUser(String userName) {
      classList.add(userName);
   }

   public void removeUser(String userName) {
      classList.remove(userName);
   }

   public double calculateRevenue() {
      return price * classList.size();
   }

   public int getClassSize() {
      return classList.size();
   }

   public String toString() {
      return String.format("ID: %d Name: %s Type: %s Trainer: %s", id, name, type, trainer);
   }

   // getters and setters
   public static int getNumSessions() {
      return totalSessionsCreated;
   }

   public int getId() {
      return id;
   }

   public String getName() {
      return name;
   }

   public String getType() {
      return type;
   }

   public String getTrainer() {
      return trainer;
   }

   public String getLocation() {
      return location;
   }

   public String getDate() {
      return date;
   }

   public String getTime() {
      return time;
   }

   public double getPrice() {
      return price;
   }

   public List<String> getClassList() {
      return classList;
   }

   public void setName(String name) {
      this.name = name;
   }

   public void setType(String type) {
      this.type = type;
   }

   public void setTrainer(String trainer) {
      this.trainer = trainer;
   }

   public void setLocation(String location) {
      this.location = location;
   }

   public void setDate(String date) {
      this.date = date;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public void setPrice(double price) {
      this.price = price;
   }
}
