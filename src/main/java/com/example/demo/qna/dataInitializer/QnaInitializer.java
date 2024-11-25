package com.example.demo.qna.dataInitializer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.demo.member.entity.Member;
import com.example.demo.member.repository.MemberRepository;
import com.example.demo.qna.dto.QnaDTO;
import com.example.demo.qna.repository.QnaRepository;
import com.example.demo.qna.service.QnaService;

@Component
@Order(7)
public class QnaInitializer implements ApplicationRunner {

	@Autowired
	QnaService qnaService;

	@Autowired
	MemberRepository memberRepository;
	
    @Autowired
    QnaRepository qnaRepository;
    
    
	@Override
	public void run(ApplicationArguments args) throws Exception {
		
		if (qnaRepository.count() > 0) {
            return;
        }

        List<Member> members = memberRepository.findAll();
        
        for (int i = 0; i < Math.min(10, members.size()); i++) {
        	
            Member member = members.get(i);

            QnaDTO qnaDTO = QnaDTO.builder()
                                  .title(member.getName() + "의 자동 게시글")
                                  .content(member.getName() + "의 자동 게시글 내용")
                                  .build();

            qnaService.writeQna(qnaDTO, member.getId());
            
        }
        
    }
		
}






