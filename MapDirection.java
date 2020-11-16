package agh.cs.lab5;

public enum MapDirection {
    NORTH, SOUTH, WEST, EAST;
     public String toString(){
         switch(this) {
             case NORTH: return "Północ";
             case SOUTH: return "Południe";
             case WEST: return "Zachód";
             case EAST: return "Wschód";
             default: return "error";
         }
     };

     public MapDirection next(){
         switch(this) {
             case NORTH: return EAST;
             case SOUTH: return WEST;
             case WEST: return NORTH;
             case EAST: return SOUTH;
             default: return null;
         }
     };

    public MapDirection previous(){
        switch(this) {
            case NORTH: return WEST;
            case SOUTH: return EAST;
            case WEST: return SOUTH;
            case EAST: return NORTH;
            default: return null;
        }
    };

    public Vector2d toUnitVector(){
        switch(this) {
            case NORTH: return new Vector2d(0,1);
            case SOUTH: return new Vector2d(0,-1);
            case WEST: return new Vector2d(-1,0);
            case EAST: return new Vector2d(1,0);
            default: return null;
        }
    };
}
