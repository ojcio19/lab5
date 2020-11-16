package agh.cs.lab5;

import java.util.Random;

public class Animal {

    private Vector2d position;
    private MapDirection mapDirection = MapDirection.NORTH;
    private IWorldMap map;

    public Animal(IWorldMap map){
        this.map = map;
        this.position = new Vector2d(0,0);
        //map.place(this);
    }

    public Animal(IWorldMap map, Vector2d initialPosition){
        this.map = map;
        //todo check map.isOccupied
        //if(!map.place(this)
        if(!map.isOccupied(initialPosition)
                && initialPosition.x < map.getWidth() && initialPosition.x >= 0
                && initialPosition.y < map.getHeight() && initialPosition.y >= 0){
            this.position = initialPosition;
            //System.out.println("hello no rand:" + initialPosition.toString());

        }else{
            //this.position = new Vector2d(new Random().nextInt(map.getWidth()),new Random().nextInt(map.getWidth()));
            this.position = initialPosition;
            //System.out.println("hello rand:" + this.position.toString());
        }

    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getMapDirection() {
        return mapDirection;
    }

    private void changePositionTo(Vector2d positionNext) {    //warunki brzegowe
        if(positionNext.x >= map.getWidth()){
                this.position = new Vector2d(0, getPosition().y);
        }else if(positionNext.x < 0){
                this.position = new Vector2d(map.getWidth() - 1, getPosition().y);
        }else if(positionNext.y >= map.getHeight()){
                this.position = new Vector2d(getPosition().x,0);
        }else if(positionNext.y < 0){
                this.position = new Vector2d(getPosition().x, map.getHeight() -1);
        }else{
                this.position = new Vector2d(positionNext.x, positionNext.y);
        }
    }


    public void move(MoveDirection direction){

        switch(direction){
            case LEFT:
                mapDirection = mapDirection.previous();
                break;
            case RIGHT:
                mapDirection = mapDirection.next();
                break;
            case FORWARD:
                if(map.canMoveTo(this.position.add(mapDirection.toUnitVector()))) {
                    changePositionTo(this.position.add(mapDirection.toUnitVector()));
                }
                break;
            case BACKWARD:
                if(map.canMoveTo(this.position.subtract(mapDirection.toUnitVector()))) {
                    changePositionTo(this.position.subtract(mapDirection.toUnitVector()));
                }
                break;
            default:
                break;
        }
    }

    public String toString(){

        switch (mapDirection) {
            case EAST -> { return "E"; }
            case WEST -> { return "W"; }
            case NORTH -> { return "N"; }
            case SOUTH -> { return "S"; }
            default -> { return "ERROR"; }
        }
    }

}
