public class User {

    // fields
    private static int totalUsers = 0;
    private static int idGenerator = 0;
    private String first_name, last_name, phone_number, email;
    private int id = 0;

    public User(String first_name, String last_name, String phone_number, String email) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.phone_number = phone_number;
        this.email = email;
        totalUsers++;
        idGenerator++;
        this.id = idGenerator;
    }

    // getters
    public static int getNumUsers() {
        return totalUsers;
    }

    public String getName() {
        return first_name + " " + last_name;
    }

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
}
