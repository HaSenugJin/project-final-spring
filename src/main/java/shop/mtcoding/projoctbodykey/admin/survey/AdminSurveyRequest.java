package shop.mtcoding.projoctbodykey.admin.survey;
import lombok.Data;
import java.util.List;
public class AdminSurveyRequest {
    @Data
    public static class SaveDTO {
        private String title;
        private List<questionElements> questionElements;
        @Data
        public static class questionElements {
            private String question;
            private List<String> choices;
        }
    }
    @Data
    public static class ChoiceCountDTO {
        private Integer choiceId;
        private Long choiceCount;

        public ChoiceCountDTO(Integer choiceId, Long choiceCount) {
            this.choiceId = choiceId;
            this.choiceCount = choiceCount;
        }
    }

    @Data
    public static class UserStatsDTO {
        private Integer surveyId;
        private String surveyTitle;
        private Long count;

        public UserStatsDTO(Integer surveyId, String surveyTitle, Long count) {
            this.surveyId = surveyId;
            this.surveyTitle = surveyTitle;
            this.count = count;
        }
    }

    @Data
    public static class TotalQuestion {
        private Integer surveyId;
        private Long count;

        public TotalQuestion(Integer surveyId, Long count) {
            this.surveyId = surveyId;
            this.count = count;
        }
    }
}