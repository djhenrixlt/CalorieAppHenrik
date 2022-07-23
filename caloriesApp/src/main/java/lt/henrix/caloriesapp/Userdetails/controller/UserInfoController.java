package lt.henrix.caloriesapp.Userdetails.controller;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.henrix.caloriesapp.Userdetails.dto.UserInfoDto;
import lt.henrix.caloriesapp.Userdetails.service.UserInfoService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/user/info")
public class UserInfoController {

    private UserInfoService userInfoService;

    @GetMapping("/all")
    public ResponseEntity<List<UserInfoDto>> getAll(){
        return  ResponseEntity.ok(userInfoService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserInfoDto> getById(@PathVariable Long id){
        return ResponseEntity.ok(userInfoService.getUserByID(id));
    }
}
