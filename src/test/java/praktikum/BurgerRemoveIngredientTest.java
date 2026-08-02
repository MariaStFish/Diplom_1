package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerRemoveIngredientTest {

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
    public void removeIngredient_shouldRemoveIngredientAtIndex() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);
        int initialSize = burger.ingredients.size();
        burger.removeIngredient(1);
        assertEquals("Размер списка должен уменьшиться на 1", initialSize - 1, burger.ingredients.size());
        assertFalse("Список не должен содержать удалённый ингредиент", burger.ingredients.contains(mockIngredient2));
        assertTrue("Первый ингредиент должен остаться", burger.ingredients.contains(mockIngredient1));
        assertTrue("Третий ингредиент должен остаться", burger.ingredients.contains(mockIngredient3));
    }

    @Test
    public void removeIngredient_shouldRemoveFirstIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(0);
        assertEquals("Размер списка должен быть 1", 1, burger.ingredients.size());
        assertFalse("Первый ингредиент должен быть удалён", burger.ingredients.contains(mockIngredient1));
        assertTrue("Второй ингредиент должен остаться", burger.ingredients.contains(mockIngredient2));
    }

    @Test
    public void removeIngredient_shouldRemoveLastIngredient() {
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.removeIngredient(1);
        assertEquals("Размер списка должен быть 1", 1, burger.ingredients.size());
        assertTrue("Первый ингредиент должен остаться", burger.ingredients.contains(mockIngredient1));
        assertFalse("Второй ингредиент должен быть удалён", burger.ingredients.contains(mockIngredient2));
    }
    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_shouldThrowException_whenIndexOutOfBounds() {
        burger.removeIngredient(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_shouldThrowException_whenIndexIsNegative() {
        burger.removeIngredient(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void removeIngredient_shouldThrowException_whenIndexIsTooLarge() {
        burger.addIngredient(mockIngredient1);
        burger.removeIngredient(5);
    }
}
