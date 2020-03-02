package test.com.example.hotel.cooker.controller;

import com.example.hotel.HotelApplication;
import com.example.hotel.cook.controller.CookerController;
import com.example.hotel.cook.domain.Cooker;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @ClassName CookerControllerTest
 * @Description TODO
 * @Author
 * @Date 2020/2/15 14:08
 **/

@RunWith(SpringRunner.class)
@SpringBootTest(classes = HotelApplication.class)
public class CookerControllerTest {
    @Autowired
    private CookerController cookerController;

    @Test
    public void test(){
        System.out.println("12333");
        int[] arr = new int[3];
    }

}
