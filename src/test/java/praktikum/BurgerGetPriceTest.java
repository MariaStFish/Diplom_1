package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerGetPriceTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

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
    public void getPrice_shouldReturnCorrectPrice_whenBunAndIngredientsExist() {
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getPrice()).thenReturn(50f);
        when(mockIngredient2.getPrice()).thenReturn(75f);
        when(mockIngredient3.getPrice()).thenReturn(25f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);

        float result = burger.getPrice();

        assertEquals("Цена бургера должна быть 350 (100*2 + 50 + 75 + 25)", 350f, result, 0.001f);
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
        verify(mockIngredient2, times(1)).getPrice();
        verify(mockIngredient3, times(1)).getPrice();
    }

    @Test
    public void getPrice_shouldReturnOnlyBunPrice_whenNoIngredients() {
        when(mockBun.getPrice()).thenReturn(150f);
        burger.setBuns(mockBun);

        float result = burger.getPrice();

        assertEquals("Цена должна быть 300 (цена булочки * 2)", 300f, result, 0.001f);
        verify(mockBun, times(1)).getPrice();
        verifyNoInteractions(mockIngredient1);
        verifyNoInteractions(mockIngredient2);
        verifyNoInteractions(mockIngredient3);
    }

    @Test
    public void getPrice_shouldReturnCorrectPrice_withOneIngredient() {
        // Arrange
        when(mockBun.getPrice()).thenReturn(100f);
        when(mockIngredient1.getPrice()).thenReturn(50f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);

        float result = burger.getPrice();

        assertEquals("Цена должна быть 250 (100*2 + 50)", 250f, result, 0.001f);
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getPrice();
    }
    @Test
    public void getPrice_shouldReturnCorrectPrice_withManyIngredients() {
        when(mockBun.getPrice()).thenReturn(100f);

        Ingredient[] ingredients = new Ingredient[5];
        float[] prices = {10f, 20f, 30f, 40f, 50f};
        float expectedSum = 0f;

        for (int i = 0; i < ingredients.length; i++) {
            ingredients[i] = mock(Ingredient.class);
            when(ingredients[i].getPrice()).thenReturn(prices[i]);
            expectedSum += prices[i];
            burger.addIngredient(ingredients[i]);
        }

        burger.setBuns(mockBun);

        float result = burger.getPrice();

        float expected = 200f + expectedSum;
        assertEquals("Цена должна быть " + expected, expected, result, 0.001f);
    }
}
