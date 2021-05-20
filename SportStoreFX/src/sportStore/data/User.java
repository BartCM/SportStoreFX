package sportStore.data;

/**
 * Clase usuario para introducir el id y el nombre del usuario
 * @author Bartolomé Cantó Mesas
 * @since 20/05/2021
 */
public class User {

    protected String id;
    protected String name;

    /**
     *
     * @param id to enter the user id
     * @param name to enter the user's name
     */

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     *
     * @return Id
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return Name
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @param name name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return returns the id and name of the user
     */
    @Override
    public String toString() {
        return "Id: " +id +"\n"+
                "Name: " +name;
    }
}
