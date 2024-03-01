package com.work.szs.user.adapter.in;

import com.work.szs.common.annotation.WebAdapter;
import com.work.szs.common.api.response.ApiRes;
import com.work.szs.user.application.dto.JoinUserRequest;
import com.work.szs.user.application.dto.LoginUserRequest;
import com.work.szs.user.application.service.JoinUserUseCase;
import com.work.szs.user.application.service.LoginUserUseCase;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@WebAdapter
@RestController
@RequiredArgsConstructor
@RequestMapping("/szs")
public class UserController {
    private final JoinUserUseCase joinUserUseCase;
    private final LoginUserUseCase loginUserUseCase;

    @PostMapping("/signup")
    @Operation(summary = "회원가입", description = "사용자가 회원가입을 한다.", responses = {
            @ApiResponse(responseCode = "200", description = "가입 성공")})
    public ApiRes<?> join(@RequestBody @Valid JoinUserRequest dto) {
        joinUserUseCase.join(dto);
        return ApiRes.createSuccessWithNoContent();
    }

    // todo : Map대신 VO 쓸지 판단
    @PostMapping("/login")
    @Operation(summary = "로그인", description = "사용자가 로그인을 한다.", responses = {
            @ApiResponse(responseCode = "200", description = "로그인 성공")})
    public ApiRes<Map<String, String>> login(@RequestBody @Valid LoginUserRequest dto) {
        return ApiRes.createSuccess(loginUserUseCase.login(dto));
    }
}