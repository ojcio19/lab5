package agh.cs.lab5;

import java.util.*;

abstract class AbstractWorldMap implements IWorldMap{

    protected int width = 5;
    protected int height = 5;
    protected Map<Vector2d,Grass> grassFields = new HashMap<>();
    protected Map<Vector2d,Animal> animals = new HashMap<>();

    public AbstractWorldMap() {}

    @Override
    public int getHeight() {
        return this.height;
    }

    @Override
    public int getWidth() {
        return this.width;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOcupiedByAnimal(position);
    }

    @Override
    public boolean place(Animal animal) {
        if(isOcupiedByAnimal(animal.getPosition())){
            throw new IllegalArgumentException("animal cannot be placed on the: " + animal.getPosition());
        }else{
            animals.put(animal.getPosition(),animal);
            return true;
        }
    }
    public boolean isOcupiedByAnimal(Vector2d position){
        for(Vector2d example: animals.keySet()){
            return example.equals(position);
        }
        return false;
    }
    @Override
    public void run(MoveDirection[] directions) {

        Collection<Animal> value = animals.values();
        ArrayList<Animal> listOfAnimals = new ArrayList<>(value);
        ArrayList<Vector2d> wrongPositions = new ArrayList<>();

        for (int i = 0; i < directions.length; i++) {
            listOfAnimals.get(i % animals.size()).move(directions[i]);
        }
        //find wrong vectors
        for (Vector2d vector: animals.keySet()) {
            if(!vector.equals(animals.get(vector).getPosition())){
                wrongPositions.add(vector);
            }
        }
        //change their positions
        for (Vector2d vector: wrongPositions) {
            Animal current = new Animal(this, new Vector2d(animals.get(vector).getPosition().x,animals.get(vector).getPosition().y));
            Object obj = animals.remove(vector);
            animals.put(current.getPosition(), current);
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Vector2d example: animals.keySet()){
            return example.equals(position);
        }
        //todo remove, probably not needed
        for(Vector2d example: grassFields.keySet()){
            return example.equals(position);
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Vector2d vector: animals.keySet()) {
            if(vector.equals(position)){
                return animals.get(vector);
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return new MapVisualizer(this).draw(new Vector2d(0,0),new Vector2d(this.width-1,this.height));
    }
}
