import java.util.List;

public class session {
   
   // variables
   private int id;
   private String name;
   private String type;
   private String trainer;
   private String location;
   private String date;
   private String time;
   private double price;
   private List<String> classList;

   public session(int id, String name, String type, String trainer, String location, String date, String time, double price) {
      this.id = id;
      this.name = name;
      this.type = type;
      this.trainer = trainer;
      this.location = location;
      this.date = date;
      this.time = time;
      this.price = price;
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
}
