package xyz.yvtq8k3n.pokemon_tile_creator.view.selection;

public class StateComponent {
    public static final int INACTIVE = 0;
    public static final int ACTIVE = 1;
    protected int state;

    public StateComponent(int state) {
        this.state = state;
    }

    public boolean isActive() {
        return state == ACTIVE;
    }

    public void setState(int state){
        this.state = state;
    }
}
