
import java.util.ArrayList;
import java.util.List;

public class Gym {

   // variables
   private List<Session> sessions;
   private List<User> users;
   private int indexLastDeletedSessions = -1;
   private int indexLastDeletedUsers = -1;

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
      indexLastDeletedSessions = i;
   }

   public User createUser(String first_name, String last_name, String phone_number, String email) {
      User new_user = new User(first_name, last_name, phone_number, email);
      users.add(new_user);
      return new_user;
   }

   public void deleteUser(int i) {
      users.remove(i);
      indexLastDeletedUsers = i;
   }

   public List<Session> get_sessions() {
      return sessions;
   }

   public List<User> get_users() {
      return users;
   }

   public Session getLastSession(){
      return sessions.get(sessions.size()-1);
   }

   public int getIndexLastDeletedSessions(){
      return indexLastDeletedSessions;
   }

   public int getIndexLastDeletedUsers() {
      return indexLastDeletedUsers;
   }
}
