package model;

public class Player {
    private String username;
    private String email;
    private int score;
    private String id;
    private boolean isactive;
    private boolean isplaying;

    public Player(String username, String email, int score, String id, boolean isactive, boolean isplaying) {
        this.username = username;
        this.email = email;
        this.score = score;
        this.id = id;
        this.isactive = isactive;
        this.isplaying = isplaying;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public boolean isIsactive() {
        return isactive;
    }

    public void setIsactive(boolean isactive) {
        this.isactive = isactive;
    }

    public boolean isIsplaying() {
        return isplaying;
    }

    public void setIsplaying(boolean isplaying) {
        this.isplaying = isplaying;
    }
    
}
