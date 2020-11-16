package agh.cs.lab5;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;


public class GrassFieldTest extends TestCase {


    @Test
    public void testPlace() {
        GrassField map = new GrassField(5);
        Assert.assertTrue(map.place(new Animal(map,new Vector2d(2,3))));
        Assert.assertTrue(map.place(new Animal(map,new Vector2d(4,-1))));//0,0
        Assert.assertEquals(2,map.animals.size());
        try{
            Assert.assertTrue(map.place(new Animal(map,new Vector2d(0,0))));
        }catch(Exception e){
            System.out.println("Exception found");
        }
    }
    @Test
    public void testRun() {
        GrassField mapNew = new GrassField(5);

        String[] moves = new String[]{"l", "f", "r", "b", "b","b","b","b","b","b"};

        MoveDirection[] directions = new OptionsParser().parse(moves);

        Animal cat = new Animal(mapNew,new Vector2d(3,2));
        Animal someAnimal = new Animal(mapNew,new Vector2d(2,3));

        mapNew.place(cat);
        mapNew.place(someAnimal);

        Assert.assertTrue(mapNew.isOccupied(cat.getPosition()));
        Assert.assertTrue(mapNew.isOccupied(someAnimal.getPosition()));

        mapNew.run(directions);

        Assert.assertEquals(new Vector2d(3,4),cat.getPosition());
        Assert.assertEquals(new Vector2d(2,0),someAnimal.getPosition());
    }
    @Test
    public void testCanMoveTo() {
        GrassField mapNew = new GrassField(5);

        Animal cat = new Animal(mapNew,new Vector2d(3,2));
        Animal someAnimal = new Animal(mapNew,new Vector2d(3,3));

        mapNew.place(cat);
        mapNew.place(someAnimal);
        Assert.assertFalse(mapNew.canMoveTo(new Vector2d(3,2)));
        Assert.assertTrue(mapNew.canMoveTo(new Vector2d(4,4)));

    }
    @Test
    public void testIsOccupied() {
        GrassField field = new GrassField(5);
        int counter = 0;
        field.place(new Animal(field));
        field.place(new Animal(field,new Vector2d(2,1)));

        Assert.assertTrue(field.isOccupied(new Vector2d(0,0)));
        Assert.assertTrue(field.isOccupied(new Vector2d(2,1)));
        for(Vector2d vector : field.grassFields.keySet()){
            counter++;
            Assert.assertTrue(field.isOccupied(vector));
        }
        Assert.assertEquals(5,counter);
    }
    @Test
    public void testObjectAt() {
        GrassField field = new GrassField(5);

        Animal first = new Animal(field);
        field.place(first);
        field.place(new Animal(field,new Vector2d(4,4)));

        Animal second = new Animal(field,new Vector2d(3,1));

        Assert.assertEquals("N", field.objectAt(new Vector2d(0,0)).toString());
        Assert.assertEquals("N", field.objectAt(new Vector2d(4,4)).toString());
        for(Vector2d vector : field.grassFields.keySet()){
            if(!(vector.equals(new Vector2d(0,0)) || vector.equals(new Vector2d(4,4)))){
                Assert.assertEquals("*", field.objectAt(vector).toString());
            }
        }


    }
}