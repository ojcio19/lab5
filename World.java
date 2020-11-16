package agh.cs.lab5;

public class World {
    public static void main(String[] args) {

        try {
        String[] moves = {"l", "r","r","r","l"};
        MoveDirection[] directions = new OptionsParser().parse(moves);

        GrassField map = new GrassField(5);

        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(2,3) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        }catch(IllegalArgumentException e) {
            System.out.println(e.toString());
        }
    }
}
