package petstore;

public enum EndPointUrlPetStore {
    PET("/pet"),
    STORE("/store"),
    USER("/user");
    String path;

    EndPointUrlPetStore(String path) {
        this.path = path;
    }

    public String addPath(String additionalPath){
        return path + additionalPath;
    }
}
