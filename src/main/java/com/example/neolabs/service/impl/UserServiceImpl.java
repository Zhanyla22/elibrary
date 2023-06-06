package com.example.neolabs.service.impl;

import com.example.neolabs.dto.request.*;
import com.example.neolabs.dto.response.*;
import com.example.neolabs.entity.ResetPassword;
import com.example.neolabs.entity.User;
import com.example.neolabs.enums.ResultCode;
import com.example.neolabs.enums.Role;
import com.example.neolabs.enums.Status;
import com.example.neolabs.exception.BaseException;
import com.example.neolabs.mapper.UserMapper;
import com.example.neolabs.repository.*;
import com.example.neolabs.security.jwt.JWTService;
import com.example.neolabs.service.UserService;
import com.example.neolabs.util.EmailUtil;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.neolabs.mapper.UserMapper.entityListToDtoList;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {

    final UserRepository userRepository;
    final JWTService jwtService;
    final PasswordEncoder passwordEncoder;
    final AuthenticationManager authenticationManager;
    final ResetPasswordRepository resetPasswordRepository;
    final EmailUtil emailUtil;
    final FileUploadServiceImpl imageUploadService;
    final SavedRepository savedRepository;
    final BookingRepository bookingRepository;
    final CourseRepository courseRepository;

    @Override
    public ResponseDto registration(RegistrationRequest registrationRequest) {
        if (!userRepository.existsByEmail(registrationRequest.getLogin())) {
            userRepository.save(User.builder()
                    .email(registrationRequest.getLogin())
                    .firstName(registrationRequest.getFirstName())
                    .lastName(registrationRequest.getLastName())
                    .phoneNumber(registrationRequest.getPhoneNumber())
                    .status(Status.ACTIVE)
                    .course(courseRepository.findById(registrationRequest.getCourseId()).orElseThrow(
                            ()-> new BaseException("not found", HttpStatus.NOT_FOUND)
                     ))
                    .password(passwordEncoder.encode(registrationRequest.getPassword()))
                    .role(Role.ROLE_READER)
                    .build());
            return ResponseDto.builder()
                    .result("User has been added successfully.")
                    .resultCode(ResultCode.SUCCESS)
                    .build();
        } else {
            throw new BaseException("User already exists", HttpStatus.CONFLICT);
        }
    }

    //TODO: fix auth status
    @Override
    public AuthResponse2RoleResponse auth(AuthenticationRequest authenticationRequest) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            authenticationRequest.getEmail(),
                            authenticationRequest.getPassword()
                    )
            );
            User user = (User) authenticate.getPrincipal();
            userRepository.save(user);//s
            return new AuthResponse2RoleResponse(
                    jwtService.generateToken((User) authenticate.getPrincipal()),
                    user.getRole(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getPhoneNumber(),
                    user.getCourse() == null ? null : user.getCourse().getName()
            );
        } catch (Exception e) {
            throw new BaseException("wrong email or password", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public AuthenticationResponse refreshToken(User user) {
        try {
            return jwtService.generateToken(user);
        } catch (Exception e) {
            throw new BaseException("not found user", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public void updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (passwordEncoder.matches(updatePasswordRequest.getOldPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
            userRepository.save(user);
        }
    }

    @Override
    public void forgotPassword(ForgotPasswordRequest forgotPasswordRequest) {
        User users = userRepository.findByEmail(forgotPasswordRequest.getEmail())
                .orElseThrow(() -> new NotFoundException("user not found"));
        String genCode = codeGenerate();
        resetPasswordRepository.save(ResetPassword.builder()
                .code(genCode)
                .dateExpiration(LocalDateTime.now().plus(10, ChronoUnit.MINUTES))
                .user(users)
                .isActive(false)
                .build());
        emailUtil.send(forgotPasswordRequest.getEmail(), "Код для сброса пароля", genCode);
    }

    @Override
    public String codeGenerate() {
        UUID uuid = UUID.randomUUID();
        String randomUUIDString = uuid.toString().replaceAll("-", "");

        return randomUUIDString.substring(0, 6);
    }

    @Override
    public void confirmCode(ForgotPasswordCodeRequest forgotPasswordCodeRequest) {
        ResetPassword resetPassword = resetPasswordRepository
                .findTopByCodeAndIsActiveOrderByDateExpirationDesc(forgotPasswordCodeRequest.getCode(), false)
                .orElseThrow(() -> new BaseException("code undefined", HttpStatus.BAD_REQUEST));
        if (resetPassword.getDateExpiration().isAfter(LocalDateTime.now())) {
            User user = resetPassword.getUser();
            user.setPassword(passwordEncoder.encode(forgotPasswordCodeRequest.getNewPassword()));
            resetPassword.setIsActive(true);
            resetPasswordRepository.save(resetPassword);
            userRepository.save(user);
        } else throw new BaseException("code date expired", HttpStatus.BAD_REQUEST);
    }

    @Override
    public void confirmCodeFirst(CodeRequest code) {
        resetPasswordRepository
                .findTopByCodeAndIsActiveOrderByDateExpirationDesc(code.getCode(), false)
                .orElseThrow(() -> new BaseException("code undefined", HttpStatus.BAD_REQUEST));
    }

    @Override
    public void updateProfilePage(Long id, UpdateUserRequest updateUserRequest) {
        User user = id != null ? getUserEntityById(id) :
                (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        user.setEmail(updateUserRequest.getLogin() != null ? updateUserRequest.getLogin() : user.getEmail());
        user.setFirstName(updateUserRequest.getFirstName() != null ? updateUserRequest.getFirstName() : user.getFirstName());
        user.setPhoneNumber(updateUserRequest.getPhoneNumber() != null ? updateUserRequest.getPhoneNumber() : user.getPhoneNumber());
        user.setLastName(updateUserRequest.getLastName() != null ? updateUserRequest.getLastName() : user.getLastName());
        user.setCourse(updateUserRequest.getCourseId() !=null ? courseRepository.findById(updateUserRequest.getCourseId()).orElseThrow(
                ()-> new BaseException("not found", HttpStatus.NOT_FOUND)
        ): user.getCourse());
        user.setPassword(updateUserRequest.getPassword() !=null ? passwordEncoder.encode(updateUserRequest.getPassword()) : user.getPassword());
        userRepository.save(user);
    }

    @Override
    public void updateProfileImage(MultipartFile multipartFile, User user) {
        user.setUrlImage(imageUploadService.saveFile(multipartFile));
        userRepository.save(user);
    }

    @Override
    public void deleteUserById(Long id) {
        User user = getUserEntityById(id);
        user.setStatus(Status.DELETED);
        user.setDeletedDate(LocalDateTime.now());
        userRepository.save(user);
    }

    @Override
    public List<UserResponse> search(String email, String firstName, String lastName, String firstOrLastName,
                                     String phoneNumber) {
        ExampleMatcher exampleMatcher = getSearchExampleMatcher();
        if (firstOrLastName != null) {
            firstName = firstOrLastName;
            lastName = firstOrLastName;
        }
        User probe = User.builder()
                .email(email)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .build();
        return entityListToDtoList(userRepository.findAll(Example.of(probe, exampleMatcher)));
    }

    @Override
    public List<UserResponse> filter(Status status, Role role) {
        ExampleMatcher exampleMatcher = getFilterExampleMatcher();
        User probe = User.builder()
                .status(status)
                .role(role)
                .build();
        return entityListToDtoList(userRepository.findAll(Example.of(probe, exampleMatcher)));
    }

    @Override
    public List<UserCountResponse> findAllByStatus(int skip,
                                              int limit) {
        List<UserResponse> userResponses = UserMapper.entityListToDtoList(
                userRepository
                        .findAllByStatusAndRoleOrderByCreatedDateDesc());

        int totalUser = userRepository.findAllByStatusAndRoleOrderByCreatedDateDesc().size();
        int startIndex = skip < totalUser ? skip : totalUser;
        int endIndex = Math.min(skip + limit, totalUser);

        List<UserCountResponse> userCountResponses = new ArrayList<>();
        UserCountResponse userCountResponse = new UserCountResponse(userResponses.subList(startIndex, endIndex),totalUser);
        userCountResponses.add(userCountResponse);
        return userCountResponses;
    }

    @Override
    public UserResponse getUserById(Long userId) {
        return UserMapper.entityToDto(getUserEntityById(userId));
    }

    @Override
    public List<SavedResponse> getAllSaved(User user) {
        List<SavedResponse> savedResponses = new ArrayList<>();

        savedRepository.findAllByUserAndStatus(user, Status.ACTIVE).forEach(
                x -> savedResponses.add(SavedResponse.builder()
                        .fileUrl(x.getBook().getUrlImage())
                        .title(x.getBook().getTitle())
                        .bookId(x.getBook().getId())
                        .savedId(x.getId())
                        .build()
                )
        );

        return savedResponses;
    }

    @Override
    public List<UserBookingInfoResponse> getALlBooked(User user, int skip, int limit) {
        List<BookingResponse> bookingResponses = new ArrayList<>();

        bookingRepository.findAllByUser(user).forEach(
                x -> bookingResponses.add(BookingResponse.builder()
                        .dateStarted(x.getGetDate() !=null ? x.getGetDate().toString():null)
                        .title(x.getBook().getTitle())
                        .dateEnd(x.getReturnDate() !=null ? x.getReturnDate().toString():null)
                        .isDeadline(x.getReturnDate() !=null ? x.getReturnDate().isBefore(LocalDate.now()):false)
                        .isLoaned(x.getGetDate() !=null ? true : false)
                        .build()
                )
        );
        int totalBooked = bookingRepository.findAllByUser(user).size();
        int startIndex = skip < totalBooked ? skip : totalBooked;
        int endIndex = Math.min(skip + limit, totalBooked);

        List<UserBookingInfoResponse> userBookingInfoResponses = new ArrayList<>();
        UserBookingInfoResponse userBookingInfoResponse = new UserBookingInfoResponse(bookingResponses.subList(startIndex, endIndex), totalBooked);
        userBookingInfoResponses.add(userBookingInfoResponse);
        return userBookingInfoResponses;
    }

    @Override
    public UserInfo currentUser(User user) {
        return UserInfo.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .courseName(user.getCourse().getGroupss() + " " + user.getCourse().getName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    @Override
    public AllUser getCountOfUsers() {
        return AllUser.builder()
                .count(userRepository.countAllByStatus(Status.ACTIVE))
                .build();
    }


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(
                () -> new BaseException("User not found", HttpStatus.NOT_FOUND));

        return new org.springframework.security.core.userdetails.User(user.getUsername(),
                user.getPassword(), user.getAuthorities());
    }

    public UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return loadUserByUsername(email);
    }

    public Long getCurrentUserId() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new BaseException("User not found", HttpStatus.NOT_FOUND)).getId();
    }

    public User getUserEntityById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new BaseException("user with id " + userId + "not found", HttpStatus.NOT_FOUND));
    }

    public User getCurrentUserEntity() {
        return userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new BaseException("User not found", HttpStatus.NOT_FOUND));
    }

    private ExampleMatcher getFilterExampleMatcher() {
        return ExampleMatcher.matchingAll()
                .withMatcher("status", ExampleMatcher.GenericPropertyMatchers.exact())
                .withMatcher("role", ExampleMatcher.GenericPropertyMatchers.exact())
                .withIgnorePaths("id");
    }

    private ExampleMatcher getSearchExampleMatcher() {
        return ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withMatcher("phoneNumber", ExampleMatcher.GenericPropertyMatchers.contains().ignoreCase())
                .withIgnorePaths("id");
    }


}
