package pl.prodzajto.estolowkabackend.order;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.context.junit4.SpringRunner;
import pl.prodzajto.estolowkabackend.user.UserEntity;
import pl.prodzajto.estolowkabackend.user.UserRepository;

import java.util.Collection;
import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class OrderServiceImplTest {
    private OrderServiceImpl orderService;
    @Autowired
    private UserMealRepository userMealRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MealRepository mealRepository;

    @Before
    public void setUp() throws Exception {
        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken("admin@gmail.com", null, null));
        OrderCreatorImpl orderCreator = new OrderCreatorImpl(userRepository, mealRepository, userMealRepository);
        orderService = new OrderServiceImpl(orderCreator, userMealRepository, userRepository);
        userRepository.save(UserEntity.builder().email("admin@gmail.com").build());
    }

    @After
    public void after() {
        SecurityContextHolder.getContext().setAuthentication(null);
    }
    
    @Test
    public void shouldSaveOrder() {
        //given
        RawOrder rawOrder = OrderUtils.getDefaultRawOrder();
        rawOrder.setUserEmail("admin@gmail.com");
        //when
        orderService.saveOrder(rawOrder);
        //then
        assertEquals(userMealRepository.findAll().size(), rawOrder.getMeals().size());
    }

    @Test
    public void shouldGetMapOfMealsByMonth() {
        //given
        RawOrder rawOrder = OrderUtils.getDefaultRawOrder();
        rawOrder.setUserEmail("admin@gmail.com");
        orderService.saveOrder(rawOrder);
        //when
        MealsWrapper mealsWrapper = orderService.getUserOrders("admin@gmail.com");
        //then
        long mealsCount = mealsWrapper.getMealsByMonth().stream().mapToLong(Collection::size).sum();
        assertEquals(mealsCount, rawOrder.getMeals().size());
    }

    @Test
    public void shouldGetListOfOrdersToRate() {
        //given
        RawOrder rawOrder = OrderUtils.getDefaultRawOrder();
        rawOrder.setUserEmail("admin@gmail.com");
        orderService.saveOrder(rawOrder);
        //when
        List<UserMealDTO> ordersToRate = orderService.getUserOrdersToRate("admin@gmail.com");
        //then
        int allOrders = rawOrder.getMeals().size();
        int ordersToRateCount = ordersToRate.size();
        assertEquals(allOrders, ordersToRateCount);
    }

}


