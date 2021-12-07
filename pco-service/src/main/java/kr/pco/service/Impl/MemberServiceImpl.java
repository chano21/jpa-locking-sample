package kr.pco.service.Impl;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.pco.core.domain.PcoEntity;
import kr.pco.core.dto.FindMember;
import kr.pco.core.exception.PcoException;
import kr.pco.repo.MemberRepository;
import kr.pco.service.MemberService;
import lombok.extern.slf4j.Slf4j;

@Service
public class MemberServiceImpl implements MemberService {
	
	@Autowired
	private MemberRepository repository;

	@Override
	public void printBean() {
		// TODO Auto-generated method stub
		System.out.println(repository);
	}

	@Override
	public FindMember findBymemberId(String memberId) {
		// TODO Auto-generated method stub
		return repository.findByMemberName();
	}

	@Override
	public void createMember() {
		repository.createMember();
	}

	@Override
	public void createProduct() {
		repository.createProduct();
		// TODO Auto-generated method stub
		
	}

	
	
}
