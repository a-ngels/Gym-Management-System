import java.util.List;

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
      totalSessionsCreated++;
   }

   // getters and setters
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

   public String toString(){
      return String.format("ID: %d Name: %s Type: %s Trainer: %s", id, name, type, trainer);
   }

   //getters
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
}
