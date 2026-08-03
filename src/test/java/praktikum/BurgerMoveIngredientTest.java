package praktikum;
import org.assertj.core.api.SoftAssertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class BurgerMoveIngredientTest {

    private Burger burger;

    @Mock
    private Ingredient mockIngredientFirst;

    @Mock
    private Ingredient mockIngredientSecond;

    @Mock
    private Ingredient mockIngredientThird;

    @Before
    public void setUp() {
        burger = new Burger();
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewPosition() {
        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(2, 0);
        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0))
                .as("Элемент с индексом 0 должен быть третьим ингредиентом")
                .isEqualTo(mockIngredientThird);
        softly.assertThat(burger.ingredients.get(1))
                .as("Элемент с индексом 1 должен быть первым ингредиентом")
                .isEqualTo(mockIngredientFirst);
        softly.assertThat(burger.ingredients.get(2))
                .as("Элемент с индексом 2 должен быть вторым ингредиентом")
                .isEqualTo(mockIngredientSecond);
        softly.assertAll();
    }

    @Test
    public void moveIngredientShouldMoveIngredientToEnd() {

        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);
        burger.addIngredient(mockIngredientThird);

        burger.moveIngredient(0, 2);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0))
                .as("Элемент с индексом 0 должен быть вторым ингредиентом")
                .isEqualTo(mockIngredientSecond);
        softly.assertThat(burger.ingredients.get(1))
                .as("Элемент с индексом 1 должен быть третьим ингредиентом")
                .isEqualTo(mockIngredientThird);
        softly.assertThat(burger.ingredients.get(2))
                .as("Элемент с индексом 2 должен быть первым ингредиентом")
                .isEqualTo(mockIngredientFirst);
        softly.assertAll();
    }
    @Test
    public void moveIngredientShouldMoveIngredientToSamePosition() {

        burger.addIngredient(mockIngredientFirst);
        burger.addIngredient(mockIngredientSecond);

        burger.moveIngredient(0, 0);

        SoftAssertions softly = new SoftAssertions();
        softly.assertThat(burger.ingredients.get(0))
                .as("Первый элемент не должен измениться")
                .isEqualTo(mockIngredientFirst);
        softly.assertThat(burger.ingredients.get(1))
                .as("Второй элемент не должен измениться")
                .isEqualTo(mockIngredientSecond);
        softly.assertAll();
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionWhenFromIndexOutOfBounds() {
        burger.moveIngredient(0, 1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionWhenToIndexOutOfBounds() {
        burger.addIngredient(mockIngredientFirst);
        burger.moveIngredient(0, 5);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionWhenFromIndexIsNegative() {
        burger.addIngredient(mockIngredientFirst);
        burger.moveIngredient(-1, 0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void moveIngredientShouldThrowExceptionWhenToIndexIsNegative() {
        burger.addIngredient(mockIngredientFirst);
        burger.moveIngredient(0, -1);
    }
}
