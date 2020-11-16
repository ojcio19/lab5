package agh.cs.lab5;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class RectangularMapTest extends TestCase {
    IWorldMap mapTest = new RectangularMap(5, 5);
    @Test
    public void testCanMoveTo() {
        mapTest.place(new Animal(mapTest));
        mapTest.place(new Animal(mapTest,new Vector2d(3,4)));

        //Assert.assertFalse(mapTest.canMoveTo(new Vector2d(3,4))); //todo
        Assert.assertFalse(mapTest.canMoveTo(new Vector2d(0,0)));
        Assert.assertTrue(mapTest.canMoveTo(new Vector2d(2,3)));
    }
    @Test
    public void testPlace() {
        RectangularMap map = new RectangularMap(5, 5);
        Assert.assertEquals(0, map.animals.size());

        map.place(new Animal(map));

        //Assert.assertFalse(map.place(new Animal(map))); //exception
        Assert.assertEquals(1, map.animals.size());

        map.place(new Animal(map,new Vector2d(0,4)));

        Assert.assertTrue(map.place(new Animal(map, new Vector2d(0,2))));
        Assert.assertEquals(3, map.animals.size());

    }
    @Test
    public void testTestRun() {
        IWorldMap mapNew = new RectangularMap(5,5);
        String[] moves = new String[5];
        moves[0] = "l";
        moves[1] = "f";
        moves[2] = "r";
        moves[3] = "b";
        moves[4] = "b";
        MoveDirection[] directions = new OptionsParser().parse(moves);

        Animal someAnimal = new Animal(mapNew,new Vector2d(2,3));
        Animal cat = new Animal(mapNew,new Vector2d(3,2));
        Assert.assertTrue(mapNew.place(someAnimal));    //previous implementation did not allow that
        Assert.assertTrue(mapNew.place(cat));

        mapNew.run(directions);

        Assert.assertEquals(new Vector2d(2,3),someAnimal.getPosition());    //now it is correct
        Assert.assertEquals(new Vector2d(3,1),cat.getPosition());           //only this animal moves
    }
    @Test
    public void testIsOccupied() {
        mapTest.place(new Animal(mapTest));
        mapTest.place(new Animal(mapTest,new Vector2d(2,1)));
        //Assert.assertTrue(mapTest.isOccupied(new Vector2d(0,0))); //todo
        Assert.assertTrue(mapTest.isOccupied(new Vector2d(2,1)));
        Assert.assertFalse(mapTest.isOccupied(new Vector2d(2,2)));
    }
    @Test
    public void testObjectAt() {
        Animal first = new Animal(mapTest);
        mapTest.place(first);
        mapTest.place(new Animal(mapTest,new Vector2d(4,4)));
        Animal second = new Animal(mapTest,new Vector2d(3,1));

        Assert.assertEquals(first, mapTest.objectAt(new Vector2d(0,0)));
        Assert.assertNotEquals(second, mapTest.objectAt(new Vector2d(4,4)));
        Assert.assertNull(mapTest.objectAt(new Vector2d(1,4)));
    }
}