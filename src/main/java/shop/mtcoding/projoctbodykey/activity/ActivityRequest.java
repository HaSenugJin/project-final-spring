package shop.mtcoding.projoctbodykey.activity;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

public class ActivityRequest {
    @Data
    public static class WeakWater{
        private Integer water;
        private Timestamp date;

        public WeakWater(Activity activity) {
            this.water = activity.getDrinkWater();
            this.date = activity.getCreatedAt();
        }
    }

    @Data
    public static class WeakWalking {
        private Integer walking;
        private Timestamp date;

        public WeakWalking(Activity activity) {
            this.walking = activity.getWalking();
            this.date = activity.getCreatedAt();
        }
    }
    @Data
    public static class WalkingToatalAndAVG {
        private Long totalMonthWalking;
        private double avgMonthWalking;
    }

    @Data
    public static class WalkingRateAvG {
        private Long rateAvgWalking;

    }

    @Data
    public static class MaxWalkingDay {
        private Integer maxWalking;
        private Timestamp maxWalkingDay;

    }

    @Data
    public static class UpdateDTO {
        private Integer walking;
        private Integer water;
    }

    // 아래는 걷기, 물 따로 업데이트 하는거
//    @Data
//    public static class WalkingUpdateDTO {
//        private Integer walking;
//    }
//
//    @Data
//    public static class WaterUpdateDTO {
//        private Integer water;
//    }
}
