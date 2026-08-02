package praktikum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerSetBunsTest {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Before
    public void setUp() {
        burger = new Burger();
    }
    @Test
    public void setBuns_shouldSetBunCorrectly() {
        String expectedName = "black bun";
        float expectedPrice = 100f;
        when(mockBun.getName()).thenReturn(expectedName);
        when(mockBun.getPrice()).thenReturn(expectedPrice);

        burger.setBuns(mockBun);

        assertNotNull("Булочка должна быть добавлена", burger.bun);
        assertEquals("Имя булочки должно совпадать", expectedName, burger.bun.getName());
        assertEquals("Цена булочки должна совпадать", expectedPrice, burger.bun.getPrice(), 0.001f);
        verify(mockBun, times(1)).getName();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void setBuns_shouldOverwritePreviousBun() {

        Bun firstBun = new Bun("white bun", 200f);
        Bun secondBun = new Bun("red bun", 300f);

        burger.setBuns(firstBun);
        burger.setBuns(secondBun);

        assertEquals("Должна быть добавлена вторая булочка", secondBun, burger.bun);
    }

    @Test
    public void setBuns_shouldAcceptNullBun() {

        burger.setBuns(null);

        assertNull("Булочка должна быть null", burger.bun);
    }
}

