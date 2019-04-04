import org.json.simple.JSONObject;

class Player {
    private String name;
    private boolean hasResume;

    Player(String name, JSONObject object) {
        this.name = name;
        long level = (long) object.get("Level");
        hasResume = (level != 0);
    }

    String getName() {
        return name;
    }

    boolean hasResume() {
        return hasResume;
    }
}
