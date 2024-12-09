
import java.util.ArrayList;
import java.util.List;

public class gym {

   // variables
   final private List<session> Sessions;

   // constructor
   public gym() {
      Sessions = new ArrayList<>();
   }

   // methods
   public void createSession(int id, String name, String trainer, String type, String location, String date, String time, double price) {
      session new_session = new session(id, name, type, trainer, location, date, time, price);
      Sessions.add(new_session);
   }

   public List<session> get_sessions() {
      return Sessions;
   }
}
