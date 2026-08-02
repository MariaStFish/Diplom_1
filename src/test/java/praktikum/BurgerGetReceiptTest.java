package praktikum;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerGetReceiptTest {

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
    public void getReceipt_shouldReturnCorrectReceipt_whenBunAndIngredientsExist() {

        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockIngredient1.getName()).thenReturn("hot sauce");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(50f);

        when(mockIngredient2.getName()).thenReturn("cutlet");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getPrice()).thenReturn(75f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);

        String receipt = burger.getReceipt().replace("\r\n", "\n");

        String expectedReceipt = "(==== black bun ====)\n" +
                "= sauce hot sauce =\n" +
                "= filling cutlet =\n" +
                "(==== black bun ====)\n" +
                "\n" +
                "Price: 325,000000\n";

        assertEquals("Чек должен соответствовать ожидаемому", expectedReceipt, receipt);
        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();
        verify(mockIngredient1, times(1)).getName();
        verify(mockIngredient1, times(1)).getType();
        verify(mockIngredient2, times(1)).getName();
        verify(mockIngredient2, times(1)).getType();
    }

    @Test
    public void getReceipt_shouldReturnReceiptWithOnlyBun_whenNoIngredients() {

        when(mockBun.getName()).thenReturn("white bun");
        when(mockBun.getPrice()).thenReturn(200f);

        burger.setBuns(mockBun);
        String receipt = burger.getReceipt().replace("\r\n", "\n");

        String expectedReceipt = "(==== white bun ====)\n" +
                "(==== white bun ====)\n" +
                "\n" +
                "Price: 400,000000\n";

        assertEquals("Чек должен содержать только булочки", expectedReceipt, receipt);
        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getReceipt_shouldReturnCorrectReceipt_withMultipleIngredients() {
        when(mockBun.getName()).thenReturn("red bun");
        when(mockBun.getPrice()).thenReturn(100f);

        when(mockIngredient1.getName()).thenReturn("sauce1");
        when(mockIngredient1.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient1.getPrice()).thenReturn(10f);

        when(mockIngredient2.getName()).thenReturn("filling1");
        when(mockIngredient2.getType()).thenReturn(IngredientType.FILLING);
        when(mockIngredient2.getPrice()).thenReturn(20f);

        when(mockIngredient3.getName()).thenReturn("sauce2");
        when(mockIngredient3.getType()).thenReturn(IngredientType.SAUCE);
        when(mockIngredient3.getPrice()).thenReturn(30f);

        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient1);
        burger.addIngredient(mockIngredient2);
        burger.addIngredient(mockIngredient3);


        String receipt = burger.getReceipt().replace("\r\n", "\n");

        String expectedReceipt = "(==== red bun ====)\n" +
                "= sauce sauce1 =\n" +
                "= filling filling1 =\n" +
                "= sauce sauce2 =\n" +
                "(==== red bun ====)\n" +
                "\n" +
                "Price: 260,000000\n";

        assertEquals("Чек должен соответствовать ожидаемому", expectedReceipt, receipt);
        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void getReceipt_shouldContainCorrectPrice_whenBunPriceChanges() {
        when(mockBun.getName()).thenReturn("black bun");
        when(mockBun.getPrice()).thenReturn(150f);

        burger.setBuns(mockBun);

        String receipt = burger.getReceipt().replace("\r\n", "\n");

        assertTrue("В чеке цена - 300,000000", receipt.contains("Price: 300,000000"));
        verify(mockBun, times(2)).getName();
        verify(mockBun, times(1)).getPrice();
    }
    @Test(expected = NullPointerException.class)
    public void getReceipt_shouldThrowNullPointerException_whenBunNotSet() {

        Burger burger = new Burger();
        burger.getReceipt();
    }
}



