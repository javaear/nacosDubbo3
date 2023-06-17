package org.jxx.security.controller;


import cn.hutool.crypto.digest.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.jxx.security.JwtTokenUtil;
import org.jxx.security.JwtUserDetailsService;
import org.jxx.security.User;
import org.jxx.security.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
/**
 * description: SignController
 * date: 2023/6/17
 * author: javaear
 */
@RestController
@RequestMapping("api/v1/auth")
@Slf4j
public class SignController {
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;
    private final JwtTokenUtil jwtTokenUtil;

    @Autowired
    public SignController(AuthenticationManager authenticationManager, UserMapper userMapper, JwtTokenUtil jwtTokenUtil) {
        this.authenticationManager = authenticationManager;
        this.userMapper = userMapper;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody User request) {
        /*String a = passwordEncoder.encode(request.getPassword());
        System.out.println(a);*/
        authenticate(request.getUsername(), request.getPassword());
        User user = userMapper.getByName(request.getUsername());
        String accessToken = jwtTokenUtil.generateAccessToken(user);
        //AuthResponse response = new AuthResponse(request.getEmail(), accessToken);
        return ResponseEntity.ok(accessToken);

    }


    private void authenticate(String username, String password) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (BadCredentialsException e) {
            e.printStackTrace();
            throw new BadCredentialsException("INVALID_CREDENTIALS", e);
        }
    }
}
