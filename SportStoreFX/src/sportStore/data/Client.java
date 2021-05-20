package sportStore.data;

/**
 * Class to enter the name and email of the client
 * @author Bartolomé Cantó Mesas
 * @since 20/05/2021
 */
public class Client extends User
{
    private String email;

    /**
     *
     * @param id variable to enter the customer id
     * @param name variable to enter the customer's name
     * @param email variable to enter the customer's email
     */
    public Client(String id, String name, String email) {
        super(id, name);
        this.email = email;
    }

    /**
     *
     * @return Email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return bring back the name of the parent class User and the email
     */
    @Override
    public String toString() {
        return super.toString() +"\n"+
                "email: " +email;
    }
}
