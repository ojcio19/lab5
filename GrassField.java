package agh.cs.lab5;

import java.util.ArrayList;
import java.util.Random;

public class GrassField extends AbstractWorldMap{

    public GrassField(int grassNumber) {
        for (int i = 0; i < grassNumber; i++) {
            Random random = new Random();
            Vector2d randomPosition = new Vector2d(random.nextInt(getWidth()),
                    random.nextInt(getHeight()));
            while(isOccupied(randomPosition)) {
                randomPosition.x = random.nextInt(getWidth());
                randomPosition.y = random   .nextInt(getHeight());
            }
            grassFields.put(randomPosition, new Grass(randomPosition));
        }
    }

    /*
    * remove grass field
    *
    * return true if success, false otherwise
    * */
    public boolean removeGrass(Vector2d position) {
        return grassFields.remove(position) != null;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return super.canMoveTo(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for(Vector2d vector: animals.keySet()){
            if(vector.equals(position)) return true;
        }
        for (Vector2d vector: grassFields.keySet()){
            if(vector.equals(position)) return true;
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {

        if(super.objectAt(position) != null){
            return super.objectAt(position);
        }

        for (Vector2d vector: grassFields.keySet()) {
            if(vector.equals(position)){
                return grassFields.get(vector);
            }
        }
        return null;
    }

    @Override
    public String toString(){
        return new MapVisualizer(this).draw(new Vector2d(0,0),new Vector2d(this.width - 1,this.height - 1));
    }
}
