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
      setName(name);
      setType(type);
      setTrainer(trainer);
      setLocation(location);
      setDate(date);
      setPrice(price);
      setTime(time);
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

   public void setName(String n) {
      this.name = n;
   }

   public void setType(String t) {
      this.type = t;
   }

   public void setTrainer(String tr) {
      this.trainer = tr;
   }

   public void setLocation(String l) {
      this.location = l;
   }

   public void setDate(String d) {
      this.date = d;
   }

   public void setTime(String t) {
      this.time = t;
   }

   public void setPrice(double p) {
      this.price = p;
   }
}
