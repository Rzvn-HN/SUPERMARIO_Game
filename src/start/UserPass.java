package start;

public class UserPass {
    private String name;
    private String password;

    private String PathJsonFile;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPathJsonFile() {
        return PathJsonFile;
    }

    public void setPathJsonFile(String pathJsonFile) {
        PathJsonFile = pathJsonFile;
    }

    public UserPass(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", PathJsonFile='" + PathJsonFile + '\'' +
                '}';
    }
}
