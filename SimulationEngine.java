package agh.cs.lab5;

public class SimulationEngine implements IEngine{
    MoveDirection[] directions;
    IWorldMap simulationMap;

    SimulationEngine(MoveDirection[] directions, IWorldMap map, Vector2d[] positions){
        this.directions = directions;
        this.simulationMap = map;

        for (Vector2d vector: positions) {
            this.simulationMap.place(new Animal(simulationMap,vector));
        }
    }

    @Override
    public void run() {
        simulationMap.run(directions);
        System.out.println(simulationMap.toString());
    }
}
