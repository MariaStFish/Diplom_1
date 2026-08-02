package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerMoveIngredientTest {

    private Burger burger;

    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Mock
    private Ingredient mockIngredient3;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void moveIngredient_shouldMoveIngredientToNewPosition() {

        Ingredient realIngredient1 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100f);
        Ingredient realIngredient2 = new Ingredient(IngredientType.FILLING, "cutlet", 150f);
        Ingredient realIngredient3 = new Ingredient(IngredientType.SAUCE, "chili sauce", 120f);

        burger.addIngredient(realIngredient1);
        burger.addIngredient(realIngredient2);
        burger.addIngredient(realIngredient3);

        burger.moveIngredient(2, 0);

        assertEquals("Элемент с индексом 0 должен быть chili sauce", realIngredient3, burger.ingredients.get(0));
        assertEquals("Элемент с индексом 1 должен быть hot sauce", realIngredient1, burger.ingredients.get(1));
        assertEquals("Элемент с индексом 2 должен быть cutlet", realIngredient2, burger.ingredients.get(2));
    }

    @Test
    public void moveIngredient_shouldMoveIngredientToEnd() {

        Ingredient realIngredient1 = new Ingredient(IngredientType.SAUCE, "sauce1", 100f);
        Ingredient realIngredient2 = new Ingredient(IngredientType.FILLING, "filling1", 150f);
        Ingredient realIngredient3 = new Ingredient(IngredientType.SAUCE, "sauce2", 120f);

        burger.addIngredient(realIngredient1);
        burger.addIngredient(realIngredient2);
        burger.addIngredient(realIngredient3);

        burger.moveIngredient(0, 2);

        assertEquals("Элемент с индексом 0 должен быть filling1", realIngredient2, burger.ingredients.get(0));
        assertEquals("Элемент с индексом 1 должен быть sauce2", realIngredient3, burger.ingredients.get(1));
        assertEquals("Элемент с индексом 2 должен быть sauce1", realIngredient1, burger.ingredients.get(2));
    }
    @Test
    public void moveIngredient_shouldMoveIngredientToSamePosition() {

        Ingredient realIngredient1 = new Ingredient(IngredientType.SAUCE, "sauce1", 100f);
        Ingredient realIngredient2 = new Ingredient(IngredientType.FILLING, "filling1", 150f);

        burger.addIngredient(realIngredient1);
        burger.addIngredient(realIngredient2);

        burger.moveIngredient(0, 0);

        assertEquals("Порядок не должен измениться", realIngredient1, burger.ingredients.get(0));
        assertEquals("Порядок не должен измениться", realIngredient2, burger.ingredients.get(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_shouldThrowException_whenFromIndexOutOfBounds() {
        burger.moveIngredient(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_shouldThrowException_whenToIndexOutOfBounds() {
        burger.addIngredient(mockIngredient1);
        burger.moveIngredient(0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_shouldThrowException_whenFromIndexIsNegative() {
        burger.addIngredient(mockIngredient1);
        burger.moveIngredient(-1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredient_shouldThrowException_whenToIndexIsNegative() {
        burger.addIngredient(mockIngredient1);
        burger.moveIngredient(0, -1);
    }
}
