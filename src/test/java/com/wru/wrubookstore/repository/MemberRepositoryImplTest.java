package com.wru.wrubookstore.repository;

import com.wru.wrubookstore.dto.MemberDto;
import com.wru.wrubookstore.dto.UserDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class MemberRepositoryImplTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MemberRepository memberRepository;

    UserDto userDto;

    LocalDate birthdate;
    MemberDto memberDto;

    @BeforeEach
    public void init() throws Exception {
        userDto = new UserDto("tester@gmail.com", "pwd", "tester");

        birthdate = LocalDate.of(2000, 3, 20);

        memberDto = new MemberDto("tester@gmail.com", "pwd", "tester", "tn",
                "01012345678", birthdate, 'M', "https://cdn-icons-png.flaticon.com/128/1077/1077063.png");
    }

    // 테스트 완료
    // userRepository - deleteAll(), selectAll(), count()
    // memberRepository - deleteAllMembers(), deleteAllUsers(), selectAll(), count(), insertUser(), insertMember()
    // memberRepository - countMembers()
    @Test
    @Transactional
    public void insertAndSelectTest() throws Exception {
        // 모든 비회원 삭제
        userRepository.deleteAll();
        assertEquals(0, userRepository.selectAll().size());
        assertEquals(0, userRepository.count());

        // 모든 회원 삭제
        memberRepository.deleteAllMembers();
        memberRepository.deleteAllUsers();
        assertEquals(0, memberRepository.selectAll().size());
        assertEquals(0, memberRepository.count());

        // 새로운 회원 추가
        assertEquals(1, memberRepository.insertUser(userDto));
        assertEquals(1, memberRepository.insertMember(memberDto));
        assertEquals(1, memberRepository.selectAll().size());
        assertEquals(1, memberRepository.count());

        // 동일한 이메일을 갖는 회원 추가 시 예외 발생하는지 확인
        assertThrows(Exception.class, () -> memberRepository.insertUser(userDto));
        assertThrows(Exception.class, () -> memberRepository.insertMember(memberDto));

        // 전체 비회원 수 조회
        assertEquals(0, userRepository.count());

        // 전체 회원 수 조회
        assertEquals(1, memberRepository.countMembers());

        // 데이터가 정상적으로 조회되는지 확인
        List<MemberDto> members = memberRepository.selectAll();
        assertNotNull(members);

        String email = members.getFirst().getEmail();
        MemberDto selectedMember = memberRepository.select(email);

        // 저장된 데이터와 일치하는지 확인
        assertEquals(memberDto, selectedMember);
    }

    // 테스트 완료
    // userRepository - deleteAll(), selectAll(), count()
    // memberRepository - deleteAllMembers(), deleteAllUsers(), selectAll(), count(), insertUser(), insertMember()
    // memberRepository - countMembers(), updateMember()
    @Test
    @Transactional
    public void updateTest() throws Exception {
        // 모든 비회원 삭제
        userRepository.deleteAll();
        assertEquals(0, userRepository.selectAll().size());
        assertEquals(0, userRepository.count());

        // 모든 회원 삭제
        memberRepository.deleteAllMembers();
        memberRepository.deleteAllUsers();
        assertEquals(0, memberRepository.selectAll().size());
        assertEquals(0, memberRepository.count());

        // 새로운 회원 추가
        assertEquals(1, memberRepository.insertUser(userDto));
        assertEquals(1, memberRepository.insertMember(memberDto));
        assertEquals(1, memberRepository.selectAll().size());
        assertEquals(1, memberRepository.count());
        assertEquals(1, memberRepository.countMembers());

        // 데이터가 정상적으로 조회되는지 확인
        List<MemberDto> members = memberRepository.selectAll();
        assertNotNull(members);

        String email = members.getFirst().getEmail();
        MemberDto selectedMember = memberRepository.select(email);

        // 해당 회원 정보 수정
        selectedMember.setNickname(selectedMember.getNickname() + "1");
        selectedMember.setPhoneNum(selectedMember.getPhoneNum() + "1");
        selectedMember.setBirthdate(selectedMember.getBirthdate().plusDays(1));
        selectedMember.setGender('F');
        selectedMember.setImage(selectedMember.getImage() + "1");

        assertEquals(1, memberRepository.updateMember(selectedMember));

        // 수정 사항 반영되었는지 확인
        MemberDto updatedMember = memberRepository.select(selectedMember.getEmail());

        assertNotNull(updatedMember);

        assertEquals(selectedMember, updatedMember);
    }

     // 테스트 완료
     // userRepository - deleteAll(), selectAll(), count()
     // memberRepository - deleteAllMembers(), deleteAllUsers(), selectAll(), count(), insertUser(), insertMember()
     // memberRepository - countMembers(), deleteMember(), deleteUser()
    @Test
    @Transactional
    public void deleteTest() throws Exception {
        // 모든 비회원 삭제
        userRepository.deleteAll();
        assertEquals(0, userRepository.selectAll().size());
        assertEquals(0, userRepository.count());

        // 모든 회원 삭제
        memberRepository.deleteAllMembers();
        memberRepository.deleteAllUsers();
        assertEquals(0, memberRepository.selectAll().size());
        assertEquals(0, memberRepository.count());

        // 새로운 회원 추가
        assertEquals(1, memberRepository.insertUser(userDto));
        assertEquals(1, memberRepository.insertMember(memberDto));
        assertEquals(1, memberRepository.selectAll().size());
        assertEquals(1, memberRepository.count());
        assertEquals(1, memberRepository.countMembers());

        // 데이터가 정상적으로 조회되는지 확인
        List<MemberDto> members = memberRepository.selectAll();
        assertNotNull(members);

        String email = members.getFirst().getEmail();

        // 해당 회원 삭제
        assertEquals(1, memberRepository.deleteMember(email));
        assertEquals(1, memberRepository.deleteUser(email));

        // 삭제 결과 확인
        assertNull(memberRepository.select(email));
        assertEquals(0, memberRepository.count());
        assertEquals(0, memberRepository.countMembers());
    }
}