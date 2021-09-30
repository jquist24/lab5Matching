

public class Partner {
    public final Role role;
    public final String[] preferences;
    public final String name; // will likely be a number for programmers,
    // but it's a String because we're not doing math with it.
    
    // Dissatisfaction being minimized rather than satisfaction being
    // maximized looks weird, but it allows us to set a Partner's like
    // or dislike of whoever they're paired with to its position
    // in the preferences array.
    private int currentDissatisfaction;
    private Partner currentPartner;

    public Partner(String name, Role role, String[] preferences){
        this.name = name;
        this.role = role;
        this.preferences = preferences;
    }

    public void setNewPartner(Partner newPartner){
        currentPartner = newPartner;
        currentDissatisfaction = getDissatisfaction(newPartner);
    } 

    public int getCurrentDissatisfaction() {
        return currentDissatisfaction;
    }

    public int getDissatisfaction(String otherName) throws IllegalArgumentException{
        for(int i = 0; i < preferences.length; i++){
            if (preferences[i] == otherName){
                return i;
            }
        }
        throw new IllegalArgumentException("Key " + otherName + " was not found in the list of preferences.");
    }

    public int getDissatisfaction(Partner other){
        return getDissatisfaction(other.name);
    }

    public boolean wouldPrefer(Partner other){
        return getDissatisfaction(other) < currentDissatisfaction;
    }

}