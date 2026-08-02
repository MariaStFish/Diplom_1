package praktikum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Parameterized.class)
public class BurgerGetPriceParameterizedTest {
    private final float price1;
    private final float price2;
    private final float price3;
    private final float expectedTotal;

    public BurgerGetPriceParameterizedTest(float price1, float price2, float price3, float expectedTotal) {
        this.price1 = price1;
        this.price2 = price2;
        this.price3 = price3;
        this.expectedTotal = expectedTotal;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {50f, 75f, 25f, 350f},
                {10f, 20f, 30f, 260f},
                {0f, 0f, 0f, 200f},
                {100f, 100f, 100f, 500f}
        });
    }

    @Test
    public void getPrice_shouldCalculateCorrectPrice_withDifferentIngredients() {
        Bun mockBun = mock(Bun.class);
        when(mockBun.getPrice()).thenReturn(100f);

        Burger testBurger = new Burger();
        testBurger.setBuns(mockBun);

        Ingredient mockIng1 = mock(Ingredient.class);
        Ingredient mockIng2 = mock(Ingredient.class);
        Ingredient mockIng3 = mock(Ingredient.class);

        when(mockIng1.getPrice()).thenReturn(price1);
        when(mockIng2.getPrice()).thenReturn(price2);
        when(mockIng3.getPrice()).thenReturn(price3);

        testBurger.addIngredient(mockIng1);
        testBurger.addIngredient(mockIng2);
        testBurger.addIngredient(mockIng3);
        float result = testBurger.getPrice();

        assertEquals("Цена бургера должна быть " + expectedTotal, expectedTotal, result, 0.001f);
    }
}
