package test.com.example.hotel.order.service;

import com.example.hotel.HotelApplication;
import com.example.hotel.order.domain.OrderInfo;
import com.example.hotel.order.service.OrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName OrderServiceTest
 * @Description TODO
 * @Author
 * @Date 2020/2/19 21:37
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class OrderServiceTest {
    @Autowired
    private OrderService orderService;

    @Test
    public void selectListTest() throws Exception{
        OrderInfo orderInfo = orderService.selectOneById("6");
        System.out.println(orderInfo);
    }
}
