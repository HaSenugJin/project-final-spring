package shop.mtcoding.projoctbodykey.meal;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import shop.mtcoding.projoctbodykey.MyRestDoc;
import shop.mtcoding.projoctbodykey._core.utils.JwtUtil;
import shop.mtcoding.projoctbodykey.choiceanswer.ChoiceAnswerRequest;
import shop.mtcoding.projoctbodykey.meal.MealRequest;
import shop.mtcoding.projoctbodykey.meal.MealResponse;
import shop.mtcoding.projoctbodykey.user.User;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@Transactional
@AutoConfigureMockMvc // MockMvc IoC 로드
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK) // 모든 빈 IoC 로드
public class MealControllerTest extends MyRestDoc {

    private ObjectMapper om = new ObjectMapper();

    private static String jwt;
    
    @BeforeAll
    public static void setUp() {
        jwt = JwtUtil.create(
                User.builder()
                        .id(1)
                        .username("ssar")
                        .password("1234")
                        .build());
    }
    
    @Test
    public void mealList_test() throws Exception {
        // given
        
        
        // when
        ResultActions actions = mvc.perform(
                get("/api/meal/2024-05-19")
                        .header("Authorization", "Bearer " + jwt)
        );

        // eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : " + respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.recommendCal").value(1249.1));
        actions.andExpect(jsonPath("$.body.recommendCarbon").value(749.5));
        actions.andExpect(jsonPath("$.body.recommendProtein").value(174.9));
        actions.andExpect(jsonPath("$.body.recommendFat").value(274.8));
        actions.andExpect(jsonPath("$.body.mealList[0].mealId").value("1"));
        actions.andExpect(jsonPath("$.body.mealList[0].mealImg").value("이미지"));
        actions.andExpect(jsonPath("$.body.mealList[0].eatTime").value("아침"));
        actions.andExpect(jsonPath("$.body.mealList[0].foods[0].foodId").value(30));
        actions.andExpect(jsonPath("$.body.mealList[0].foods[0].foodName").value("닭가슴살 샐러드"));
        actions.andExpect(jsonPath("$.body.mealList[0].foods[0].carbo").value(5.0));
        actions.andExpect(jsonPath("$.body.mealList[0].foods[0].protein").value(20.0));
        actions.andExpect(jsonPath("$.body.mealList[0].foods[0].fat").value(3.0));
        actions.andExpect(jsonPath("$.body.mealList[0].foods[0].kcal").value(150.0));
        actions.andExpect(jsonPath("$.body.mealList[0].foods[0].gram").value(100));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void save_test() throws Exception {
        // given
        List<MealRequest.SaveDTO.Food> foods =new ArrayList<>();
        MealRequest.SaveDTO.Food food = new MealRequest.SaveDTO.Food();
        food.setFoodId(1);
        food.setQty(5);
        foods.add(food);
        MealRequest.SaveDTO reqDTO = new MealRequest.SaveDTO();
        reqDTO.setEatTime("간식");
        reqDTO.setMealImg("이미지");
        reqDTO.setFoods(foods);

        String reqBody = om.writeValueAsString(reqDTO);


        // when
        ResultActions actions = mvc.perform(
                post("/api/meal/2024-05-19")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : "+respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body.mealId").value(14));
        actions.andExpect(jsonPath("$.body.mealImg").value("이미지"));
        actions.andExpect(jsonPath("$.body.foods[0].foodId").value(1));
        actions.andExpect(jsonPath("$.body.foods[0].foodQuantity").value(5));
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void save_fail_test() throws Exception {
        // given
        List<MealRequest.SaveDTO.Food> foods =new ArrayList<>();
        MealRequest.SaveDTO.Food food = new MealRequest.SaveDTO.Food();
        food.setFoodId(500);
        food.setQty(5);
        foods.add(food);
        MealRequest.SaveDTO reqDTO = new MealRequest.SaveDTO();
        reqDTO.setEatTime("간식");
        reqDTO.setMealImg("이미지");
        reqDTO.setFoods(foods);

        String reqBody = om.writeValueAsString(reqDTO);


        // when
        ResultActions actions = mvc.perform(
                post("/api/meal/2024-05-19")
                        .header("Authorization", "Bearer " + jwt)
                        .content(reqBody)
                        .contentType(MediaType.APPLICATION_JSON)
        );

        //eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : "+respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.msg").value("찾으시는 음식이 없습니다"));
        actions.andExpect(jsonPath("$.body").isEmpty());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);

    }

    @Test
    public void delete_test() throws Exception {
        // given


        // when
        ResultActions actions = mvc.perform(
                delete("/api/meal/2024-05-19/1")
                        .header("Authorization", "Bearer " + jwt)
        );

        //eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : "+respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(200));
        actions.andExpect(jsonPath("$.msg").value("성공"));
        actions.andExpect(jsonPath("$.body").isEmpty());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }

    @Test
    public void delete_fail_test() throws Exception {
        // given


        // when
        ResultActions actions = mvc.perform(
                delete("/api/meal/2024-05-19/100")
                        .header("Authorization", "Bearer " + jwt)
        );

        //eye
//        String respBody = actions.andReturn().getResponse().getContentAsString();
//        System.out.println("respBody : "+respBody);

        // then
        actions.andExpect(jsonPath("$.status").value(404));
        actions.andExpect(jsonPath("$.msg").value("식단이 없습니다"));
        actions.andExpect(jsonPath("$.body").isEmpty());
        actions.andDo(MockMvcResultHandlers.print()).andDo(document);
    }
}

