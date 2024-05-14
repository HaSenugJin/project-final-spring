package shop.mtcoding.projoctbodykey.bodydata;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import shop.mtcoding.projoctbodykey._core.utils.ApiUtil;
import shop.mtcoding.projoctbodykey.user.SessionUser;

@RequiredArgsConstructor
@RestController
public class BodyDataController {
    private final BodyDataService bodydataService;
    private final HttpSession session;

    @PutMapping("/api/body-date/update")
    public ResponseEntity<?> update(@RequestBody BodyDataRequest.UpdateDTO reqDTO) {
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        BodyDataResponse.UpdateDTO respDTO = bodydataService.update(sessionUser, reqDTO);

        return  ResponseEntity.ok(new ApiUtil<>(respDTO));
    }
}
