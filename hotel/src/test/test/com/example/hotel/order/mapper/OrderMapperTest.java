package test.com.example.hotel.order.mapper;

import com.example.hotel.HotelApplication;
import com.example.hotel.order.domain.OrderInfo;
import com.example.hotel.order.mapper.OrderMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName OrderMapperTest
 * @Description TODO
 * @Author
 * @Date 2020/2/19 13:28
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class OrderMapperTest {
    @Autowired
    private OrderMapper orderMapper;

    @Test
    public void insertTest() throws Exception{
        OrderInfo orderInfo = new OrderInfo();
        orderInfo.setAppraise("好");
        orderInfo.setTotalPrice(555);

        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String format = sd.format(new Date());

        orderInfo.setAppraiseTime(format);
        orderInfo.setCreateTime(format);
        orderInfo.setCookerList("1,2,3");
        orderInfo.setLevel("5星");
        orderInfo.setMenuList("1,2,3");

        orderMapper.insertOne(orderInfo);
    }

    @Test
    public void selectOneTest(){
        OrderInfo orderInfo = this.orderMapper.selectOneById("1");
        System.out.println(orderInfo);
    }

    @Test
    public void updateOneTest(){
        OrderInfo orderInfo = this.orderMapper.selectOneById("6");
        orderInfo.setAppraise("不好吃");
        orderInfo.setLevel("2.0");

        orderMapper.updateOne(orderInfo);
    }

    @Test
    public void deleteOneTest(){
        this.orderMapper.deleteOneById("1");
    }
}
