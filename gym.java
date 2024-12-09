
import java.util.ArrayList;
import java.util.List;

public class Gym {

   // variables
   private List<Session> sessions;
   private List<User> users;
   private int indexLastDeleted = -1;

   // constructor
   public Gym() {
      sessions = new ArrayList<>();
      users = new ArrayList<>();
   }

   // methods
   public Session createSession(String name, String trainer, String type, String location, String date, String time,
         double price) {
      Session new_session = new Session(name, type, trainer, location, date, time, price);
      sessions.add(new_session);
      return new_session;
   }

   public void deleteSession(int i){
      sessions.remove(i);
      indexLastDeleted = i;
   }

   public User createUser(String first_name, String last_name, String phone_number, String email) {
      User new_user = new User(first_name, last_name, phone_number, email);
      users.add(new_user);
      return new_user;
   }

   public List<Session> get_sessions() {
      return sessions;
   }

   public Session getLastSession(){
      return sessions.get(sessions.size()-1);
   }

   public int getIndexLastDeleted(){
      return indexLastDeleted;
   }
}
