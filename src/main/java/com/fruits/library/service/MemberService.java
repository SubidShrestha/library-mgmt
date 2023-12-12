package com.fruits.library.service;

import com.fruits.library.entity.Member;
import com.fruits.library.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public Member getMember(Long id){
        return memberRepository.findById(id).orElse(null);
    }

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }
    public Member addMember(Member member){
        return memberRepository.save(member);
    }
    public Member updateMember(Member memberData,Long id){
        Member member = memberRepository.findById(id).orElse(null);
        if (member != null){
            member.setEmail(memberData.getEmail());
            member.setPhone(memberData.getPhone());
            member.setFirst_name(memberData.getFirst_name());
            member.setLast_name(memberData.getLast_name());
            member.setJoined_date(memberData.getJoined_date());
            member.setExpiry_date(memberData.getExpiry_date());
            return memberRepository.save(member);
        }
        return null;
    }
    public void deleteMember(Long id){
        memberRepository.deleteById(id);
    }
}
