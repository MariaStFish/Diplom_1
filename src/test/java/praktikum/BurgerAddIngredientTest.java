package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerAddIngredientTest {

    private Burger burger;
    @Mock
    private Ingredient mockIngredient1;

    @Mock
    private Ingredient mockIngredient2;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void addIngredient_shouldAddIngredientToList() {

        int initialSize = burger.ingredients.size();
        burger.addIngredient(mockIngredient1);
        assertEquals("Размер списка должен увеличиться на 1", initialSize + 1, burger.ingredients.size());
        assertTrue("Список должен содержать добавленный ингредиент", burger.ingredients.contains(mockIngredient1));
    }

    @Test
    public void addIngredient_shouldAddMultipleIngredients() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        assertEquals("Размер списка должен быть 2", 2, burger.ingredients.size());
        assertTrue("Список должен содержат первый ингредиент", burger.ingredients.contains(mockIngredient1));
        assertTrue("Список должен содержать второй ингредиент", burger.ingredients.contains(mockIngredient2));
    }

    @Test
    public void addIngredient_shouldAddIngredientsInCorrectOrder() {
        Ingredient realIngredient1 = new Ingredient(IngredientType.SAUCE, "sauce1", 100f);
        Ingredient realIngredient2 = new Ingredient(IngredientType.FILLING, "filling1", 150f);
        Ingredient realIngredient3 = new Ingredient(IngredientType.SAUCE, "sauce2", 120f);
        burger.addIngredient(realIngredient1);
        burger.addIngredient(realIngredient2);
        burger.addIngredient(realIngredient3);
        assertEquals("Первый элемент должен быть sauce1", realIngredient1, burger.ingredients.get(0));
        assertEquals("Второй элемент должен быть filling1", realIngredient2, burger.ingredients.get(1));
        assertEquals("Третий элемент должен быть sauce2", realIngredient3, burger.ingredients.get(2));
    }

    @Test
    public void addIngredient_shouldAddIngredientWithNullFields() {

        Ingredient nullIngredient = new Ingredient(null, null, 0f);

        burger.addIngredient(nullIngredient);

        assertEquals("Размер списка должен быть 1", 1, burger.ingredients.size());
        assertNull("Тип ингредиента должен быть null", burger.ingredients.get(0).getType());
        assertNull("Имя ингредиента должно быть null", burger.ingredients.get(0).getName());
        assertEquals("Цена ингредиента должна быть 0", 0f, burger.ingredients.get(0).getPrice(), 0.001f);
    }
}

