package test.com.example.hotel.cooker.service;

import com.example.hotel.HotelApplication;
import com.example.hotel.cook.domain.Cooker;
import com.example.hotel.cook.service.CookerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName CookerServiceTest
 * @Description TODO
 * @Author
 * @Date 2020/2/15 14:16
 **/
@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class CookerServiceTest {
    @Autowired
    private CookerService cookerService;

    @Test
    public void getCookerListTest() throws Exception{
        List<Cooker> cookerList = cookerService.getCookerList(",3,4,5");
        System.out.println(cookerList);
    }

}
