import java.util.ArrayList;

public class User {

    // fields
    private static int totalUsers = 0;
    private static int idGenerator = 0;
    private String first_name, last_name, phone_number, email;

    private int id = 0;

    private ArrayList<Session> class_list;

    public User(String first_name, String last_name, String phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        totalUsers++;
        idGenerator++;
        this.id = idGenerator;
        this.class_list = new ArrayList<>();
    }

    public ArrayList<Session> getClassList() {
        return class_list;
    }

    public void addToList(Session s) {
        class_list.add(s);
    }

    public static int getNumUsers() {
        return totalUsers;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

    public boolean alreadyScheduled(Session session) {
        for (Session s : class_list) {
            if (s.getId() == session.getId()) {
                return true;
            }
        }
        return false;
    }

    public void removeSession(int id) {
        class_list.removeIf(session -> session.getId() == id);
    }


    //getters and setters
    public String getFirstName() {
        return first_name;
    }

    public String getLastName() {
        return last_name;
    }

    public String getPhoneNumber() {
        return phone_number;
    }

    public String getEmail() {
        return email;
    }

    public int getID() {
        return id;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
