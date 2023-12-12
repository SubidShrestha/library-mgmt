package com.fruits.library.controller;

import com.fruits.library.entity.Member;
import com.fruits.library.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    @GetMapping
    public List<Member> getMembers(){
        return memberService.getAllMembers();
    }
    @GetMapping("/{id}")
    public Member getMemberById(@PathVariable Long id){
        return memberService.getMember(id);
    }
    @PostMapping
    public Member addNewMember(@RequestBody Member member){
        return memberService.addMember(member);
    }
    @PutMapping("/{id}")
    public Member updateMember(@RequestBody Member member,@PathVariable Long id){
        return memberService.updateMember(member,id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMember(@PathVariable Long id){
        memberService.deleteMember(id);
        return ResponseEntity.ok("Member has been removed successfully");
    }
}
