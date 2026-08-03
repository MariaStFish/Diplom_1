package praktikum;
import org.assertj.core.api.SoftAssertions;
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

    @Mock
    private Bun mockFirstBun;

    @Mock
    private Bun mockSecondBun;

    @Before
    public void setUp() {
        burger = new Burger();
    }
    @Test
    public void setBunsShouldSetBunCorrectly() {
        String expectedName = "black bun";
        float expectedPrice = 100f;

        when(mockBun.getName()).thenReturn(expectedName);
        when(mockBun.getPrice()).thenReturn(expectedPrice);

        burger.setBuns(mockBun);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.bun).as("Булочка должна быть добавлена").isNotNull();
        softly.assertThat(burger.bun.getName()).as("Имя булочки должно совпадать").isEqualTo(expectedName);
        softly.assertThat(burger.bun.getPrice()).as("Цена булочки должна совпадать").isEqualTo(expectedPrice);
        softly.assertAll();

        verify(mockBun, times(1)).getName();
        verify(mockBun, times(1)).getPrice();
    }

    @Test
    public void setBunsShouldOverwritePreviousBun() {

        burger.setBuns(mockFirstBun);
        burger.setBuns(mockSecondBun);

        assertEquals("Должна быть добавлена вторая булочка", mockSecondBun, burger.bun);
    }

    @Test
    public void setBunsShouldAcceptNullBun() {

        burger.setBuns(null);

        assertNull("Булочка должна быть null", burger.bun);
    }
}

